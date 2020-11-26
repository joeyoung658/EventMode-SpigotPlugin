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

import static Origin.EventMode.Contants.currentEvent;
import static Origin.EventMode.Contants.teams;

public class listCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length > 1) return false;

        StringBuilder stringOnline = new StringBuilder();
        for (String key : teams.keySet()) {
            stringOnline.append(key.toString() + "- ");
            for (int i = 0; i < teams.get(key).size(); i++) {
                stringOnline.append(teams.get(key).get(i).getDisplayName() + ", ");
            }
        }

        EventMode eventMode = new EventMode();

        sender.sendMessage(ChatColor.AQUA + "There are currently, " + teams.size() + "/" + eventMode.eventSize() + " people within teams whom are in event mode.");
        sender.sendMessage(ChatColor.YELLOW + "Team players: " + ChatColor.RESET + stringOnline.toString());



        return false;
    }
}
