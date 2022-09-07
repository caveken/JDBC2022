package jdbcPractise;

import jdbc.DBWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



    public class  Query04{


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1) Driver yukle
        Class.forName("org.postgresql.Driver");

        // 2) Baglanti olustur (Connection)
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed",
        "postgres", "cvdn77kn");
        // Statement st = con.createStatement();
        PreparedStatement ps = con.prepareStatement("insert into ogrenciler values(?,?,?,?) ");
        ps.setInt(1, 200);
        ps.setString(2, "Veli Can");
        ps.setString(3, "12");
        ps.setString(4, "E");
        ps.executeUpdate();
        System.out.println("Veri girisi yapildi");

        con.close();
        ps.close();

    }
}
