package Origin.EventMode.Commands.EventPlayer;


import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static England.Origin.FirstPlugin.Data.PVPToggleContains.PVPtoggle;
import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 27/05/2017.
 */
public class eventjoin implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("eventjoin")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  + " This command can only be run by a player!");
                return true;
            } else {
                EventMode eventMode = new EventMode();
                boolean result = eventMode.addPlayerToEvent((Player) sender, false, false);

                if (!(result)) {
                    if (!(eventMode.hadEventJoinWarningMsg((Player) sender))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.AQUA + "Error: You're unable to join the event as this time!");
                    }
                }

                return true;
            }

        }

        return true;
    }

//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        if (cmd.getName().equalsIgnoreCase("eventjoin")) {
//            if (!(sender instanceof Player)) {
//                sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  + " This command can only be run by a player!");
//                return true;
//            } else {
//                if (eventopen) {
//                    Player player = (((Player) sender));
//
//                    if(eventlocked){
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  + ChatColor.RED + "Participation to the event has been locked." +
//                                " Try contacting the Event Host(s) if you feel that this is an error.");
//                        return true;
//                    }
//
//                    if (currentEvent.contains(player.getUniqueId())) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  + ChatColor.RED + " You are already within the event!");
//                        return true;
//                    }
//
//                    if (!(EventJoinWarning.contains(player))){
//                        EventJoinWarning.add(player);
//
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4WARNING&e]&f" +
//                                " &4Clear your inventory before you join the event! Your items and armour will be removed!"));
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f" +
//                                " &aAre you sure you want to join the event? " +  "&aType \"&2/eventjoin&a\" again to join, or \"&2/eventleave&a\" to cancel."));
//
//
//                        return true;
//                    } else {
//                        EventJoinWarning.remove(player);
//                    }
//
//                    currentEvent.add(player.getUniqueId());
//                    ((Player) sender).teleport(LobbyLocation);
//
//
//                    if (PVPToggledEM){
//                        if (!(PVPtoggle(sender.getName()))) {
//                            Bukkit.dispatchCommand(((Player) sender).getPlayer(), "togglepvp");
//                        }
//                    } else {
//                        if (PVPtoggle(sender.getName())) {
//                            Bukkit.dispatchCommand(((Player) sender).getPlayer(), "togglepvp");
//                        }
//                    }
//
//
//                    if (!(PlayerNameData.filegetdata(((Player) sender).getPlayer(), "keepinven") == null)) {
//                        KeepInvenBeforeEvent.add(((Player) sender).getPlayer());
//                    }
//
//                    if (KeepInvenToggledEM) {
//                        if (PlayerNameData.filegetdata(((Player) sender).getPlayer(), "keepinven") == null) {
//                            ChangeData.changedatac(((Player) sender).getPlayer(), "keepinven", "true");
//                        }
//
//                    } else {
//                        if (!(PlayerNameData.filegetdata(((Player) sender).getPlayer(), "keepinven") == null)) {
//                            ChangeData.changedatac(((Player) sender).getPlayer(), "keepinven", null);
//                        }
//                    }
//
//                    if (!(((Player) sender).getPlayer().getGameMode().equals(eventGamemode))) {
//                        ((Player) sender).getPlayer().setGameMode(eventGamemode);
//                    }
//
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (currentEvent.contains(p.getUniqueId()))
//                        {
//                            p.sendMessage( ((Player) sender).getDisplayName() +  ChatColor.AQUA + " has joined the event!");
//                        }
//                        if (EventLeaders.contains(p)){
//                            p.sendMessage( ((Player) sender).getDisplayName() +  ChatColor.AQUA +  " has joined the event. "  + currentEvent.size() + " players are now in Event mode.");
//                        }
//                    }
//
//                    player.getInventory().clear();
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "Your inventory has been cleared, if you would like to leave the event at any point, type \"/eventleave\". You will be removed from the event and teleported to spawn.");
//                return true;
//                }
//                sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")
//                        + ChatColor.AQUA + "Error: There is no event running.");
//                return true;
//            }
//
//        }
//
//        return true;
//    }

}
