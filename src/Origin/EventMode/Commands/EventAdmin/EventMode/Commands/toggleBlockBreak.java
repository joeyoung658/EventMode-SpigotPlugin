package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggleBlockBreak implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        //We don't have to check if the args length is equal to one, but you will have to check if it is greater than 1.
        if(args.length > 1) return false;
        Player p = (Player) sender;
        EventMode eventMode = new EventMode();

        boolean result = eventMode.toggleBlockBreak();

        if (result){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have prevented everyone within the event from breaking blocks.");

        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "Everyone within the event can now break blocks.");
        }

        return false;
    }
}
