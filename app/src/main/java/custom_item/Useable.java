package custom_item;

import org.bukkit.entity.Player;

public interface Useable {

    default void rightClick(Player player) {};

    default void leftClick(Player player) {};
    
}
