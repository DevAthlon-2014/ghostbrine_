package me.ghostbrine.devathlon;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInst() {
        return instance;
    }

    private EffectsManager effectsManager;
    private ProtocolManager protocolManager;

    @Override
    public void onLoad() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onEnable() {
        effectsManager = new EffectsManager();
    }

    public void spawnParticleAt(int id, Location loc) {
        
    }
}