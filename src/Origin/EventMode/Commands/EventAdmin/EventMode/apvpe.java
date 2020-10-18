package Origin.EventMode.Commands.EventAdmin.EventMode;

import Origin.EventMode.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class apvpe implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("This command can only be run by a player");
        }

        if (cmd.getName().equalsIgnoreCase("apvpe")) {
            if (sender.hasPermission("<fp>.apvpe")) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("addcmdpvpe")) {
                        List<String> pvpe = Main.instance.getConfig().getStringList("pvpe");
                        if (pvpe.contains(args[1])) {
                            sender.sendMessage(ChatColor.RED + args[1] + " is already blocked!");
                            return false;
                        }
                        pvpe.add(args[1]);
                        Main.instance.getConfig().set("pvpe", pvpe);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Command added to pvpe block list!");

                    } else if (args[0].equalsIgnoreCase("rmcmdpvpe")) {
                        List<String> pvpe = Main.instance.getConfig().getStringList("pvpe");
                        if (!(pvpe.contains(args[1]))) {
                            sender.sendMessage(ChatColor.RED + args[1] + " is not already blocked!");
                            return false;
                        }
                        pvpe.remove(args[1]);
                        Main.instance.getConfig().set("pvpe", pvpe);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Command removed from pvpe block list!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
                    sender.sendMessage(ChatColor.AQUA + "/apvpe [addcmdpvpe] [Command] Adds commands which are blocked during PVP events");
                    sender.sendMessage(ChatColor.AQUA + "/apvpe [rmcmdpvpe] [Command]  Removes commands which are blocked during PVP events");
                    sender.sendMessage(ChatColor.AQUA + "/fp-reload Reloads config");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This is a Admin + command only!");
            }
        }
        return false;
    }
}
