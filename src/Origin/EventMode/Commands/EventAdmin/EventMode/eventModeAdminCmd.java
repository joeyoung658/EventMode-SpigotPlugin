package Origin.EventMode.Commands.EventAdmin.EventMode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class eventModeAdminCmd implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.RED + "----------------Incorrect arguments!----------------");
        sender.sendMessage(ChatColor.AQUA + "/eventmode help [Page #]");
        sender.sendMessage(ChatColor.RED + "----------------------------------------------------");
        return false;
    }
}
