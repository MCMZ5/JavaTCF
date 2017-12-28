package endlessrunningtcf;

public class Game{
    public static void main(String[] args){
        Player p = new Player();

        System.out.print("\033[2J");
        System.out.print("PHYSICS RUN"); 
        System.out.println(); 
        System.out.print("+");
        for(int i=0; i<p.getWidth(); i++){
            System.out.print("-");  
        }  
        System.out.print("+");
        System.out.println();    

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

        p.Run();

        System.out.print("\033[2K");
        System.out.println("Gioco terminato!");
        System.out.print("\033[0m");

        System.exit(0);

    }
}