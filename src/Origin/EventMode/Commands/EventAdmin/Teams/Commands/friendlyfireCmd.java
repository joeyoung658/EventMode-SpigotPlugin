/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 25/11/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams.Commands;

import Origin.EventMode.Commands.EventAdmin.Teams.TeamsInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.friendlyfire;
import static Origin.EventMode.Contants.teams;

//todo when implementing make it a toggle rather than on/off
public class friendlyfireCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [create] [TeamName]");
            return false;
        }
        Player p = (Player) sender;

        if (!(teams.containsKey(args[1]))) {
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                            + ChatColor.RED + "Team " + args[1] + " does not exists!");
            return false;
        }

        if (args[2].equalsIgnoreCase("on")) {
            if (friendlyfire.contains(args[1])) {
                friendlyfire.remove(args[1]);
                sender.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.AQUA + "You've enabled friendly fire for " + args[1]);
            } else {
                sender.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.AQUA + "Friendly fire for " + args[1] + " is already enabled!");
            }
        } else if (args[2].equalsIgnoreCase("off")){
            friendlyfire.add(args[1]);
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes(
                            '&', "&e[&4Server&e]&f")
                            + ChatColor.AQUA + "You've disabled friendly fire for " + args[1]);
        } else {
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes(
                            '&', "&e[&4Server&e]&f")
                            + ChatColor.AQUA + "Error: Correct useage - /teams friendlyfire [Team Name] [off/on]");
        }

        return false;
    }
}


