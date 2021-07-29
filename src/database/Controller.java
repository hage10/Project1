package database;

import java.sql.*;
import java.util.*;

public class Controller {

    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);
        return connection;
    }

    public Vector<Vector<String>> getOne(String a) throws ClassNotFoundException, SQLException{
        Vector<Vector<String>> data = new Vector<>();

        Connection connection = getConnect();
        PreparedStatement stmt = connection.prepareStatement("Select * from dbo.mytable Where Id = ?");
        stmt.setString(1, a);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String Sid = rs.getString(1);
            String Ssta = rs.getString(2);
            String Sdes = rs.getString(3);
            String Sref = rs.getString(4);
            String Sphase = rs.getString(5);
            String Svotes = rs.getString(6);
            String Scomments = rs.getString(7);

            Vector<String> temp = new Vector<>();
            temp.add(Sid);
            temp.add(Ssta);
            temp.add(Sdes);
            temp.add(Sref);
            temp.add(Sphase);
            temp.add(Svotes);
            temp.add(Scomments);

            data.add(temp);

        }
        connection.close();

        return data;
    }

    public Vector<Vector<String>> getAll() throws ClassNotFoundException, SQLException {
        Vector<Vector<String>> data = new Vector<>();

        Connection connection = getConnect();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from dbo.mytable");
        while (rs.next()) {
            String Sid = rs.getString(1);
            String Ssta = rs.getString(2);
            String Sdes = rs.getString(3);
            String Sref = rs.getString(4);
            String Sphase = rs.getString(5);
            String Svotes = rs.getString(6);
            String Scomments = rs.getString(7);

            Vector<String> temp = new Vector<>();
            temp.add(Sid);
            temp.add(Ssta);
            temp.add(Sdes);
            temp.add(Sref);
            temp.add(Sphase);
            temp.add(Svotes);
            temp.add(Scomments);

            data.add(temp);
        }
        return data;
    }
}
