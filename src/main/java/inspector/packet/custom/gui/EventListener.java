package inspector.packet.custom.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements org.bukkit.event.Listener {

    public EventListener(App plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent p) {
        Boolean value = App.getPlugin().getConfig().getBoolean("GUI.DEFAULT_PERMISSION_VALUE");
        if (value == true) {
            Player player = p.getPlayer();
            PermissionAttachment attachment = player.addAttachment(App.getPlugin());
    
            attachment.setPermission(
                App.getPlugin().getConfig().getString("GUI.PERMISSION"),
                App.getPlugin().getConfig().getBoolean("GUI.DEFAULT_PERMISSION_VALUE")
            );
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(App.getInv())) return;
        e.setCancelled(true);
    
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;
        
        final Player player = (Player) e.getWhoClicked();
        for (String i : App.getPlugin().getConfig().getConfigurationSection("GUI.ITEMS").getKeys(false)) {
            if (clickedItem.getType() == Material.valueOf(App.getPlugin().getConfig().getString("GUI.ITEMS." + i + ".ITEM"))) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), App.getPlugin().getConfig().getString("GUI.ITEMS." + i + ".COMMAND").replace("%player%", player.getName()));
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(App.getInv())) {
          e.setCancelled(true);
        }
    }
}