package eu.samosadlaker.fancyanvils.core;

import eu.samosadlaker.fancyanvils.commands.Rename;
import eu.samosadlaker.fancyanvils.commands.TabCompleter;
import eu.samosadlaker.fancyanvils.listeners.AnvilRename;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    static Main plugin;
    CommandSender logger = Bukkit.getConsoleSender();
    PluginDescriptionFile pdf = this.getDescription();
    FileConfiguration config = this.getConfig();

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;
        File file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            getConfig().options().copyDefaults(true);
            getConfig().options().copyHeader(true);
            saveDefaultConfig();
        }
        reloadConfig();

        registerCommands();
        registerTabCompleter();
        registerListeners();

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&aPlugin &b" + pdf.getName() + " &asuccessfully enabled"));
        logger.sendMessage(Colors.formatColor("&a" + pdf.getVersion() + " &f| &a" + pdf.getAuthors().toString() + " &f| &a" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));

    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new AnvilRename(), this);
    }
    private void registerTabCompleter(){
        getCommand("fancyanvils").setTabCompleter(new TabCompleter());
    }
    private void registerCommands(){
        getCommand("fancyanvil").setExecutor(new Rename());
    }

    @Override
    public void  onDisable(){

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&4Plugin &b" + pdf.getName() + " &4successfully disabled"));
        logger.sendMessage(Colors.formatColor("&c" + pdf.getVersion() + " &f| &c" + pdf.getAuthors().toString() + " &f| &c" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));

    }

}
