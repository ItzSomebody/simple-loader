package me.itzsomebody.simpleloader;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example loader.
 */
public class Loader extends JavaPlugin {
    // Probably not the best way to do this
    private static Loader instance;

    public static Loader getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        CustomClassLoader loader = new CustomClassLoader();

        try {
            Class clazz = loader.findClass("me.itzsomebody.simpleloader.TestClass");
            clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
