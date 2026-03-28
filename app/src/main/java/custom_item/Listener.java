package custom_item;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void entityItemUse(PlayerInteractEvent event) {

        Useable item = null;

        {
            ItemStack itemStack = event.getItem();
            AbstractItem abstItem = AbstractItem.getFromItemStack(itemStack);

            if (abstItem != null) {
                abstItem.tempItemStack = itemStack;
                item = (Useable) abstItem;
            }
        }

        if (item != null) {

            if (event.getAction().isLeftClick()) {
                item.leftClick(event.getPlayer(), event.getItem());
            } else {
                item.rightClick(event.getPlayer(), event.getItem());
            }

        } else {
            event.setCancelled(true);
        }
        
    }

    @EventHandler
    public void plrDroppedItemEvent(PlayerDropItemEvent event) {

        AbstractItem item = AbstractItem.getFromItemStack(event.getItemDrop().getItemStack());
        if (item != null) {
            item.tempItemStack = event.getItemDrop().getItemStack();
            item.dropped(event.getPlayer());
        }

    }

    @EventHandler
    public void entityDamagedByEntity(EntityDamageEvent event) {

        Player plr = null;

        {
            Entity damager = event.getDamageSource().getCausingEntity();
            plr = (damager instanceof Player) ? (Player) damager : null;
        }

        if (plr != null) {

            ItemStack itemStack = plr.getActiveItem();
            AbstractItem item = AbstractItem.getFromItemStack(itemStack);

            if (item != null) {
                item.tempItemStack = itemStack;
                item.attack(plr, event.getEntity());
            }

        }
    }


    @EventHandler
    public void PlayerJoined(PlayerJoinEvent event) {
        AbstractItem.getFromList("Fire Sword").giveItem(event.getPlayer());
    }

}