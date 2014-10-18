package me.ghostbrine.devathlon.effects;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import me.ghostbrine.devathlon.Core;
import me.ghostbrine.devathlon.Gun;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class WaterGun extends Gun {

    public WaterGun(Player player) {
        super(player);
    }

    private static final double speed = 0.3d;

    private Map<Location, Vector> dirs = new HashMap<>();

    @Override
    public void spawn(Location loc, Vector direction) {
        for (int i = 0; i < 10; i++) {

        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < 4; i++) {
            currLoc = currLoc.add(direction);
            Core.getInst().spawnParticleAt(WrapperPlayServerWorldParticles.ParticleEffect.DRIP_WATER, currLoc, 1, 1);
        }
    }

    @Override
    public int getMaxSteps() {
        return 100;
    }
}