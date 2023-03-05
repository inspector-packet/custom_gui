package inspector.packet.custom.gui;

import java.lang.reflect.Field;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class App extends org.bukkit.plugin.java.JavaPlugin {
    private static App plugin;
    private static Inventory inv;

    public static App getPlugin() {
        return plugin;
    }

    public static Inventory getInv() {
        return inv;
    }

    @Override
    public void onEnable() {
        getLogger().info("Initiating EventListener...");
        new EventListener(this);

        getLogger().info("Constructing Config.yml...");
        constructConfig();

        getLogger().info("Registering Command...");
        registerCommand();

        getLogger().info("Building GUI...");
        buildGUI();

        getLogger().info("SUCCESS: Activated Custom GUI Plugin");
    }

    public void constructConfig() {
        plugin = this;
        plugin.saveDefaultConfig();
    }

    public void registerCommand() {
        try {
            Field f;
            f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getServer());
            String command = this.getConfig().getString("GUI.COMMAND").replace("/","");
            commandMap.register(command, "", new openInventory(command));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void buildGUI() {
        inv = Bukkit.createInventory(
            null, 
            getPlugin().getConfig().getInt("GUI.SIZE"), 
            getPlugin().getConfig().getString("GUI.TITLE")
        );

        initializeItems();
    }

    public void initializeItems() {
        for (String i : this.getConfig().getConfigurationSection("GUI.ITEMS").getKeys(false)) {
            inv.setItem(this.getConfig().getInt("GUI.ITEMS." + i + ".SLOT_NUMBER"), 
                createGuiItem(
                    Material.valueOf(getPlugin().getConfig().getString("GUI.ITEMS." + i + ".ITEM")), 
                    this.getConfig().getString("GUI.ITEMS." + i + ".NAME"), 
                    this.getConfig().getString("GUI.ITEMS." + i + ".DESCRIPTION")
                )
            );
        }
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public void onDisable() {
        getLogger().info("SUCCESS: Disabled Custom GUI Plugin");
    }
}
