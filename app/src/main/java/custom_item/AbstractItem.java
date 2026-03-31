package custom_item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public abstract class AbstractItem {

    private ItemStack itemStack = new ItemStack(Material.STICK);
    public ItemStack tempItemStack = new ItemStack(Material.STICK);
    private String name = "Custom Item";

    private static final ItemList itemList = new ItemList();

    public static final NamespacedKey ITEM_KEY = new NamespacedKey("custom", "item_key");

    protected AbstractItem(String name) {

        this.name = name;
        itemList.updateAbstractItem(this, name);
        
        itemStack.editPersistentDataContainer(pdc -> {pdc.set(ITEM_KEY, PersistentDataType.STRING, name);});

    }


    public static void registerItem(AbstractItem item) {
        if (item != null && item.getName() != null) {
            itemList.updateAbstractItem(item, item.getName());
        }
    }

    public static AbstractItem getFromList(String name) {
        return itemList.getItem(name);
    }

    public static ItemList getItemList() {
        return itemList;
    }

    public static AbstractItem getFromItemStack(ItemStack item) {
        if (item != null) {
            String name = (String) item.getPersistentDataContainer().get(AbstractItem.ITEM_KEY, PersistentDataType.STRING);
            return (name != null) ? AbstractItem.getFromList(name) : null;
        } else {
            return null;
        }
    }

    public void updateItemStack(ItemStack newItemStack) {

        if (newItemStack != null) {
            itemStack = newItemStack;
            itemStack.editPersistentDataContainer(pdc -> {pdc.set(ITEM_KEY, PersistentDataType.STRING, name);});
        }

    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public void giveItem(Player player) {
        player.give(itemStack);
    }

    public abstract void dropped(Player player);

    public abstract void attack(Player damager, Entity target);

}
