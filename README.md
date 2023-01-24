# Camera
a light-weight bukkit library for camera movement animation

## How to use
### Use this project as plugin dependency
Compile this project manually and add camera-api to your dependencies, and put camera-impl into server plugins folder, then just develop your plugin
### Shade this project into your plugin
Compile this project and add camera-impl to your dependencies (shade with shadow plugin), and register event listener `net.deechael.camera.CameraPlayer.INSTANCE` while your plugin enabling
