package me.itzsomebody.simpleloader;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TestClass implements Listener {
    public TestClass() {
        Loader instance = Loader.getInstance();
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.sendMessage("Hi there, " + p.getDisplayName() + "!");
    }

    @EventHandler
    public void onChestOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();

        p.sendMessage("I see you opened a chest!");
    }
}
