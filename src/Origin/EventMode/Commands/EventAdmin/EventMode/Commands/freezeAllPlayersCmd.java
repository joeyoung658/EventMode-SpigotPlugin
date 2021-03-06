package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class freezeAllPlayersCmd implements EventModeAdminInterface
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Player p = (Player) sender;

        //We don't have to check if the args length is equal to one, but you will have to check if it is greater than 1.
        if(args.length > 1) return false;
        EventMode eventMode = new EventMode();
        if (eventMode.freezeAllPlayers()){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have frozen everyone within the event.");
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have unfrozen everyone within the event.");

        }
        return false;
    }
}

//todo make it possible to freeze just one player within this command handler
//else if (args[0].equalsIgnoreCase("freeze")) {
//
//        if (target == null) {
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
//        return false;
//        }
//        if (!(currentEvent.contains(target.getUniqueId()))) {
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player not within event!");
//        return false;
//        }
//        if (!(Freeze.Frozen.contains(target.getUniqueId()))) {
//        Freeze.Frozen.add(target.getUniqueId());
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have frozen " + target.getDisplayName());
//        } else {
//        Freeze.Frozen.remove(target.getUniqueId());
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have unfrozen " + target.getDisplayName());
//        }
//        }