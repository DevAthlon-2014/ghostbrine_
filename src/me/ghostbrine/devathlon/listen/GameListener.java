package me.ghostbrine.devathlon.listen;

import me.ghostbrine.devathlon.Core;
import me.ghostbrine.devathlon.GunType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class GameListener implements Listener {
    @EventHandler
    public void onShoot(PlayerInteractEvent event) {
        if (event.hasItem() && event.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = event.getItem();
            if (item.getType() == Material.STATIONARY_WATER) {
                Core.getInst().getEffectsManager().playerShoot(event.getPlayer(), GunType.WATER);
            } else if (item.getType() == Material.STATIONARY_LAVA) {
                Core.getInst().getEffectsManager().playerShoot(event.getPlayer(), GunType.FIRE);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(0, new ItemStack(Material.STATIONARY_LAVA));
        event.getPlayer().getInventory().setItem(1, new ItemStack(Material.STATIONARY_WATER));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Core.getInst().getEffectsManager().checkPlayer(event.getPlayer(), event.getTo());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}
