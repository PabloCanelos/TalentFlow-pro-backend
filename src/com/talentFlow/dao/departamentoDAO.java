
package com.talentFlow.dao;
import com.talentFlow.config.DatabaseConnection;
import com.talentFlow.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.talentFlow.model.Departamento;
/**
 *
 * @author Pavilion X360
 */
public class DepartamentoDAO {
    public boolean insertarNuevoDepartamento(Departamento dep) throws SQLException{
        String query = "INSERT INTO departamentos(nombre_departamento) VALUES(?)";
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, dep.getNombreDepartamento());
            return ps.executeUpdate()>0;
            
       
        } catch (SQLException e) {
            System.out.println("Error: error al insertar departamento " + e.getMessage());
        }
        return false;
        
    }
    
    public Departamento buscarDepartamentoPorId(int id) throws SQLException {
        String query =  "SELECT * FROM departamentos WHERE id_departamento = ?";
        Departamento dep = null;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    dep = new Departamento();
                    dep.setIdDepartamento(rs.getInt("id_departamento"));
                    dep.setNombreDepartamento(rs.getString("nombre_departamento"));
                }     
                
            } catch (SQLException e) {
                System.out.println("Error EN departamentoDAO"+ e.getMessage());
            }
            return dep;
        }
        
    }
    
    public Departamento buscarDepartamentoPorNombre(String nombre)throws SQLException{
        String query = "SELECT * FROM departamentos WHERE nombre_departamento = ?";
        Departamento dep = null;
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nombre);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    dep = new Departamento();
                    dep.setNombreDepartamento(rs.getString("nombre_departamento"));
                    dep.setIdDepartamento(rs.getInt("id_departamento"));
                }
                
            } 
            return dep;
            
        }
        
    }
    
    public List<Departamento> listarDepartamentos()throws SQLException{
        String query = "SELECT * FROM departamentos";
        List<Departamento> listaDepartamentos = new ArrayList<>();
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Departamento dep = new Departamento();
                
                dep.setIdDepartamento(rs.getInt("id_departamento"));
                dep.setNombreDepartamento(rs.getString("nombre_departamento"));
                listaDepartamentos.add(dep);               
            }        
            
        } catch (SQLException e) {
            System.out.println("Error en listar Deapartamentos" + e.getMessage());
        }
        return listaDepartamentos;
    }

    public boolean eliminarDepartamento(int id) throws SQLException{
        String query= "DELETE FROM departamentos WHERE id_departamento = ?";
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error: revise conexion" + e.getMessage());
        }
        return false;
        
    }
    
    public boolean actualizarDepartamento(Departamento dep) throws SQLException{
        String query = "UPDATE departamentos SET nombre_departamento = ? WHERE id_departamento = ?";
        
        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dep.getNombreDepartamento());
            ps.setInt(2, dep.getIdDepartamento());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas >0;
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar departamento" + e.getMessage());
            throw e;
        }
    }
   
    
}
