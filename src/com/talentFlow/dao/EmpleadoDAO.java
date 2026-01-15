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
    
//    public List<Empleado> listar() throws SQLException{
//        List<Empleado> lista = new ArrayList<>();
//        String query = "SELECT * FROM empleados";
//        
//        
//        try(Connection cn = DatabaseConnection.getInstance().getConnection();
//            PreparedStatement ps = cn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Empleado emp = new Empleado();
//                emp.setId(rs.getInt("id"));
//                emp.setNombre(rs.getString("nombre"));
//                emp.setSueldo(rs.getDouble("sueldo"));
//                emp.setIdDepartamento(rs.getInt("id_departamento"));
//                lista.add(emp);
//                
//            }        
//            
//        } catch (SQLException e) {
//            System.out.println("Error en la base de datos: " +e.getMessage());
//        }
//        return lista;
//        
//    }
//    
//
//    
//    
//    
//    public boolean eliminarEmpleado(int id) throws SQLException{
//        
//        String query = "DELETE FROM empleados where id = ?";
//        
//        try(Connection con = DatabaseConnection.getInstance().getConnection();
//            PreparedStatement ps = con.prepareStatement(query)) {
//            ps.setInt(1, id );
//           int filasEliminadas = ps.executeUpdate();
//           return filasEliminadas >0;      
//
//        } catch (SQLException e) {
//            System.out.println("ERROR al eliminar" + e.getMessage());
//            throw e;// Propagamos el error para que el Test sepa que falló
//            
//        }
//    }
//    
//    public List<Empleado> listaEmpleados(Empleado emp) throws SQLException{
//        List<Empleado> lista = new ArrayList<>();
//    
//        Connection con = DatabaseConnection.getInstance().getConnection();
//        String query = "SELECT * FROM empleados";
//        
//        try(PreparedStatement ps = con.prepareStatement(query);
//            ResultSet rs = ps.executeQuery()){
//            
//            while (rs.next()) {
//                Empleado e = new Empleado();         
//            
//                e.setId(rs.getInt("id"));
//                e.setNombre(rs.getString("nombre"));
//                e.setSueldo(rs.getDouble("sueldo"));
//                e.setIdDepartamento(rs.getInt("id_departamento"));
//
//                 lista.add(e);
//        }    
//            
//        } catch (SQLException e) {
//            System.out.println("ERROR en el dao" + e.getMessage());
//            throw  e;       
//    }
//        return lista;    
//}
//    
//    public boolean buscarPorId(int id) throws SQLException{
//        Empleado emp = null;
//        String query= "SELECT id, nombre, sueldo, id_departamento FROM empleados WHERE id= ?";
//        try(Connection conn = DatabaseConnection.getInstance().getConnection();
//            PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setInt(1, emp.getId() );
//            ps.setString(2, emp.getNombre());
//            ps.setDouble(3, emp.getSueldo());
//            ps.setInt(4, emp.getIdDepartamento());
//            
//            ps.executeQuery();
//            
//        } catch (SQLException e) {
//            System.out.println("ERROR verifique conexion" + e.getMessage());
//            throw e;
//        }
//        return Empleado;
//    }
//    public Empleado buscarPorId(int idBuscado) throws SQLException{
//        Empleado emp = null;
//        Connection conn = DatabaseConnection.getInstance().getConnection();
//        String query= "SELECT * empleado FROM empleados WHERE id = ?";
//        try(PreparedStatement ps = conn.prepareStatement(query)) {
//            try(ResultSet rs = ps.executeQuery()) {
//                //usamos if en vez de while porque solo esperamos un resultado
//                if(rs.next()){
//                    emp = new Empleado();
//                    emp.setId(rs.getInt("id"));
//                    emp.setNombre(rs.getString("nombre"));
//                    emp.setSueldo(rs.getDouble("sueldo"));
//                }
//                
//            } 
//            return emp;
//            // Si no lo encontró, devolverá null, y el Controlador sabrá qué hacer.
//        } 
//    }
    
   //metodo de conexion
    
//    public boolean conectar(){
//        try {
//            Connection conn = DatabaseConnection.getInstance().getConnection();
//            if(conn != null && conn.isClosed()) return  true;
//        
//            
//        } catch (SQLException e) {
//            System.out.println("ERROR en la conexion" + e.getMessage());
//        }
//        return false;
//    }
//    public Empleado contarEmpleado(Empleado emp) throws SQLException{
//        int total=0;
//        String query = "SELECT COUNT(*) FROM empleados";
//        
//        
//        try(Connection conn = DatabaseConnection.getInstance().getConnection();
//            PreparedStatement ps = conn.prepareStatement(query)) {
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                total= rs.getInt(1);
//                
//            }            
//        } catch (SQLException e) {
//            System.out.println("Error al contar" + e.getMessage());
//            throw e;
//        }
//        return total;
//        
//    }
}