/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;


/**
 *
 * @author Jhosh PC
 */
public class ControladorDatos {
    
    private ControladorDatos(){
    }
    
    private static final ControladorDatos controladorDatos = new ControladorDatos();
    
    private Memoria memoria;
    
    public static ControladorDatos getController(){
        return controladorDatos;
    }
    
    public void guardarObjeto(){
        try{
            FileOutputStream fileIn = new FileOutputStream("memoria.loc");
            ObjectOutputStream in = new ObjectOutputStream(fileIn);
            in.writeObject(memoria);
            System.out.println("Registro Exitoso!");
            in.close();
            fileIn.close();
        }catch(IOException e){
            System.out.println("ERROR al Registrar" + e.getMessage());
        }
    }
    
    public void cargarMemoria(){
        
      try{   
        FileInputStream fileIn = new FileInputStream("memoria.loc");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         memoria = (Memoria) in.readObject();
         System.out.println("Datos Cargados");
         in.close();
         fileIn.close();
      } catch (IOException i) {
          System.out.println(i.getMessage());
      } catch (ClassNotFoundException c) {
          System.out.println(c.getMessage());
      } finally {
            if(memoria == null)
            {
                memoria = new Memoria();
            }else{}
      }
      
    }
    
    public Memoria getMemoria(){
        return memoria;
    }
    
    
    
}
