package custom_item;
import java.util.HashMap;

public class ItemList {

    private HashMap<String, AbstractItem> List = new HashMap<String, AbstractItem>();

    public void updateAbstractItem(AbstractItem item, String name) {

        if (List.get(name) == null) {
            List.put(name, item);
        } else {
            List.replace(name, item);
        }

    }

    public void updateName(String name) {

        AbstractItem saveItem = List.get(name);
        
        if (saveItem != null) {
            List.remove(name);
            List.put(name, saveItem);
        }

    }

    public AbstractItem get(String name) {
        return List.get(name);
    }
    
}
