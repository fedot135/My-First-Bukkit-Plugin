package plugin_items;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import custom_item.AbstractItem;
import custom_item.Useable;
import net.kyori.adventure.text.Component;

public class FireSword extends AbstractItem implements Useable {

    public FireSword() {
        super("FireSword");
    }

    @Override
    public void rightClick(Player player, ItemStack item) {

        if (player.getCooldown(item) == 0) {
            for (Entity entity : player.getNearbyEntities(10, 2, 10)) {
                if (entity.getType() != EntityType.ITEM) {
                    entity.setFireTicks(20);
                }
            }
            player.setCooldown(item, 100);
        }

    }

    @Override
    public void dropped(Player player) {
    }

    @Override
    public void attack(Player damager, Entity target) {
        target.setFireTicks(200);
        damager.sendActionBar(Component.text("Attack"));
    }

}
