/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 25/11/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams.Commands;

import Origin.EventMode.Commands.EventAdmin.Teams.TeamsInterface;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.*;
import static Origin.EventMode.Contants.teams;

public class addPlayerToTeamCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 3){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [create] [TeamName]");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[2]);


        if (target == null){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[2] + " isn't online!");
            return false;
        }

        if (!(teams.containsKey(args[1]))) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
            return false;
        }

        EventMode eventMode = new EventMode();

        if (!(eventMode.isPlayerInEvent(target))){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " isn't in event mode.");
            return false;
        }


        if (teams.get(args[1]).contains(target)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " already within selected team!");
            return false;
        }


        if (eventMode.playerWithinTeam(target)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " already within another team!");
            return false;
        }

        teams.get(args[1]).add(target);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (eventMode.isPlayerInEvent(p))
            {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName() + ChatColor.AQUA + " has joined team \"" + args[1] + "\"");
            }
            
            if (EventLeaders.contains(p)){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName() + ChatColor.AQUA + " has joined team \"" + args[1] + "\". " +  teams.get(args[1]).size() + " players are now in that team.");
            }
        }


        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Use /tc to talk to your team mates!");

        return false;
    }
}
