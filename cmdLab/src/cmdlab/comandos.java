/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdlab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David
 */
public class comandos {
    
    private static File currentDir;
    private File rootDir=logica.getRoot();
    
    static public void cmCd(String cd, File ruta){
        ruta = new File(cd);
        currentDir=ruta;
        System.out.println("Ruta cambiada a "+cd);
    }
    
    
    
 
    
    
     static public boolean cmMfile(String nombre, File f) throws IOException{
         //String rutaPath = System.getProperty("user.dir");
         
         File newArch = new File(currentDir,nombre);//seteo de ruta en base al nombre del nuevo archivo
         
         if(newArch.exists()){
             System.out.println("El archivo ya existe");
             return false;
         }else{
             System.out.println("El archivo se ha creado con exito");
             return newArch.createNewFile();
         }
     }
     
     
     static public boolean cmMkdir(String nombre, File f){
         //seteo de ruta
         //String rutaPath = System.getProperty("user.dir");
         f = new File(currentDir, nombre);         
         
         if(f.exists()){
             System.out.println("El Directorio ya existe");
             return false;
         }else{
             System.out.println("Se ha creado el directorio de manera exitosa");
             return f.mkdirs();
         }
     }
     
     
     
     static void cmRm(String nombre, File f){
         //seteo de ruta
         //String rutaPath = System.getProperty("user.dir");
         f = new File(currentDir, nombre);
         
         if(f.isDirectory()){
             if(!f.exists()){
                 System.out.println("Este directorio no EXISTE");
             }else{
                 RmAux(f);
                 System.out.println("El directorio se ha eliminado con exito");
             }
         }
         
          System.out.println("El archivo se ha eliminado con exito");
          f.delete();
     }
     
     static private boolean RmAux(File f){
        
        if(f.isDirectory()){
            for(File arc:f.listFiles()){
                RmAux(arc);
            }
        }
        return f.delete();
    }
     
     
     
     static public void cmReturn() throws IOException{
        File padre = currentDir.getCanonicalFile().getParentFile();
        if(padre== null){
            System.out.println("Ya se encuentra en la raiz");
        }
         System.out.println("Regresando a "+padre.getName());
         currentDir=padre;
     }
     
    static public String cmDate(){
        Calendar hoy = Calendar.getInstance();
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat formater = new SimpleDateFormat(formatoFecha);
        String fFormateada = formater.format(hoy.getTime());
        System.out.println("Fecha formateada: "+fFormateada);
        return fFormateada;
    }
    
    static public String cmTime(){
        Calendar hoy = Calendar.getInstance();
        int hora= hoy.get(Calendar.HOUR_OF_DAY);
        int minuto = hoy.get(Calendar.MINUTE);
        String day="";
        
        if(hora<12){
            day="AM";
        }else if(hora>=12){
            day="PM";
        }
        
        
        String time = String.format("%02d:%02d", hora, minuto);
        time+=" "+day;
        System.out.println("Hora actual: "+time);
        return time;   
    }
    
    
    static public String cmDir(){
        if(currentDir.isDirectory()){
            String content="";
             content+=("Folder: "+currentDir.getName()+"\n");
           
             
             for(File child: currentDir.listFiles()){
                 content+=(new Date(child.lastModified()));
                 if(child.isDirectory()){
                     content+=(" \t<DIR>\t ");
                 }
                 if(child.isFile()){
                     content+=( "\t    \t" );
                     content+= (child.length());
                 }
                 content+=( "\t"+child.getName()+"\n");
             }
             
             System.out.println(content);
             return content;
        }else{
            String content="";
            content+=" Accion no permitida";
            return content;
        }
    }
    
    
    
     
     
    
   
}
