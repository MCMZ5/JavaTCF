package endlessrunningtcf;

import java.util.Vector;

public abstract class Object{

    //-----ATTRIBUTI------

    private int width;                      //larghezza della box
    private int lenght;                     //altezza della box
    private Vector<Vector<Point>> box;      //rettangolo di punti che rappresenta l'ostacolo
    private double speed;                   //velocità
                                              //abbiamo un problema, servono forse due velocità
                                              //orizontale e verticale, rispettivamente per 
                                              //Character e per Obstacle? Oppure basta questa? 
                                              //Idem per la futura accelerazione

    //------METODI-------

    /**
     * Invocata da un Object, ricalcola la sua posizione sulla mappa che viene 
     * passata, e restituisce la nuova mappa
     */
    //è definita abstract per rendere obbligatorio l'override, con una sola velocità
    //e una sola accelerazione si potrebbero scrivere due funzioni agenti sulle x
    //per gli ostacoli e sulle y per il character
    public abstract Vector<Vector<Point>> UpdatePosition(Vector<Vector<Point>> map);

}