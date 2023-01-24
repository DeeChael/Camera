package net.deechael.camera.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class LocationUtil {

    public static float[] calculateYawAndPitch(Location from, Location looking) {
        Location location = from.clone().setDirection(LocationUtil.toVector(from, looking));
        return new float[]{location.getYaw(), location.getPitch()};
    }

    public static float[] calculateReversedYawAndPitch(Location from, Location looking) {
        Location location = from.clone().setDirection(VectorUtil.reverse(LocationUtil.toVector(from, looking)));
        return new float[]{location.getYaw(), location.getPitch()};
    }

    public static Vector toVector(Location from, Location to) {
        return new Vector(to.x() - from.x(), to.y() - from.y(), to.z() - from.z());
    }

    private LocationUtil() {
    }

}
