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

    private Map<Vector, Location> dirs = new HashMap<>();

    @Override
    public void spawn(Location loc, Vector direction) {
        for (int i = 0; i < 10; i++) {
            Vector x = direction.clone().add(new Vector(0.5 * Core.rand.nextDouble() - 0.25, 0.5 * Core.rand.nextDouble() - 0.25, 0.5 * Core.rand.nextDouble() - 0.25));
            dirs.put(x.normalize().multiply(speed), loc.clone());
        }
    }

    @Override
    public void tick() {
        for (Map.Entry<Vector, Location> e : dirs.entrySet()) {
            Location loc = e.getValue();
            for (int i = 0; i < 4; i++) {
                loc = loc.add(e.getKey());
                Core.getInst().spawnParticleAt(WrapperPlayServerWorldParticles.ParticleEffect.DRIP_WATER, loc, 1, 1);
            }
        }
    }

    @Override
    public int getMaxSteps() {
        return 100;
    }

    @Override
    public void checkPlayer(Player player, Location to) {

    }
}