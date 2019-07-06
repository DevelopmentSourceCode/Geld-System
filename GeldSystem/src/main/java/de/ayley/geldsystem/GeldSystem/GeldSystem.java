package de.ayley.geldsystem.GeldSystem;

import de.ayley.geldsystem.API.EinfacheMethoden;
import de.ayley.geldsystem.API.Strings;
import de.ayley.geldsystem.Commands.MoneyCMD;
import de.ayley.geldsystem.Events.JoinEvent;
import de.ayley.geldsystem.MySQL.MySQLConection;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class GeldSystem extends JavaPlugin {

    private static GeldSystem Plugin;

    //MySQL
    public static MySQLConection MySQL;


    @Override
    public void onEnable() {
        Plugin = this;
        onLoadConfig();
        new EinfacheMethoden();

        String Host = Strings.Host;
        String Database = Strings.Database;
        String User = Strings.User;

        String Password = Strings.Password;
        String Prefix = Strings.Prefix;
        String Start = Strings.Start;
        String Gestartet = Strings.Gestartet;
        String Fehler = Strings.Fehler;
        MySQL = new MySQLConection(Host,Database,User,Password);

        EinfacheMethoden.consolMessage(Prefix + Start);
        try{
            onLoadCommands();
            tableCreate();
            onLoadEvents(Bukkit.getPluginManager());
            EinfacheMethoden.consolMessage(Prefix + Gestartet);
        }catch (Exception e){
            EinfacheMethoden.consolMessage(Prefix + Fehler);
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

    private void onLoadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static GeldSystem getPlugin() {
        return Plugin;
    }

    private void tableCreate() {
        String Prefix = Strings.Prefix;
        MySQL.update("CREATE TABLE IF NOT EXISTS `playermoney` ( `UUID` VARCHAR(255) NOT NULL ,`PlayerName` VARCHAR(255) NOT NULL, `Money` INT(100) NOT NULL ) ENGINE = InnoDB;");
        EinfacheMethoden.consolMessage(Prefix + "&2Die Tabelle wurde erstellt!");
    }

    private void onLoadEvents(PluginManager pm){
        pm.registerEvents(new JoinEvent(),this);
    }

    private void onLoadCommands(){
        getCommand("money").setExecutor(new MoneyCMD());
    }

}
