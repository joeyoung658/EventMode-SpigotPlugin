/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 28/10/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class eventTeamsCmd implements TeamsInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.RED + "----------------Incorrect arguments!----------------");
        sender.sendMessage(ChatColor.AQUA + "/teams help [Page #]");
        sender.sendMessage(ChatColor.RED + "----------------------------------------------------");
        return false;
    }
}
