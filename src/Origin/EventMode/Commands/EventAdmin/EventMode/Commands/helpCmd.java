package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCmd implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if((args.length == 1) || args.length > 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [gm] [0/1/2/3]");
            return false;
        }
        Player p = (Player) sender;
        EventMode eventMode = new EventMode();

        String page = args[1];
        switch (page) {
            case "2":
                sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 2/3--------------");
                sender.sendMessage(ChatColor.AQUA + "/eventmode tpall - Teleports everyoe to you");
                sender.sendMessage(ChatColor.AQUA + "/eventmode clearinven - Clear all inventories");
                sender.sendMessage(ChatColor.AQUA + "/eventmode setspawn - Set the event spawn & respawn delay");
                sender.sendMessage(ChatColor.AQUA + "/eventmode setrespawmspawn [Delay]- Set the event respawn delay");
                sender.sendMessage(ChatColor.AQUA + "/eventmode healthregen - Heals a player to full Health");
                sender.sendMessage(ChatColor.AQUA + "/eventmode list - Lists all players within the event");
                sender.sendMessage(ChatColor.AQUA + "/eventmode freeze - Freeze/Unfreezes everyone");
                sender.sendMessage(ChatColor.AQUA + "/eventmode freeze [Playername] - Freezes/unfreezes given player");
                sender.sendMessage(ChatColor.AQUA + "/eventmode lock - Locks/Unlocks the event");
                break;
            case "3":
                sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 3/3--------------");
                sender.sendMessage(ChatColor.AQUA + "/eventmode addleader [Player Name] - Gives given player access to all event mode commands.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode keepinven - Toggles if inventory is kept upon death.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode blockbreak - Toggles if players within the event can break blocks.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode setlobby - Sets the event lobby spawn location");
                sender.sendMessage(ChatColor.AQUA + "/teams - Views all team help commands");
                break;
            default:
                sender.sendMessage(ChatColor.RED + "------------Event Mode Help Page 1/3--------------");
                sender.sendMessage(ChatColor.AQUA + "/eventmode help [Page] - Displays all the event mode commands.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode add [Playername] - Enables pvp event mode for player");
                sender.sendMessage(ChatColor.AQUA + "/eventmode remove [Playername] - Disables pvp event mode for player");
                sender.sendMessage(ChatColor.AQUA + "/eventmode close - Removes all players from pvp event mode.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode open - Opens the event, enables players to join.");
                sender.sendMessage(ChatColor.AQUA + "/eventmode togglepvp - Toggles everyone into pvp mode");
                sender.sendMessage(ChatColor.AQUA + "/eventmode stogglepvp [Playername] - Toggles given player to pvp mode");
                sender.sendMessage(ChatColor.AQUA + "/eventmode heal - Heals everyone within the event");
                break;
        }

        return false;
    }
}
