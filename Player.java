public class Player{

    //-----ATTRIBUTI------

    private String name;
    private Boolean crushed;

    //------METODI-------

    public Player(){
        crushed = false;
    }
    public Boolean getCrushed(){
        return crushed;
    }
    public void Run(){
        System.out.println("Run");
        crushed = true; 
    }

}