package net.deechael.camera.impl.builder;

import net.deechael.camera.api.Node;
import net.deechael.camera.api.Path;
import net.deechael.camera.api.builder.CameraBuilder;
import net.deechael.camera.api.builder.PathBuilder;
import net.deechael.camera.impl.PathImpl;
import org.bukkit.util.Vector;

public class PathBuilderImpl implements PathBuilder {

    private final CameraBuilderImpl builder;
    private final Node from;
    private final Node to;
    private Vector looking;
    private boolean reverse;
    private double time;
    private double axis;

    PathBuilderImpl(CameraBuilderImpl builder, Node from, Node to) {
        this.builder = builder;
        this.from = from;
        this.to = to;
    }

    @Override
    public PathBuilder looking(Vector vector, boolean reverse) {
        this.looking = vector;
        this.reverse = reverse;
        return this;
    }

    @Override
    public PathBuilder time(double seconds) {
        this.time = seconds;
        return this;
    }

    @Override
    public PathBuilder axis(double length) {
        this.axis = length;
        return this;
    }

    @Override
    public CameraBuilder done() {
        if (this.time < 0)
            throw new RuntimeException("Time cannot be lower than 0");
        this.builder.complete(this);
        return this.builder;
    }

    public Path build() {
        return new PathImpl(this.from, this.to, this.looking, this.reverse, this.time, this.axis);
    }

}
