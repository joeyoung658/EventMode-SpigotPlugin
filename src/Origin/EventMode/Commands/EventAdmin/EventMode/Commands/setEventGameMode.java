package Origin.EventMode.Commands.EventAdmin.EventMode.Commands;

import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminInterface;
import Origin.EventMode.EventMode;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class setEventGameMode implements EventModeAdminInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length < 2){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Error: Correct usage /eventmode [gm] [0/1/2/3]");
            return false;
        }
        Player p = (Player) sender;
        EventMode eventMode = new EventMode();
        String gm = args[1];
        switch (gm){
            case "0":
                GameMode mode0 = GameMode.SURVIVAL;
                eventMode.setGameMode(mode0);
                sendMsg(p, mode0);
                break;
            case "1":
                GameMode mode1 = GameMode.CREATIVE;
                eventMode.setGameMode(mode1);
                sendMsg(p, mode1);
                break;
            case "2":
                GameMode mode2 = GameMode.ADVENTURE;
                eventMode.setGameMode(mode2);
                sendMsg(p, mode2);
                break;
            case "3":
                GameMode mode3 = GameMode.SPECTATOR;
                eventMode.setGameMode(mode3);
                sendMsg(p, mode3);
                break;
            default:
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                        + ChatColor.RED + "Incorrect arguments /em gm [0/1/2/3]");
                break;

        }
        return false;
    }
    private void sendMsg(Player sender, GameMode mode){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                + ChatColor.AQUA + "You've set the event to " + mode.toString().toLowerCase() +  " mode.");
    }
}