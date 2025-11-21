/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdlab;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David
 */
public class comandos {
    
    private static File currentDir;
    
    static public String cmCd(String cd, File ruta){
        ruta = new File(cd);
        currentDir=ruta;
        return ("Ruta cambiada a "+cd);
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
             return false;
         }else{
             return f.mkdirs();
         }
     }
     
     static boolean cmRm(String nombre, File f){
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
          return f.delete();
     }
     
     static private boolean RmAux(File f){
        
        if(f.isDirectory()){
            for(File arc:f.listFiles()){
                RmAux(arc);
            }
        }
        return f.delete();
    }
     
     
     
     static public String cmReturn() throws IOException{
        File padre = currentDir.getCanonicalFile().getParentFile();
        if(padre== null){
            return ("Ya se encuentra en la raiz");
        }
         currentDir=padre;
          return ("Regresando a "+padre.getName());
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

    public static void setCurrentDir(File currentDir) {
        comandos.currentDir = currentDir;
    }
    

    static public String cmLeer(String nombre) throws IOException{
        File temp= new File(currentDir, nombre);
        String content = new String(Files.readAllBytes(temp.toPath()));
        String fullContent ="";
        fullContent+=("====="+temp.getName()+"=====\n");
        fullContent+=content;
        fullContent+="==========";
        return fullContent; 
    }
            
    static public boolean cmEscribir (String nombre, String content) throws IOException{
        File temp= new File(currentDir, nombre);
        Path p = Paths.get(temp.getPath());
        Files.writeString(p, content, StandardCharsets.UTF_8);
        return true;
    }
     
     

    public static File getCurrentDir() {
        return currentDir;
    }

    
    
}
