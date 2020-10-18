package Origin.EventMode.Commands.EventTeams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static Origin.EventMode.Commands.EventAdmin.Teams.teams.getPlayerTeamName;
import static Origin.EventMode.Commands.EventAdmin.Teams.teams.playerWithinTeam;
import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 20/06/2017.
 */
public class teamChat implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 0; i < args.length; i++)
            b.append(args[i] + " ");
        if (label.equalsIgnoreCase("tc")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Player command only");
                return true;
            }
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect usage - /tc [Message]"); //If user does not add a message
                } else {
                    if (!(eventopen)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.AQUA + "Error: You cannot talk in the team chat when there is no event.");
                        return false;
                    }

                    if (teams.isEmpty()){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: There are currently no teams.");
                        return false;
                    }

                    if (!(playerWithinTeam(((Player) sender)))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: You are not in a team.");
                        return false;
                    }

                    String teamName = getPlayerTeamName(((Player) sender));
                    if (teamName == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: You are not apart of a team.");
                        return false;
                    }

                    //ArrayList<Player> list = teams.get(teamName);
                    for (int i = 0; i < teams.get(teamName).size(); i++) {
                        teams.get(teamName).get(i).sendMessage("[" + ChatColor.BLUE + "Team-Chat" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ": " +
                                ChatColor.translateAlternateColorCodes('&', b.toString()));
                    }
                    //list.clear();
                }
        }
        return false; //ends commands statement
    }
}