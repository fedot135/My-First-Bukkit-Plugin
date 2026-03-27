package custom_item;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void entityItemUse(PlayerInteractEvent event) {

        Useable item = (Useable) AbstractItem.getFromItemStack(event.getItem());

        if (item != null) {

            if (event.getAction().isLeftClick()) {
                item.leftClick(event.getPlayer());
            } else {
                item.rightClick(event.getPlayer());
            }

        }
        
    }


    @EventHandler
    public void prePlayerAttackEntityEvent(PrePlayerAttackEntityEvent event) {

        AbstractItem item = AbstractItem.getFromItemStack(event.getPlayer().getActiveItem());
        if (item != null) {
            item.attack(event.getPlayer(), event.getAttacked());
        }

    }

    @EventHandler
    public void plrDroppedItemEvent(PlayerDropItemEvent event) {

        AbstractItem item = AbstractItem.getFromItemStack(event.getItemDrop().getItemStack());
        if (item != null) {
            item.dropped(event.getPlayer());
        }

    }


    @EventHandler
    public void PlayerJoined(PlayerJoinEvent event) {
        AbstractItem.getFromList("Fire Sword").giveItem(event.getPlayer());
    }
    
}