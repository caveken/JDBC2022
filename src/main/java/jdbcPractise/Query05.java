package jdbcPractise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        // 1) Driver yukle
        Class.forName("org.postgresql.Driver");

        // 2) Baglanti olustur (Connection)
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed",
                "postgres", "cvdn77kn");
        Statement st = con.createStatement();
        //SORU: Öğrenciler tablosuna yen bir kayıt ekleyin (300, 'Sena Can', 12, 'K')
        //int s1=st.executeUpdate(("insert into ogrenciler values (302, 'Sena Can',12,'K')"));
        //System.out.println(s1+" satır eklendi");

        /*
        //1. YOL
        String [] veri={"insert into ogrenciler values (400, 'Sena Can',12,'K')",
                "insert into ogrenciler values (401, 'Sena Can',12,'K')",
                "insert into ogrenciler values (402, 'Sena Can',12,'K')",
                "insert into ogrenciler values (403, 'Sena Can',12,'K')"};
        int count=0;
        for (String each:veri){
            //count=count+st.executeUpdate(each);
            count++;
        }
        System.out.println(count+" data eklendi");
        */

        //2.YOL
        String [] veri2={"insert into ogrenciler values (600, 'Sena Can',12,'K')",
                "insert into ogrenciler values (601, 'Sena Can',12,'K')",
                "insert into ogrenciler values (602, 'Sena Can',12,'K')",
                "insert into ogrenciler values (603, 'Sena Can',12,'K')"};
        int count=0;
        for (String each:veri2) {
            st.addBatch(each);
            count++;
        }
        st.executeBatch();
        System.out.println(count);

    }
}
