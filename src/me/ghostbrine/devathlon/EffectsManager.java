package me.ghostbrine.devathlon;

import me.ghostbrine.devathlon.effects.WaterGun;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class EffectsManager {

    private Set<Effect> runningEffects;

    public void load() {
        runningEffects = new HashSet<>();
    }

    public void unload() {
        runningEffects = null;
    }

    public void tick() {
        for (Effect effect : runningEffects) {
            effect.tick();
        }
    }

    public void playerShoot(Player player, GunType type) {
        Effect effect;
        switch (type) {
            case WATER:
                effect = new WaterGun();
                break;
            case FIRE:
                effect = new FireGun();
                break;
        }
    }
}