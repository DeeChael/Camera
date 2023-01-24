package net.deechael.camera.util;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class S {

    public static BukkitTask run(Plugin plugin, boolean async, BukkitRunnable runnable) {
        return async ? runnable.runTaskAsynchronously(plugin) : runnable.runTask(plugin);
    }

    private S() {
    }

}
