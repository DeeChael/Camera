package net.deechael.camera.impl;

import net.deechael.camera.api.Node;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class NodeImpl implements Node {

    private final Vector vector;

    public NodeImpl(double x, double y, double z) {
        this.vector = new Vector(x, y, z);
    }

    @NotNull
    @Override
    public Vector position() {
        return this.vector;
    }

}
