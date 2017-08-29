/*
 * EntityRemoto.java
 *
 * Created on 05/08/2017, 11:00:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package utilitario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Elida
 */
public class Entity {

 public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException  {
        try {
            String dbDriver = "jdbc:mysql:";
            String dbUrl = "//localhost/trabalho_de_java";
            String dbUser = "root";
            String dbPwd = "jcuser";
           // System.out.println("URL="+dbDriver+dbUrl+" User="+dbUser+" pwd="+ dbPwd);
            Connection con = DriverManager.getConnection(dbDriver+dbUrl, dbUser, dbPwd);
            //con.setAutoCommit(false);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
