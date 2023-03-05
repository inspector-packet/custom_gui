package inspector.packet.custom.gui;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.defaults.BukkitCommand;

public class openInventory extends BukkitCommand {
    
    public openInventory(String name)  {
        super(name);
        this.setName(name);
        this.setDescription(App.getPlugin().getConfig().getString("GUI.DESCRIPTION"));
        this.setUsage(App.getPlugin().getConfig().getString("GUI.COMMAND"));
        this.setPermission(App.getPlugin().getConfig().getString("GUI.PERMISSION"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(App.getInv());
            return true;
        } else {
            return false;
        }
    }
}