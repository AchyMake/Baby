package org.achymake.baby;

import org.achymake.baby.commands.BabyCommand;
import org.achymake.baby.data.Database;
import org.achymake.baby.data.Message;
import org.achymake.baby.listeners.PlayerJoin;
import org.achymake.baby.net.UpdateChecker;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Baby extends JavaPlugin {
    private static Baby instance;
    private static Database database;
    private static Message message;
    private static UpdateChecker updateChecker;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message(this);
        database = new Database(this);
        updateChecker = new UpdateChecker(this);
        commands();
        events();
        reload();
        getMessage().sendLog(Level.INFO, "Enabled " + getDescription().getName() + " " + getDescription().getVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getMessage().sendLog(Level.INFO, "Disabled " + getDescription().getName() + " " + getDescription().getVersion());
    }
    private void commands() {
        getCommand("baby").setExecutor(new BabyCommand(this));
    }
    private void events() {
        getManager().registerEvents(new PlayerJoin(this), this);
    }
    public void reload() {
        File file = new File(getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfig().load(file);
            } catch (IOException | InvalidConfigurationException e) {
                getMessage().sendLog(Level.WARNING, e.getMessage());
            }
        } else {
            getConfig().options().copyDefaults(true);
            try {
                getConfig().save(file);
            } catch (IOException e) {
                getMessage().sendLog(Level.WARNING, e.getMessage());
            }
        }
    }
    public boolean isBaby(Player player) {
        return getDatabase().isBaby(player);
    }
    public void toggleBaby(Player player) {
        getDatabase().toggleBaby(player);
    }
    private PluginManager getManager() {
        return getServer().getPluginManager();
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public Database getDatabase() {
        return database;
    }
    public Message getMessage() {
        return message;
    }
    public Baby getInstance() {
        return instance;
    }
}