package endlessrunningtcf;

import java.io.IOException;

public class Game{
    public static void main(String[] args){
        String instructions[] = {"In questo gioco interpreti un personaggio che deve saltare degli ostacoli.","Ogni 300 punti passerai di livello e aumenterà la difficoltà con movimenti più veloci e complessi degli ostacoli.","Nel primo livello si muoveranno a velocità costante mentre dal secondo avranno anche un'accelerazione.","Dal quinto avranno anche un moto verticale rimbalzando sul pavimento.","Velocità, accelerazione e frequenza degli ostacoli aumenteranno col progredire dei livelli.","L'obiettivo è arrivare il più avanti possibile senza schiantarsi.","Fai attenzione a premere solo il tasto ENTER.","Premendo ENTER inizierà il gioco, buona fortuna!"};
        
        Player p = new Player();    //si crea il giocatore

        System.out.print("\033[2J");            //queste istruzioni
        System.out.print("PHYSICS RUN");        //per il terminale 
        System.out.println();                   //stampano il titolo
        System.out.print("+");                  //e due barre orizzontale 
        for(int i=0; i<p.getWidth(); i++){      //sopra e sotto la 
            System.out.print("-");              //schermata di gioco
        }                                       //stampano inoltre le
        System.out.print("+");                  //istruzioni per giocare
        System.out.println();                   //in basso

        for(int i=0; i<p.getLenght(); i++){
            if(i<8){
                System.out.println(instructions[i]);
            }
            else{
                System.out.println();

            }
        }  
        System.out.print("+");
        for(int i=0; i<p.getWidth(); i++){
            System.out.print("-");  
        }  
        System.out.print("+");
        System.out.println();  
        System.out.print("Premere ENTER per saltare");     
        System.out.println();  
        System.out.print("\033[A");
        System.out.print("\033[A");
        
        try{
            System.in.read();   //Thread.sleep(30000);
        }
        catch(IOException e){
        }
        System.out.print("\033[A");

        p.Run();                                //la funzione Run è il gioco vero e proprio

        System.out.print("\033[2K");            //queste istruzioni 
        System.out.println("Gioco terminato!"); //stampano un messaggio finale
        System.out.print("\033[0m");

        System.exit(0);                         //chiusura del programma

    }
}
