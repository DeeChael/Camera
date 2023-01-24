package net.deechael.camera;

import net.deechael.camera.api.Node;
import net.deechael.camera.api.builder.CameraBuilder;
import net.deechael.camera.impl.NodeImpl;
import net.deechael.camera.impl.builder.CameraBuilderImpl;

public final class CameraAPI {

    public static Node newNode(double x, double y, double z) {
        return new NodeImpl(x, y, z);
    }

    public static CameraBuilder newCamera(Node start) {
        return CameraBuilderImpl.of(start);
    }

    private CameraAPI() {
    }

}
