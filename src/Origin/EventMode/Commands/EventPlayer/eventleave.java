package Origin.EventMode.Commands.EventPlayer;

import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 27/05/2017.
 */
public class eventleave implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("eventleave")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +"This command can only be run by a player!");
                return true;
            } else {
                if (eventopen) {

                    if (EventJoinWarning.contains(sender)){
                        EventJoinWarning.remove(sender);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "You have canceled your entry into the event!");
                        return false;
                    }

                    if (currentEvent.contains(((Player) sender).getUniqueId())) {

                        currentEvent.remove(((Player) sender).getUniqueId());

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (currentEvent.contains(p.getUniqueId())) {
                                p.sendMessage( ((Player) sender).getDisplayName() + ChatColor.AQUA + " has left the event!");
                            }
                            if (EventLeaders.contains(p)){
                                p.sendMessage( ((Player) sender).getDisplayName() +  ChatColor.AQUA +  " has left the event. "  + currentEvent.size() + " players are now in Event mode.");
                            }
                        }

                        if (KeepInvenBeforeEvent.contains(((Player) sender).getPlayer())){
                            if (PlayerNameData.filegetdata(((Player) sender).getPlayer(), "keepinven") == null) {
                                ChangeData.changedatac(((Player) sender).getPlayer(), "keepinven", "true");
                            }
                        } else {
                            if (!(PlayerNameData.filegetdata(((Player) sender).getPlayer(), "keepinven") == null)) {
                                ChangeData.changedatac(((Player) sender).getPlayer(), "keepinven", null);
                            }
                        }


                        ((Player) sender).getInventory().clear();
                        if (((Player) sender).getGameMode() != GameMode.SURVIVAL) {
                            ((Player) sender).getPlayer().setGameMode(GameMode.SURVIVAL);
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Your inventory has been cleared, Command use has been restored! Thank you for being apart of the event.");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "Error: You're not in an event");
                    }
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "Sorry but there are no events open at this moment in time.");
                return true;
            }

        }

        return true;
    }

}
