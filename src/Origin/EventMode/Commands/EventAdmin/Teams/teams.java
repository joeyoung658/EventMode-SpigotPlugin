package Origin.EventMode.Commands.EventAdmin.Teams;

import England.Origin.FirstPlugin.Data.StringToInt;
import England.Origin.FirstPlugin.Player.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static Origin.EventMode.Contants.*;

/**
 * Created by josep on 20/06/2017.
 */
public class teams implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("teams")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by players!");
                return true;
            }

            if (!(EventLeaders.contains(sender))) {
                if (!(sender.hasPermission("<fp>.apvpe"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "This is a Admin + command only!");
                    return false;
                }
            }

            if (eventopen) {
                if (!(sender.isOp())) {
                    if (!(EventLeaders.contains(sender))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") +ChatColor.RED + "You are not a team leader!");
                        return false;
                    }
                }
            }

            if (args.length == 1) {
                if (!(eventopen)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event is closed");
                    return false;
                }
                if (args[0].equalsIgnoreCase("list")) {


                    StringBuilder stringOnline = new StringBuilder();
                    for (String key : teams.keySet()) {
                        stringOnline.append(key.toString() + "- ");
                        for (int i = 0; i < teams.get(key).size(); i++) {
                            stringOnline.append(teams.get(key).get(i).getDisplayName() + ", ");
                        }
                    }

                    sender.sendMessage(ChatColor.AQUA + "There are currently, " + teams.size() + "/" + currentEvent.size() + " people within teams whom are in event mode.");
                    sender.sendMessage(ChatColor.YELLOW + "Team players: " + ChatColor.RESET + stringOnline.toString());


                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /teams for help!");
                }
            } else if (args.length == 2) {

                if (!(eventopen)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event is closed");
                    return false;
                }
                if (args[1] == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /team for help!");
                    return false;
                }
                Player target = Bukkit.getPlayerExact(args[1]);
                if (args[0].equalsIgnoreCase("create")) {

                    if (teams.containsKey(args[1])){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " has already exists!");
                        return false;
                    }

                    teams.put(args[1],new ArrayList<>());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " has been created.");
                    return false;
                } else if (args[0].equalsIgnoreCase("delete")) {


                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }

                    teams.remove(args[1]);
                    teamRespawnDelay.remove(args[1]);
                    teamRespawnPoint.remove(args[1]);
                    playerDeathCount.clear();
                    teamRespawnLimit.remove(args[1]);

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " has been removed.");
                    return false;

                } else if (args[0].equalsIgnoreCase("clear")) {
                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }


                    teams.put(args[1], new ArrayList<>());

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " has been cleared of players.!");
                    return false;

                }  else if (args[0].equalsIgnoreCase("remove")) {

                    if (target == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[1] + " isn't online!");
                        return false;
                    }

                    String teamName = getPlayerTeamName(target);
                    if (!(playerWithinTeam(target))){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") +  target.getDisplayName() + ChatColor.RED  + " is not within a team!");
                        return false;
                    }

                    teams.get(teamName).remove(target);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName()  + ChatColor.AQUA +
                            " has been removed form the team " + teamName);
                    return false;
                } else if (args[0].equalsIgnoreCase("freeze")) {

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }


                    for (int i = 0; i < teams.get(args[1]).size(); i++) {

                        if (Freeze.Frozen.contains(teams.get(args[1]).get(i).getUniqueId())) {
                                    Freeze.Frozen.remove(teams.get(args[1]).get(i).getUniqueId());
                                    teams.get(args[1]).get(i).sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has unfrozen you!");
                                } else {
                                    Freeze.Frozen.add(teams.get(args[1]).get(i).getUniqueId());
                                    teams.get(args[1]).get(i).sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "An admin has frozen you!");
                                }

                            }

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +
                                ChatColor.AQUA + "You've toggled freeze for team " +  args[1] + ".");
                        return true;

                } else if (args[0].equalsIgnoreCase("setspawn")) {

                    if (args[1].equalsIgnoreCase("clearspawns")) {
                        teamRespawnPoint.clear();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "You've restored all teams respawn points to default");
                        return true;
                    }

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }

                    eventSpawnLoc = null;
                    teamRespawnPoint.put(args[1], ((Player) sender).getLocation());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                            + ChatColor.AQUA + "You've set respawn point for " + args[1]);
                } else if (args[0].equalsIgnoreCase("friendlyfire")) {

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(
                                ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                        + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }


                    if (args[2].equalsIgnoreCase("on")) {
                        if (friendlyfire.contains(args[1])) {
                            friendlyfire.remove(args[1]);
                            sender.sendMessage(
                                    ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "You've enabled friendly fire for " + args[1]);
                        } else {
                            sender.sendMessage(
                                    ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                            + ChatColor.AQUA + "Friendly fire for " + args[1] + " is already enabled!");
                        }
                    } else if (args[2].equalsIgnoreCase("off")){
                        friendlyfire.add(args[1]);
                        sender.sendMessage(
                                ChatColor.translateAlternateColorCodes(
                                        '&', "&e[&4Server&e]&f")
                                        + ChatColor.AQUA + "You've disabled friendly fire for " + args[1]);
                    } else {
                        sender.sendMessage(
                                ChatColor.translateAlternateColorCodes(
                                        '&', "&e[&4Server&e]&f")
                                        + ChatColor.AQUA + "Error: Correct useage - /teams friendlyfire [Team Name] [off/on]");
                    }


                }  else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /teams for help!");
                }

        } else if (args.length == 3) {
                if (!(eventopen)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Error: The event is closed");
                    return false;
                }
                if (args[1] == null || args[2] == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /team for help!");
                    return false;
                }
                Player target = Bukkit.getPlayerExact(args[2]);
                if (args[0].equalsIgnoreCase("add")){

                    if (target == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + args[2] + " isn't online!");
                        return false;
                    }

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }

                    if (!(currentEvent.contains(target.getUniqueId()))){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " isn't in event mode.");
                        return false;
                    }


                    if (teams.get(args[1]).contains(target)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " already within selected team!");
                        return false;
                    }

                    if (playerWithinTeam(target)){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +  target.getDisplayName() + ChatColor.RED  + " already within another team!");
                        return false;
                    }

                    teams.get(args[1]).add(target);

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (currentEvent.contains(p.getUniqueId()))
                        {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName() + ChatColor.AQUA + " has joined team \"" + args[1] + "\"");
                        }
                        if (EventLeaders.contains(p)){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f ") + target.getDisplayName() + ChatColor.AQUA + " has joined team \"" + args[1] + "\". " +  teams.get(args[1]).size() + " players are now in that team.");
                        }
                    }


                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Use /tc to talk to your team mates!");

                    return false;
                } else if (args[0].equalsIgnoreCase("respawndelay")) {

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }

                    if (!(teamRespawnPoint.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.RED + "Team " + args[1] + " must have a spawn point before delay can be set!");
                        return false;
                    }

                    int delay = StringToInt.String_To_Int_Convert(args[2]);
                    teamRespawnDelay.put(args[1], delay ); //GotFromFirstPlugin)
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +
                            ChatColor.RED  + "You've set the respawn delay to " + delay + " for team " + args[1]);
                } else if (args[0].equalsIgnoreCase("respawnlimit")){

                    if (args[1].equalsIgnoreCase("clear")) {
                        teamRespawnLimit.clear();
                        playerDeathCount.clear();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.AQUA + "Player death count and teams respawn limits have been cleared!");
                        return true;
                    }

                    if (!(teams.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Team " + args[1] + " does not exists!");
                        return false;
                    }

                    if (!(teamRespawnPoint.containsKey(args[1]))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f")
                                + ChatColor.RED + "Team " + args[1] + " must have a spawn point before delay can be set!");
                        return false;
                    }

                    int limit = StringToInt.String_To_Int_Convert(args[2]);//GotFromFirstPlugin)
                    teamRespawnLimit.put(args[1], limit);

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") +
                            ChatColor.RED  + "You've set the respawn limit to " + limit + " for team " + args[1]);



                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&4Server&e]&f") + ChatColor.RED + "Incorrect arguments! Type /teams for help!");
                }



            } else {
                sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
                sender.sendMessage(ChatColor.AQUA + "/teams create [Team Name] - Creates a team within event mode");
                sender.sendMessage(ChatColor.AQUA + "/teams delete [Team Name] - Removes all players from a team and deletes the team.");
                sender.sendMessage(ChatColor.AQUA + "/teams clear [Team Name] - Removes all players from a team.");
                sender.sendMessage(ChatColor.AQUA + "/teams add [Team Name] [Player] - Adds a player to given team.");
                sender.sendMessage(ChatColor.AQUA + "/teams remove - Removes a player from their team.");
                sender.sendMessage(ChatColor.AQUA + "/teams respawndelay [Team Name] [Delay] - Sets delay, in seconds, a team has before they respawn after death.");
                sender.sendMessage(ChatColor.AQUA + "/teams setspawn [Team Name] or [clearspawn] - Sets a team's respawn point, or clears it");
                sender.sendMessage(ChatColor.AQUA + "/tc - Private team chat");
                sender.sendMessage(ChatColor.AQUA + "/teams freeze [Team Name] - Freezes given team");
                sender.sendMessage(ChatColor.AQUA + "/teams respawnlimit [Team Name]/[Clear] [Limit] - Sets/clears the respawn limit for a given team.");
                sender.sendMessage(ChatColor.AQUA + "/teams list - Lists all the teams and all their players.");
                sender.sendMessage(ChatColor.AQUA + "/teams friendlyfire [Team Name] [Off/On]");
//                sender.sendMessage(ChatColor.AQUA + "/eventmode freeze [Playername] - Freezes/unfreezes given player");
//                sender.sendMessage(ChatColor.AQUA + "/eventmode lock - Locks/Unlocks the event");

            }
        }
        return false;
    }

    public static boolean playerWithinTeam(Player player){
        for (String key : teams.keySet()){
            if (teams.get(key).contains(player)) {
               return true;
           }
       }
       return false;
    }
    public static String getPlayerTeamName(Player player){
        for (String key : teams.keySet()){
            if (teams.get(key).contains(player)) {
                return key;
            }
        }
        return null;
    }
}