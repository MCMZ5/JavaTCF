package endlessrunningtcf;

import java.util.Vector;

public class Obstacle extends Object{

    /**
     * - dati i parametri, costruisce la box dell'ostacolo e lo "aggiunge" in fondo (fuori, oltre) alla mappa
     */
    //aggiunge è virgolettato perché non viene effettivamente aggiunto, ma i suoi punti corrispondono
    //al fondo della mappa, verrà aggiunto davvero col successivo UpdatePosition()
    public Obstacle(int width_, int lenght_, double speed_, double acc_, Vector<Vector<Point>> map){
        width = width_;
        lenght = lenght_;
        speed = speed_;
        acc = acc_;
        box = new Vector<Vector<Point>>();

        // qui inizializzo box
        int mapw = map.size();                  //la dimensione del vector "esterno" (width)
        int mapl = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)
        for(int i=mapw-1;i>mapw-width;i--){                           //|
            Vector<Point> r = new Vector<Point>();          //| la box del personaggio corrisponde ai primi
            for(int j=mapl-1;j>mapl-lenght;j--){               //| punti della mappa, dichiarati come personaggio
                r.add(map.get(i).get(j));//new Point(i, j, true, false));        //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }
    }
    public Vector<Vector<Point>> UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //per il momento non si considerano accelerazioni e si calcola per MRU
        int newpos = 0;
        newpos = (int)(speed * (time/1000));       //(int) avverte il compilatore che sono
                                                   //consapevole della conversione double->int

        //elimino l'ostacolo dalla mappa
        for (Vector<Point> vm : map) {
            for (Point pm : vm) {
                for (Vector<Point> vb : box) {
                    for (Point pb : vb) {
                        if(pm == pb){              //domandone da un milione, visto che in java non esiste overload
                            pm.setChar(' ');       //degli operatori, vedi ==, !=, <, >, eccetera, come capisce se pm==pb?
                            pm.setObstacle(false);
                        }
                    }
                }
            }
        }

        //sposto i punti della box dell'ostacolo                                                   
        for (Vector<Point> vp : box) {
            for (Point p : vp) {
                p.setX(p.getX()-newpos);    // - non + perché si muove da destra verso sinistra
            }
        }

        //reinserisco l'ostacolo spostato
        for (Vector<Point> vm : map) {
            for (Point pm : vm) {
                for (Vector<Point> vb : box) {
                    for (Point pb : vb) {
                        if(pm == pb){
                            pm.setChar('#');
                            pm.setObstacle(true);
                        }
                    }
                }
            }
        }

        return map;

        //l'operazione di mettere e togliere l'ostacolo è necassaria, non posso semplicemente svuotare tutta la mappa
        //perché altrimenti eliminerei anche gli altri ostacoli, pur rimettendone solo uno, di conseguenza, preso un generico
        //vettore di ostacoli, a ogni Update non ne rimarrebbe che l'ultimo
    }
}