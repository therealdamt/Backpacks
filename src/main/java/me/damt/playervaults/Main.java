package me.damt.playervaults;

import me.damt.playervaults.commands.PVCommand;
import me.damt.playervaults.listeners.BackPack;
import me.damt.playervaults.listeners.GUIClose;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;

    public Map<String, ItemStack[]> pvs = new HashMap<String, ItemStack[]>();

    public void onEnable() {
        getServer().getConsoleSender().sendMessage(Util.chat("&c&lPlayerVaults have been enabled"));
        getServer().getConsoleSender().sendMessage(Util.chat("&6&lDeveloped by damt"));
        instance = this;
        registerCommands();
        registerListeners();
        loadConfig();

        if (this.getConfig().contains("data")) {
            this.restoreInventories();
            this.getConfig().set("data", null);
            this.saveConfig();
        }
    }
    public void onDisable() {
        if (!pvs.isEmpty()) {
            saveInventories();
        }
    }
    public void saveInventories() {
        for (Map.Entry<String, ItemStack[]> entry : pvs.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
    }
    public void restoreInventories() {
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            ItemStack[] content = ((List<ItemStack>) this.getConfig().get("data." + key)).toArray(new ItemStack[0]);
            pvs.put(key, content);
        });
    }

    private void registerCommands() {
        getCommand("backpack").setExecutor(new PVCommand());
    }
    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIClose(), this);
        pm.registerEvents(new BackPack(), this);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public static Main getInstance() {
        return instance;
    }
}
