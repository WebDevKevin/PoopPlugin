package me.tauntaunchewie.utils;

import me.tauntaunchewie.PoopPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PoopUtils {
    private static PoopPlugin plugin;
    private static boolean shiftPoopOn = false;
    private static String poopName = "poop";

    /**
     * Sets whether the plugin is on or off
     * @param onOff
     */
    public static void setPoopOn(boolean onOff) {
        shiftPoopOn = onOff;

        plugin.getConfig().set("enabled", onOff);
        plugin.saveConfig();
    }

    /**
     * Set the poop name and save to config
     * @param newName
     */
    public static void setPoopName(String newName) {
        poopName = newName;

        plugin.getConfig().set("poopName", newName);
        plugin.saveConfig();
    }

    /**
     * Retrieve the configured name of the poop
     * @return
     */
    public static String getPoopName() {
        return poopName;
    }

    /**
     * Return whether or not the plugin is on
     * @return
     */
    public static boolean poopOn() {
        return shiftPoopOn;
    }

    /**
     * Take poop from main hand and throw it
     *
     * @param player
     */
    public static void throwPoop(Player player) {
        // Throw poop
        ItemStack poopItem = player.getInventory().getItemInMainHand();

        // Create a stack and add 1 item to it
        ItemStack throwStack = new ItemStack(poopItem);
        throwStack.setAmount(1);

        // Count amount of poop in inventory main hand
        int amt = poopItem.getAmount();

        // Get player current location so we know where to start the throw
        Location pLoc = player.getEyeLocation();

        // Drop (aka throw) poop
        Item thrownPoopItem = player.getWorld().dropItem(pLoc, throwStack);

        // Set velocity so it moves
        thrownPoopItem.setVelocity(pLoc.getDirection());

        // Decrement count of items in main hand inventory
        poopItem.setAmount(amt - 1);

        // Repopulate main hand inventory with one less
        player.getInventory().setItemInMainHand(poopItem);
    }

    /**
     * Grab defaults from config and set locally
     * @param instance
     */
    public static void loadDefaults(PoopPlugin instance) {
        plugin = instance;
        shiftPoopOn = plugin.getConfig().getBoolean("enabled");
        poopName = plugin.getConfig().getString("poopName");
    }
}