package Origin.EventMode.Commands.EventAdmin.EventMode;


import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import England.Origin.FirstPlugin.Data.StringToInt;
import England.Origin.FirstPlugin.Player.DenyBlockBreak;
import England.Origin.FirstPlugin.Player.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


import static England.Origin.FirstPlugin.Data.PVPToggleContains.PVPtoggle;
import static Origin.EventMode.Contants.*;


public class pvpe implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("pvpe")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by players!");
                return true;
            }

            if (!(EventLeaders.contains(sender))) {
                if (!(sender.hasPermission("<fp>.apvpe"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "This is a Admin + command only!");
                    return false;
                }
            }

            if (eventopen) {
                if (!(sender.isOp())) {
                    if (!(EventLeaders.contains(sender))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.RED + "You are not a team leader!");
                        return false;
                    }
                }
                if (!(teams.isEmpty())) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f &e[&4WARNING&e]&f") + ChatColor.AQUA + "Some players are within teams!");
                }
            }

            if (args.length == 1) {
//region Single Args


                if (!(eventopen) && !(args[0].equalsIgnoreCase("open")) && !(args[0].equalsIgnoreCase("help")) && !(args[0].equalsIgnoreCase("setlobby"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event is closed");
                    return false;
                }


//                if (args[0].equalsIgnoreCase("open")) {
//
//                    //Done
//                    if (eventopen) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event already open!");
//                        return false;
//                    }
//
//                    if (LobbyLocation == null) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: Please set a event lobby location before the event is opened." +
//                                " Ensure that the location is enclosed to players cannot escape!");
//                        return false;
//                    }
//
//                    EventLeaders.add(((Player) sender));
//                    eventopen = true;
//                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f" +
//                            " &aEvent participation has been opened! " + "&aType &2\"/eventjoin\" &aif you would like to join the event!"));
//                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4WARNING&e]&f" +
//                            " &4Clear your inventory before you join the event! Your items and armour will be removed."));
//
//                }
//                else if (args[0].equalsIgnoreCase("close")) {
//
//                    if (!(eventopen)) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event already closed!");
//                        return false;
//                    }
//
//                    if (!(teams.isEmpty())) {
//                        teams.clear();
//                    }
//                    if (!(teamRespawnDelay.isEmpty())) {
//                        teamRespawnDelay.clear();
//                    }
//                    if (!(teamRespawnPoint.isEmpty())) {
//                        teamRespawnPoint.clear();
//                    }
//                    if (!(teamRespawnLimit.isEmpty())) {
//                        teamRespawnLimit.clear();
//                    }
//                    if (!(playerDeathCount.isEmpty())) {
//                        playerDeathCount.clear();
//                    }
//
//                    if (!(friendlyfire.isEmpty())){
//                        friendlyfire.clear();
//                    }
//
//                    if (!(KeepInvenBeforeEvent.isEmpty())){
//                        KeepInvenBeforeEvent.clear();
//                    }
//
//
//                    eventopen = false;
//                    eventlocked = false;
//                    eventlocation = null;
//                    LobbyLocation = null;
//                    EventLeaders.clear();
//
//                    eventGamemode = GameMode.SURVIVAL;
//
//
//
//                    KeepInvenToggledEM = false;
//
//
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (currentEvent.contains(p.getUniqueId())) {
//                            if (KeepInvenBeforeEvent.contains(p)){
//                                if (PlayerNameData.filegetdata(p, "keepinven") == null) {
//                                    ChangeData.changedatac(p, "keepinven", "true");
//                                }
//                            } else {
//                                if (!(PlayerNameData.filegetdata(p, "keepinven") == null)) {
//                                    ChangeData.changedatac(p, "keepinven", null);
//                                }
//                            }
//                            p.getInventory().clear();
//                            p.setGameMode(GameMode.SURVIVAL);
//                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
//                        }
//                    }
//                    currentEvent.clear();
//                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f" +
//                            " &aThe event has been closed! Thank you to everyone for participating, until next time!"));
//                    return false;
//
//                }
//                else if (args[0].equalsIgnoreCase("heal")) {
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (currentEvent.contains(p.getUniqueId())) {
//
//
//                            p.setHealth(20.0);
//                            p.setFoodLevel(20);
//
//                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has healed you!");
//                        }
//                    }
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've healed everyone within the event");
//
//
//                }
//                else if (args[0].equalsIgnoreCase("tpall")) {
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (currentEvent.contains(p.getUniqueId())) {
//
//                            p.teleport(((Player) sender).getLocation());
//
//                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has teleported you to his location!");
//
//                        }
//                    }
//
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've teleported everyone to you");
//
//                }
//                else if (args[0].equalsIgnoreCase("clearinven")) {
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (currentEvent.contains(p.getUniqueId())) {
//                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You inventory has been cleared by an admin");
//                            p.getInventory().clear();
//                        }
//                    }
//
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've cleared all inventories");
//
//                }
//                else if (args[0].equalsIgnoreCase("keepinven")) {
//                    if (KeepInvenToggledEM){
//                        KeepInvenToggledEM = false;
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                if (!(PlayerNameData.filegetdata(p, "keepinven") == null)) {
//                                    ChangeData.changedatac(p, "keepinven", null);
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "You will now drop your items upon death!");
//                                }
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have toggled keep inventory off for the event");
//                        return true;
//
//                    } else {
//                        KeepInvenToggledEM = true;
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//
//                                if (PlayerNameData.filegetdata(p, "keepinven") == null) {
//                                    ChangeData.changedatac(p, "keepinven", "true");
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "You will no longer drop your items upon death!");
//                                }
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have toggled keep inventory on for the event");
//                        return true;
//                    }
//
////                    for (Player p : Bukkit.getOnlinePlayers()) {
////                        if (pvpeo.contains(p.getUniqueId())) {
////
////                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "togglekeepinven " + p.getName());
////                        }
////                    }
////                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have toggled keep inventory for the event");
//
//
//
//                }
//                else if (args[0].equalsIgnoreCase("healthregen")) {
//                    if (healthregen == true) {
//                        healthregen = false;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have enabled natural regen");
//                    } else {
//                        healthregen = true;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have disabled natural regen");
//                    }
//                }
//                else if (args[0].equalsIgnoreCase("list")) {
//
//
//                    StringBuilder stringOnline = new StringBuilder();
//
//                    for (int i = 0; i < currentEvent.size(); i++) {
//                        stringOnline.append(Bukkit.getServer().getPlayer(currentEvent.get(i)).getDisplayName() + ", ");
//                    }
//
//                    sender.sendMessage(ChatColor.AQUA + "There are currently, " + currentEvent.size() + " players in the event!");
//                    sender.sendMessage(ChatColor.YELLOW + "All online players: " + ChatColor.RESET + stringOnline.toString());
//
//
//                }
//                else if (args[0].equalsIgnoreCase("togglepvp")) {
//
//
//                    if (PVPToggledEM) {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                //p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has toggled your !");
//                                    Bukkit.dispatchCommand(p, "togglepvp");
//                            }
//                        }
//                        PVPToggledEM = false;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA +  " You have toggled PVP off for the event");
//
//
//                    } else {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                               // p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has toggled into PVP mode!");
//                                    Bukkit.dispatchCommand(p, "togglepvp");
//                            }
//                        }
//                        PVPToggledEM = true;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA +  " You have toggled PVP on for the event");
//                    }
//                }
//                else if (args[0].equalsIgnoreCase("freeze")) {
//                    if (Freeze.Frozen.isEmpty()) {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                if (!(Freeze.Frozen.contains(p.getUniqueId()))) {
//                                    Freeze.Frozen.add(p.getUniqueId());
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has frozen you!");
//                                }
//
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have frozen everyone within the event.");
//                    } else {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                if (Freeze.Frozen.contains(p.getUniqueId())) {
//                                    Freeze.Frozen.remove(p.getUniqueId());
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has unfrozen you!");
//                                }
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have unfrozen everyone within the event.");
//                    }
//
//                }
//                else if (args[0].equalsIgnoreCase("lock")) {
//                    if (eventlocked) {
//                        eventlocked = false;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "The event has been unlocked");
//                    } else {
//                        eventlocked = true;
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "The event has been locked");
//                    }
//                }
//                else if (args[0].equalsIgnoreCase("blockbreak")) {
//
//
//                    if (DenyBlockBreak.DenyBlockBreak.isEmpty()) {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                if (!(DenyBlockBreak.DenyBlockBreak.contains(p.getUniqueId()))) {
//                                    DenyBlockBreak.DenyBlockBreak.add(p);
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You can no longer place/break blocks within this event.");
//                                }
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "You have prevented everyone within the event from breaking blocks.");
//                    } else {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            if (currentEvent.contains(p.getUniqueId())) {
//                                if (!(DenyBlockBreak.DenyBlockBreak.contains(p.getUniqueId()))) {
//                                    DenyBlockBreak.DenyBlockBreak.remove(p);
//                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You can now place/break blocks within this event.");
//                                }
//
//                            }
//                        }
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + "Everyone within the event can now break blocks.");
//                    }
//
//                }
//                else if (args[0].equalsIgnoreCase("setlobby")) {
//
//
//
//                    LobbyLocation = ((Player) sender).getLocation();
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've set the event lobby spawn.");
//
//
//                } else {
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /eventmode for help!");
//                }

//endregion

            }
            else if (args.length == 2) {
//region Two args


//                if (!(eventopen) && !(args[0].equalsIgnoreCase("open")) && !(args[0].equalsIgnoreCase("help"))) {
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event is closed");
//                    return false;
//                }
//                if (args[1] == null) {
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /eventmode for help!");
//                    return false;
//                }
                Player target = Bukkit.getPlayerExact(args[1]);
//                if (args[0].equalsIgnoreCase("add")) {
//                    if (target == null) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
//                        return false;
//                    }
//                    if (currentEvent.contains(target.getUniqueId())) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player already within list!");
//                        return false;
//                    }
//
//
//                    if (!(currentEvent.contains(((Player) sender).getUniqueId()))) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + target.getDisplayName() + " has joined the event!");
//                    }
//
//                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has entered you into an event.");
//                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + target.getName() + " eventjoin");
//                } else if (args[0].equalsIgnoreCase("remove")) {
//                    if (target == null) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
//                        return false;
//                    }
//                    if (!(currentEvent.contains(target.getUniqueId()))) {
//                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player not within list!");
//                        return false;
//                    }
//                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has removed you from the event.");
//                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + target.getName() + " eventleave");
//                }
                else if (args[0].equalsIgnoreCase("stogglepvp")) {
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
                        return false;
                    }
                    if (!(currentEvent.contains(target.getUniqueId()))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player not within list!");
                        return false;
                    }
                    Bukkit.dispatchCommand(target, "togglepvp");

                    String status;
                    if (PVPtoggle(target.getName())){status = "on";} else {status =  "off";}
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.AQUA + "You've toggled " + target.getDisplayName() + " pvp status to " + status );
                } else if (args[0].equalsIgnoreCase("freeze")) {

                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
                        return false;
                    }
                    if (!(currentEvent.contains(target.getUniqueId()))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Player not within event!");
                        return false;
                    }
                    if (!(Freeze.Frozen.contains(target.getUniqueId()))) {
                        Freeze.Frozen.add(target.getUniqueId());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have frozen " + target.getDisplayName());
                    } else {
                        Freeze.Frozen.remove(target.getUniqueId());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have unfrozen " + target.getDisplayName());
                    }
                } else if (args[0].equalsIgnoreCase("setspawn")) {


                    eventlocation = ((Player) sender).getLocation();
                    eventRespawnDelay = StringToInt.String_To_Int_Convert(args[1]); //GotFromFirstPlugin
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've set the event spawn, with a respawn delay of " + args[1]);

                } else if (args[0].equalsIgnoreCase("addleader")) {
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " is offline!");
                        return false;
                    }
                    EventLeaders.add(target);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've added " + target.getDisplayName() + " as an event leader.");
                } else if (args[0].equalsIgnoreCase("gm")) {

                    String gm = args[1];
                    switch (gm){
                        case "0":
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (currentEvent.contains(p.getUniqueId())) {
                                    p.setGameMode(GameMode.SURVIVAL);
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "Your game mode has been updated to Survival mode.");
                                }
                            }
                            eventGamemode = GameMode.SURVIVAL;
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                    + ChatColor.AQUA + "You've set the event to Survival mode.");
                            break;
                        case "1":
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (currentEvent.contains(p.getUniqueId())) {
                                    p.setGameMode(GameMode.CREATIVE);
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "Your game mode has been updated to Creative mode.");
                                }
                            }
                            eventGamemode = GameMode.CREATIVE;
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                    + ChatColor.AQUA + "You've set the event to Creative mode.");
                            break;
                        case "2":
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (currentEvent.contains(p.getUniqueId())) {
                                    p.setGameMode(GameMode.ADVENTURE);
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "Your game mode has been updated to Adventure mode.");
                                }
                            }
                            eventGamemode = GameMode.ADVENTURE;
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                    + ChatColor.AQUA + "You've set the event to Adventure mode.");
                            break;
                        case "3":
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (currentEvent.contains(p.getUniqueId())) {
                                    p.setGameMode(GameMode.SPECTATOR);
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "Your game mode has been updated to Spectator mode.");
                                }
                            }
                            eventGamemode = GameMode.SPECTATOR;
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                    + ChatColor.AQUA + "You've set the event to Spectator mode.");
                            break;
                        default:
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                    + ChatColor.RED + "Incorrect arguments /em gm [0/1/2/3]");
                            break;

                    }


                } else if (args[0].equalsIgnoreCase("help")) {

                    String page = args[1];
                    switch (page) {
                        case "2":
                            sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 2/3--------------");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode tpall - Teleports everyoe to you");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode clearinven - Clear all inventories");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode setspawn [Delay]- Set the event spawn & respawn delay");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode healthregen - Heals a player to full Health");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode list - Lists all players within the event");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode freeze - Freeze/Unfreezes everyone");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode freeze [Playername] - Freezes/unfreezes given player");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode lock - Locks/Unlocks the event");
                            break;
                        case "3":
                            sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 3/3--------------");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode addleader [Player Name] - Gives given player access to all event mode commands.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode keepinven - Toggles if inventory is kept upon death.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode blockbreak - Toggles if players within the event can break blocks.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode setlobby - Sets the event lobby spawn location");
                            sender.sendMessage(ChatColor.AQUA + "/teams - Views all team help commands");
                            break;
                        default:
                            sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 1/3--------------");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode help [Page] - Displays all the event mode commands.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode add [Playername] - Enables pvp event mode for player");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode remove [Playername] - Disables pvp event mode for player");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode close - Removes all players from pvp event mode.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode open - Opens the event, enables players to join.");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode togglepvp - Toggles everyone into pvp mode");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode stogglepvp [Playername] - Toggles given player to pvp mode");
                            sender.sendMessage(ChatColor.AQUA + "/eventmode heal - Heals everyone within the event");
                            break;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /eventmode for help!");
                }
//endregion
            } else {
                sender.sendMessage(ChatColor.RED + "----------------Incorrect arguments!----------------");
                sender.sendMessage(ChatColor.AQUA + "/eventmode help [Page #] - Enables pvp event mode for player");
            }
//region apvpe



        }
        return false;
    }
}