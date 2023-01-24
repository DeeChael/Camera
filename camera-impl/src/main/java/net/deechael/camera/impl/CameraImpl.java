package net.deechael.camera.impl;

import net.deechael.camera.CameraPlayer;
import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Node;
import net.deechael.camera.api.Path;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CameraImpl implements Camera {

    private final List<Node> nodes = new ArrayList<>();
    private final List<Path> paths = new ArrayList<>();

    public CameraImpl(List<Node> nodes, List<Path> paths) {
        this.nodes.addAll(nodes);
        this.paths.addAll(paths);
    }

    @NotNull
    @Override
    public List<Node> nodes() {
        return new ArrayList<>(nodes);
    }

    @NotNull
    @Override
    public List<Path> paths() {
        return new ArrayList<>(paths);
    }

    @Override
    public void play(Plugin plugin, World world, Player player) {
        CameraPlayer.play(plugin, player, world, this);
    }

}
