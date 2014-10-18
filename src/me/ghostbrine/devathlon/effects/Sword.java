package me.ghostbrine.devathlon.effects;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import me.ghostbrine.devathlon.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Sword {

    public static void spawnPlayer(Player player) {
        for (PotionEffect peffect : player.getActivePotionEffects()) {
            if (peffect.getType() == PotionEffectType.WITHER) return;
        }

        Vector dir = player.getEyeLocation().getDirection();
        Location loc = player.getEyeLocation().clone().add(0, -0.6, 0).add(dir.normalize().multiply(0.3));
        dir.normalize().multiply(0.05);

        Vector hand = new Vector();
        hand.setX(dir.getZ() > 0 ? -dir.getZ() : dir.getZ());
        hand.setZ(dir.getX() > 0 ? dir.getX() : -dir.getX());
        loc.add(hand.normalize().multiply(0.6));

        for (int i = 0; i < 80; i++) {
            loc = loc.add(dir);
            Core.getInst().spawnParticleAt(WrapperPlayServerWorldParticles.ParticleEffect.RED_DUST, loc, 0, 1);
            if (i % 5 == 0) {
                checkNear(loc, player);
            }
        }
    }

    private static void checkNear(Location loc, Player nope) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.equals(nope)) continue;
            if (player.getEyeLocation().distance(loc) < 0.3) {
                player.damage(20, nope);
            } else if (player.getLocation().distance(loc) < 0.8) {
                player.damage(10, nope);
            }
        }
    }
}