package me.tauntaunchewie;

import me.tauntaunchewie.utils.PoopUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class PoopListener implements Listener {
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (!player.isSneaking() && PoopUtils.poopOn()) {
            // Create Poop
            ItemStack is = new ItemStack(Material.BROWN_DYE);
            ItemMeta newMetaName = is.getItemMeta();
            newMetaName.setDisplayName(PoopUtils.getPoopName());
            is.setItemMeta(newMetaName);

            // Place item behind player
            Location spawnSpot = player.getLocation().add(player.getLocation().getDirection().multiply(-2.5));
            player.getWorld().dropItem(spawnSpot, is);

            // Force player to jump a little - it was an explosive poop
            player.setVelocity(new Vector(0, 0.4, 0).multiply(1D));

            // Create mini explosion
            World w = player.getWorld();
            w.createExplosion(player.getLocation(), 0);
        }
    }

    @EventHandler
    public void throwPoop(PlayerInteractEvent event) {
        // If player is right clicking, evaluate object in main hand and throw if possible
        if ( (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && PoopUtils.poopOn()) {
            Player player = event.getPlayer();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            // Only throw if this is Brown Dye
            if (itemInHand.getType() == Material.BROWN_DYE) {
                PoopUtils.throwPoop(player);
            }
        }
    }
}