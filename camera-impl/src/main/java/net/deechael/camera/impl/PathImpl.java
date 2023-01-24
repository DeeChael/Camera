package net.deechael.camera.impl;

import net.deechael.camera.api.Node;
import net.deechael.camera.api.Path;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class PathImpl implements Path {

    private final Node from;
    private final Node to;
    private final Vector looking;
    private final boolean reverse;
    private final double time;
    private final double axis;

    public PathImpl(Node from, Node to, Vector looking, boolean reverse, double time, double axis) {
        this.from = from;
        this.to = to;
        this.looking = looking;
        this.reverse = reverse;
        this.time = time;
        this.axis = axis;
    }

    @NotNull
    @Override
    public Node from() {
        return this.from;
    }

    @NotNull
    @Override
    public Node to() {
        return this.to;
    }

    @NotNull
    @Override
    public Vector looking() {
        return this.looking;
    }

    @Override
    public boolean reverseLooking() {
        return this.reverse;
    }

    @Override
    public double time() {
        return this.time;
    }

    @Override
    public double axis() {
        return this.axis;
    }

}
