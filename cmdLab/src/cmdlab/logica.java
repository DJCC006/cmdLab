/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdlab;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class logica extends comandos {
    private static File ruta;
    private static String input;
    private static File root;
    
    public static void main(String[] args) {
         Scanner lea = new Scanner(System.in);
        lea.useDelimiter("\n");
        
        
        boolean control = true;
        while(control){
            System.out.print("COMAND LINE>");
            input = lea.next().trim();
            processCommand(input);
        }
          
    }
    
    
    public static void processCommand(String command){
         
            String[] partes = command.split(" ",2);//probar que el espacio sirva como separador de comando
            String comando= partes[0];
            
            switch(comando){
                
                case "Mkdir":
                    if(partes.length<2 || partes[1].isEmpty()){
                        System.out.println("Estructura incorrecta");
                        return;
                    }
                    
                    String argumento = partes[1].trim();
                    
                    comandos.cmMkdir(argumento, ruta);
                    
                    break;
                
                    
                case "Mfile":
                    if(partes.length<2 || partes[1].isEmpty()){
                        System.out.println("Estructura incorrecta");
                        return;
                    }
                    
                    String argumentof = partes[1].trim();
                    
                    try{
                        comandos.cmMfile(argumentof, ruta);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    
                    break;
                    
                    
                case "Rm":
                    
                    if(partes.length<2 || partes[1].isEmpty()){
                        System.out.println("Estructura incorrecta");
                        return;
                    }
                    
                    String argumentoR = partes[1].trim();
                    
                    comandos.cmRm(argumentoR, ruta);
                    
                    break;
                
                case "Cd":
                      if(partes.length<2 || partes[1].isEmpty()){
                        System.out.println("Estructura incorrecta");
                        return;
                    }
                    
                    String argumentocd = partes[1].trim();
                    
                    comandos.cmCd(argumentocd, ruta);
                    
                    break;
                    
                case "...":
                    try{
                        comandos.cmReturn();
                    }catch(IOException e2){
                        System.out.println("Error al volver");
                    }
                    break;
                
                case "Dir":
                    comandos.cmDir();
                    break;
                    
                case "Date":
                    comandos.cmDate();
                    break;
                    
                case "Time":
                    comandos.cmTime();
                    break;
                    
                    
                default:
                    System.out.println("Comando Invalido "+comando);
            }
        
      
    }
    
   public static File getRoot(){
       return root;
   }
    
    
}
