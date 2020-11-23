package me.damt.playervaults.listeners;

import me.damt.playervaults.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class GUIClose implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (e.getInventory().getTitle().equals(player.getName() + "'s PlayerVault")) {
            Main.getInstance().pvs.put(player.getUniqueId().toString(), player.getInventory().getContents());
        }
    }

}
