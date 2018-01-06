package endlessrunningtcf;

import java.util.Vector;

public class Character extends Object{

    private boolean[][] icon = {{false,false,false,true,true,false,false,false},{false,true,true,true,true,true,true,false},
    {false,false,true,true,true,true,false,false},{false,false,true,false,false,true,false,false}};
    
    /**
     * - dati i parametri, costruisce la box del personaggio e inizializza il suo moto
     */
    public Character(Vector<Vector<Point>> map){
        width = 8;
        lenght = 4;
        speedy = 0;
        accy = -9.81;
        box = new Vector<Vector<Point>>();
        y = 0;
        
        
        // qui inizializzo box
        int mapl = map.size()-1;                  //la dimensione del vector "esterno" (width)
        
        int mapw = map.firstElement().size()-1;   //la dimensione del vector "interno" (andava bene un elemento qualsiasi)(lenght)

        for(int i=mapl;i>mapl-lenght;i--){                 
            Vector<Point> r = new Vector<Point>();          
            for(int j=0;j<width;j++){              
                r.add(map.get(i).get(j));               //aggiungo al box direttamente dei punti della map 
            }                                           //visto che non esistono i puntatori
            box.add(r); 
        }
    }

    /**
     * - sposta il personaggio
     */
    public void UpdatePosition(Vector<Vector<Point>> map, double time){
        
        //calcolo per MRUA
        int newpos = 0;
        speedy = speedy + (accy * (time/1000));
        y = y + ((speedy * (time/1000))+(.5 * accy * (time/1000) * (time/1000)));

        if(y<0){
            y=0;    //evita che il personaggio affondi nel pavimento
        }
        newpos = 2*(int)y;   //(int) avverte il compilatore che sono consapevole della conversione double->int
                                                                        
        //elimino l'ostacolo dalla mappa
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setCharacter(false);
            }
        }
        box.removeAllElements();


        int mapw = map.firstElement().size() -1;        //parametri della mappa
        int mapl = map.size() -1;
        int k=lenght-1;

        //sposto i punti della box del personaggio                                                   
        for(int i=mapl-newpos;i>mapl-newpos-lenght;i--){                               
            Vector<Point> r = new Vector<Point>();         
            for(int j=0;j<width;j++){  
                if(icon[k][j]==true){           
                    r.add(map.get(i).get(j));
                }
            }
            k--;
            box.add(r); 
        }            

        //reinserisco il personaggio spostato
        for (Vector<Point> vb : box) {
            for (Point pb : vb) {
                pb.setCharacter(true);
            }
        }
    }

    //semplici metodi di set e get

    public void setAcc(double acc_){
        accy = -9.81 + acc_;
    }
    public void setSpeed(double speed_){
        speedy = speed_;
    }
    public int getIntPosY(){
        return (int)y;
    }
}

    
