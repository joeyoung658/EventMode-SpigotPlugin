/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 28/10/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface TeamsInterface {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
