package me.tauntaunchewie;

import me.tauntaunchewie.utils.PoopUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class PoopCommander implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Logger logger = Logger.getLogger("Minecraft");

        // Make sure a player is calling this
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if user has permission to execute this
            if (player.hasPermission("poopplugin.set")) {
                // Make sure we have exactly 2 args
                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("on")) {
                        // Turn shif pooping on
                        PoopUtils.setPoopOn(true);
                        player.sendMessage("Pooping turned ON");
                    }
                    else if (args[0].equalsIgnoreCase("off")) {
                        // Turn shift pooping off
                        PoopUtils.setPoopOn(false);
                        player.sendMessage("Pooping turned OFF");
                    }
                    else if (args[0].equalsIgnoreCase("status")) {
                        player.sendMessage("Pooping status is: " + (PoopUtils.poopOn() ? "ON" : "OFF"));
                    }
                    else {
                        player.sendMessage("Usage: /poop (on or off)");
                        player.sendMessage("Example: /poop on");
                    }
                }
                else if (args.length >= 2) {
                    // Rename Poop entities
                    if (args[0].equalsIgnoreCase("rename")) {
                        String finalName = "";
                        for (int i=1; i<args.length; i++) {
                            finalName = finalName + args[i] + " ";
                        }

                        // Strip last character
                        PoopUtils.setPoopName(finalName.replaceAll(".$", ""));
                    }
                    else {
                        player.sendMessage("Usage: /poop rename NEWNAME");
                        player.sendMessage("Example: /poop rename BearNugs");
                    }
                }
                else {
                    player.sendMessage("Usage: /poop (on or off or status)");
                    player.sendMessage("Example: /poop on");
                    player.sendMessage("Usage: /poop rename NEWNAME");
                    player.sendMessage("Example: /poop rename BearNugs");
                }
            }
            else {
                player.sendMessage("You do not have permission to execute this command");
            }

            return true;
        }
        else {
            // Only allow players to invoke
            return false;
        }
    }

    public PoopCommander(PoopPlugin plugin) {
        PoopUtils.loadDefaults(plugin);
    }
}