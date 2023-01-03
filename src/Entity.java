import java.awt.Color;

public abstract class Entity extends Thread implements Utilities {
    private Element element;
    private Color color;
    private Point mainBlock;
    private Map map;
    private boolean alive;
    abstract boolean contains(Point point);

    public Entity(Element element, Color color, Point mainBlock, Map map){
        this.element = element;
        this.color = color;
        this.mainBlock = mainBlock;
        this.map = map;
        this.alive = true;
    }

    public boolean bitten(Point p){
        this.alive = false;
        System.out.println(this + " --> goodbye..\n");
        return true;
    }

    public void setMainBlock(Point mainBlock){ this.mainBlock = mainBlock;}

    public Element getElement(){ return element;}
    public Color getColor(){ return color;}
    public Point getMainBlock(){ return mainBlock;}
    public Map getMap(){ return map;}
    public boolean getAlive(){ return alive;}

    @Override
    public String toString(){
        return "main block: " + mainBlock + ", color: " + color + ", alive: " + alive;
    }
}
