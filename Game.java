package endlessrunningtcf;

public class Game{
    public static void main(String[] args){
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
            System.out.println();  
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

        p.Run();                                //la funzione Run è il gioco vero e proprio

        System.out.print("\033[2K");            //queste istruzioni 
        System.out.println("Gioco terminato!"); //stampano un messaggio finale
        System.out.print("\033[0m");

        System.exit(0);                         //chiusura del programma

    }
}