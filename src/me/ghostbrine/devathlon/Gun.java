package me.ghostbrine.devathlon;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class Gun {

    private final Player player;

    private int steps;

    public Gun(Player player) {
        this.player = player;
    }

    public abstract void spawn(Location loc, Vector direction);
    public abstract void tick();
    public abstract int getMaxSteps();
    public abstract void checkPlayer(Player player, Location to);

    public boolean step() {
        steps++;
        if (getMaxSteps() < steps) return true;
        tick();
        return false;
    }

    public Player getPlayer() {
        return player;
    }
}