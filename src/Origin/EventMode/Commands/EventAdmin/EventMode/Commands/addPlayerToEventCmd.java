package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class addPlayerToEventCmd implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if((args.length == 1) || args.length > 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [add] [plyaername]");
            return false;
        }
        Player target = Bukkit.getPlayerExact(args[1]);
        if (target == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
            return false;
        }
        Player p = (Player) sender;
        EventMode eventMode = new EventMode();
        boolean result =  eventMode.addPlayerToEvent(target,true, true);

        if (result){
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has entered you into an event.");
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "The player is either already in the event for the event is closed!");
        }
        return false;
    }
}
