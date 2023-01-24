package net.deechael.camera.util;

import org.bukkit.util.Vector;

import java.util.Objects;

public final class VectorUtil {

    public static Vector getVertical(Vector from, Vector to, Vector outside) {
        {
            Vector first = point(from, to);
            Vector second = point(outside, to);
            second.multiply(first.length() / second.length());
        }
        if (Objects.equals(point(from, to), point(outside, to)) || Objects.equals(point(to, from), point(outside, to))) {
            Vector point = point(from, to);
            return new Vector(point.getZ(), 0, point.getX());
        }
        double k = -((from.getX() - outside.getX()) * (to.getX() - from.getX())
                + (from.getY() - outside.getY()) * (to.getY() - from.getY())
                + (from.getZ() - outside.getZ()) * (to.getZ() - from.getZ()))
                / ((from.getX() - to.getX()) * (from.getX() - to.getX())
                + (from.getY() - to.getY()) * (from.getY() - to.getY())
                + (from.getZ() - to.getZ()) * (from.getZ() - to.getZ()));
        double xf = k * (to.getX() - from.getX()) + from.getX();
        double yf = k * (to.getY() - from.getY()) + from.getY();
        double zf = k * (to.getZ() - from.getZ()) + from.getZ();
        return new Vector(xf, yf, zf);
    }

    public static Vector reverse(Vector vector) {
        return new Vector(-vector.getX(), -vector.getY(), -vector.getZ());
    }

    public static Vector point(Vector from, Vector to) {
        return new Vector(to.getX() - from.getX(), to.getY() - from.getY(), to.getZ() - from.getZ());
    }

    public static Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisZ(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos - v.getY() * sin;
        y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    public static Vector rotateAroundAxisX(Vector v, double cos, double sin) {
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double cos, double sin) {
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisZ(Vector v, double cos, double sin) {
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    private VectorUtil() {
    }

}
