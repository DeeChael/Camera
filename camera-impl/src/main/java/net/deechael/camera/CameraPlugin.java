package net.deechael.camera;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CameraPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(CameraPlayer.INSTANCE, this);
    }

}
