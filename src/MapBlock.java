import javax.swing.JLabel;
import java.awt.Color;

public class MapBlock extends JLabel {
    private Point point;
    private Entity entity;

    // ctor
    public MapBlock(Point point){
        super();
        this.point = point;
        this.updateBlock(null, Element.VOID);
        this.entity = null;
    }

    // ctor
    public MapBlock(int row, int col){
        super();
        this.point = new Point(row, col);
        this.updateBlock(null, Element.VOID);
    }

    public void updateBlock(Entity entity, Element element){
        this.entity = entity;
        if(element == Element.VOID){
            super.setBackground(Color.LIGHT_GRAY);
        }
        else if(element == Element.SNAKE_REMAINS){
            super.setBackground(this.getBackground().darker().darker());
        }
        else{
            super.setBackground(entity.getColor());
        }
    }

    public void setPoint(int i, int j){
        this.point.setx(i);
        this.point.sety(j);
    }

    public void setPoint(Point point){
        this.point = point;
    }

    public Element getElement(){
        if(entity == null){
            if(this.getBackground() == Color.LIGHT_GRAY){
                return Element.VOID;
            }
            else{
                return Element.SNAKE_REMAINS;
            }
        }
        else{
            return entity.getElement();
        }
    }
    public Color getColor(){ return this.getBackground();}
    public int getRow(){ return point.getx();}
    public int getCol(){ return point.gety();}
    public Entity getEntity(){ return entity;}

    public String toString(){
        return " " + point + " " + entity + " ";
    }
}
