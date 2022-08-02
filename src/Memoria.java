/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
import java.io.Serializable;
import java.util.*;
import javax.swing.JFrame;
public class Memoria implements Serializable{
    private int[] ranking;
    private int nivel;
    private int puntuacion;
    public Memoria(){
    ranking=new int[10];
    nivel=4;
    puntuacion=0;
    }
    public int[] getRanking(){
    return ranking;
    }
    public void RestablecerR(){
        for(int i=0; i<10;i++){
            ranking[i]=0;
        }
    }
    public void setRanking(int n){
        for(int i=0; i<10;i++){
            if(n>ranking[i]){
                int aux=n;
                n=ranking[i];
                ranking[i]=aux;
            }
        }
    }
    public void setPuntuacion(int p){
    puntuacion+=p;
    }
    public int getPuntuacion(){
    return puntuacion;
    }
    public int getNivel(){
    return nivel;
    }
    public void setNivel(int n){
    nivel =n;
    }
    public void MemoriaRestart(){
    puntuacion=0;
    }
}
