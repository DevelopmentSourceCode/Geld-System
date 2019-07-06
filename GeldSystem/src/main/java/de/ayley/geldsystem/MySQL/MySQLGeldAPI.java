package de.ayley.geldsystem.MySQL;

import de.ayley.geldsystem.API.EinfacheMethoden;
import de.ayley.geldsystem.GeldSystem.GeldSystem;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLGeldAPI {

    public static MySQLGeldAPI msm;

    public static int getMoney(String uuid)  {
        int money = 0;
        PreparedStatement st = null;
        try {
            st = GeldSystem.getPlugin().MySQL.con.prepareStatement("SELECT * FROM playermoney WHERE UUID= '" + uuid + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next() ){
               money = rs.getInt("Money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    public static boolean playerExists(String uuid)
    {
        PreparedStatement st = null;
        try {
            st = GeldSystem.getPlugin().MySQL.con.prepareStatement("SELECT * FROM `playermoney` WHERE UUID= '" + uuid + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("UUID") != null) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void addMoney(Player player,int money){
        int current = msm.getMoney(player.getUniqueId().toString());
        int add = money;

        int addedMoney = current + add;

        GeldSystem.getPlugin().MySQL.update("UPDATE `playermoney` SET `Money`=" + addedMoney + " WHERE UUID='" + player.getUniqueId().toString() + "'");
    }
    public static void removeMoney(Player player,int money,String ZuWenigGelt){
        int current = msm.getMoney(player.getUniqueId().toString());
        int remove = money;

        if(!(current - remove < 0)) {
            int removedMoney = current - remove;
            GeldSystem.getPlugin().MySQL.update("UPDATE `playermoney` SET `Money`=" + removedMoney + " WHERE UUID='" + player.getUniqueId().toString() + "'");
        }else{
            EinfacheMethoden.playerMessage(player, ZuWenigGelt);
        }
    }
    public static void setMoney(Player player,int money){
         GeldSystem.getPlugin().MySQL.update("UPDATE `playermoney` SET `Money`=" + money + " WHERE UUID='" + player.getUniqueId().toString() + "'");
    }

    public static MySQLGeldAPI getMySQLMoney() {
        return msm;
    }
}
