package plugin_items;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import custom_item.AbstractItem;

public class FireSword extends AbstractItem {

    public FireSword() {
        super("Fire Sword");
    }

    @Override
    protected void used(Action action, Player player) {
        if (action.isRightClick()) {
            for (Entity entity : player.getNearbyEntities(10, 2, 10)) {
                entity.setFireTicks(20);
            }
        }
    }

    @Override
    protected void attack(Player player, Entity entity) {
        entity.setFireTicks(200);
    }

}
