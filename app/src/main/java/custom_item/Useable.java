package custom_item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Useable {

    default void rightClick(Player player, ItemStack item) {};

    default void leftClick(Player player, ItemStack item) {};
    
}
