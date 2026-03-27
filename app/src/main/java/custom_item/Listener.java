package custom_item;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
    public void prePlayerAttackEntityEvent(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            Player plr = (Player) event.getDamager();
            AbstractItem item = AbstractItem.getFromItemStack(plr.getActiveItem());

            if (item != null) {
                item.attack(plr, event.getEntity());
            }

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