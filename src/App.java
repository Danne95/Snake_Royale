

public class App implements Utilities{
    public static void main(String[] args) throws Exception {
        System.out.println("\t\t\t\t\t\t\t\t\t***Hello, Welcome to 'App.java!'***");
        Game game = new Game(7);
        game.runSnakeRun(5);

        System.out.println("\t\t\t\t\t\t\t\t\t\t***end of 'App.java!'***");
    }
}