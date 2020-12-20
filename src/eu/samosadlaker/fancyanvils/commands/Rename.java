package eu.samosadlaker.fancyanvils.commands;

import eu.samosadlaker.fancyanvils.core.Colors;
import eu.samosadlaker.fancyanvils.core.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Rename implements CommandExecutor {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    public List<String> getRenameCmds(){
        List<String> renameCmds = new ArrayList<>();
        renameCmds.add("fancyanvils");
        renameCmds.add("fancyanvil");
        renameCmds.add("anvils");
        renameCmds.add("anvil");
        return renameCmds;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(getRenameCmds().contains(label)){
            if (!(sender instanceof Player)) {
                sender.sendMessage(Colors.formatColor(prefix + config.getString("notplayer")));
                return false;
            }
            if(args.length == 0){
                sender.sendMessage(Colors.formatColor(prefix + config.getString("command-usage")));
                return false;
            }

            if (!sender.hasPermission(config.getString("permission"))){
                return false;
            }

            if(args[0].equalsIgnoreCase("reload")){
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage(Colors.formatColor(prefix + config.getString("reload-completed")));
                return true;
            }

            if(args[0].equalsIgnoreCase("rename")){
                Player p = (Player) sender;
                if(args.length <= 1){
                    sender.sendMessage(Colors.formatColor(prefix + config.getString("command-usage")));
                    return false;
                }

                if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                    sender.sendMessage(Colors.formatColor(prefix + config.getString("holdblock")));
                    return false;
                }

                ItemMeta itemmeta = p.getInventory().getItemInMainHand().getItemMeta();

                itemmeta.setDisplayName(Colors.formatColor(args[1]));
                p.getInventory().getItemInMainHand().setItemMeta(itemmeta);

                p.sendMessage(Colors.formatColor(prefix + config.getString("itemrenamed") + "&f &e" + args[1]));
                return true;
            }

            if(args[0].equalsIgnoreCase("clearlore")){
                Player p = (Player) sender;
                if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                    sender.sendMessage(Colors.formatColor(prefix + config.getString("holdblock")));
                    return false;
                }

                ItemMeta itemmeta = p.getInventory().getItemInMainHand().getItemMeta();

                itemmeta.setLore(null);
                p.getInventory().getItemInMainHand().setItemMeta(itemmeta);
                p.sendMessage(Colors.formatColor(prefix + config.getString("clearlore")));
                return true;
            }

            if(args[0].equalsIgnoreCase("glowing")){
                Player p = (Player) sender;
                if(args.length <= 1){
                    sender.sendMessage(Colors.formatColor(prefix + config.getString("command-usage")));
                    return false;
                }
                if(args[1].equalsIgnoreCase("true")){

                    if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                        sender.sendMessage(Colors.formatColor(prefix + config.getString("holdblock")));
                        return false;
                    }

                    ItemMeta itemmeta = p.getInventory().getItemInMainHand().getItemMeta();
                    itemmeta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
                    itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    p.getInventory().getItemInMainHand().setItemMeta(itemmeta);
                    p.sendMessage(Colors.formatColor(prefix + config.getString("itemglowing")));
                    return true;

                }
                if(args[1].equalsIgnoreCase("false")){

                    if(p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                        sender.sendMessage(Colors.formatColor(prefix + config.getString("holdblock")));
                        return false;
                    }

                    ItemMeta itemmeta = p.getInventory().getItemInMainHand().getItemMeta();
                    itemmeta.removeEnchant(Enchantment.QUICK_CHARGE);
                    itemmeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                    p.getInventory().getItemInMainHand().setItemMeta(itemmeta);
                    p.sendMessage(Colors.formatColor(prefix + config.getString("itemnotglowing")));
                    return true;
                }

            }


        }

        return false;
    }
}
