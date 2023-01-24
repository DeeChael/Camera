package net.deechael.camera;

import org.bukkit.GameMode;
import org.bukkit.Location;

import java.util.UUID;

public final class PlayerData {

    private final UUID player;
    private final GameMode lastGameMode;
    private final Location lastLocation;

    public PlayerData(UUID player, GameMode lastGameMode, Location lastLocation) {
        this.player = player;
        this.lastGameMode = lastGameMode;
        this.lastLocation = lastLocation;
    }

    public UUID getPlayer() {
        return player;
    }

    public GameMode getLastGameMode() {
        return lastGameMode;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

}
