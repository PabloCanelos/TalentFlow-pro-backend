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
/**
 *
 * @author Pavilion X360
 */
public class EmpleadoDAO {
    public void insertar(Empleado emp) throws SQLException{
        Connection cn = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO empleados (nombre, sueldo, id_departamento)VALUES(?,?,?)";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre());
            ps.setDouble(2, emp.getSueldo());
            ps.setInt(3, emp.getIdDepartamento());
            ps.executeUpdate();
            
       /* } catch (Exception e) {
        }*/
    }
    }
    public boolean eliminarEmpleado(int id) throws SQLException{
        Connection con = DatabaseConnection.getInstance().getConnection();
        String query = "DELETE FROM empleados where id = ?";
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id );
           int filasEliminadas = ps.executeUpdate();
           return filasEliminadas >0;      

        } catch (SQLException e) {
            System.out.println("ERROR al eliminar" + e.getMessage());
            throw e;// Propagamos el error para que el Test sepa que falló
            
        }
    }
    
    public List<Empleado> listaEmpleados(Empleado emp) throws SQLException{
        List<Empleado> lista = new ArrayList<>();
    
        Connection con = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM empleados";
        
        try(PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {
                Empleado e = new Empleado();         
            
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setSueldo(rs.getDouble("sueldo"));
                e.setIdDepartamento(rs.getInt("id_departamento"));

                 lista.add(e);
        }    
            
        } catch (SQLException e) {
            System.out.println("ERROR en el dao" + e.getMessage());
            throw  e;       
    }
        return lista;    
}
    public Empleado buscarPorId(int idBuscado) throws SQLException{
        Empleado emp = null;
        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query= "SELECT * empleado FROM empleados WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            try(ResultSet rs = ps.executeQuery()) {
                //usamos if en vez de while porque solo esperamos un resultado
                if(rs.next()){
                    emp = new Empleado();
                    emp.setId(rs.getInt("id"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setSueldo(rs.getDouble("sueldo"));
                }
                
            } 
            return emp;
            // Si no lo encontró, devolverá null, y el Controlador sabrá qué hacer.
        } 
    }
}