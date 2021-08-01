package database;

import java.sql.*;
import java.util.*;

public class Controller {

    //Khoi tao ket noi toi SQL
    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);
        return connection;
    }

    //Ham Import file input
    public void Import(String a) throws ClassNotFoundException, SQLException{
        Connection connection = getConnect();

        a = "bulk insert CVEList from \'" + a + "\' with(fieldterminator = \',\', rowterminator = \'\\n\', Format = \'CSV\')";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(a);
    }

    //Ham del du lieu
    public void DelAll() throws ClassNotFoundException,SQLException{
        Connection connection = getConnect();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("Delete from CVEList");
    }

    //Tra ve vector chua du lieu
    public Vector<Vector<String>> getOne(String a) throws ClassNotFoundException, SQLException{
        Vector<Vector<String>> data = new Vector<>();

        Connection connection = getConnect();
        PreparedStatement stmt = connection.prepareStatement("Select * from CVEList Where Id = ?");
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

    //Ham xuat toan bo data
    public Vector<Vector<String>> getAll() throws ClassNotFoundException, SQLException {
        Vector<Vector<String>> data = new Vector<>();

        Connection connection = getConnect();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from CVEList");
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

    //Tra ve vector chua phan header
    public Vector<String> getHeader(){
        Vector<String> header = new Vector<String>();
        header.add("Id");
        header.add("Sta");
        header.add("Des");
        header.add("Ref");
        header.add("Phase");
        header.add("Votes");
        header.add("Comments");
        return header;
    }

    //Tra ve String hien thi du lieu tu data
    public String displaySelectedRow(Vector<Vector<String>> data,int index) {
        Vector<String> SelectedRow = data.get(index);
        String result;
        result = "Id:   " + SelectedRow.get(0)
                + "\n\nStatus:   " + SelectedRow.get(1)
                + "\n\nDescription:\n" + SelectedRow.get(2)
                + "\n\nReference:|   " + SelectedRow.get(3)
                + "\n\nPhase:   " + SelectedRow.get(4)
                + "\n\nVotes:|  " + SelectedRow.get(5)
                + "\n\nComments:|    " + SelectedRow.get(6);
        String s = result.replaceAll("\\|", "\n\\|");
        return s;

    }

    //
}