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

import static Origin.EventMode.Contants.teams;

public class removePlayerFromTeam implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [remove] [playername]");
            return false;
        }
        Player p = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[2]);
        if (target == null){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " isn't online!");
            return false;
        }

        EventMode eventMode = new EventMode();
        String teamName = eventMode.getPlayerTeamName(target);
        if (!(eventMode.playerWithinTeam(target))){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") +  target.getDisplayName() + ChatColor.RED  + " is not within a team!");
            return false;
        }

        teams.get(teamName).remove(target);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName()  + ChatColor.AQUA +
                " has been removed form the team " + teamName);
        return false;
    }

}
