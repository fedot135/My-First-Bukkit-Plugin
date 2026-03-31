package custom_item;

import java.util.Collection;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public final class GiveItemCommand implements BasicCommand {

    private static final TextComponent GET_ITEM_MESSAGE = Component.text("are you get item!", TextColor.color(0,255,0));
    private static final TextComponent INCORRECT_NAME_MESSAGE = Component.text("Incorrect item name!", TextColor.color(255,0,0));

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player) {
            Player plrSender = (Player) sender;

            for (String arg : args) {
                AbstractItem item = AbstractItem.getFromList(arg);
                if (item != null) {
                    item.giveItem(plrSender);
                    sender.sendMessage(GET_ITEM_MESSAGE);
                } else {
                    sender.sendMessage(INCORRECT_NAME_MESSAGE);
                }
            }
        }

    }

    @Override
    public Collection<String> suggest(final CommandSourceStack commandSourceStack, final String[] args) {
        return AbstractItem.getItemList().getHashMap().keySet();
    }

    @Override
    public boolean canUse(final CommandSender sender) {
        return sender.hasPermission("op");
    }


}
