/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import javax.swing.ImageIcon;

/**
 * Clase que contiene las caracteristicas y movimientos basicos de un fantasma, 
 * Esta clase hereda de la clase Personaje
 * @author Juan Paulo
 * 
 */
public class Fantasma extends Personaje {
    
    protected static int ID_Fantasma=0; // variable que genera un id para un fantasma

    protected int id; // id de un fantasma
    
    private boolean azul;// true si un fantasma es azul false en caso contrario
    
    protected ImageIcon ii_f ;
    protected ImageIcon ii_fa;
    
    // ruta con las magenes de pacman
    protected String f ;
    protected String fa ;
    
    private final int valor = 100;// valor en puntos de un fantasma comido
    
    private boolean colision; // true si un fantasma colisiono con otro fantasma
    
   /**
    *Constructor de la clase Fantasma
    * @param pos posicion inicial
    * @param ancho ancho del personaje
    * @param alto alto del personaje
    * @param velocidad velocidad de desplazamiento del personaje
    * @param mapa mapa en el que se desplaza el personaje
    */
    public Fantasma( Posicion pos, int ancho, int alto,int velocidad, Mapa mapa) {
        super(pos,ancho,alto,velocidad,mapa);
        azul=false;
        ID_Fantasma++;  
        id=ID_Fantasma;
        colision=false;
    }
    /**
     * Metodo que comprueba que el personaje no salga de los limites
     * Se soobrescribe para que los fantasmas no pasen al otro lado de la pantalla
     */
    protected void comprobarLimites(){
        if(this.getPos().getX()<0){
            this.getPos().setX(0);
        }
        if(this.getPos().getX()>this.getMapa().getAncho()){
              this.getPos().setX(this.getMapa().getAncho());
        }  
    }
    /**
     * Metodo que hace a un fantasma huir de pacman
     * Este metodo solo se utiliza cuando el fantasma es azul
     * @param pacman pacman del que huye el fantasma
     */
    public void huirPacman(Heroe pacman){
        
        // si la la corrdenda x del fantasma es menor a la de pacman y no choca contra la pared el fantasma se mueve hacia la izquierda
        if(this.getPos().getX()<=pacman.getPos().getX()){
            if(!mapa.comprobarPared(this,pos.getX()-velocidad, this.pos.getY())){
                setDireccion(1);
            }
        }
        // si la la corrdenda x del fantasma es mayor a la de pacman y no choca contra la pared el fantasma se mueve hacia la derecha
        if(this.getPos().getX()>pacman.getPos().getX()){
            if(!mapa.comprobarPared(this,pos.getX()+velocidad, this.pos.getY())){
                setDireccion(2);
            }
        }
        // si la la corrdenda y del fantasma es menor a la de pacman y no choca contra la pared el fantasma se mueve hacia arriba
        if(this.getPos().getY()<=pacman.getPos().getY()){
            if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()-velocidad)){
                setDireccion(3);
            }
        }
        // si la la corrdenda y del fantasma es mayor a la de pacman y no choca contra la pared el fantasma se mueve hacia abajo
        if(this.getPos().getY()>pacman.getPos().getY()){
            if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()+velocidad)){
                setDireccion(4);
            }
        }
    }
    /**
     * Metodo actualiza la posicion de un fantasma segun la velocidad y su direccion
     */
    public void mover(){
       // antes de mover al personaje se comprueba si se superan los limites del mapa
        this.comprobarLimites(); 
        // si direcccion es = 1 el fantasma se mueve a hacia la izquierda en funcion de la velocidad 
        if(getDireccion()==1){
            int dx =getPos().getX()-velocidad ;   
            getPos().setX(dx);           
        }
        // si direcccion es = 2 el fantasma se mueve a hacia la derecha en funcion de la velocidad 
        if(getDireccion()==2){
            int dx =getPos().getX()+velocidad ;
            getPos().setX(dx);      
        }
        // si direcccion es = 4 el fantasma se mueve a hacia abajo en funcion de la velocidad 
        if(getDireccion()==4){
            int dy =getPos().getY()+velocidad ;
            getPos().setY(dy);  
        }
        // si direcccion es =3 el fantasma se mueve a hacia arriba en funcion de la velocidad 
        if(getDireccion()==3){
            int dy =getPos().getY()-velocidad ;  
            getPos().setY(dy); 
        }
    }
    /**
     * Metodo que cambia la direccion del fantasma
     * Solo se utiliza cuando colisionan 2 fantasmas
     * @param fantasma fantasma con el que coolisiona
     */
     public void cambioDeDireccion(Fantasma fantasma){
         
        if(this.getPos().getX()<=fantasma.getPos().getX()){
            if(!mapa.comprobarPared(this,pos.getX()-velocidad, this.pos.getY())){
                setDireccion(1);
            }
        }
        
        if(this.getPos().getX()>fantasma.getPos().getX()){
            if(!mapa.comprobarPared(this,pos.getX()+velocidad, this.pos.getY())){
                setDireccion(2);
            }
        }
        
        if(this.getPos().getY()<=fantasma.getPos().getY()){
            if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()-velocidad)){
                setDireccion(3);
            }
        }
        
        if(this.getPos().getY()>=fantasma.getPos().getY()){
            if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()+velocidad)){
                setDireccion(4);
            }
        }
    }
    
     /**
      * Meetodo que define el tipo de movimiento especifico de cada fantasma
      * Este metodo se redefine en las subsclases de esta segun el tipo de fantasma
      * @param pacman 
      */
    public void movimiento(Heroe pacman){
        
    }

    /**
     * @return the azul
     */
    public boolean isAzul() {
        return azul;
    }

   /**
     * @param azul the azul to set
     */
    public void setAzul(boolean azul) {
        if(azul){
             imagen= ii_fa.getImage();
        }else{
            imagen= ii_f.getImage();
        }
        this.azul = azul;
    }

    /**
     * cmprueba si 2 fantasmas son iguales
     * @param fantasma
     * @return 
     */
     public boolean equals (Fantasma fantasma){
        if(this.getId()==fantasma.getId()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * 
     * @return the valor
     */
     public  int getValor() {
        return valor;
    }

    /**
     * @return the colision
     */
    public boolean isColision() {
        return colision;
    }

    /**
     * @param colision the colision to set
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }
    
}
