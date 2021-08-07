package database;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseInfo {
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String dbURL = "";

    public static String dbUser = "";
    public static String dbPass = "";
    public static String dbTable = "";

    public static void getSetting() throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\setting.txt"));
        DatabaseInfo.dbURL = buff.readLine();
        DatabaseInfo.dbTable = buff.readLine();
        buff.close();
    }

    public static void setSetting() throws IOException {
        BufferedWriter buff1 = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+ "\\setting.txt"));
        buff1.write(DatabaseInfo.dbURL);
        buff1.write("\n");
        buff1.write(DatabaseInfo.dbTable);
        buff1.flush();
        buff1.close();
    }

}


