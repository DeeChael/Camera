package net.deechael.camera.api;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Path {

    /**
     * Get the start point of the path
     *
     * @return point
     */
    @NotNull
    Node from();

    /**
     * Get the end point of the path
     *
     * @return point
     */
    @NotNull
    Node to();

    /**
     * Get the point looking at
     *
     * @return point
     */
    @NotNull
    Vector looking();

    /**
     * If reversed looking point
     *
     * @return status
     */
    boolean reverseLooking();

    /**
     * Get how many seconds taken to play this path
     *
     * @return seconds
     */
    double time();

    /**
     * Get the length of axis, 0 means this is a straight line
     *
     * @return length of axis
     */
    double axis();

}
