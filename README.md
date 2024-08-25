# Camera
a light-weight bukkit library for camera movement animation

**ATTENTION**: This project is already outdated. If you want the a camera movement library, you can try my [visual-api](https://github.com/DeeChael/visual-api), which has more parameters like Curve to make the camera movement more customizable.

## Known Issues
1. The transition is not smooth, so maybe you should try to find a way to set a perfect looking center and use looking reverse well to make the animation smoothly
2. Path axis feature hasn't been implemented yet because I don't know how to calculate ellipse

## How to use
### Use this project as plugin dependency
Compile this project manually and add camera-api to your dependencies, and put camera-impl into server plugins folder, then just develop your plugin
### Shade this project into your plugin
Compile this project and add camera-impl to your dependencies (shade with shadow plugin), and register event listener `net.deechael.camera.CameraPlayer.INSTANCE` while your plugin enabling

## Code
You can also see [CameraTestPlugin](./camera-test/src/main/java/net/deechael/camera/test/CameraTestPlugin.java)
```java
package net.deechael.camera.test;

import net.deechael.camera.CameraAPI;
import net.deechael.camera.CameraPlayer;
import net.deechael.camera.api.Camera;
import net.deechael.camera.api.Node;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Example {

    public void play(Plugin plugin, World world, Player player) {
        // All random numbers, you should calculate a good number to make the transition smooth
        Camera camera = CameraAPI.newCamera(Node.of(0, 64, 0))
                .next(Node.of(12, 68, 4))
                        .looking(new Vector(16, 65, 13), false)
                        .time(3)
                    .done()
                .next(Node.of(-9, 72, 16))
                        .looking(new Vector(0, 64, 0), true)
                        .time(3)
                    .done()
                .next(Node.of(-18, 64, 9))
                        .looking(new Vector(-9, 8, 6), false)
                        .time(3)
                    .done()
                .build();
        camera.play(plugin, world, player);
    }

}
```
