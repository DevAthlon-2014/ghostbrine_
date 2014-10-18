package me.ghostbrine.devathlon.effects;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import me.ghostbrine.devathlon.Core;
import me.ghostbrine.devathlon.Gun;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class FireGun extends Gun {
    public FireGun(Player player) {
        super(player);
    }

    private static final double speed = 1.3d;

    private Location currLoc;
    private Vector direction;

    private Map<Location, Integer> firePoints = new HashMap<>();

    @Override
    public void spawn(Location loc, Vector direction) {
        currLoc = loc;
        this.direction = direction.normalize().multiply(speed);
    }

    @Override
    public void tick() {
        currLoc = currLoc.add(direction);
        for (int i = 0; i < 10; i++) {
            Core.getInst().spawnParticleAt(WrapperPlayServerWorldParticles.ParticleEffect.FLAME, currLoc, 0.01f, 3);
        }
        firePoints.put(currLoc, 300);

        for (Location loc : firePoints.keySet()) {
            int now = firePoints.get(loc);
            if (now == 0) continue;
            firePoints.put(loc, now - 1);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.equals(getPlayer())) checkPlayer(player, player.getLocation());
        }
    }

    @Override
    public int getMaxSteps() {
        return 50;
    }

    @Override
    public void checkPlayer(Player player, Location to) {
        if (player.equals(getPlayer())) return;
        for (Location loc : firePoints.keySet()) {
            if (loc.distance(to.add(0, 0.3, 0)) < 1) {
                player.setFireTicks(300);
            }
        }
    }
}
