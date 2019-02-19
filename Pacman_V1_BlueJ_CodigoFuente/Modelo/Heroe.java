
package Modelo;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Clase encargada de generar el modelo del personaje principal del juego
 * define el las caracteristicas y el movimiento del personaje principal.
 * @author Juan Paulo
 */
public class Heroe extends personajeJugador{

    private ArrayList<puntoChico> puntos; // puntos comidos
    private ArrayList<Fantasma> fantasmasComidos; // fantasmas azules comidos 
    private Image pac_ar;
    private Image pac_ab;
    private Image pac_i;
    private Image pac_d; 
    // ruta con las magenes de pacman
    private String p_ab = "Imagenes/Pac_Ab_A.gif";
    private String p_ar = "Imagenes/Pac_Ar_A.gif";
    private String p_d = "Imagenes/Pac_D_A.gif";
    private String p_i = "Imagenes/Pac_I_A.gif";
    
    /**
     * Constructor de la clase Heroe
     * @param pos posicion inicial
     * @param ancho ancho del personaje
     * @param alto alto del personaje
     * @param velocidad velocidad de desplazamiento del personaje
     * @param mapa mapa en el que se desplaza el personaje
     * @param vidas vidas del personaje
     * @param teclado teclado que utiliza jugador para mover al personaje
     */
    public Heroe(Posicion pos, int ancho, int alto, int velocidad, Mapa mapa, int vidas, Teclado teclado) {
        super(pos, ancho, alto, velocidad, mapa, vidas, teclado);
        puntos = new ArrayList<puntoChico>();
        fantasmasComidos = new ArrayList<Fantasma>();
        ImageIcon ii_pac_ar = new ImageIcon(this.getClass().getResource(p_ar));
        pac_ar= ii_pac_ar.getImage();
        ImageIcon ii_pac_ab = new ImageIcon(this.getClass().getResource(p_ab));
        pac_ab= ii_pac_ab.getImage();
        ImageIcon ii_pac_i = new ImageIcon(this.getClass().getResource(p_i));
        pac_i= ii_pac_i.getImage();
        ImageIcon ii_pac_d = new ImageIcon(this.getClass().getResource(p_d));
        pac_d= ii_pac_d.getImage();
        imagen= pac_i;
        puntuacion=0;
        
    }

   /**
    * Añade puntos comidos por pacman a la lista 
    * @param o objeto de tipo puntos
    */
    public void addPuntos(Objetos o){
        if(o instanceof puntoChico){
            puntoChico pc = (puntoChico) o;
 
            getPuntos().add(pc);
        }
        
    }
    /**
     * Añade fantasmas azules comidos por pacman a la lista
     * @param f fanstasma
     */
    public void addFantasmasComidos(Fantasma f){
        getFantasmasComidos().add(f);
    }
    
    /**
     * Metodo actualiza la posicion de pacman en el laberinto en funcion de la direccion y la velocidad
     */
    public void mover (){
        System.out.println("Direccion : "+direccion);
        // antes de mover al personaje se comprueba si se superan los limites del mapa
        this.comprobarLimites();
        // se actualiza la posicion en funcion del codigo de direccion
        if(getDireccion()==1  ){       
            int dx =getPos().getX()-getVelocidad() ;   
            getPos().setX(dx);
            setImagen(pac_i);
        }
       
        if(getDireccion()==2  ){
            int dx =getPos().getX()+getVelocidad() ;    
            getPos().setX(dx); 
            setImagen(pac_d);
        }
        
        if(getDireccion()==3 ){
            int dy =getPos().getY()-getVelocidad() ;
            getPos().setY(dy);
            setImagen(pac_ar);
        }
        if(getDireccion()==4 ){
            int dy =getPos().getY()+getVelocidad() ;
            getPos().setY(dy);         
            setImagen(pac_ab);
        }
        
    }


    /**
     * Metodod que devuelve la puntuacion de pacman 
     * @return the puntuacion
     */
    public int getPuntuacion() {
        puntuacion=0;
        
        for(puntoChico p : puntos){
            puntuacion += p.getValor();
        }
        
        for(Fantasma f : getFantasmasComidos() ){
             puntuacion += f.getValor();
        }
        
        return puntuacion;
    }

    /**
     * @return the puntos
     */
    public ArrayList<puntoChico> getPuntos() {
        return puntos;
    }

    /**
     * @return the fantasmasComidos
     */
    public ArrayList<Fantasma> getFantasmasComidos() {
        return fantasmasComidos;
    }

}
