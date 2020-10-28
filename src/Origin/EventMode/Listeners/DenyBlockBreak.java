package Origin.EventMode.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import static Origin.EventMode.Contants.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by josep on 28/10/2020.
 */
public class DenyBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (DenyBlockBreak.contains(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.RED + "You can't place/break blocks within this event.");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (DenyBlockBreak.contains(event.getPlayer())){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + ChatColor.RED + "You can't place/break blocks within this event.");
        }
    }
}