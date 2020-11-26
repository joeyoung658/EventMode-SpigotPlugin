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

import static Origin.EventMode.Contants.*;
import static Origin.EventMode.Contants.teamRespawnPoint;

public class setTeamSpawnCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        //todo what on earth does this even do?
        if (args[1].equalsIgnoreCase("clearspawns")) {
            teamRespawnPoint.clear();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've restored all teams respawn points to default");
            return true;
        }

        if (!(teams.containsKey(args[1]))) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
            return false;
        }

        eventSpawnLoc = null;
        teamRespawnPoint.put(args[1], ((Player) sender).getLocation());
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                + ChatColor.AQUA + "You've set respawn point for " + args[1]);



        return false;
    }
}
