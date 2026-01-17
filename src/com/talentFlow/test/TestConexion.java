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
import com.talentFlow.dao.DepartamentoDAO;
import com.talentFlow.model.Departamento;

/**
 *
 * @author Pablo Canelos
 */
public class TestConexion {
    public static void main(String[] args) {
        // 1. Instanciamos el DAO
        // 1. Necesitamos un objeto departamento primero
Departamento d1 = new Departamento();
d1.setIdDepartamento(1);
d1.setNombreDepartamento("Tecnología");

// 2. Ahora creamos al empleado pasándole el OBJETO completo
Empleado emp = new Empleado(101, "Carlos Gomez", 2500.0, d1);

// 3. Probamos que funcione
System.out.println("Empleado: " + emp.getNombre());
System.out.println("Depto: " + emp.getDepartamento().getNombreDepartamento());
      
    }
      
}
