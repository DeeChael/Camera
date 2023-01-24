package net.deechael.camera.api.builder;

import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Node;

public interface CameraBuilder {

    /**
     * Add next node
     *
     * @param node new node
     * @return this
     */
    PathBuilder next(Node node);

    /**
     * Build camera object
     * @return camera
     */
    Camera build();

}
