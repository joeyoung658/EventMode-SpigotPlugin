package Origin.EventMode.Commands.EventAdmin.EventMode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Origin.EventMode.Contants.*;

public class EventModeAdminCommandHandler implements CommandExecutor
{
    //This is where we will store the commands
    private static HashMap<String, EventModeAdminInterface> commands = new HashMap<String, EventModeAdminInterface>();


    public void register(String name, EventModeAdminInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    public EventModeAdminInterface getExecutor(String name) {
        return commands.get(name);
    }

    public List<String> getCommands(){
        List<String> commandsList = new ArrayList<>();
        for (String i:
                commands.keySet()) {
            commandsList.add(i);
        }
        return commandsList;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(sender instanceof Player) {
            if (!(EventLeaders.contains(sender))) {
                if (!(sender.hasPermission("<fp>.eventmode"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "This is a Admin + command only!");
                    return false;
                }
            }

            if (eventopen) {
                if (!(sender.isOp())) {
                    if (!(EventLeaders.contains(sender))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.RED + "You are not a team leader!");
                        return false;
                    }
                }
                //Not too sure the purpose of this as of yet?
                if (!(teams.isEmpty())) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f &e[&4WARNING&e]&f") + ChatColor.AQUA + "Some players are within teams!");
                }
            }

            if(args.length == 0) {
                getExecutor("eventmode").onCommand(sender, cmd, commandLabel, args);
                return true;
            }
            if(args.length > 0) {
                if(exists(args[0])){
                    getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ")
                            + ChatColor.AQUA + "That's not something you can do yet. Type /eventmode help for support.");
                    return true;
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ")
                    + ChatColor.AQUA + "You must be a player to use this command.");
            return true;
        }
        return false;
    }

}
