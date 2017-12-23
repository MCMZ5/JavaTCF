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
        int mapw = map.size();                  //la dimensione del vector "esterno" (width)
        
        int mapl = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)

        for(int i=mapw-1;i>mapw-width;i--){                           //|
            Vector<Point> r = new Vector<Point>();          //| la box del personaggio corrisponde ai primi
            for(int j=0;j<lenght;j++){               //| punti della mappa, dichiarati come personaggio
                r.add(map.get(i).get(j));//new Point(i, j, true, false));        //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }
    }
    public Vector<Vector<Point>> UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //calcolo per MRUA iniziato ma non terminato
        int newpos = 0;
        speed = speed + (acc * (time/1000));
        newpos = (int)((speed * (time/1000))+(.5 * acc * (time/1000) * (time/1000)));   //(int) avverte il compilatore che sono
                                                                                        //consapevole della conversione double->int

        //elimino l'ostacolo dalla mappa
        box.removeAllElements();
        int mapl = map.firstElement().size();
        int mapw = map.size();

        //sposto i punti della box del personaggio                                                   
        for(int i=mapw-1;i>mapw-width;i--){                           //|
            Vector<Point> r = new Vector<Point>();          //| la box del personaggio corrisponde ai primi
            for(int j=0;j<lenght;j++){               //| punti della mappa, dichiarati come personaggio
                r.add(map.get(i).get(j));//new Point(i, j, true, false));        //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }                             //personaggio scende
                                            //infatti ricordiamo che la mappa è del tipo
                                            //0,0   0,1   0,2   ...
                                            //1,0   1,1   1,2   ...
                                            //n,0   n,1   ...   n,n

        //reinserisco il personaggio spostato
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setCharacter(true);
            }
        }
                
            
        

        return map;

        //l'operazione di mettere e togliere l'ostacolo è necassaria, non posso semplicemente svuotare tutta la mappa
        //perché altrimenti eliminerei anche gli altri ostacoli, pur rimettendone solo uno, di conseguenza, preso un generico
        //vettore di ostacoli, a ogni Update non ne rimarrebbe che l'ultimo
    }
}