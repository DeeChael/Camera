package net.deechael.camera.test;

import net.deechael.camera.CameraAPI;
import net.deechael.camera.CameraPlayer;
import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Node;
import net.deechael.camera.util.VectorUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class CameraTestPlugin extends JavaPlugin {


    private static int[] parse(String[] args, int index) {
        int[] ints = new int[3];
        for (int i = index; i < index + 3; i++) {
            ints[i - index] = Integer.parseInt(args[i]);
        }
        return ints;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(CameraPlayer.INSTANCE, this);

        // /test 10 90 8 -2 95 14 -15 94 8 -5 103 2 5 94 22

        Bukkit.getCommandMap().register("camera", new Command("test") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                if (sender instanceof Player player && args.length == 15)
                    execute(player, args);
                return true;
            }

            public void execute(Player player, String[] args) {
                World world = player.getWorld();
                int[] startXYZ = parse(args, 0);
                int[] endXYZ = parse(args, 3);
                int[] end2XYZ = parse(args, 6);
                int[] end3XYZ = parse(args, 9);
                int[] lookXYZ = parse(args, 12);
                Vector end = new Vector(endXYZ[0], endXYZ[1], endXYZ[2]);
                Vector look = new Vector(lookXYZ[0], lookXYZ[1], lookXYZ[2]);
                Vector nextCenter = end.clone().add(VectorUtil.reverse(VectorUtil.point(end.clone().add(new Vector(0, 1.78, 0)), look)));
                Camera camera = CameraAPI.newCamera(Node.of(startXYZ[0], startXYZ[1], startXYZ[2]))
                        .next(Node.of(endXYZ[0], endXYZ[1], endXYZ[2]))
                        .looking(new Vector(lookXYZ[0], lookXYZ[1], lookXYZ[2]), false)
                        .time(3)
                        .done()
                        .next(Node.of(end2XYZ[0], end2XYZ[1], end2XYZ[2]))
                        .looking(nextCenter, true)
                        .time(3)
                        .done()
                        .next(Node.of(end3XYZ[0], end3XYZ[1], end3XYZ[2]))
                        .looking(new Vector(end2XYZ[0], end2XYZ[1] + 1, end2XYZ[2]), false)
                        .time(3)
                        .done()
                        .build();
                CameraPlayer.play(CameraTestPlugin.this, player, world, camera);
            }
        });
    }

}
