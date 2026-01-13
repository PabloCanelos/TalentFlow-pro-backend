
package com.talentFlow.model;

/**
 *
 * @author Pavilion X360
 */
public class Empleado {
    private int id;
    private String nombre;
    private double sueldo;
    private int idDepartamento;
    
    // Constructor vacío (necesario para frameworks)
    public Empleado(){}

    public Empleado(int id, String nombre, double sueldo, int idDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.idDepartamento = idDepartamento;
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

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
    
    
}
