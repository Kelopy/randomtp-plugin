package me.kelopy.randomtp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class RTPFuncs {

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static{
        bad_blocks.add(Material.CACTUS);
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
    }

    public static Location generateLocation(Player p){
        Random random = new Random();

        int x = 0;
        int y = 0;
        int z = 0;

        if(RandomTP.getInstance().getConfig().getBoolean("world-border")){
            x = random.nextInt(RandomTP.getInstance().getConfig().getInt("border"));
            z = random.nextInt(RandomTP.getInstance().getConfig().getInt("border"));
        }else if(!RandomTP.getInstance().getConfig().getBoolean("world-border")){
            x = random.nextInt(25000);
            z = random.nextInt(25000);
        }

        Location randomLocation = new Location(p.getWorld(), x,y,z);

        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        while(!isLocationSafe(randomLocation)){
            randomLocation = generateLocation(p);
        }

        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x,y,z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(bad_blocks.contains(below.getType()) || (above.getType().isSolid()) ||(block.getType().isSolid()));

    }

}
