package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "cvdn77kn");
        Statement st = con.createStatement();


        //1. Örnek: Prepared statement kullanarak
        // company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. adım: prepared statement query olustur

        String sql1="update companies set number_of_employees= ? where company=?";

        //2. adım: preparedstatement objesini olustur.
        PreparedStatement pst1=con.prepareStatement(sql1);

        //3. adım: set...() metotları ile ? için deger gir
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4. adım: execute query
        System.out.println(pst1.executeUpdate()+" satir güncellendi");

        String sql2="select * from companies";
        ResultSet result1=st.executeQuery(sql2);

        while (result1.next()){
            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));
        }

        //Google için değişiklik
        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");
        pst1.executeUpdate();
        String sql3="select * from companies";
        ResultSet result2=st.executeQuery(sql3);
        while (result2.next()){
            System.out.println(result2.getInt(1)+"--"+result2.getString(2)+"--"+result2.getInt(3));

        }

        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.

        String sql4="select * from ?";
        PreparedStatement pst2=con.prepareStatement(sql4);
        pst2.setString(1,"companies");
        ResultSet result3=pst2.executeQuery(sql4);//SOR SOR SOR:PreparedStatement ile sorgu satirlari alan sorgu yontemleri kullanilamaz. hatası verdi.
                                                  // ResultSet result2=st.executeQuery(sql3); Kullanımı standart ve zorunlu mu
        while(result3.next()){
            System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+result3.getInt(3));
        }








    }
}
