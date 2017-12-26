package endlessrunningtcf;

public class Game{
    public static void main(String[] args){
        Player p = new Player();

        System.out.print("\033[2J");

        p.Run();

        System.out.print("\033[A");
        System.out.print("\033[2K");
        System.out.println("Gioco terminato!");

        System.exit(0);

    }
}