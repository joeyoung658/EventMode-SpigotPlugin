package Origin.EventMode;

import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

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
}
