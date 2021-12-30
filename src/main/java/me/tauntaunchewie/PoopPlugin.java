package me.tauntaunchewie;

import org.bukkit.plugin.java.JavaPlugin;

public class PoopPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Set up config file on server
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PoopListener(), this);
        this.getCommand("poop").setExecutor(new PoopCommander(this));
    }
    @Override
    public void onDisable() {
        //getLogger().info("onDisable is called!");
    }

}