package org.achymake.baby.listeners;

import org.achymake.baby.Baby;
import org.achymake.baby.net.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public record PlayerJoin(Baby plugin) implements Listener {
    private UpdateChecker getUpdateChecker() {
        return plugin.getUpdateChecker();
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("baby.event.join.update"))return;
        getUpdateChecker().getUpdate(player);
    }
}