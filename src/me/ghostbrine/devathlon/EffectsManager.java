package me.ghostbrine.devathlon;

import me.ghostbrine.devathlon.effects.FireGun;
import me.ghostbrine.devathlon.effects.WaterGun;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class EffectsManager {

    private int task;
    private Set<Gun> runningGuns;

    public void load() {
        runningGuns = new HashSet<>();
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInst(), new Runnable() {
            @Override
            public void run() {
                tick();
            }
        }, 1, 1);
    }

    public void unload() {
        runningGuns = null;
        Bukkit.getScheduler().cancelTask(task);
    }

    public void tick() {
        Set<Gun> done = new HashSet<>();
        for (Gun gun : runningGuns) {
            if (gun.step()) {
                done.add(gun);
            }
        }
        runningGuns.removeAll(done);
    }

    public void playerShoot(Player player, GunType type) {
        Gun gun = null;
        switch (type) {
            case WATER:
                gun = new WaterGun(player);
                break;
            case FIRE:
                gun = new FireGun(player);
                break;
        }
        gun.spawn(player.getEyeLocation(), player.getEyeLocation().getDirection());
        runningGuns.add(gun);
    }

    public void checkPlayer(Player player, Location to) {
        for (Gun gun : runningGuns) {
            gun.checkPlayer(player, to);
        }
    }
}