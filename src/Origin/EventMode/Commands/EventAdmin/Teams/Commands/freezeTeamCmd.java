/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 25/11/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams.Commands;

import England.Origin.FirstPlugin.Player.Freeze;
import Origin.EventMode.Commands.EventAdmin.Teams.TeamsInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.teams;

public class freezeTeamCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [freeze] [teamname]");
            return false;
        }
        Player p = (Player) sender;

        if (!(teams.containsKey(args[1]))) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
            return false;
        }


        for (int i = 0; i < teams.get(args[1]).size(); i++) {

            if (Freeze.Frozen.contains(teams.get(args[1]).get(i).getUniqueId())) {
                Freeze.Frozen.remove(teams.get(args[1]).get(i).getUniqueId());
                teams.get(args[1]).get(i).sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has unfrozen you!");
            } else {
                Freeze.Frozen.add(teams.get(args[1]).get(i).getUniqueId());
                teams.get(args[1]).get(i).sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has frozen you!");
            }

        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +
                ChatColor.AQUA + "You've toggled freeze for team " +  args[1] + ".");
        return true;
    }
}