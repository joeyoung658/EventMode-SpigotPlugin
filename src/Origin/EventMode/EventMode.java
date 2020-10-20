package Origin.EventMode;

import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import England.Origin.FirstPlugin.Player.DenyBlockBreak;
import England.Origin.FirstPlugin.Player.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static England.Origin.FirstPlugin.Data.PVPToggleContains.PVPtoggle;
import static Origin.EventMode.Contants.*;

public class EventMode {


    /**
     * Checks if the event is closed, if it is sends a message to the player
     * @param player
     * @return
     */
    public boolean isClosedMsg(Player player){
        if (!(eventopen)){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event already closed!");
            return eventopen;
        }
        return eventopen;
    }

    /**
     * Checks if an event is open, if it is sends an message to the player
     * @param player
     * @return
     */
    public boolean isOpenMsg(Player player){
        if (eventopen) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event already open!");
            return eventopen;
        }
        return eventopen;
    }

    /**
     * Checks if the event lobby is null, if it is warns the player
     * @param player So a message can be sent to the player informing them that the lobby hasn't been set
     * @return boolean
     */
    public boolean isLobbyNullMsg(Player player){
        if (LobbyLocation == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: Please set a event lobby location before the event is opened." +
                    " Ensure that the location is enclosed to players cannot escape!");
            return true;
        } else {
            return false;
        }
    }

    public void addLeader(Player player){
        EventLeaders.add(player);
    }

    /**
     * Removes the player as an eventleader
     * @param player Player which to be removed as leader
     */
    public void removeLeader(Player player){
        EventLeaders.remove(player);
    }

    /**
     * Opens a event for the server & broadcasts the message
     * @param player Required to add player as an event leader
     */
    public void openEvent(Player player){
        this.addLeader(player);
        eventopen = true;
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f" +
                " &aEvent participation has been opened! " + "&aType &2\"/eventjoin\" &aif you would like to join the event!"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4WARNING&e]&f" +
                " &4Clear your inventory before you join the event! Your items and armour will be removed."));
    }

    public boolean isEventOpen(){
        return eventopen;
    }

    /**
     * Closes the current event that is open and resets all the constant variables
     */
    public void closeEvent(){
        if (!(teams.isEmpty())) {
            teams.clear();
        }
        if (!(teamRespawnDelay.isEmpty())) {
            teamRespawnDelay.clear();
        }
        if (!(teamRespawnPoint.isEmpty())) {
            teamRespawnPoint.clear();
        }
        if (!(teamRespawnLimit.isEmpty())) {
            teamRespawnLimit.clear();
        }
        if (!(playerDeathCount.isEmpty())) {
            playerDeathCount.clear();
        }

        if (!(friendlyfire.isEmpty())){
            friendlyfire.clear();
        }
        if (!(KeepInvenBeforeEvent.isEmpty())){
            KeepInvenBeforeEvent.clear();
        }
        eventopen = false;
        eventlocked = false;
        eventlocation = null;
        LobbyLocation = null;
        EventLeaders.clear();

        eventGamemode = GameMode.SURVIVAL;
        KeepInvenToggledEM = false;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId())) {
                if (KeepInvenBeforeEvent.contains(p)){
                    if (PlayerNameData.filegetdata(p, "keepinven") == null) {
                        ChangeData.changedatac(p, "keepinven", "true");
                    }
                } else {
                    if (!(PlayerNameData.filegetdata(p, "keepinven") == null)) {
                        ChangeData.changedatac(p, "keepinven", null);
                    }
                }
                p.getInventory().clear();
                p.setGameMode(GameMode.SURVIVAL);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
            }
        }
        currentEvent.clear();
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server-Announcement&e]&f" +
                " &aThe event has been closed! Thank you to everyone for participating, until next time!"));
    }

    /**
     * Heals all players within the event
     */
    public void healAllPlayers(){
        //No point looping through all players if there is no one within the event
        if (currentEvent.isEmpty()){
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId())) {
                p.setHealth(20.0);
                p.setFoodLevel(20);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has healed you!");
            }
        }
    }

    /**
     * Teleports all players within the event to the sender
     * @param sender Player in which to teleport too
     */
    public void tpAllToSender(Player sender){
        if (currentEvent.isEmpty()){
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId())) {
                p.teleport(sender.getLocation());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has teleported you to his location!");
            }
        }
    }

    /**
     * Clears all players inventory which is within the event
     */
    public void clearAllPlayersIven(){
        if (currentEvent.isEmpty()){
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId())) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You inventory has been cleared by an admin");
                p.getInventory().clear();
            }
        }
    }


    /**
     * Toogles Keep Inventory for the players within the event
     * @return False - KeepInven Off / True - KeepInven On
     */
    public boolean toggleKeepIven(){
        if (KeepInvenToggledEM){
            KeepInvenToggledEM = false;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (!(PlayerNameData.filegetdata(p, "keepinven") == null)) {
                        ChangeData.changedatac(p, "keepinven", null);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "You will now drop your items upon death!");
                    }
                }
            }
            return false;
        } else {
            KeepInvenToggledEM = true;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {

                    if (PlayerNameData.filegetdata(p, "keepinven") == null) {
                        ChangeData.changedatac(p, "keepinven", "true");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "You will no longer drop your items upon death!");
                    }
                }
            }
            return true;
        }
    }

    /**
     * Toggles natural health regen for all players within the event
     * @return
     */
    public boolean toogleHealthRegen(){
        if (healthregen == true) {
            healthregen = false;
            return true;
        } else {
            healthregen = true;
            return false;
        }
    }

    /**
     * Returns the amount if players within the whole event
     * @return
     */
    public int eventSize(){
        return currentEvent.size();
    }

    /**
     * TogglesPVP on/off for all players within the event
     * @return False - PVP toggled off / True PVP toggle on
     */
    public boolean toggleAllPlayersPVP(){
        //todo Handle this within the eventmode plugin rather than pushing to FirstPlugin
        if (PVPToggledEM) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    //p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has toggled your !");
                    Bukkit.dispatchCommand(p, "togglepvp");
                }
            }
            PVPToggledEM = false;
            return false;
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    // p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has toggled into PVP mode!");
                    Bukkit.dispatchCommand(p, "togglepvp");
                }
            }
            PVPToggledEM = true;
            return true;
        }
    }

    public boolean freezeAllPlayers(){
        //todo handle this within eventmode plugin as could cause issues with main freeze from FirstPLugin
        if (Freeze.Frozen.isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (!(Freeze.Frozen.contains(p.getUniqueId()))) {
                        Freeze.Frozen.add(p.getUniqueId());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has frozen you!");
                    }

                }
            }
            return true;
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (Freeze.Frozen.contains(p.getUniqueId())) {
                        Freeze.Frozen.remove(p.getUniqueId());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has unfrozen you!");
                    }
                }
            }
            return false;
        }
    }

    /**
     * Toggles of the event is locked or closed.
     * @return
     */
    public boolean toggleEventLock(){
        if (eventlocked) {
            eventlocked = false;
            return false;
        } else {
            eventlocked = true;
            return true;
        }
    }

    public boolean isEventLocked(){
        return eventlocked;
    }

    public boolean isPlayerInEvent(Player player){
        if (currentEvent.contains(player.getUniqueId())){
            return true;
        } else {
            return false;
        }
    }

    public boolean hadEventJoinWarningMsg(Player player){
        return EventJoinWarning.contains(player);
    }

    /**
     * Adds a player to the current active event
     * @param target Player in which should be added to the event
     * @param eventJoinWarningBypass If set to true, the player will not be warned about thier inventory being cleared
     * @param eventLockBypass If set to true, a player will be able to join regardless of if the event is locked
     * @return
     */
    public boolean addPlayerToEvent(Player target, Boolean eventJoinWarningBypass, Boolean eventLockBypass){
        if ((!(isEventOpen())) && (isPlayerInEvent(target))){
            return false;
        }
        if (!(eventLockBypass)){
            if (isEventLocked()){
                return false;
            }
        }
        if (!(eventJoinWarningBypass)){
            if (!(EventJoinWarning.contains(target))){
                EventJoinWarning.add(target);
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4WARNING&e]&f" +
                        " &4Clear your inventory before you join the event! Your items and armour will be removed!"));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f" +
                        " &aAre you sure you want to join the event? " +  "&aType \"&2/eventjoin&a\" again to join, or \"&2/eventleave&a\" to cancel."));
                return false;
            } else {
                EventJoinWarning.remove(target);
            }
        }
        currentEvent.add(target.getUniqueId());
        target.teleport(LobbyLocation);
        if (PVPToggledEM){
            if (!(PVPtoggle(target.getName()))) {
                Bukkit.dispatchCommand(target.getPlayer(), "togglepvp");
            }
        } else {
            if (PVPtoggle(target.getName())) {
                Bukkit.dispatchCommand(target.getPlayer(), "togglepvp");
            }
        }
        if (!(PlayerNameData.filegetdata(target.getPlayer(), "keepinven") == null)) {
            KeepInvenBeforeEvent.add(target.getPlayer());
        }
        if (KeepInvenToggledEM) {
            if (PlayerNameData.filegetdata(target.getPlayer(), "keepinven") == null) {
                ChangeData.changedatac(target.getPlayer(), "keepinven", "true");
            }

        } else {
            if (!(PlayerNameData.filegetdata(target.getPlayer(), "keepinven") == null)) {
                ChangeData.changedatac(target.getPlayer(), "keepinven", null);
            }
        }
        if (!(target.getPlayer().getGameMode().equals(eventGamemode))) {
            target.getPlayer().setGameMode(eventGamemode);
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId()))
            {
                p.sendMessage( target.getDisplayName() +  ChatColor.AQUA + " has joined the event!");
            }
            if (EventLeaders.contains(p)){
                p.sendMessage( target.getDisplayName() +  ChatColor.AQUA +  " has joined the event. "  + currentEvent.size() + " players are now in Event mode.");
            }
        }
        target.getInventory().clear();
        target.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "Your inventory has been cleared, if you would like to leave the event at any point, type \"/eventleave\". You will be removed from the event and teleported to spawn.");
        return true;
    }

    /**
     * Removes a player from an event
     * @param target
     * @return
     */
    public boolean removePlayerFromEvent(Player target){
        if (!(isEventOpen())){
            return false;
        }
        if (EventJoinWarning.contains(target)){
            EventJoinWarning.remove(target);
            target.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f")  +ChatColor.AQUA + "You have canceled your entry into the event!");
            return false;
        }
        if (!(isPlayerInEvent(target))){
            return false;
        }
        currentEvent.remove(target.getUniqueId());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (currentEvent.contains(p.getUniqueId())) {
                p.sendMessage(target.getDisplayName() + ChatColor.AQUA + " has left the event!");
            }
            if (EventLeaders.contains(p)){
                p.sendMessage(target.getDisplayName() +  ChatColor.AQUA +  " has left the event. "  + currentEvent.size() + " players are now in Event mode.");
            }
        }
        if (KeepInvenBeforeEvent.contains(target.getPlayer())){
            if (PlayerNameData.filegetdata(target.getPlayer(), "keepinven") == null) {
                ChangeData.changedatac(target.getPlayer(), "keepinven", "true");
            }
        } else {
            if (!(PlayerNameData.filegetdata(target.getPlayer(), "keepinven") == null)) {
                ChangeData.changedatac(target.getPlayer(), "keepinven", null);
            }
        }
        target.getInventory().clear();
        if (target.getGameMode() != GameMode.SURVIVAL) {
            target.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + target.getName());
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Your inventory has been cleared, Command use has been restored! Thank you for being apart of the event.");
        return true;
    }

    public void setLobby(Location lobby){
        LobbyLocation = lobby;
    }


    /**
     * Toggles the players ability to break blocks whilst in a event
     * @return True - Stops block breaking False - ALlows block breaking
     */
    public boolean toggleBlockBreak(){
        //todo tidy this up
        if (DenyBlockBreak.DenyBlockBreak.isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (!(DenyBlockBreak.DenyBlockBreak.contains(p.getUniqueId()))) {
                        DenyBlockBreak.DenyBlockBreak.add(p);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You can no longer place/break blocks within this event.");
                    }
                }
            }
            return true;
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (!(DenyBlockBreak.DenyBlockBreak.contains(p.getUniqueId()))) {
                        DenyBlockBreak.DenyBlockBreak.remove(p);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You can now place/break blocks within this event.");
                    }

                }
            }
            return false;
        }
    }
}
