package me.kelopy.randomtp.commands;

import me.kelopy.randomtp.RTPFuncs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class randomtp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player){
            if (args.length == 0){

                Location randomLocation = RTPFuncs.generateLocation(player);
                player.teleport(randomLocation);

                player.sendMessage(ChatColor.GRAY + "Teleported you to: " + ChatColor.AQUA + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());

            }else if(args.length == 1){
                if (player.hasPermission("randomtp.others")){
                    Player target = Bukkit.getPlayer(args[0]);

                    Location randomLocation = RTPFuncs.generateLocation(target);
                    target.teleport(randomLocation);

                    target.sendMessage(ChatColor.RED + player.getDisplayName() + ChatColor.GOLD + " just rtp'd you.");
                    target.sendMessage(ChatColor.GRAY + "Teleported you to: " + ChatColor.AQUA + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());

                    player.sendMessage(ChatColor.GRAY + "Player successfully teleported to: " + ChatColor.AQUA + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());
                }else{
                    player.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
                }
            }
        }else {
            System.out.println("You need to be a player to execute this command.");
        }

        return true;
    }
}
