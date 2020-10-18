package Origin.EventMode;


import Origin.EventMode.Commands.EventAdmin.EventMode.Commands.*;
import Origin.EventMode.Commands.EventAdmin.EventMode.EventModeAdminCommandHandler;
import Origin.EventMode.Commands.EventAdmin.EventMode.apvpe;
import Origin.EventMode.Commands.EventAdmin.EventMode.pvpe;
import Origin.EventMode.Commands.EventPlayer.eventChat;
import Origin.EventMode.Commands.EventPlayer.eventjoin;
import Origin.EventMode.Commands.EventPlayer.eventleave;
import Origin.EventMode.Commands.EventTeams.teamChat;
import Origin.EventMode.Commands.EventAdmin.Teams.teams;
import Origin.EventMode.Listeners.*;
import Origin.EventMode.Commands.EventAdmin.EventMode.eventModeAdminCmd;

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

        //this.getCommand("pvpe").setExecutor(new pvpe());
        this.getCommand("apvpe").setExecutor(new apvpe());

        this.getCommand("eventjoin").setExecutor(new eventjoin());
        this.getCommand("eventleave").setExecutor(new eventleave());

        this.getCommand("teams").setExecutor(new teams());

        this.getCommand("tc").setExecutor(new teamChat());
        this.getCommand("ec").setExecutor(new eventChat());
    }


    public void registerEventAdminCommands(){
        EventModeAdminCommandHandler eventModeAdminCommands = new EventModeAdminCommandHandler();

        eventModeAdminCommands.register("eventmode", new eventModeAdminCmd());

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



        getCommand("eventmode").setExecutor(eventModeAdminCommands);
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

    }
}
