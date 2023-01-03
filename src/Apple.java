import java.awt.Color;

public class Apple extends Entity{
    
    public Apple(Color color, Point mainBlock, Map map){
        super(Element.APPLE, color, mainBlock, map);
        System.out.println("apple creation complete:\n" + this);
        map.placeApple(this);
    }

    @Override
    public void run(){
        
    }

    @Override
    public boolean bitten(Point p){
        System.out.println("apple has been consumed.. ");
        return super.bitten(p);
    }

    @Override
    public String toString(){
        return "Apple:\n" + super.toString() + "\n";
    }

    @Override
    public boolean contains(Point point){
        return this.getMainBlock().equals(point);
    }
}
