package dank.memes.spigotcracker;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.net.URL;

public class SpigotCrackerPlugin extends JavaPlugin {
    public static SpigotCrackerPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
        unsetURLStreamHandlerFactory();
        getLogger().info("All requests to SpigotMC's resources API will be intercepted.");
        URL.setURLStreamHandlerFactory(new InterceptedStreamHandlerFactory());
    }

    private static String unsetURLStreamHandlerFactory() {
        try {
            Field f = URL.class.getDeclaredField("factory");
            f.setAccessible(true);
            Object curFac = f.get(null);
            f.set(null, null);
            URL.setURLStreamHandlerFactory(null);
            return curFac.getClass().getName();
        } catch (Exception e) {
            return null;
        }
    }
}