package Origin.EventMode.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static Origin.EventMode.Commands.EventAdmin.Teams.teams.getPlayerTeamName;
import static Origin.EventMode.Commands.EventAdmin.Teams.teams.playerWithinTeam;
import static Origin.EventMode.Contants.friendlyfire;

/**
 * Created by josep on 17/07/2017.
 */
public class PVP implements Listener {
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(friendlyfire.isEmpty())) {
            if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
                Player p = (Player) e.getEntity();
                Player k = (Player) e.getDamager();
                if (playerWithinTeam(p) && playerWithinTeam(k)) {
                    if (getPlayerTeamName(p) == getPlayerTeamName(k)) {
                        e.setCancelled(true);
                        k.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "You may not damage your team mate!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrow(EntityDamageByEntityEvent e) {
        if (!(friendlyfire.isEmpty())) {
            if ((e.getEntity() instanceof Player) && (e.getDamager() instanceof Projectile) && (((Projectile) e.getDamager()).getShooter() instanceof Player)) {
                Player p = (Player) e.getEntity();
                Player k = ((Player) ((Projectile) e.getDamager()).getShooter());
                if (playerWithinTeam(p) && playerWithinTeam(k)) {
                    if (getPlayerTeamName(p) == getPlayerTeamName(k)) {
                        e.setCancelled(true);
                        k.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.AQUA + "You may not damage your team mate!");
                    }
                }

            }
        }
    }

    @EventHandler
    public void onFire(EntityCombustByEntityEvent e) {
        if (!(friendlyfire.isEmpty())) {
            if ((e.getEntity() instanceof Player) && (e.getCombuster() instanceof Projectile) && (((Projectile) e.getCombuster()).getShooter() instanceof Player)) {
                Player p = (Player) e.getEntity();
                Player k = ((Player) ((Projectile) e.getCombuster()).getShooter());
                if (playerWithinTeam(p) && playerWithinTeam(k)) {
                    if (getPlayerTeamName(p) == getPlayerTeamName(k)) {
                        e.setCancelled(true);
                        k.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.AQUA + "You may not damage your team mate!");
                    }
                }
            }
        }
    }
}
