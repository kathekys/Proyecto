package proyectofinal;

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
public class ConexionBD {


     Connection conecta;
     Statement st;
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conecta = DriverManager.getConnection("jdbc:mysql://localhost/basededatosproyecto", "root", "");
            System.out.println("conexion exitosa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conecta;
    
}
//    
    Statement createStatement(){
        throw new UnsupportedOperationException("No soportado");
    }
}
