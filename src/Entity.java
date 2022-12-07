import java.awt.Color;

public abstract class Entity {
    private Color color;
    private Point mainBlock;
    private Map map;
    private boolean alive;

    public Entity(Color color, Point mainBlock, Map map){
        this.color = color;
        this.mainBlock = mainBlock;
        this.map = map;
        this.alive = true;
    }


    public void bitten(Point p){
        this.alive = false;
        System.out.println("goodbye..");
    }

    public void setMainBlock(Point mainBlock){ this.mainBlock = mainBlock;}

    public Color getColor(){ return color;}
    public Point getMainBlock(){ return mainBlock;}
    public Map getMap(){ return map;}
    public boolean getExists(){ return alive;}

}
