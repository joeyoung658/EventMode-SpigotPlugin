/*
 * Joseph Young - josephyoung658@gmail.com
 * Copyright (c) 28/10/2020.
 */

package Origin.EventMode.Commands.EventAdmin.Teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class TeamsTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("teams"))){
            if ((sender instanceof Player) && (args.length == 1)){
                TeamsCommandHandler eventModeTeamsCommandHandler = new TeamsCommandHandler();
                List<String> newList = eventModeTeamsCommandHandler.getCommands();
                newList.remove("teams");
                if (newList.isEmpty()){
                    return null;
                } else {
                    return newList;
                }
            }
        }
        return null;
    }
}
