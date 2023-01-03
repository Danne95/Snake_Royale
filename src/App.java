import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class App    {
    private JFrame frame;
    private JPanel panel;

    public App(){
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(" Snake_Royale ");
        frame.setMinimumSize(new java.awt.Dimension(400,300));
        frame.pack();
        frame.setVisible(true);
    }

    // runner
    public static void main(String[] args) throws Exception {
        System.out.println("\t\t\t\t\t\t\t\t\t***Hello, Welcome to 'App.java!'***");
        App app = new App();
        Game game = new Game(app, 23, 1);
        game.start(1337, 69);
        game.summary();
        System.out.println("\t\t\t\t\t\t\t\t\t\t***end of 'App.java!'***");
    }

    public JFrame getFrame(){return frame;}
    public JPanel getPanel(){return panel;}

    public void setPanel(JPanel panel){
        this.panel = panel;
    }
}