package me.ghostbrine.devathlon;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import me.ghostbrine.devathlon.listen.GameListener;
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

        getServer().getPluginManager().registerEvents(new GameListener(), this);

        effectsManager.load();
    }

    public void spawnParticleAt(org.bukkit.Effect effect, int data, Location loc, float speed, int count) {
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getStrings()
                .write(0, effect.getName());
        packet.getFloat()
                .write(0, (float) loc.getX())
                .write(1, (float) loc.getY())
                .write(2, (float) loc.getZ())
                .write(3, 0F)
                .write(4, 0F)
                .write(5, 0F)
                .write(7, speed);
        packet.getIntegers()
                .write(0, count);
        protocolManager.broadcastServerPacket(packet);
    }



    public EffectsManager getEffectsManager() {
        return effectsManager;
    }
}