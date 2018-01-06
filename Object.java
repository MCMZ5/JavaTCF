package endlessrunningtcf;

import java.util.Vector;

public abstract class Object{

    //-----ATTRIBUTI------

    protected int width;                      //larghezza della box
    protected int lenght;                     //altezza della box
    protected Vector<Vector<Point>> box;      //rettangolo di punti che rappresenta l'ostacolo
    protected double speedx;                  //velocità orizzontale
    protected double speedy;                  //velocità verticale
    protected double accx;                    //accelerazione orizzontale
    protected double accy;                    //accelerazione verticale
    protected double x;                       //posizione orizzontale
    protected double y;                       //posizione verticale

    //------METODI-------

    /**
     * Invocata da un Object, dato un tempo in millisecondi e una mappa, ricalcola 
     * la sua posizione e restituisce la mappa aggiornata
     */
    public abstract void UpdatePosition(Vector<Vector<Point>> map, double time);

}
