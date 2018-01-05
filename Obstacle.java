package endlessrunningtcf;

import java.util.Vector;

public class Obstacle extends Object{

    /**
     * - dati i parametri, costruisce la box dell'ostacolo e inizializza i parametri del moto
     */
    public Obstacle(int y_, int width_, int lenght_, double speedx_,double speedy_, double accx_, Vector<Vector<Point>> map){
        width = width_;
        lenght = lenght_;
        speedx = speedx_;
        speedy = speedy_;
        accx = accx_;
        accy = -9.81;
        box = new Vector<Vector<Point>>();
        x = 0;
        y = y_;

        // qui inizializzo box
        int mapl = map.size()-1;                  //la dimensione del vector "esterno" (width)
        int mapw = map.firstElement().size()-1;   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)
        for(int i=mapl;i>mapl-lenght;i--){                           
            Vector<Point> r = new Vector<Point>();                    
            for(int j=mapw;j>mapw-width;j--){                     
                map.get(i).get(j).setObstacle(true);
                r.add(map.get(i).get(j)); 
            }
            box.add(r); 
        }
    }

    public void UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //calcolo del moto
        int mapl = map.firstElement().size()-1;
        int mapw = map.size()-1;
        int newx = mapl;
        int newy = mapw;
        double t = ((-speedx)+(Math.sqrt((speedx*speedx)+2*accx*x)))/accx;
        double gamma = 6*3.14*(width/2)*15;
        if(speedy<=0.){
            speedy = speedy + (accy/gamma)*(1-Math.exp(-gamma*t));
        }
        if(speedy>0.){
            speedy = speedy + (accy/gamma)*(Math.exp(-gamma*t)-1);
        }
        y = y + ((speedy * (time/1000))+(.5 * accy * (time/1000) * (time/1000)));
        speedx = speedx + (accx * (time/1000));
        x = x + ((speedx * (time/1000))+(.5 * accx * (time/1000) * (time/1000)));
        newy = mapw - 2*(int)(y);       //il 2X riscala la map da 1m a quadretto a 0,5m a quadretto (personaggio di 2m e non 4)
        newx = mapl - 2*(int)(x);       //(int) avverte il compilatore che sono consapevole della conversione double->int
        
        if(newx-lenght<0){      //questi 2 if permettono agli ostacoli di scomparire se escono dalla map
            lenght=newx;
        }
        if(newy-width<0){
                width=newy;
        }

        if(y==0 || y<0){                    //urto elastico con il pavimento
            if(speedy<0.){         //se la velocità basta a risollevarsi conserva la quantità di moto
                speedy = -speedy;
            }
            else if(speedy==0. || Math.abs(speedy)<0.5){                           //con velocità troppo basse l'ostacolo inizia a muoversi in orizzontale
                y = 0;
                speedy = 0;
                accy = 0;
            }
        }

        //elimino l'ostacolo dalla mappa
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setObstacle(false);
            }
        }
        box.removeAllElements();

        //sposto i punti della box del personaggio                                                   
        for(int i=newy;i>newy-width;i--){                           
            Vector<Point> r = new Vector<Point>();          
            for(int j=newx;j>newx-lenght;j--){               
                r.add(map.get(i).get(j));
            }
            box.add(r); 
        }

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
