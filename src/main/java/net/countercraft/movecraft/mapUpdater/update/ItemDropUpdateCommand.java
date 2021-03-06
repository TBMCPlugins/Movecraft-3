/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.countercraft.movecraft.mapUpdater.update;

import net.countercraft.movecraft.Movecraft;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Class that stores the data about a item drops to the map in an unspecified world. The world is retrieved contextually from the submitting craft.
 */
public class ItemDropUpdateCommand implements UpdateCommand{
    private final Location location;
    private final ItemStack itemStack;

    public ItemDropUpdateCommand(Location location, ItemStack itemStack) {
        this.location = location;
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void doUpdate() {
        if (itemStack != null) {
            final World world = location.getWorld();
            final Location loc = this.location;
            final ItemStack stack = itemStack;
            // drop Item
            new BukkitRunnable() {
                @Override
                public void run() {
                    world.dropItemNaturally(loc, stack);
                }
            }.runTaskLater(Movecraft.getInstance(), 20);
        }
    }

}
