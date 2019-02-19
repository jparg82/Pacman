/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Calse que genera el laberinto por donde se movera pacman y los fantasmas, se encargara de crear las parades y los puntos
 * @author Juan Paulo
 */
public class Mapa {
    
    private final int ancho=600;
    private final int alto=600;
    
    // variable que contiene la configuracion del laberinto inicial
    // los valores menores que 0 son paredes, el cero son hucos vacios , el 1 son puntos chicos y el 2 puntos grandes
    private int [][] modelo = {
    {-1,-5,-5,-5,-5,-5,-5,-5,-5,-5,-8,-5,-5,-5,-5,-5,-5,-5,-5,-5,-2},
    {-6,1,1,1,1,1,1,1,1,1,-6,1,1,1,1,1,1,1,1,1,-6},
    {-6,1,-1,-8,-2,1,-1,-8,-2,1,-6,1,-1,-8,-2,1,-1,-8,-2,1,-6},
    {-6,2,-3,-7,-4,1,-3,-7,-4,1,-12,1,-3,-7,-4,1,-3,-7,-4,2,-6},
    {-6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-6},
    {-6,1,-13,-5,-14,1,-11,0,-13,-5,-8,-5,-14,0,-11,1,-13,-5,-14,1,-6},
    {-6,1,1,1,1,1,-6,0,0,0,-6,0,0,0,-6,1,1,1,1,1,-6},
    {-3,-5,-5,-5,-2,1,-10,-5,-14,0,-12,0,-13,-5,-9,1,-1,-5,-5,-5,-4},
    {0,0,0,0,-6,1,-6,0,0,0,0,0,0,0,-6,1,-6,0,0,0,0},
    {-5,-5,-5,-5,-4,1,-12,0,-1,-8,-8,-8,-2,0,-12,1,-3,-5,-5,-5,-5},
    {0,0,0,0,0,1,0,0,-10,0,0,0,-9,0,0,1,0,0,0,0,0},
    {-5,-5,-5,-5,-2,1,-11,0,-3,-7,-7,-7,-4,0,-11,1,-1,-5,-5,-5,-5},
    {0,0,0,0,-6,1,-6,0,0,0,0,0,0,0,-6,1,-6,0,0,0,0},
    {-1,-5,-5,-5,-4,1,-12,0,-13,-5,-8,-5,-14,0,-12,1,-3,-5,-5,-5,-2},
    {-6,1,1,1,1,1,1,1,1,1,-6,1,1,1,1,1,1,1,1,1,-6},
    {-6,1,-13,-5,-2,1,-13,-5,-14,1,-12,1,-13,-5,-14,1,-1,-5,-14,1,-6},
    {-6,2,1,1,-6,1,1,1,1,1,1,1,1,1,1,1,-6,1,1,2,-6},
    {-10,-5,-14,1,-12,1,-11,1,-13,-5,-8,-5,-14,1,-11,1,-12,1,-13,-5,-9},
    {-6,1,1,1,1,1,-6,1,1,1,-6,1,1,1,-6,1,1,1,1,1,-6},
    {-6,1,-13,-5,-5,-5,-7,-5,-14,1,-12,1,-13,-5,-7,-5,-5,-5,-14,1,-6},
    {-6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-6},
    {-3,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-4},
    };
    
   private ArrayList<Pared> paredes; // array que contiene las paredes del laberinto
   private ArrayList<Objetos> puntos; // array que contiene los puntos del laberinto
   
