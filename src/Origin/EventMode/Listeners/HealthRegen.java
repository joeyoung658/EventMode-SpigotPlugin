package Origin.EventMode.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 19/05/2017.
 */
public class HealthRegen implements Listener {
    @EventHandler
    public void onHealthRegen(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (healthregen == true) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}