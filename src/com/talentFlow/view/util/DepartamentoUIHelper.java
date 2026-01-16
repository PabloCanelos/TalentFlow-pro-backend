package com.talentflow.view.util;

import javax.swing.*;
import java.awt.Color;

/**
 * Clase auxiliar para gestionar la lógica visual y validaciones de la interfaz.
 * Evita saturar el Source del Frame con códigos repetitivos.
 */
public class DepartamentoUIHelper {

    // Colores para indicar estados al usuario
    private static final Color COLOR_ERROR = new Color(255, 204, 204); // Rojo suave
    private static final Color COLOR_EDICION = new Color(204, 229, 255); // Azul suave
    private static final Color COLOR_NORMAL = Color.WHITE;

    /**
     * Resetea el formulario al estado inicial de "Selección de Menú".
     */
    public static void limpiarFormulario(JTextField campo, JLabel instruccion) {
        campo.setText(""); 
        campo.setBackground(COLOR_NORMAL);
        instruccion.setForeground(Color.BLACK);
        instruccion.setText("MENÚ: 1. Agregar | 2. Eliminar | 3. Listar | 4. Buscar | 5. Editar");
    }

    /**
     * Cambia dinámicamente el mensaje de ayuda según el número que el usuario elija.
     * Implementa tu idea de "Interfaz Dinámica".
     */
    public static void prepararEntradaSegunOpcion(int opcion, JTextField campo, JLabel instruccion) {
        campo.setText(""); 
        campo.requestFocus();
        campo.setBackground(COLOR_EDICION);
        instruccion.setForeground(new Color(0, 102, 204));

        switch (opcion) {
            case 1: instruccion.setText("MODO AGREGAR: Ingrese el nombre del nuevo departamento:"); break;
            case 2: instruccion.setText("MODO ELIMINAR: Ingrese el ID numérico a eliminar:"); break;
            case 3: instruccion.setText("MODO LISTAR: Presione ACEPTAR para cargar los datos."); break;
            case 4: instruccion.setText("MODO BUSCAR: Ingrese el nombre o ID a consultar:"); break;
            case 5: instruccion.setText("MODO EDITAR: Ingrese el ID del departamento a modificar:"); break;
            default:
                marcarError(campo, instruccion, "Opción inválida. Elija de 1 a 5.");
                break;
        }
    }

    /**
     * Tu herramienta de Parse: Convierte texto a número de forma segura [cite: 2026-01-12].
     * @return El número si es válido, o -1 si hay error de formato.
     */
    public static int intentarParse(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1; // Indica que no es un número válido [cite: 2026-01-12]
        }
    }

    /**
     * Lógica de confirmación (Pop-up) antes de tocar el DAO o Singleton [cite: 2026-01-08].
     */
    public static boolean confirmarOperacion(JFrame frame, String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(
            frame, 
            mensaje, 
            "Confirmación de Seguridad", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        return (respuesta == JOptionPane.YES_OPTION);
    }

    /**
     * Indica visualmente un error al usuario.
     */
    public static void marcarError(JTextField campo, JLabel instruccion, String mensaje) {
        campo.setBackground(COLOR_ERROR);
        instruccion.setText(mensaje);
        instruccion.setForeground(Color.RED);
    }

    public static void limpiarFormnulario(JTextField txtNombreDepartamento, JLabel lblInstruccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}