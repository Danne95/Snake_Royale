import javax.swing.JLabel;
import java.awt.Color;

public class MapBlock extends JLabel {
    private Point point;
    private Color color;
    private Element element;

    // ctor
    public MapBlock(Point point, Color color){
        super();
        this.point = point;
        this.element = Element.VOID;
        this.updateBlock(color, Element.VOID);
    }

    // ctor
    public MapBlock(int row, int col, Color color){
        super();
        this.point = new Point(row, col);
        this.element = Element.VOID;
        this.updateBlock(color, Element.VOID);
    }

    public void updateBlock(Color color, Element element){
        this.color = color;
        this.element = element;
        super.setBackground(color);
    }

    public void setPoint(int i, int j){
        this.point.setx(i);
        this.point.sety(j);
    }

    public void setPoint(Point point){
        this.point = point;
    }

    public int getRow(){ return point.getx();}
    public int getCol(){ return point.gety();}
    public Color getColor(){ return color;}
    public Element getElement(){ return element;}

    public String toString(){
        return color.toString();
    }
}
