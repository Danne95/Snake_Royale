import javax.swing.JLabel;
import java.awt.Color;

public class MapBlock extends JLabel {
    private Color color;
    private Element element;

    // ctor
    public MapBlock(Color color){
        super();
        this.element = Element.VOID;
        this.updateBlock(color, Element.VOID);
    }

    public void updateBlock(Color color, Element element){
        this.color = color;
        this.element = element;
        super.setBackground(color);
    }

    public Color getColor(){ return color;}
    public Element getElement(){ return element;}

    public String toString(){
        return color.toString();
    }
}
