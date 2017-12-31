package endlessrunningtcf;

import java.util.Vector;

public class Obstacle extends Object{

    /**
     * - dati i parametri, costruisce la box dell'ostacolo e lo "aggiunge" in fondo (fuori, oltre) alla mappa
     */
    //aggiunge è virgolettato perché non viene effettivamente aggiunto, ma i suoi punti corrispondono
    //al fondo della mappa, verrà aggiunto davvero col successivo UpdatePosition()
    public Obstacle(int y_, int width_, int lenght_, double speed_,double speedy_, double acc_, Vector<Vector<Point>> map){
        width = width_;
        lenght = lenght_;
        speed = speed_;
        speedy = speedy_;
        acc = acc_;
        accy = -9.81;
        box = new Vector<Vector<Point>>();
        x = 0;
        y = y_;

        // qui inizializzo box
        int mapl = map.size()-1;                  //la dimensione del vector "esterno" (width)
        int mapw = map.firstElement().size()-1;   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)
        for(int i=mapl;i>mapl-lenght;i--){                           //|
            Vector<Point> r = new Vector<Point>();                    //| la box del personaggio corrisponde ai primi
            for(int j=mapw;j>mapw-width;j--){                      //| punti della mappa, dichiarati come personaggio
                map.get(i).get(j).setObstacle(true);
                r.add(map.get(i).get(j));//new Point(i, j, true, false)); //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }
    }

    public void UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //per il momento non si considerano accelerazioni e si calcola per MRU
        int mapl = map.firstElement().size()-1;
        int mapw = map.size()-1;
        int newx = mapl;
        int newy = mapw;
        speedy = speedy + (accy * (time/1000));
        y = y + ((speedy * (time/1000))+(.5 * accy * (time/1000) * (time/1000)));
        speed = speed + (acc * (time/1000));
        x = x + ((speed * (time/1000))+(.5 * acc * (time/1000) * (time/1000)));
        newy = mapw - (int)(y);  
        newx = mapl - (int)(x);       //(int) avverte il compilatore che sono
                                                   //consapevole della conversione double->int
        //System.out.println("speed: "+speed+" time: "+time+" newx: "+newx);
        //elimino l'ostacolo dalla mappa
        if(newx-lenght<0){
            lenght=newx;
        }
        if(newy-width<0){
            width=newy;
        }
        if(y==0 || y<0){
            speedy = -speedy;
        }
        //if(lenght=0){
            //this.~Obstacle(); //elimino l'ostacolo (come???)
        //}

        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setObstacle(false);
            }
        }
        box.removeAllElements();

        //sposto i punti della box del personaggio                                                   
        for(int i=newy;i>newy-width;i--){                           //|
            Vector<Point> r = new Vector<Point>();          //| la box del personaggio corrisponde ai primi
            for(int j=newx;j>newx-lenght;j--){               //| punti della mappa, dichiarati come personaggio
                r.add(map.get(i).get(j));//new Point(i, j, true, false));        //| (anche se si potrebbe evitare)
            }
            box.add(r); 
        }                             //personaggio scende
                                            //infatti ricordiamo che la mappa è del tipo
                                            //0,0   0,1   0,2   ...
                                            //1,0   1,1   1,2   ...
                                            //n,0   n,1   ...   n,n

        //reinserisco l'ostacolo spostato
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setObstacle(true);
            }
        }

        //l'operazione di mettere e togliere l'ostacolo è necassaria, non posso semplicemente svuotare tutta la mappa
        //perché altrimenti eliminerei anche gli altri ostacoli, pur rimettendone solo uno, di conseguenza, preso un generico
        //vettore di ostacoli, a ogni Update non ne rimarrebbe che l'ultimo
    }
}
