/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentFlow.test;
import com.talentFlow.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author Pablo Canelos
 */
public class TestConexion {
    public static void main(String[] args) {
        try {
    // Simulamos un dato que viene de un formulario (String)
    String sueldoTxt = "2500.80"; 
    
    // Aplicamos el PARSE que mencionaste [cite: 2026-01-12]
    double sueldoProcesado = Double.parseDouble(sueldoTxt); 
    
    // Obtenemos la conexión del Singleton corregido [cite: 2026-01-08]
    Connection con = DatabaseConnection.getInstance().getConnection();
    
    String sql = "INSERT INTO empleados (nombre, sueldo, id_departamento) VALUES (?, ?, ?)";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, "Empleado de Prueba");
    ps.setDouble(2, sueldoProcesado); // El sueldo ya es double gracias al parse
    ps.setInt(3, 1); // ID del departamento Tecnología
    
    ps.executeUpdate();
    System.out.println("¡Éxito! Empleado guardado tras el Parse.");
    
} catch (Exception e) {
    System.out.println("Error en la prueba: " + e.getMessage());
}
    }
        
    
    
    
    
}
