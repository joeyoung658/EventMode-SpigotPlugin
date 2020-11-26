package Origin.EventMode.Listeners;

import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


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
                EventMode eventMode = new EventMode();
                if (eventMode.playerWithinTeam(p) && eventMode.playerWithinTeam(k)) {
                    if (eventMode.getPlayerTeamName(p) == eventMode.getPlayerTeamName(k)) {
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
                EventMode eventMode = new EventMode();
                if (eventMode.playerWithinTeam(p) && eventMode.playerWithinTeam(k)) {
                    if (eventMode.getPlayerTeamName(p) == eventMode.getPlayerTeamName(k)) {
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
                EventMode eventMode = new EventMode();
                if (eventMode.playerWithinTeam(p) && eventMode.playerWithinTeam(k)) {
                    if (eventMode.getPlayerTeamName(p) == eventMode.getPlayerTeamName(k)) {
                        e.setCancelled(true);
                        k.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.AQUA + "You may not damage your team mate!");
                    }
                }
            }
        }
    }
}
