package Origin.EventMode.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static Origin.EventMode.Contants.healthregen;
import static Origin.EventMode.Contants.currentEvent;

/**
 * Created by josep on 02/08/2017.
 */
public class FoodRegen implements Listener {
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if(event.getEntity() instanceof Player) {

            Player p = (Player) event.getEntity();
            if (healthregen == true) {
                if (currentEvent.contains(p.getUniqueId())) {
                    if (!(event.getFoodLevel() == 20)) {
                        ((Player) event.getEntity()).setFoodLevel(20);
                    }
                }
            }
        }
    }
}
