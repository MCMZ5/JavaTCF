package endlessrunningtcf;

import java.util.Vector;

public class Obstacle extends Object{

    //mi sono fuso, sia il costruttore sia la update sono da rivedere completamente perché non so più cosa fanno
    //il problema principale è che box e map non possono condividere i punti (non esistono i puntatori), e il 
    //costruttore di obstacle non può nemmeno disegnare direttamente sulla mappa (non esiste il passaggio by reference)
    //l'unica soluzione che mi pare al momento plausibile è delegare il disegno degli ostacoli a Game (ovvero al main)
    //o trasformare map e/o obvect (del main) in classi a sé stanti (tuttavia ci ho provato e ho ottenuto scarsi risultati
    //in termini di semplicità, quindi non credo sia la soluzione migliore)

    /**
     * - dati i parametri, costruisce la box dell'ostacolo
     */
    public Obstacle(int width_, int lenght_, Vector<Vector<Point>> map){
        width = width_;
        lenght = lenght_;

        // qui inizializzo box
        int mapw = map.size();                  //la dimensione del vector "esterno"
        int mapl = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)
        for(int i=mapw-width;i<mapw;i++){                   //|
            Vector<Point> r = new Vector<Point>();          //| la box dell'ostacolo corrisponde agli ultimi
            for(int j=mapl-lenght;j<mapl;j++){              //| punti in fondo alla mappa, dichiarati come ostacoli
                r.add(new Point(i, j, '#', false, true));   //|
            }
            box.add(r);
        }
    }
    public Vector<Vector<Point>> UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //per il momento non si considerano accelerazioni e si calcola per MRU
        int newpos = 0;
        newpos = (int)(speed * (time/1000));       //(int) avverte il compilatore che sono
                                                   //consapevole della conversione double->int

        for (Vector<Point> vp : box) {
            for (Point p : vp) {
                p.setX(p.getX()-newpos);    // - non + perché si muove da destra verso sinistra
            }
        }

        //sposto i punti della box dell'ostacolo                                                   
        for (Vector<Point> vp : box) {
            for (Point p : vp) {
                p.setX(p.getX()-newpos);    // - non + perché si muove da destra verso sinistra
            }
        }

        //aggiorno la mappa spostando i punti 
        int mapw = map.size();                  //la dimensione del vector "esterno"
        int mapl = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)

        Point newp = new Point(0,0);
        for (Vector<Point> v : map) {
            for (Point p : v) {
                if(p.isObstacle()==true){
                    newp.setChar('#');
                    newp.setObstacle(true);
                    p.setChar(' ');
                    p.setObstacle(false);
                }
                newp.setX(p.getX()-newpos);
            }
        }
        return map;
    }  
    
}