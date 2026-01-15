/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentFlow.dao;
import com.talentFlow.config.DatabaseConnection;
import com.talentFlow.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Pavilion X360
 */
public class EmpleadoDAO {
    
    public boolean conectar() throws SQLException{
        try{
            Connection cn = DatabaseConnection.getInstance().getConnection();
            if(cn != null && !cn.isClosed()) return true;
            
            else{
                return false;
                
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return false;
    }
    
    public boolean insertar(Empleado emp) throws  SQLException{
        String query = "INSERT INTO empleados(nombre, sueldo,id_departamento) VALUES(?,?,?)";
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, emp.getNombre());
            ps.setDouble(2, emp.getSueldo());
            ps.setInt(3, emp.getIdDepartamento());
            
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas >0;
            
        } catch (SQLException e) {
            System.out.println("Error: al conectar la base de datos" + e.getMessage());
        }
        return false;
        
    }
    
    public Empleado buscarPorId(int id) throws SQLException{
        String query = "SELECT * FROM empleados WHERE id = ?";
        Empleado emp = null;
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    emp = new Empleado();
                    emp.setId(rs.getInt("id"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setSueldo(rs.getDouble("sueldo"));
                    emp.setIdDepartamento(rs.getInt("id_departamento"));
                }
                
            } catch (SQLException e) {
                System.out.println("ERROR: en EmpleadoDAO.BuscarPorId()");
                
            }         
        } return emp;
       
    }
    public boolean eliminar(int id){
        String query= "DELETE FROM empleados WHERE id = ?";
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas >0){
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion " + e.getMessage());
        }
        return false;      
    }
    
    public List listarEmpleados(){
        String query = "SELECT * FROM empleados";
        List<Empleado> lista = new ArrayList<>();
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setSueldo(rs.getDouble("sueldo"));
                emp.setIdDepartamento(rs.getInt("id_departamento"));
                
                lista.add(emp);
                return lista;                     
            }
       
        } catch (SQLException e) {
            System.out.println("Error en tecnico en listarEmpleados()" + e.getMessage());
        }
        return lista;
        
    }
    public List<Empleado> buscarPorNombre(String nombre) throws SQLException{
        String query = "SELECT * FROM empleado WHERE nombre LIKE ?";
        List<Empleado> resultados = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            
            if(nombre == null || nombre.trim().isEmpty()){
                return resultados;
            }          
            //pasamos el parametro 
            ps.setString(1, "%" + nombre + "%");
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                Empleado emp = new  Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setSueldo(rs.getDouble("sueldo"));
                emp.setIdDepartamento(rs.getInt("id_departamento"));
                
                resultados.add(emp);                
            }      
            
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre" + e.getMessage());
        }
        return resultados;    
    }
    }
}