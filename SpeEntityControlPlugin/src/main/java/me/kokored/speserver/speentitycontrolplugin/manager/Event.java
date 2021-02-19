package me.kokored.speserver.speentitycontrolplugin.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import me.kokored.speserver.speentitycontrolplugin.SpeEntityControlPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Event implements Listener {

    Plugin plugin = SpeEntityControlPlugin.getPlugin(SpeEntityControlPlugin.class);

    List<Material> spawnEggList = new ArrayList<>();

    public Event() {

        Bukkit.getPluginManager().registerEvents(this, plugin);

        spawnEggList.add(Material.BAT_SPAWN_EGG);
        spawnEggList.add(Material.BEE_SPAWN_EGG);
        spawnEggList.add(Material.BLAZE_SPAWN_EGG);

    }

    @EventHandler
    public void onPlayerUseSpawnEgg(PlayerInteractEvent event) {

        Action clickAction = event.getAction();

        if (!(clickAction.equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }

    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {

        EntityType entityType = event.getEntityType();

        plugin.getLogger().log(Level.WARNING, String.valueOf(event.getLocation().getChunk().getEntities().length));

        if (event.getLocation().getChunk().getEntities().length > 200) {

            event.getEntity().remove();

            plugin.getLogger().info("remove");

        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        plugin.getLogger().log(Level.WARNING, event.getEntity().getLocation().getChunk().getEntities().length + "");

        if (event.getEntity().getLocation().getChunk().getEntities().length > 300) {

            for (Entity entity : event.getEntity().getLocation().getChunk().getEntities()) {

                Location location = entity.getLocation();

                if (entity.getType().equals(EntityType.PLAYER)) {

                    entity.teleport(new Location(entity.getWorld(), 0, 0, 0));

                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {

                            entity.remove();

                        }
                    }, 5);

                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {

                            entity.teleport(location);

                        }
                    }, 6);

                }

            }

        }

    }

}
