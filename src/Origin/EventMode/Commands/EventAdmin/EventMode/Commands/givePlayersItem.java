package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.Data.StringToInt;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class givePlayersItem implements EventModeAdminInterface
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        if(args.length < 3){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [giveitem] [item] [qty]");
            return false;
        }
        Player p = (Player) sender;


        String item = args[1];
        Material itemType = Material.matchMaterial(item);
        if (itemType == null){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error - Unknown item: " + item + " !");
            return false;
        }


        int qty = StringToInt.stringToInt(args[2]);
        if (qty == 0){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Please give a valid qty!");
            return false;
        }


        ItemStack itemStack = new ItemStack(itemType);
        EventMode event = new EventMode();
        event.giveWholeEventItem(itemStack, qty);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've successfully given all players " + item + "!");


        return false;
    }
}
