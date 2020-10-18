package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class healCmd implements EventModeAdminInterface
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        Player p = (Player) sender;

        //We don't have to check if the args length is equal to one, but you will have to check if it is greater than 1.
        if(args.length > 1) return false;
        EventMode eventMode = new EventMode();
        eventMode.healAllPlayers();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've healed everyone within the event");
        return false;
    }
}
