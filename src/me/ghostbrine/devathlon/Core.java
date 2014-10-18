package me.ghostbrine.devathlon;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.ghostbrine.devathlon.listen.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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

        getServer().getPluginManager().registerEvents(new GameListener(), this);

        effectsManager.load();
    }

    public void spawnParticleAt(WrapperPlayServerWorldParticles.ParticleEffect effect, Location loc, float speed, int count) {
        WrapperPlayServerWorldParticles packet = new WrapperPlayServerWorldParticles();
        packet.setLocation(loc);
        packet.setNumberOfParticles(count);
        packet.setOffsetX(0);
        packet.setOffsetY(0);
        packet.setOffsetZ(0);
        packet.setParticleSpeed(speed);
        packet.setParticleEffect(effect);
        for (Player player : Bukkit.getOnlinePlayers()) {
            packet.sendPacket(player);
        }
    }


    public EffectsManager getEffectsManager() {
        return effectsManager;
    }
}