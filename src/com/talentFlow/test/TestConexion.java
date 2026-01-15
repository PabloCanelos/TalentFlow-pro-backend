/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentFlow.test;
import com.talentFlow.config.DatabaseConnection;
import com.talentFlow.model.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.talentFlow.dao.EmpleadoDAO;
import com.talentFlow.model.Empleado;

/**
 *
 * @author Pablo Canelos
 */
public class TestConexion {
    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO();
    int idABuscar = 101; // Cambia esto por un ID que tengas en tu BD

    System.out.println("--- PRUEBA DE FLUJO DE DATOS ---");

    try {
        // 1. Intentamos buscar al empleado
        Empleado emp = dao.buscarPorId(idABuscar);

        if (emp != null) {
            System.out.println("Empleado encontrado: " + emp.getNombre());
            System.out.println("Sueldo: " + emp.getSueldo());

            // 2. Si existe, intentamos eliminarlo
            System.out.println("\nIntentando eliminar al empleado...");
            if (dao.eliminar(idABuscar)) {
                System.out.println("ÉXITO: Registro borrado de la base de datos.");
            } else {
                System.out.println("FALLO: No se pudo eliminar.");
            }

            // 3. Verificamos que ya no exista
            Empleado comprobacion = dao.buscarPorId(idABuscar);
            if (comprobacion == null) {
                System.out.println("\nConfirmación: El empleado ya no existe en la BD (Resultado null).");
            }

        } else {
            System.out.println("El empleado con ID " + idABuscar + " no existe. Prueba con otro.");
        }

    } catch (Exception e) {
        System.err.println("Ocurrió un error inesperado en el Main: " + e.getMessage());
    }
    
}
   
}
    
    
    
    
        

        
    
    
    
    
}
