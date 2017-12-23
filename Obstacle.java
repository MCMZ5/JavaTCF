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
        int mapl = map.size();                  //la dimensione del vector "esterno" (width)
        int mapw = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)
        for(int i=mapl-1;i>mapl-lenght;i--){                           //|
            Vector<Point> r = new Vector<Point>();                    //| la box del personaggio corrisponde ai primi
            for(int j=mapw-1;j>mapw-width;j--){                      //| punti della mappa, dichiarati come personaggio
                map.get(i).get(j).setObstacle(true);
                r.add(map.get(i).get(j));//new Point(i, j, true, false)); //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }
    }
    public void UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //per il momento non si considerano accelerazioni e si calcola per MRU
        int newpos = 0;
        newpos = (int)(speed * (time/1000));       //(int) avverte il compilatore che sono
                                                   //consapevole della conversione double->int

        int mapl = map.size();                  //la dimensione del vector "esterno" (width)
        int mapw = map.firstElement().size();   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)                                           

        //box.removeAllElements();

        for (Vector<Point> vm : map) {
            for (int i=0; i<mapw; i++){
                if(vm.get(i).isObstacle() == true){
                    vm.get(i).setObstacle(false);
                    vm.get(i).setChar(' ');
                    vm.get(i-newpos).setObstacle(true);
                    vm.get(i-newpos).setChar('#');
                }
            }
        }

        //l'operazione di mettere e togliere l'ostacolo è necassaria, non posso semplicemente svuotare tutta la mappa
        //perché altrimenti eliminerei anche gli altri ostacoli, pur rimettendone solo uno, di conseguenza, preso un generico
        //vettore di ostacoli, a ogni Update non ne rimarrebbe che l'ultimo
    }
}