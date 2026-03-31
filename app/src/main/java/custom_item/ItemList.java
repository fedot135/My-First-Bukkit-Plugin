package custom_item;

import java.util.HashMap;

public class ItemList {

    private HashMap<String, AbstractItem> list = new HashMap<String, AbstractItem>();

    public void updateAbstractItem(AbstractItem item, String name) {

        if (list.get(name) == null) {
            list.put(name, item);
        } else {
            list.replace(name, item);
        }

    }

    public void updateName(String name) {

        AbstractItem saveItem = list.get(name);
        
        if (saveItem != null) {
            list.remove(name);
            list.put(name, saveItem);
        }

    }

    public AbstractItem getItem(String name) {
        return list.get(name);
    }

    public HashMap<String, AbstractItem> getHashMap() {
        return list;
    }
    
}
