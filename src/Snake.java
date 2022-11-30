import java.util.LinkedList;
import java.awt.Color;

public class Snake implements Utilities{
    private int length = 3;
    private Color color;
    private String headDir;
    private LinkedList<Point> body;
    private int potential = 0;
    private Map map;
    private int[] sides;
    
    // ctor
    public Snake(App app, Color color, LinkedList<Point> body, String headDir, Map map){
        this.color = color;
        this.body = body;
        this.headDir = headDir;
        this.map = map;
        this.sides = new int[4]; //N,E,S,W
    }

    // ctor // TO-DO
    public Snake(App app, Color color, int[] xs, int[]ys, String headDir, Map map){
        //constructor with two lists of coordinates
    }

    public String toString(){
        return "Snake: \nlength: " + length + ", color: " + color + "\nbody: " + body + "\n";
    }
    
    public void move(){
        //System.out.println(color + " snake is doing snake things");
        Point currHead = body.getFirst();
        Point temp;

        //move randomly (even on self, its gonna be funny)
        int rand = getRandomInt(0, 4);
        System.out.println("randomized a " + rand);
        switch(rand){
        case(0):
            // North
            temp = new Point((currHead.getx()+map.getMapSize()-1)%map.getMapSize(), currHead.gety());
            headDir = "north";
            break;
        case(1):
            // East
            temp = new Point(currHead.getx(), (currHead.gety()+1)%map.getMapSize());
            headDir = "east";
            break;
        case(2):
            // South
            temp = new Point((currHead.getx()+1)%map.getMapSize(), currHead.gety());
            headDir = "south";
            break;
        case(3):
            // West
            temp = new Point(currHead.getx(), (currHead.gety()+map.getMapSize()-1)%map.getMapSize());
            headDir = "west";
            break;
        default:
            //didn't move >> stay in place and retail tail
            System.out.println("snake didnt randomly didnt move, huh??");
            temp = currHead;
            potential +=1;
        }
        body.addFirst(temp);
        map.setBlock(temp, color, Element.SNAKE);
        if(potential == 0){
            map.setBlock(body.getLast(), Color.LIGHT_GRAY, Element.VOID);
            body.removeLast();   
        }
        //snake grows forward
        else{
            length++;
            potential--;
        }
    }

    public int getLength(){ return length;}
    public Color getColor(){ return color;}
    public LinkedList<Point> getBody(){ return body;}
    public Point getHead(){ return body.getFirst();}
    public String getheadDir(){ return headDir;}
    public int getPotential(){ return potential;}
    public Map getMap(){ return map;}

    public void changeLength(int delta){ this.length += delta;}
    public void setheadDir(String dir){ this.headDir = dir;}
    public void addPotential(int growth){ this.potential += potential;}
}



        