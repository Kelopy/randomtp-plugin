package me.kelopy.randomtp;

import me.kelopy.randomtp.commands.randomtp;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTP extends JavaPlugin {

    private static RandomTP instance;

    @Override
    public void onEnable() {
        System.out.println("RandomTP is teleporting...");

        new RTPFuncs();
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("randomtp").setExecutor(new randomtp());
    }

    public static RandomTP getInstance(){
        return instance;
    }

}
