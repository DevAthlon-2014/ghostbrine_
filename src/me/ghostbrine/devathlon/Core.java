package me.ghostbrine.devathlon;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInst() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }
}