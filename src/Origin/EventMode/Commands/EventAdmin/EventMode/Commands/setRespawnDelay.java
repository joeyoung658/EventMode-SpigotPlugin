package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import England.Origin.FirstPlugin.Data.StringToInt;
import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.eventRespawnDelay;
import static Origin.EventMode.Contants.eventlocation;

public class setRespawnDelay implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [setrespawndelay] [delay]");
            return false;
        }
        //todo what units is this using mins, seconds etc?
        EventMode eventMode = new EventMode();
        int result = eventMode.setRespawnDelay(args[1]);
        Player p = (Player) sender;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've set the event spawn, with a respawn delay of " + result);

        return false;
    }
}
