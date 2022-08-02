/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ControladorPrincipal {
    
    private static final ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    
    public static ControladorPrincipal getController(){
        return controladorPrincipal;
    }
    
    private Memoria M;
    private ControladorDatos data;

    
    public ControladorPrincipal(){
       
        M=new Memoria();
    }
    
    
       
    public ControladorDatos getControladorDatos(){
        return data;
    }
        
    public Memoria getJuego(){
        return M;
    }
    


}
