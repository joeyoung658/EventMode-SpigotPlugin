package Origin.EventMode.Commands.EventAdmin.EventMode;

import Origin.EventMode.EventMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class EventModeAdminTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("eventmode")) || cmd.getName().equalsIgnoreCase("em")){
            if ((sender instanceof Player) && (args.length == 1)){
                EventModeAdminCommandHandler eventModeAdminCommands = new EventModeAdminCommandHandler();
                List<String> newList = eventModeAdminCommands.getCommands();
                newList.remove("eventmode");
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
