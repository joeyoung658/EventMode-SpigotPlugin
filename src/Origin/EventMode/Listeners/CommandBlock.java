package Origin.EventMode.Listeners;

import Origin.EventMode.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 22/08/2017.
 */
public class CommandBlock implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerCommandPreprocessEvent e) {
        if (!(eventopen)) return;
        List<String> cmds = Main.instance.getConfig().getStringList("blocked_cmds");
        Player p = e.getPlayer();
        if (p.hasPermission("<fp>.pvpe.bypass")) {
            return;
        }
        if (EventLeaders.contains(p)){
            return;
        }
        if (currentEvent.contains(p.getUniqueId())) {
            for (String command : cmds) {
                if (e.getMessage().startsWith("/" + command)) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "You may not use that command you are in event mode!");
                }
            }
        }

    }

}
