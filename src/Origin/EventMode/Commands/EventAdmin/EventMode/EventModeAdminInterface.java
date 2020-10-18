package Origin.EventMode.Commands.EventAdmin.EventMode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface EventModeAdminInterface {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
