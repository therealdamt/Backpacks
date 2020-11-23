package me.damt.playervaults.commands;

import me.damt.playervaults.Main;
import me.damt.playervaults.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PVCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
            if (sender.hasPermission("backpack.access")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("give")) {
                        Player player = Bukkit.getPlayer(args[1]);

                        if (player != null) {
                            int amount;
                            try {
                                amount = Integer.parseInt(args[2]);
                            } catch (NumberFormatException e) {
                                sender.sendMessage(Util.chat("&cPlease insert a valid number!"));
                                return false;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                sender.sendMessage(Util.chat("&cWrong usage: &7/backpack give <player> <amount>"));
                                return false;
                            }

                            ItemStack backpack = new ItemStack(Material.CHEST, amount);
                            ItemMeta backmeta = backpack.getItemMeta();
                            backmeta.setDisplayName(Util.chat("&c&lEggshell's Backpack"));
                            List<String> lore = new ArrayList<>();
                            lore.add(Util.chat("&7&oRight click this to open your favorite backpack!"));
                            backmeta.setLore(lore);
                            backpack.setItemMeta(backmeta);

                            player.sendMessage(Util.chat("&aYou are recieved a BACKPACK from " + sender.getName()));
                            sender.sendMessage(Util.chat("&aYou have given " + player.getName() + " a backpack!"));


                            player.getInventory().addItem(backpack);
                        } else {
                            sender.sendMessage(Util.chat("&cPlayer does not exist or is currently not online!"));
                        }
                    }
                } else {
                    sender.sendMessage(Util.chat("&cWrong usage: &7&o/backpack give <player> <amount>"));
                }
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(Util.chat("&cThis command can only be run by players!"));
        }


        return false;
    }
}
