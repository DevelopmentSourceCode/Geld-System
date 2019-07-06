package de.ayley.geldsystem.Events;

import de.ayley.geldsystem.API.EinfacheMethoden;
import de.ayley.geldsystem.API.Strings;
import de.ayley.geldsystem.GeldSystem.GeldSystem;
import de.ayley.geldsystem.MySQL.MySQLGeldAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {

    private int Startgeld = Strings.StartGeld;
    private String Prefix = Strings.Prefix;
    private String KontoErstellt = Strings.KontoErstellt;
    private String Währung = Strings.Währung;

    @EventHandler
    private void onLoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID playeruuid = player.getUniqueId();

        if(!MySQLGeldAPI.getMySQLMoney().playerExists(playeruuid.toString())){
            GeldSystem.getPlugin().MySQL.update("INSERT INTO `playermoney`(`UUID`,`Money`,`PlayerName`) VALUES ('%uuid%','%startgeld%','%player%')".replace("%uuid%", playeruuid.toString()).replace("%player%",player.getName()).replace("%startgeld%", String.valueOf(Startgeld)) );
            EinfacheMethoden.playerMessage(player, Prefix + KontoErstellt.replace("%Money%",String.valueOf(Startgeld)).replace("%Waehrung%",Währung));
        }
    }
}
