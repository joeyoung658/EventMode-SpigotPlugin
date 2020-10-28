/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 28/10/2020.
 */

package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import Origin.EventMode.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class unblockCmd implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [unblock] [command]");
            return false;
        }
        String givenCmd = args[1];
        List<String> pvpe = Main.instance.getConfig().getStringList("blocked_cmds");
        if (pvpe.contains(givenCmd)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + givenCmd + " wasn't blocked to begin with!");
            return false;
        }
        pvpe.remove(givenCmd);
        Main.instance.getConfig().set("blocked_cmds", givenCmd);
        Main.instance.saveConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "The command " + givenCmd + " is now unblocked from use within eventmode!");
        return false;
    }
}
