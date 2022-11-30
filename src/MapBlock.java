import javax.swing.JLabel;
import java.awt.Color;

public class MapBlock extends JLabel {
    private Color color;

    // ctor
    public MapBlock(){super();}
    public MapBlock(Color color){
        super();
        this.setBackground(color);
    }

    //public void setColor(Color color){ this.color=color;}
    public void setBackground(Color color){
        this.color = color;
        super.setBackground(color);
    }

    public Color getColor(){ return color;}

    public String toString(){
        return color.toString();
    }
}
