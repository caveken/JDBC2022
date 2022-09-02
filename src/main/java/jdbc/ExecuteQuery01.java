package jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "cvdn77kn");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1. Yol OFFSET ve FETCH NEXT kullanarak
        String sql1 = "SELECT company, number_of_employees\n" +
                "from companies\n" +
                "order by number_of_employees DESC\n" +
                "offset 1 ROW\n" +
                "fetch NEXT 1 ROW ONLY";


        ResultSet result1=st.executeQuery(sql1);
        while (result1.next()){
            System.out.println(result1.getString("company")+"---"+result1.getInt("number_of_employees"));
        }

        //2. Yol Subquery kullanarak
        String sql2="\n" +
                "select company, number_of_employees\n" +
                "from companies\n" +
                "where number_of_employees=(select max(number_of_employees) \n" +
                "\t\t\t\t\t\t   from companies\n" +
                "\t\t\t\t\t\t   where number_of_employees<(select max(number_of_employees)\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tfrom companies))\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t";
        ResultSet result2=st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getString("company")+"---"+result2.getInt("number_of_employees"));
        }

        con.close();
        st.close();
        result1.close();
        result2.close();
    }
}
