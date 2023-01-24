package net.deechael.camera;

import net.deechael.camera.api.Node;
import net.deechael.camera.api.builder.CameraBuilder;

public final class CameraAPI {

    /**
     * Create new node
     *
     * @param x x
     * @param y y
     * @param z z
     * @return new node
     */
    public static Node newNode(double x, double y, double z) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Create new camera
     *
     * @param start start point
     * @return new camera builder
     */
    public static CameraBuilder newCamera(Node start) {
        throw new RuntimeException("Not implemented");
    }

    private CameraAPI() {
    }

}
