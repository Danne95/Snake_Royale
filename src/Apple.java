import java.awt.Color;

public class Apple extends Entity{
    
    public Apple(Color color, Point mainBlock, Map map){
        super(color, mainBlock, map);
        System.out.println("apple creation complete, " + this);
        map.placeApple(this);
    }

    @Override
    public void bitten(Point p){
        System.out.println("apple has been consumed.. ");
        super.bitten(p);
    }

    @Override
    public String toString(){
        return "Apple: \ncolor: " + this.getColor() + "\nat: " + getMainBlock() + "\n";
    }
}
