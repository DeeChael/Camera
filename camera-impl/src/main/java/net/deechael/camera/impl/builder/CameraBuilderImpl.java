package net.deechael.camera.impl.builder;

import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Node;
import net.deechael.camera.api.Path;
import net.deechael.camera.api.builder.CameraBuilder;
import net.deechael.camera.api.builder.PathBuilder;
import net.deechael.camera.impl.CameraImpl;

import java.util.ArrayList;
import java.util.List;

public class CameraBuilderImpl implements CameraBuilder {

    private final List<Node> nodes = new ArrayList<>();
    private final List<Path> paths = new ArrayList<>();

    private Node lastNode;

    private CameraBuilderImpl(Node node) {
        this.nodes.add(node);
        this.lastNode = node;
    }

    @Override
    public PathBuilder next(Node node) {
        return new PathBuilderImpl(this, this.lastNode, node);
    }

    @Override
    public Camera build() {
        return new CameraImpl(nodes, paths);
    }

    public void complete(PathBuilderImpl builder) {
        Path path = builder.build();
        this.paths.add(path);
        this.nodes.add(path.to());
        this.lastNode = path.to();
    }

    public static CameraBuilder of(Node start) {
        return new CameraBuilderImpl(start);
    }

}
