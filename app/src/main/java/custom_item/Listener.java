package custom_item;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;


public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void EntityItemUse(PlayerInteractEvent event) {

        AbstractItem item = AbstractItem.getAbstItemFromItemStack(event.getItem());
        if (item != null) {
            item.used(event.getAction(), event.getPlayer());
        }
        
    }


    @EventHandler
    public void plrAttackEntityEvent(PrePlayerAttackEntityEvent event) {

        AbstractItem item = AbstractItem.getAbstItemFromItemStack(event.getPlayer().getActiveItem());
        if (item != null) {
            item.attack(event.getPlayer(), event.getAttacked());
        }

    }


    @EventHandler
    public void PlayerJoined(PlayerJoinEvent event) {
        AbstractItem.getAbstItemFromList("Fire Sword").giveItem(event.getPlayer());
    }
}