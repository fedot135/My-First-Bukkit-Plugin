package plugin_items;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import custom_item.AbstractItem;
import custom_item.Useable;

public class FireSword extends AbstractItem implements Useable {

    public FireSword() {
        super("Fire Sword");
    }

    @Override
    public void rightClick(Player player) {
        for (Entity entity : player.getNearbyEntities(10, 2, 10)) {
            entity.setFireTicks(20);
        }
    }

    @Override
    protected void attack(Player player, Entity entity) {
        entity.setFireTicks(200);
    }

    @Override
    protected void dropped(Player player) {}

}
