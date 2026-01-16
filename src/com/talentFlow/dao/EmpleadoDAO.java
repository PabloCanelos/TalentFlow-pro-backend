/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentFlow.dao;
import com.talentFlow.config.DatabaseConnection;
import com.talentFlow.model.Departamento;
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
            
            int idDepto = emp.getDepartamento().getIdDepartamento();
            ps.setInt(3, idDepto);
        
            ps.executeUpdate();
            System.out.println("✅ Empleado insertado con éxito.");
            int filasAfectadas = ps.executeUpdate();
        
            if (filasAfectadas > 0) {
            System.out.println("✅ Empleado insertado con éxito.");
            return true; // <--- ÉXITO REAL
        }
        } catch (SQLException e) {
            System.out.println("Error: al conectar la base de datos" + e.getMessage());
            throw e;
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
                    
                    int idDeptoFK = rs.getInt("id_departamento");
                
                // 2. Usamos el otro DAO para traer el objeto Departamento completo
                DepartamentoDAO deptoDAO = new DepartamentoDAO();
                Departamento deptoCompleto = deptoDAO.buscarDepartamentoPorId(idDeptoFK);
                
                // 3. Se lo "inyectamos" al empleado
                emp.setDepartamento(deptoCompleto);
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
                
                Departamento dep = new Departamento();
            // 2. Le asignamos el ID que viene de la base de datos
            dep.setIdDepartamento(rs.getInt("id_departamento"));
            
            // 3. Le pasamos el objeto completo al empleado
            emp.setDepartamento(dep);
                
                lista.add(emp);                  
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
                
                Departamento dep = new Departamento();
                dep.setIdDepartamento(rs.getInt("id_departamento"));
                
                // Si quieres que el objeto esté "lleno" (con nombre de depto), 
                // podrías llamar aquí al DepartamentoDAO, pero por ahora 
                // esto es suficiente para que el código compile y funcione.
                emp.setDepartamento(dep);
                
                resultados.add(emp);                
            }      
            
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre" + e.getMessage());
        }
        return resultados;    
    }
    }
}