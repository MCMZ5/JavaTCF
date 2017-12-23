package endlessrunningtcf;

import java.util.Vector;

public abstract class Object{

    //-----ATTRIBUTI------

    protected int width;                      //larghezza della box
    protected int lenght;                     //altezza della box
    protected Vector<Vector<Point>> box;      //rettangolo di punti che rappresenta l'ostacolo
    protected double speed;                   //velocità
    protected double acc;                     //accelerazione
                                              //abbiamo un problema, servono forse due velocità
                                              //orizontale e verticale, rispettivamente per 
                                              //Character e per Obstacle? Oppure basta questa? 
                                              //Idem per la futura accelerazione

    //------METODI-------

    /**
     * Invocata da un Object, dato un tempo in millisecondi e una mappa, ricalcola 
     * la sua posizione e restituisce la mappa aggiornata
     */
    //è definita abstract per rendere obbligatorio l'override, con una sola velocità
    //e una sola accelerazione si potrebbero scrivere due funzioni agenti sulle x
    //per gli ostacoli e sulle y per il character
    public abstract void UpdatePosition(Vector<Vector<Point>> map, double time);

}