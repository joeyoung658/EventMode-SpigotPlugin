
package Origin.EventMode.Listeners;

//Creates red death message



import England.Origin.FirstPlugin.Main;
import England.Origin.FirstPlugin.Player.Freeze;
import Origin.EventMode.Contants;
import Origin.EventMode.EventMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static Origin.EventMode.Contants.*;
import static Origin.EventMode.Commands.EventAdmin.Teams.teams.*;

public class PlayerRespawn implements Listener {

    private String teamName;
    private int teamRespawnDelay;
    private Integer rlimit;

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event ) {

        Player player = event.getPlayer();

        if (currentEvent.contains(player.getUniqueId())) {

            if (!(teamRespawnPoint.isEmpty())) {

                EventMode eventMode =  new EventMode();
                teamName = eventMode.getPlayerTeamName(player);
                if (teamName == null){
                    return;
                }

            if (!(teamRespawnLimit.isEmpty())) {

                if (playerDeathCount.get(player) == null) {
                    playerDeathCount.put(player, 1);
                } else {
                    playerDeathCount.put(player, playerDeathCount.get(player) + 1);
                }

                rlimit = teamRespawnLimit.get(teamName);
                if (rlimit == null){
                    rlimit = 0;
                }

                if (playerDeathCount.get(player) >= rlimit) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (currentEvent.contains(p.getUniqueId())) {
                            p.sendMessage(player.getDisplayName() + ChatColor.AQUA + " is out of the event!");
                        }
                        if (EventLeaders.contains(p)) {
                            p.sendMessage(player.getDisplayName() + ChatColor.AQUA + " is out of the event!");
                        }
                    }
                    return;
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (EventLeaders.contains(p)) {
                            p.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has " + playerDeathCount.get(player) + "/" + teamRespawnLimit.get(teamName) + " lives remaining.");
                        }
                    }
                }
            }

            if (Contants.teamRespawnDelay.containsKey(teamName)){
                teamRespawnDelay = Contants.teamRespawnDelay.get(teamName);
            } else {
                teamRespawnDelay = 0;
            }


            if (teamRespawnPoint.containsKey(teamName)){
                event.setRespawnLocation(teamRespawnPoint.get(teamName));
            } else {
                return;
            }
                Freeze.Frozen.add(player.getUniqueId());
                player.setGameMode(GameMode.SPECTATOR);
                if (teamRespawnDelay > 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You will respawn in " + teamRespawnDelay + " seconds.");
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    @Override
                    public void run() {

                        if (eventopen) {
                            player.setGameMode(GameMode.SURVIVAL);
                            Freeze.Frozen.remove(player.getUniqueId());
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have respawned to your teams spawn point!");
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + event.getPlayer().getName());
                        }

                    }
                }, 20l * teamRespawnDelay);
                return;
            }



            if (!(eventSpawnLoc == null)) {
                event.setRespawnLocation(eventSpawnLoc);
                if (eventRespawnDelay == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have respawned!");
                    return;
                }
                Freeze.Frozen.add(player.getUniqueId());
                player.setGameMode(GameMode.SPECTATOR);
                if (eventRespawnDelay > 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You will respawn in " + eventRespawnDelay + " seconds.");
                }
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    @Override
                    public void run() {

                        if (eventopen) {
                            player.setGameMode(GameMode.SURVIVAL);
                            Freeze.Frozen.remove(player.getUniqueId());
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You have respawned!");
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + event.getPlayer().getName());
                        }

                    }
                }, 20l * eventRespawnDelay);
            }

        }
    }
}