   // ruta de las imagenes del laberinto
   private String esq_inf_izq = "Imagenes/Esq_inf_izq.png";
   private String esq_inf_dcha = "Imagenes/Esq_inf_Dcha.png";
   private String esq_sup_izq = "Imagenes/Esq_sup_izq_H.png";
   private String esq_sup_dcha = "Imagenes/Esq_sup_Dcha_H.png";
   private String pared1_ab = "Imagenes/Pared1_Ab_H.png"; 
   private String pared1_ar = "Imagenes/Pared1_Ar_H.png";
   private String pared1_dcha = "Imagenes/Pared1_Dcha_V.png";
   private String pared1_iz = "Imagenes/Pared1_Izq_V.png";
   private String pared2_h = "Imagenes/Pared2_H.png";
   private String pared2_v = "Imagenes/Pared2_V.png";
   private String pared3_ab = "Imagenes/Pared3_Ab_V.png";
   private String pared3_ar = "Imagenes/Pared3_Ar_V.png";
   private String pared3_dcha = "Imagenes/Pared3_Dcha_H.png";
   private String pared3_izq = "Imagenes/Pared3_izq_H.png";
   private String punto = "Imagenes/PuntoChico.png";
   private String puntoGrande="Imagenes/PuntoGrande.png";
   
   // imagenes del laberinto
   private Image eii;
   private Image eid;
   private Image esi;
   private Image esd;
   private Image p1ar;
   private Image p1ab;
   private Image p1i;
   private Image p1d;
   private Image p2v;
   private Image p2h;
   private Image p3ar;
   private Image p3ab;
   private Image p3i;
   private Image p3d;
   private Image p;
   private Image pg;
   
   /**
    * Constructor
    */
    public Mapa() { 
        inicializarVariables();    
        generarMapa();

    }
    /**
     * Metodo que inicializa las variables de la clase
     */
    private void inicializarVariables(){
        
        paredes = new ArrayList<Pared>();
        puntos = new ArrayList<Objetos>();
        
        ImageIcon ii_eii = new ImageIcon(this.getClass().getResource(esq_inf_izq));
        eii= ii_eii.getImage();
        
        ImageIcon ii_eid = new ImageIcon(this.getClass().getResource(esq_inf_dcha));
        eid= ii_eid.getImage();
        
        ImageIcon ii_esi = new ImageIcon(this.getClass().getResource(esq_sup_izq));
        esi= ii_esi.getImage();
        
        ImageIcon ii_esd = new ImageIcon(this.getClass().getResource(esq_sup_dcha));
        esd= ii_esd.getImage();
        
        ImageIcon ii_p1ar = new ImageIcon(this.getClass().getResource(pared1_ab));
        p1ab= ii_p1ar.getImage();
        
        ImageIcon ii_p1ab = new ImageIcon(this.getClass().getResource(pared1_ar));
        p1ar= ii_p1ab.getImage();
        
        ImageIcon ii_p1d = new ImageIcon(this.getClass().getResource(pared1_dcha));
        p1d= ii_p1d.getImage();
        
        ImageIcon ii_p1i = new ImageIcon(this.getClass().getResource(pared1_iz));
        p1i= ii_p1i.getImage();
        
        ImageIcon ii_p2h = new ImageIcon(this.getClass().getResource(pared2_h));
        p2h= ii_p2h.getImage();
        
        ImageIcon ii_p2v = new ImageIcon(this.getClass().getResource(pared2_v));
        p2v= ii_p2v.getImage();
        
        ImageIcon ii_p3ab = new ImageIcon(this.getClass().getResource(pared3_ab));
        p3ab= ii_p3ab.getImage();
        
        ImageIcon ii_p3ar = new ImageIcon(this.getClass().getResource(pared3_ar));
        p3ar= ii_p3ar.getImage();
        
        ImageIcon ii_p3d = new ImageIcon(this.getClass().getResource(pared3_dcha));
        p3d= ii_p3d.getImage();
        
        ImageIcon ii_p3i = new ImageIcon(this.getClass().getResource(pared3_izq));
        p3i= ii_p3i.getImage();
        
        ImageIcon ii_p = new ImageIcon(this.getClass().getResource(punto));
        p= ii_p.getImage();
        
        ImageIcon ii_pg = new ImageIcon(this.getClass().getResource(puntoGrande));
        pg= ii_pg.getImage();
    }
    
