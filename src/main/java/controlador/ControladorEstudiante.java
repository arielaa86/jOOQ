/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTable;
import modelo.Estudiante;
import principal.JFramePrincipal;
import servicios.EstudianteService;
import vista.ActualizarJDialog;
import vista.CrearJDialog;
import vista.MostrarJDialog;

/**
 *
 * @author ariel
 */
public class ControladorEstudiante {

    public static void Crear(JFramePrincipal padre) {

        CrearJDialog ventana = new CrearJDialog(padre, true);
        ventana.setLocationRelativeTo(padre);
        ventana.setVisible(true);

    }

    public static void Mostrar(JFramePrincipal padre, int idEstudiante) {

        MostrarJDialog ventana = new MostrarJDialog(padre, true, idEstudiante);
        ventana.setLocationRelativeTo(padre);
        ventana.setVisible(true);

    }
    
    
    public static void Actualizar(JFramePrincipal padre, Estudiante estudiante) {

        ActualizarJDialog ventana = new ActualizarJDialog(padre, true, estudiante);
        ventana.setLocationRelativeTo(padre);
        ventana.setVisible(true);

    }
    
    
    
    public static void Eliminar(Estudiante estudiante) throws SQLException {

       
        EstudianteService.eliminar(estudiante);
        

    }
    
    
    

}
