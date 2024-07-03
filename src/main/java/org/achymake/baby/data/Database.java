package org.achymake.baby.data;

import org.achymake.baby.Baby;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public record Database(Baby plugin) {
    public boolean isBaby(Player player) {
        return player.getAttribute(Attribute.GENERIC_SCALE).getValue() == 0.5;
    }
    public void toggleBaby(Player player) {
        setBaby(player, !isBaby(player));
    }
    public void setBaby(Player player, boolean value) {
        if (value) {
            if (!isBaby(player)) {
                setScale(player, 0.5);
            }
        } else {
            if (isBaby(player)) {
                setScale(player, 1.0);
            }
        }
    }
    public void setScale(Player player, double value) {
        player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(value);
    }
}