package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static England.Origin.FirstPlugin.Data.PVPToggleContains.PVPtoggle;
import static Origin.EventMode.Contants.currentEvent;

public class togglePlayerPVP implements EventModeAdminInterface

{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [stoggle] [plyaername]");
            return false;
        }
        Player target = Bukkit.getPlayerExact(args[1]);
        if (target == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
            return false;
        }
        Player p = (Player) sender;
        EventMode eventMode = new EventMode();

        if (eventMode.isPlayerInEvent(target)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player not within list!");
            return false;
        }

        Bukkit.dispatchCommand(target, "togglepvp");
        String status;
        if (PVPtoggle(target.getName())){status = "on";} else {status =  "off";}


        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.AQUA + "You've toggled " + target.getDisplayName() + " pvp status to " + status );


        return false;
    }
}
