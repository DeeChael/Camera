package net.deechael.camera;

import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
import it.unimi.dsi.fastutil.Pair;
import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Path;
import net.deechael.camera.util.LocationUtil;
import net.deechael.camera.util.S;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public final class CameraPlayer implements Listener {

    public final static CameraPlayer INSTANCE = new CameraPlayer();

    private final static Map<UUID, PlayerData> PLAYING = Collections.synchronizedMap(new HashMap<>());
    private final static Map<UUID, Pair<ArmorStand, BukkitTask>> TASKS = Collections.synchronizedMap(new HashMap<>());

    private static void clean(Player player) {
        if (!PLAYING.containsKey(player.getUniqueId()))
            return;
        if (!TASKS.containsKey(player.getUniqueId()))
            return;
        PlayerData data = PLAYING.get(player.getUniqueId());
        Pair<ArmorStand, BukkitTask> pair = TASKS.get(player.getUniqueId());
        pair.right().cancel();
        pair.left().remove();
        player.setGameMode(data.getLastGameMode());
        player.teleport(data.getLastLocation());
        PLAYING.remove(player.getUniqueId());
        TASKS.remove(player.getUniqueId());
    }

    public static void play(Plugin plugin, Player player, World world, Camera camera) {
        List<Path> paths = camera.paths();
        if (paths.isEmpty())
            return;
        synchronized (PLAYING) {
            if (PLAYING.containsKey(player.getUniqueId()))
                clean(player);
        }
        S.run(plugin, true, new BukkitRunnable() {
            @Override
            public void run() {
                synchronized (PLAYING) {
                    if (!PLAYING.containsKey(player.getUniqueId()))
                        PLAYING.put(player.getUniqueId(), new PlayerData(player.getUniqueId(), player.getGameMode(), player.getLocation().clone()));
                }
                if (!player.isOnline())
                    cancel();
                S.run(plugin, false, new BukkitRunnable() {
                    @Override
                    public void run() {
                        CompletableFuture<Void> v = new CompletableFuture<>();
                        playPath(v, null, plugin, player, world, paths, 0);
                        S.run(plugin, true, new BukkitRunnable() {
                            @Override
                            public void run() {
                                try {
                                    v.get();
                                    S.run(plugin, false, new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            clean(player);
                                        }
                                    });
                                } catch (InterruptedException | ExecutionException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private static void playPath(CompletableFuture<Void> future, ArmorStand armorStand, Plugin plugin, Player player, World world, List<Path> paths, int index) {
        if (index < 0)
            return;
        if (index >= paths.size())
            return;
        Path path = paths.get(index);

        Location from = path.from().position().toLocation(world);
        Location to = path.to().position().toLocation(world);
        Location look = path.looking().toLocation(world);

        double ticks = path.time() * 20;

        double x = (to.x() - from.x()) / ticks;
        double y = (to.y() - from.y()) / ticks;
        double z = (to.z() - from.z()) / ticks;

        Vector unitVector = new Vector(x, y, z);

        if (armorStand == null) {
            armorStand = (ArmorStand) from.getWorld().spawnEntity(from, EntityType.ARMOR_STAND);
            armorStand.setInvisible(true);
            armorStand.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1, false, false, false));
            armorStand.setGravity(false);

            player.setGameMode(GameMode.SPECTATOR);
            player.setSpectatorTarget(armorStand);
        }
        getYawAndPitch(path, from, armorStand, look);

        armorStand.teleport(from);

        ArmorStand finalArmorStand = armorStand;
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                // TODO: path.axis() should be calculated and be used right here, but I don't know how to implement it
                for (double start = 50; start <= (ticks * 50); start += 50) {
                    try {
                        Thread.sleep(50L);
                        if (!player.isOnline())
                            cancel();
                        from.add(unitVector);
                        getYawAndPitch(path, from, finalArmorStand, look);
                        S.run(plugin, false, new BukkitRunnable() {
                            @Override
                            public void run() {
                                finalArmorStand.teleport(from);
                            }
                        });

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (index + 1 >= paths.size()) {
                    S.run(plugin, false, new BukkitRunnable() {
                        @Override
                        public void run() {
                            finalArmorStand.remove();
                        }
                    });
                    future.complete(null);
                } else
                    S.run(plugin, false, new BukkitRunnable() {
                        @Override
                        public void run() {
                            playPath(future, finalArmorStand, plugin, player, world, paths, index + 1);
                        }
                    });
            }
        }.runTaskAsynchronously(plugin);
        synchronized (TASKS) {
            TASKS.put(player.getUniqueId(), Pair.of(armorStand, task));
        }
    }

    private static void getYawAndPitch(Path path, Location from, ArmorStand finalArmorStand, Location look) {
        float[] ynp = path.reverseLooking() ? LocationUtil.calculateReversedYawAndPitch(from.clone().add(0, finalArmorStand.getEyeHeight(true), 0), look)
                : LocationUtil.calculateYawAndPitch(from.clone().add(0, finalArmorStand.getEyeHeight(true), 0), look);
        from.setYaw(ynp[0]);
        from.setPitch(ynp[1]);
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        clean(event.getPlayer());
    }

    @EventHandler
    public void track(PlayerStopSpectatingEntityEvent event) {
        synchronized (PLAYING) {
            if (PLAYING.containsKey(event.getPlayer().getUniqueId()))
                event.setCancelled(true);
        }
    }

    private CameraPlayer() {
    }

}
