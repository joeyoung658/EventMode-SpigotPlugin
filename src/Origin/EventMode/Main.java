package Origin.EventMode;


import Origin.EventMode.Commands.EventAdmin.EventMode.Commands.*;
import Origin.EventMode.Commands.EventAdmin.EventMode.Commands.helpCmd;
import Origin.EventMode.Commands.EventAdmin.EventMode.Commands.listCmd;
import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminCommandHandler;
import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminTabCompleter;
import Origin.EventMode.Commands.EventAdmin.EventMode.eventModeAdminCmd;
import Origin.EventMode.Commands.EventAdmin.Teams.Commands.*;
import Origin.EventMode.Commands.EventAdmin.Teams.TeamsCommandHandler;
import Origin.EventMode.Commands.EventAdmin.Teams.*;
import Origin.EventMode.Commands.EventPlayer.eventChat;
import Origin.EventMode.Commands.EventPlayer.eventjoin;
import Origin.EventMode.Commands.EventPlayer.eventleave;
import Origin.EventMode.Commands.EventTeams.teamChat;
import Origin.EventMode.Listeners.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by josep on 19/06/2017.
 */
public class Main extends JavaPlugin implements Listener {
   public static Main instance;


    @Override
    public void onEnable() {
        registerCommands();
        registerListners();

        instance = this;
        getLogger().info("EventMode has been enabled!");
    }

    private void registerCommands(){
        registerEventAdminCommands();
        registerEventTeamCommands();

        this.getCommand("eventjoin").setExecutor(new eventjoin());
        this.getCommand("eventleave").setExecutor(new eventleave());

        this.getCommand("tc").setExecutor(new teamChat());
        this.getCommand("ec").setExecutor(new eventChat());
    }


    public void registerEventAdminCommands(){
        EventModeAdminCommandHandler eventModeAdminCommands = new EventModeAdminCommandHandler();

        //Main
        eventModeAdminCommands.register("eventmode", new eventModeAdminCmd());

        //Sub
        eventModeAdminCommands.register("open", new openCmd());
        eventModeAdminCommands.register("close", new closeCmd());
        eventModeAdminCommands.register("heal", new healCmd());
        eventModeAdminCommands.register("tpall", new tpAllCmd());
        eventModeAdminCommands.register("clearallinven", new clearAllPlayersInven());
        eventModeAdminCommands.register("keepinven", new keepInvenCmd());
        eventModeAdminCommands.register("healthregen", new toggleHealthRegen());
        eventModeAdminCommands.register("list", new listCmd());
        eventModeAdminCommands.register("togglepvp", new togglePVPCmd());
        eventModeAdminCommands.register("freeze", new freezeAllPlayersCmd());
        eventModeAdminCommands.register("lock", new toggleEventLock());
        eventModeAdminCommands.register("add", new addPlayerToEventCmd());
        eventModeAdminCommands.register("remove", new removePlayerFromEventCmd());
        eventModeAdminCommands.register("setlobby", new setLobbyCmd());
        eventModeAdminCommands.register("blockbreak", new toggleBlockBreak());
        eventModeAdminCommands.register("stogglepvp", new togglePlayerPVP());
        eventModeAdminCommands.register("setrespawndelay", new setRespawnDelay());
        eventModeAdminCommands.register("addeventleader", new addEventLeader());
        eventModeAdminCommands.register("gm", new setEventGameMode());
        eventModeAdminCommands.register("help", new helpCmd());
        eventModeAdminCommands.register("giveitem", new givePlayersItem());
        eventModeAdminCommands.register("setspawn", new setEventSpawn());
        eventModeAdminCommands.register("blockcmd", new blockCmd());
        eventModeAdminCommands.register("unblockcmd", new unblockCmd());



        getCommand("eventmode").setExecutor(eventModeAdminCommands);
        getCommand("eventmode").setTabCompleter(new EventModeAdminTabCompleter());

    }

    public void registerEventTeamCommands(){


        //Main
        TeamsCommandHandler teamsCommandHandler = new TeamsCommandHandler();
        teamsCommandHandler.register("teams", new eventTeamsCmd());

        //Sub
        teamsCommandHandler.register("help", new Origin.EventMode.Commands.EventAdmin.Teams.Commands.helpCmd());
        teamsCommandHandler.register("create", new createTeamCmd());
        teamsCommandHandler.register("delete", new deleteTeamCmd());
        teamsCommandHandler.register("clear", new clearTeamCmd());
        teamsCommandHandler.register("add", new addPlayerToTeamCmd());
        teamsCommandHandler.register("remove", new removePlayerFromTeam());
        teamsCommandHandler.register("respawndelay", new respawnDelay());
        teamsCommandHandler.register("setspawn", new setTeamSpawnCmd());
        teamsCommandHandler.register("freeze", new freezeTeamCmd());
        teamsCommandHandler.register("respawnlimit", new respawnLimit());
        teamsCommandHandler.register("list", new Origin.EventMode.Commands.EventAdmin.Teams.Commands.listCmd());
        teamsCommandHandler.register("friendlyfire", new friendlyfireCmd());


        getCommand("teams").setExecutor(teamsCommandHandler);
        getCommand("teams").setTabCompleter(new TeamsTabCompleter());

    }


    private void registerListners(){
        //getServer().getPluginManager().registerEvents(new Freeze(), this);
        getServer().getPluginManager().registerEvents(new HealthRegen(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        //getServer().getPluginManager().registerEvents(new TeamSelect(), this);
        getServer().getPluginManager().registerEvents(new PVP(), this);
        getServer().getPluginManager().registerEvents(new FoodRegen(), this);
        getServer().getPluginManager().registerEvents(new CommandBlock(), this);
        getServer().getPluginManager().registerEvents(new DenyBlockBreak(), this);
    }
}
