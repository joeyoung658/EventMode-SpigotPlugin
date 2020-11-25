/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 25/11/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams.Commands;

import Origin.EventMode.Commands.EventAdmin.Teams.TeamsInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCmd implements TeamsInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if(args.length < 2){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /teams [help] [1/2]");
        return false;
    }
    Player p = (Player) sender;
    EventMode eventMode = new EventMode();

    //Todo make it build the pages & get commands from eventMode handler
    String page = args[1];
    switch (page) {
        case "2":
            sender.sendMessage(ChatColor.RED + "------------Event Teams Help Page 2/2--------------");
            sender.sendMessage(ChatColor.AQUA + "/teams create [Team Name] - Creates a team within event mode");
            sender.sendMessage(ChatColor.AQUA + "/teams delete [Team Name] - Removes all players from a team and deletes the team.");
            sender.sendMessage(ChatColor.AQUA + "/teams clear [Team Name] - Removes all players from a team.");
            sender.sendMessage(ChatColor.AQUA + "/teams add [Team Name] [Player] - Adds a player to given team.");
            sender.sendMessage(ChatColor.AQUA + "/teams remove - Removes a player from their team.");
            break;
        default:
            sender.sendMessage(ChatColor.RED + "------------Event Teams Help Page 1/2--------------");
            sender.sendMessage(ChatColor.AQUA + "/teams respawndelay [Team Name] [Delay] - Sets delay, in seconds, a team has before they respawn after death.");
            sender.sendMessage(ChatColor.AQUA + "/teams setspawn [Team Name] or [clearspawn] - Sets a team's respawn point, or clears it");
            sender.sendMessage(ChatColor.AQUA + "/tc - Private team chat");
            sender.sendMessage(ChatColor.AQUA + "/teams freeze [Team Name] - Freezes given team");
            sender.sendMessage(ChatColor.AQUA + "/teams respawnlimit [Team Name]/[Clear] [Limit] - Sets/clears the respawn limit for a given team.");
            sender.sendMessage(ChatColor.AQUA + "/teams list - Lists all the teams and all their players.");
            sender.sendMessage(ChatColor.AQUA + "/teams friendlyfire [Team Name] [Off/On]");
            break;
    }
    return false;
    }
}
