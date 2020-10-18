package Origin.EventMode;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by josep on 19/06/2017.
 */
public class Contants {

    public static ArrayList<UUID> currentEvent = new ArrayList<>();
    public static Location eventlocation = null;
    public static Boolean healthregen = false;
    public static Boolean eventopen = false;
    public static ArrayList<Player> EventJoinWarning = new ArrayList<>();
    public static ArrayList<Player> EventLeaders = new ArrayList<>();
    public static Boolean eventlocked = false;
    public static int eventRespawnDelay = 0;
    public static Map<String, ArrayList<Player>> teams = new HashMap<>();
    public static Map<String, Location> teamRespawnPoint = new HashMap<>();
    public static Map<String, Integer> teamRespawnDelay = new HashMap<>();
    public static Map<String, Integer> teamRespawnLimit = new HashMap<>();
    public static Map<Player, Integer> playerDeathCount = new HashMap<>();
    public static Location LobbyLocation = null;

    public static Boolean KeepInvenToggledEM = false;
    public static Boolean PVPToggledEM = false;

    public static List<Player> KeepInvenBeforeEvent = new ArrayList<>();

    public static ArrayList<String> friendlyfire = new ArrayList<>();

    public static GameMode eventGamemode = GameMode.SURVIVAL;


}