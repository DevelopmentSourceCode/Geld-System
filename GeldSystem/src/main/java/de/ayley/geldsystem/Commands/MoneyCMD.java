package de.ayley.geldsystem.Commands;

import de.ayley.geldsystem.API.EinfacheMethoden;
import de.ayley.geldsystem.API.Strings;
import de.ayley.geldsystem.MySQL.MySQLGeldAPI;
import de.ayley.geldsystem.MySQL.MySQLGeldAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCMD implements CommandExecutor {

    private String Prefix = Strings.Prefix;
    private String GetMoney = Strings.DeinGeld;
    private String Waehrung = Strings.Währung;
    private String KeineBerechtigung = Strings.KeineBerechtigung;
    private String KeinKonsolenBefehl = Strings.KeinKonsolenBefehl;

    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0 || args.length == 1) {
                if (args.length == 0) {
                    int getMoney = MySQLGeldAPI.getMySQLMoney().getMoney(player.getUniqueId().toString());
                    EinfacheMethoden.playerMessage(player, Prefix + GetMoney.replace("%Money%", String.valueOf(getMoney)).replace("%Waehrung%", Waehrung));
                }

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        int getMoney = MySQLGeldAPI.getMySQLMoney().getMoney(target.getUniqueId().toString());
                        EinfacheMethoden.playerMessage(player, Prefix + GetMoney.replace("%Money%", String.valueOf(getMoney)).replace("%Waehrung%", Waehrung));
                    }
                }
            } else if (args[0].equalsIgnoreCase("add") && args.length != 0) {
                Player target = Bukkit.getPlayer(args[1]);

                if (player.hasPermission("Money.Add")) {

                    if (args.length == 2) {
                        int addMoney = Integer.parseInt(args[1]);
                        MySQLGeldAPI.getMySQLMoney().addMoney(player, addMoney);
                    }

                    if (target != null) {
                        if (args.length == 3 && args[1].equalsIgnoreCase(target.getName())) {
                            int addMoney = Integer.parseInt(args[2]);
                            MySQLGeldAPI.getMySQLMoney().addMoney(player, addMoney);
                        }
                    }
                } else {
                    EinfacheMethoden.playerMessage(player, Prefix + KeineBerechtigung);
                }
            } else if (args[0].equalsIgnoreCase("remove") && args.length != 0) {

                Player target = Bukkit.getPlayer(args[1]);

                if (player.hasPermission("Money.Remove")) {

                    if (args.length == 2) {
                        int removeMoney = Integer.parseInt(args[1]);
                        MySQLGeldAPI.getMySQLMoney().removeMoney(player, removeMoney, Prefix);
                    }
                    if (target != null) {
                        if (args.length == 3 && args[1].equalsIgnoreCase(target.getName())) {
                            int addMoney = Integer.parseInt(args[2]);
                            MySQLGeldAPI.getMySQLMoney().removeMoney(player, addMoney, Prefix);
                        }
                    }
                } else {
                    EinfacheMethoden.playerMessage(player, Prefix + KeineBerechtigung);
                }
            } else if (args[0].equalsIgnoreCase("set") && args.length != 0) {
                Player target = Bukkit.getPlayer(args[1]);

                if (player.hasPermission("Money.Set")) {
                    if (args.length == 2) {
                        int setMoney = Integer.parseInt(args[1]);
                        MySQLGeldAPI.getMySQLMoney().setMoney(player, setMoney);
                    }
                    if (target != null) {
                        if (args.length == 3 && args[1].equalsIgnoreCase(target.getName())) {
                            int setMoney = Integer.parseInt(args[2]);
                            MySQLGeldAPI.getMySQLMoney().setMoney(target, setMoney);
                        }
                    }
                } else {
                    EinfacheMethoden.playerMessage(player, Prefix + KeineBerechtigung);
                }
            }
        }else {
            EinfacheMethoden.consolMessage(Prefix + KeinKonsolenBefehl);
        }
        return true;
    }
}
