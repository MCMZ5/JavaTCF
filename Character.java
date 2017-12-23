package endlessrunningtcf;

import java.util.Vector;

public class Character extends Object{
    
    /**
     * - dati i parametri, costruisce la box dell'ostacolo e lo "aggiunge" in fondo (fuori, oltre) alla mappa
     */
    //aggiunge è virgolettato perché non viene effettivamente aggiunto, ma i suoi punti corrispondono
    //al fondo della mappa, verrà aggiunto davvero col successivo UpdatePosition()
    public Character(int width_, int lenght_, double speed_, double acc_, Vector<Vector<Point>> map){
        width = width_;
        lenght = lenght_;
        speed = speed_;
        acc = acc_;
        box = new Vector<Vector<Point>>();
        
        // qui inizializzo box
        int mapl = map.size();                  //la dimensione del vector "esterno" (width)
        
        int mapw = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)

        for(int i=mapl-1;i>mapl-lenght;i--){                 
            Vector<Point> r = new Vector<Point>();          
            for(int j=0;j<width;j++){              
                r.add(map.get(i).get(j));
            }
            box.add(r); 
        }
    }
    public void UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //calcolo per MRUA iniziato ma non terminato
        int newpos = 0;
        speed = speed + (acc * (time/1000));
        newpos = (int)((speed * (time/1000))+(.5 * acc * (time/1000) * (time/1000)));   //(int) avverte il compilatore che sono
                                                                                        //consapevole della conversione double->int

        //elimino l'ostacolo dalla mappa
        box.removeAllElements();
        int mapw = map.firstElement().size();
        int mapl = map.size();

        //sposto i punti della box del personaggio                                                   
        for(int i=mapl-1;i>mapl-lenght;i--){                          
            Vector<Point> r = new Vector<Point>();         
            for(int j=0;j<width;j++){               
                r.add(map.get(i).get(j));
            }
            box.add(r); 
        }                 

        //reinserisco il personaggio spostato
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setCharacter(true);
            }
        }

        //l'operazione di mettere e togliere l'ostacolo è necassaria, non posso semplicemente svuotare tutta la mappa
        //perché altrimenti eliminerei anche gli altri ostacoli, pur rimettendone solo uno, di conseguenza, preso un generico
        //vettore di ostacoli, a ogni Update non ne rimarrebbe che l'ultimo
    }
}