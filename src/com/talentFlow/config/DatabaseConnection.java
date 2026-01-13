
package com.talentFlow.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Pavilion X360
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private String url = "jdbc:mysql://localhost:3306/talentflow_db_pro";
    private String user = "root"; // Usuario por defecto de MySQL
    private String pass = ""; // Pon la que usas para entrar a Workbench

    private DatabaseConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            throw  new SQLException("ERROR: no se encontro el DRIVER de MySQL");
        }
        
    } 
    
    public static DatabaseConnection getInstance() throws SQLException{
        if(instance == null || instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
    return connection;
}
    
}
