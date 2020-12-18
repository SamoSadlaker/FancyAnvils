package eu.samosadlaker.fancyanvils.listeners;


import eu.samosadlaker.fancyanvils.core.Colors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilRename implements Listener {

    @EventHandler
    public void onRenameItem(PrepareAnvilEvent e){
        if(e.getResult() != null && e.getResult().hasItemMeta() && e.getInventory().getRenameText() != ""){
            ItemStack result = e.getResult();
            ItemMeta resultMeta = result.getItemMeta();
            String colored = Colors.formatColor(e.getInventory().getRenameText());
            resultMeta.setDisplayName(colored);
            result.setItemMeta(resultMeta);
        }
    }
}
