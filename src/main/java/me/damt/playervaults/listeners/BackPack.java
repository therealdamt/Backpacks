package me.damt.playervaults.listeners;

import me.damt.playervaults.Main;
import me.damt.playervaults.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BackPack implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();

        if (item != null && item.getType().equals(Material.CHEST) && item.getItemMeta().getDisplayName().equals(Util.chat("&clBackpack"))) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (Main.getInstance().pvs.containsKey(player.getUniqueId().toString())) {
                    Inventory inv = Bukkit.createInventory(player, 9, player.getName() + "'s Backpack");
                    inv.setContents(Main.getInstance().pvs.get(player.getUniqueId().toString()));
                    player.openInventory(inv);
                } else {
                    Inventory inv = Bukkit.createInventory(player, 9, player.getName() + "'s Backpack");
                    player.openInventory(inv);
                }
            }
        }
    }
}
