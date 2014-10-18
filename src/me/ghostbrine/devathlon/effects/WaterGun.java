package me.ghostbrine.devathlon.effects;

import me.ghostbrine.devathlon.Core;
import me.ghostbrine.devathlon.Gun;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WaterGun extends Gun {

    public WaterGun(Player player) {
        super(player);
    }

    private static final double speed = 0.5d;

    private Location currLoc;
    private Vector direction;

    @Override
    public void spawn(Location loc, Vector direction) {
        currLoc = loc;
        this.direction = direction.normalize().multiply(speed);

    }

    @Override
    public void tick() {
        currLoc = currLoc.add(direction);
        Core.getInst().spawnParticleAt(6 /* SPLASH */, currLoc, 1, 0);
    }
}