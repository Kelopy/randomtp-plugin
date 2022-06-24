package me.kelopy.randomtp;

import me.kelopy.randomtp.commands.randomtp;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("RandomTP is teleporting...");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        RTPFuncs funcs = new RTPFuncs(this);
        getCommand("randomtp").setExecutor(new randomtp());
    }

}
