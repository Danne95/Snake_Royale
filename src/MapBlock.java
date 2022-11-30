import javax.swing.JLabel;
import java.awt.Color;

public class MapBlock extends JLabel {
    private Color color;
    private int blockValue;

    // ctor
    public MapBlock(Color color){
        super();
        this.updateBlock(color, Element.VOID);
    }

    public void updateBlock(Color color, Element element){
        this.color = color;
        super.setBackground(color);
        blockValue = element.getValue();
    }

    public Color getColor(){ return color;}
    public int getBlockValue(){ return blockValue;}

    public String toString(){
        return color.toString();
    }
}
