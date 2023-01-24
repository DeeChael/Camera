package net.deechael.camera.api;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Camera {

    /**
     * Get all the nodes
     *
     * @return nodes
     */
    @NotNull
    List<Node> nodes();

    /**
     * Convert nodes to paths
     *
     * @return paths
     */
    @NotNull
    List<Path> paths();

    /**
     * Play the camera in the world for player
     *
     * @param plugin plugin
     * @param world  world
     * @param player player
     */
    void play(Plugin plugin, World world, Player player);

}
