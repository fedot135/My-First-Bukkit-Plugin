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
    public void entityItemUseEvent(PlayerInteractEvent event) {

        ItemStack itemStack = event.getItem();
        AbstractItem abstItem = AbstractItem.getFromItemStack(itemStack);
        Useable item = (abstItem != null) ? (Useable) abstItem : null;

        if (item != null) {

            abstItem.tempItemStack = itemStack;
            Player player = event.getPlayer();

            if (event.getAction().isLeftClick()) {
                item.leftClick(player, itemStack);
            } else {
                item.rightClick(player, itemStack);
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
    public void entityDamageEvent(EntityDamageEvent event) {

        Entity damager = event.getDamageSource().getDirectEntity();
        Player plr = damager != null && damager instanceof Player ? (Player) damager : null;

        if (plr != null) {
            ItemStack activeItem = plr.getInventory().getItemInMainHand();
            AbstractItem abstItem = AbstractItem.getFromItemStack(activeItem);

            if (abstItem != null) {
                abstItem.tempItemStack = activeItem;
                abstItem.attack(plr, event.getEntity());
            }
        }
    }


    @EventHandler
    public void PlayerJoined(PlayerJoinEvent event) {
        AbstractItem.getFromList("Fire Sword").giveItem(event.getPlayer());
    }

}