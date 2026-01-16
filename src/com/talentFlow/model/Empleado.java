
package com.talentFlow.model;
import com.talentFlow.model.Departamento;
/**
 *
 * @author Pavilion X360
 */
public class Empleado {
    private int id;
    private String nombre;
    private double sueldo;
    private Departamento departamento;
    
    // Constructor vacío (necesario para frameworks)
    public Empleado(){}

    public Empleado(int id, String nombre, double sueldo, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.departamento = departamento;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }



    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
    
}
