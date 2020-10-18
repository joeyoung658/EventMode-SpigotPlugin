package Origin.EventMode.Commands.EventPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 22/08/2017.
 */
public class eventChat implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 0; i < args.length; i++)
            b.append(args[i] + " ");
        if (label.equalsIgnoreCase("ec")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Player command only");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_RED + "Incorrect usage - /ec [Message]"); //If user does not add a message
            } else {
                if (!(eventopen)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                            + ChatColor.AQUA + "Error: You cannot talk in the event chat when there is no event.");
                    return false;
                }

                if (!(EventLeaders.contains(sender))) {
                    if (!(currentEvent.contains(((Player) sender).getUniqueId()))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.AQUA + "Error: You cannot talk in the event chat when you're not in the event!");
                        return false;
                    }
                }


                //ArrayList<Player> list = teams.get(teamName);
                for (int i = 0; i < currentEvent.size(); i++) {
                    Player player = Bukkit.getPlayer(currentEvent.get(i));
                    player.sendMessage("[" + ChatColor.BLUE + "Event-Chat" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ": " +
                            ChatColor.translateAlternateColorCodes('&', b.toString()));
                }
                for (int i = 0; i < EventLeaders.size(); i++){
                    EventLeaders.get(i).sendMessage("[" + ChatColor.BLUE + "Event-Chat" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ": " +
                            ChatColor.translateAlternateColorCodes('&', b.toString()));
                }
                //list.clear();
            }
        }
        return false; //ends commands statement
    }
}
