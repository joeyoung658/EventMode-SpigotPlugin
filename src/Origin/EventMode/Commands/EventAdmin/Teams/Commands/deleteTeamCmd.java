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

public class deleteTeamCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [delete] [TeamName]");
            return false;
        }
        Player p = (Player) sender;


        if (!(teams.containsKey(args[1]))) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
            return false;
        }

        teams.remove(args[1]);
        teamRespawnDelay.remove(args[1]);
        teamRespawnPoint.remove(args[1]);
        playerDeathCount.clear();
        teamRespawnLimit.remove(args[1]);

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " has been removed.");
        return false;



    }
}
