/*
 * This file is part of Movecraft.
 *
 *     Movecraft is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Movecraft is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Movecraft.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.countercraft.movecraft.mapUpdater.update;

import net.countercraft.movecraft.utils.MovecraftLocation;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores the data about a single blocks changes to the map in an unspecified world. The world is retrieved contextually from the submitting craft.
 */
public class EntityUpdateCommand implements UpdateCommand{
    private final Location newLocation;
    private final Entity entity;

    public EntityUpdateCommand(Location newLocation, Entity entity) {
        this.newLocation = newLocation;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    @Override
    public void doUpdate() {
        //TODO: Fix this
        MovecraftLocation entityLoc = new MovecraftLocation(i.getNewLocation().getBlockX(), i.getNewLocation().getBlockY() - 1, i.getNewLocation().getBlockZ());
        if (!entityMap.containsKey(entityLoc)) {
            List<EntityUpdateCommand> entUpdateList = new ArrayList<>();
            entUpdateList.add(i);
            entityMap.put(entityLoc, entUpdateList);
        } else {
            List<EntityUpdateCommand> entUpdateList = entityMap.get(entityLoc);
            entUpdateList.add(i);
        }
    }
}

