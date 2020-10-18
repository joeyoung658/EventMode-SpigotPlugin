package Origin.EventMode.Listeners;

import England.Origin.FirstPlugin.Data.ChangeData;
import England.Origin.FirstPlugin.Data.PlayerNameData;
import England.Origin.FirstPlugin.Player.DenyBlockBreak;
import England.Origin.FirstPlugin.Player.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static Origin.EventMode.Contants.KeepInvenBeforeEvent;
import static Origin.EventMode.Contants.currentEvent;
import static Origin.EventMode.Commands.EventAdmin.Teams.teams.*;
import static Origin.EventMode.Contants.teams;


/**
 * Created by josep on 19/06/2017.
 */
public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

         if ((currentEvent.contains(event.getPlayer().getUniqueId()))) {
             Player player = event.getPlayer();

             player.getInventory().clear();
             if (player.getGameMode() != GameMode.SURVIVAL) {
                 player.getPlayer().setGameMode(GameMode.SURVIVAL);
             }
             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " +player.getName());
             if (Freeze.Frozen.contains(player.getUniqueId())) {
                 Freeze.Frozen.remove(player.getUniqueId());
             }
             if (DenyBlockBreak.DenyBlockBreak.contains(player)) {
                 Freeze.Frozen.remove(player);
             }
             if (playerWithinTeam(player)){
                 String teamName = getPlayerTeamName(player);
                 if (!(playerWithinTeam(player))){
                     return;
                 }
                 teams.get(teamName).remove(player);
             }
             if (KeepInvenBeforeEvent.contains(player)){
                 if (PlayerNameData.filegetdata(player, "keepinven") == null) {
                     ChangeData.changedatac(player, "keepinven", "true");
                 }
             } else {
                 if (!(PlayerNameData.filegetdata(player, "keepinven") == null)) {
                     ChangeData.changedatac(player, "keepinven", null);
                 }
             }
             currentEvent.remove(player.getUniqueId());
         }

    }
}
