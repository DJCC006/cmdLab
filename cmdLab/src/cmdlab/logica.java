/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdlab;

import java.io.File;

/**
 *
 * @author David
 */
public class logica {
    private static File ruta;
    private String input;
    
    
    
    public void processCommand(String command){
        input= command.trim();
        
        String[] partes = input.split("\\s+",2);//probar que el espacio sirva como separador de comando
        String comando= partes[0].toLowerCase();
        
        
            
        
        
    }
            
    
}
