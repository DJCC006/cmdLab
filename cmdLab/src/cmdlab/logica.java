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
    
    
    public static String processCommand(String command){
         
            String[] partes = command.split(" ",2);//probar que el espacio sirva como separador de comando
            String comando= partes[0];
            
            switch(comando){
                
                case "Mkdir":
                    if(partes.length<2 || partes[1].isEmpty()){
                        return "Estructura Incorrecta";
                    }
                    
                    String argumento = partes[1].trim();
                    
                    if(comandos.cmMkdir(argumento, ruta)){
                        return "Directorio ["+argumento+"] creado Exitosamente";
                    }else{
                        return "NO SE PUDO CREAR DIRECTORIO";
                    }
                    
                    
                
                    
                case "Mfile":
                    if(partes.length<2 || partes[1].isEmpty()){
                       return ("Estructura incorrecta");
                       
                    }
                    
                    String argumentof = partes[1].trim();
                    
                    try{
                        if(comandos.cmMfile(argumentof, ruta)){
                            return "Archivo ["+argumentof+"] creado Exitosamente";
                        }else{
                            return "NO SE PUDO CREAR ARCHIVO";
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
        
                    
                    
                case "Rm":
                    
                    if(partes.length<2 || partes[1].isEmpty()){
                        return ("Estructura incorrecta");
                    }
                    
                    String argumentoR = partes[1].trim();
                    
                    if(comandos.cmRm(argumentoR, ruta)){
                        return "Se elimino ["+argumentoR+"] Exitosamente";
                    }else{
                        return "NO SE PUDO ELIMINAR EL ARCHIVO";
                    }
                    
                    
                
                case "Cd":
                      if(partes.length<2 || partes[1].isEmpty()){
                        return("Estructura incorrecta");
                    }
                    
                    String argumentocd = partes[1].trim();
                    return comandos.cmCd(argumentocd, ruta);
                    
                    
                case "...":
                    try{
                       return comandos.cmReturn();
                    }catch(IOException e2){
                        return ("Error al volver");
                    }
            
                case "Dir":
                    return comandos.cmDir();
                    
                case "Date":
                    return comandos.cmDate();      
                case "Time":
                    return  comandos.cmTime();
                    
                default:
                    return ("Comando Invalido "+comando);
            }
    }
    
   public static File getRoot(){
       return root;
   }
   
   public static String getPrompt() {
       return comandos.getCurrentDir().getAbsolutePath();
   }
    
}
