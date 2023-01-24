package net.deechael.camera.api;

import net.deechael.camera.CameraAPI;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Node {

    /**
     * The position of the node
     *
     * @return position
     */
    @NotNull
    Vector position();

    /**
     * Create new node
     *
     * @param x x
     * @param y y
     * @param z z
     * @return new node
     */
    static Node of(double x, double y, double z) {
        return CameraAPI.newNode(x, y, z);
    }

}
