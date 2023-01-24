package net.deechael.camera.api.builder;

import org.bukkit.util.Vector;

public interface PathBuilder {

    /**
     * Set where to look when playing this path
     * @param vector position
     * @param reverse should reverse looking direction
     * @return builder
     */
    PathBuilder looking(Vector vector, boolean reverse);

    /**
     * Set how many seconds taken to play this path
     * @param seconds seconds
     * @return builder
     */
    PathBuilder time(double seconds);

    /**
     * Set the length of axis, 0 for straight line
     * @param length length of axis
     * @return builder
     */
    PathBuilder axis(double length);

    /**
     * Return to camera builder
     * @return camera builder
     */
    CameraBuilder done();

}
