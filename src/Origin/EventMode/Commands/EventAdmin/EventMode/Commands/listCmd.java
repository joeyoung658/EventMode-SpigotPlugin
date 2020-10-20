package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.currentEvent;

public class listCmd implements EventModeAdminInterface
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        //todo tidy command up

        Player p = (Player) sender;

        //We don't have to check if the args length is equal to one, but you will have to check if it is greater than 1.
        if(args.length > 1) return false;
        EventMode eventMode = new EventMode();
        int eventSize = eventMode.eventSize();
        StringBuilder stringOnline = new StringBuilder();

        for (int i = 0; i < currentEvent.size(); i++) {
            stringOnline.append(Bukkit.getServer().getPlayer(currentEvent.get(i)).getDisplayName() + ", ");
        }

        p.sendMessage(ChatColor.AQUA + "There are currently, " + eventSize + " players in the event!");
        p.sendMessage(ChatColor.YELLOW + "All online players: " + ChatColor.RESET + stringOnline.toString());

        return false;
    }
}
