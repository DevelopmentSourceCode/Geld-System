package de.ayley.geldsystem.API;

import de.ayley.geldsystem.GeldSystem.GeldSystem;

public class Strings {
    //MySQL
    public static String Host = GeldSystem.getPlugin().getConfig().getString("MySQL.Host");
    public static String Database = GeldSystem.getPlugin().getConfig().getString("MySQL.Database");
    public static String User = GeldSystem.getPlugin().getConfig().getString("MySQL.User");
    public static String Password = GeldSystem.getPlugin().getConfig().getString("MySQL.Password");


    //Allgemein
    public static String Prefix = GeldSystem.getPlugin().getConfig().getString("Messages.Prefix");
    public static String KeinKonsolenBefehl = GeldSystem.getPlugin().getConfig().getString("Messages.KeinKonsolenBefehl");
    public static String KeineBerechtigung = GeldSystem.getPlugin().getConfig().getString("Messages.KeineBerechtigung");
    public static String KeinVault = GeldSystem.getPlugin().getConfig().getString("Messages.KeinVault");
    public static String MitVault = GeldSystem.getPlugin().getConfig().getString("Messages.MitVault");

    //Plugin startet
    public static String Start = GeldSystem.getPlugin().getConfig().getString("Messages.Start");
    public static String Gestartet = GeldSystem.getPlugin().getConfig().getString("Messages.Gestartet");
    public static String Fehler = GeldSystem.getPlugin().getConfig().getString("Messages.Fehler");

    //Money
    public static int StartGeld = GeldSystem.getPlugin().getConfig().getInt("Money.StartGeld");
    public static String KontoErstellt = GeldSystem.getPlugin().getConfig().getString("Money.KontoErstellt");
    public static String Währung = GeldSystem.getPlugin().getConfig().getString("Money.Waehrung");
    public static String DeinGeld = GeldSystem.getPlugin().getConfig().getString("Money.DeinGeld");


}