    /**
     * Metodo que genera el laberitno
     */
    private void generarMapa(){
        //paredes.clear();
        puntos.clear();
        // se recorre la variable que contiene la configuracion del laberinto inicial
        int ancho=30;
        int alto=30;
        for(int i=0;i<modelo.length;i++){
            for (int j=0;j<modelo[i].length;j++){
               // si es una pared se añade una pared a la lista de paredes
               if(modelo[i][j]<0){
                   int x=j*30;
                   int y=i*30;
                   int t = modelo[i][j];
                   Posicion p = new Posicion (x,y);
                    getParedes().add(new Pared(t,p,ancho,alto));
                   
               }else{
                   // si es un punto se añade un punto a la lista de puntos
                   if(modelo[i][j]==1){
                       int x=j*30;
                       int y=i*30;
                       int t = modelo[i][j];
                       Posicion p = new Posicion (x,y);
                       getPuntos().add(new puntoChico(p,ancho,alto));
                      
                   }
                   
                   if(modelo[i][j]==2){
                       int x=j*30;
                       int y=i*30;
                       int t = modelo[i][j];
                       Posicion p = new Posicion (x,y);
                       getPuntos().add(new puntoGrande(p,ancho,alto));
                       
                   }
               }
            
            }
                
        }
        
        cargarImagenesParedes();
        cargarImagenesPuntos();
    }
    /**
     * Metodo que comprueba si el el personaje pasado como parametro colision con alguna pared
     * @param personaje personaje 
     * @param x coordenada x donde se quiere comprobar la colision
     * @param y coordenada y donde se quiere comprobar la colision
     * @return true si colision y false en caso contrario
     */
    public boolean comprobarPared(Personaje personaje, int x, int y){
        boolean pared=false; // indica si pacman colisiona con una pared
        
        // se reccorre el laberinto
        int contador=0;
        // mientras pacman no haya colisionado con una parad se recorren las paredes del laberinto 
        while(!pared && contador< getParedes().size()){
            Pared p = getParedes().get(contador);
            Rectangle pac = personaje.getBounds(x, y, personaje.getAncho(), personaje.getAlto());
            
            Rectangle lab = p.getBounds(p.getPos().getX(), p.getPos().getY(), p.getAncho(), p.getAlto());

            if(pac.intersects(lab) ){
                pared= true;
            }
            
            contador++;  
        }
        return pared;
    }
    
    /**
     * Metodo que carga las imagenes de las paredes del labrinto
     */
    private void cargarImagenesParedes(){
        for(Pared p: getParedes()){
            
            if(p.getTipo()==-14){
                p.setImagen(p3d);
            }
            if(p.getTipo()==-13){
                p.setImagen(p3i);
            }
            if(p.getTipo()==-12){
                
                p.setImagen(p3ab);
            }
            if(p.getTipo()==-11){
                
                p.setImagen(p3ar);
            }

            if(p.getTipo()==-10){
                
                p.setImagen(p1i);
            }

            if(p.getTipo()==-9){
                
                p.setImagen(p1d);
            }
            if(p.getTipo()==-8){
                
                p.setImagen(p1ar);
            }
            if(p.getTipo()==-7){
                
                p.setImagen(p1ab);
            }

            if(p.getTipo()==-6){
                
                p.setImagen(p2v);
            }

            if(p.getTipo()==-5){
                
                p.setImagen(p2h);
            }

            if(p.getTipo()==-4){
                
                p.setImagen(eid);
            }

            if(p.getTipo()==-3){
                
                p.setImagen(eii);
            }

            if(p.getTipo()==-2){
               
                p.setImagen(esd);
            }

            if(p.getTipo()==-1){
             
                p.setImagen(esi);
            }
        }
    }
  
    /**
     * Metodo que carga las imagenes de los puntos del laberinto
     */
    private void cargarImagenesPuntos(){
        for(Objetos p: puntos){
            
            if(p instanceof puntoChico){
                p.setImagen(this.p);
            }
            
            if(p instanceof puntoGrande){
                p.setImagen(this.pg);
            }
        }
    }

    /**
     * @return the paredes
     */
    public ArrayList<Pared> getParedes() {
        return paredes;
    }

    /**
     * @return the puntos
     */
    public ArrayList<Objetos> getPuntos() {
        return puntos;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }


    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }
    
}
