package me.ghostbrine.devathlon;

import me.ghostbrine.devathlon.effects.FireGun;
import me.ghostbrine.devathlon.effects.WaterGun;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class EffectsManager {

    private int task;
    private Set<Effect> runningEffects;

    public void load() {
        runningEffects = new HashSet<>();
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInst(), new Runnable() {
            @Override
            public void run() {
                tick();
            }
        }, 1, 1);
    }

    public void unload() {
        runningEffects = null;
        Bukkit.getScheduler().cancelTask(task);
    }

    public void tick() {
        for (Effect effect : runningEffects) {
            effect.tick();
        }
    }

    public void playerShoot(Player player, GunType type) {
        Effect effect = null;
        switch (type) {
            case WATER:
                effect = new WaterGun(player);
                break;
            case FIRE:
                effect = new FireGun(player);
                break;
        }
        effect.spawn(player.getEyeLocation(), player.getEyeLocation().getDirection());
    }
}