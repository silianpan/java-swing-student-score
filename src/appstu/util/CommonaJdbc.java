package appstu.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonaJdbc {
    public static Connection conection = null;
    
    public CommonaJdbc() {
        getCon();
    }
    
    private Connection getCon() {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=DB_Student ", "sa", "");

            Class.forName("com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student?useSSL=false&serverTimezone=UTC","root","root");
        } catch (java.lang.ClassNotFoundException classnotfound) {
            classnotfound.printStackTrace();
        } catch (java.sql.SQLException sql) {
            new appstu.view.JF_view_error(sql.getMessage());
            sql.printStackTrace();
        }
        return conection;
    }
}
