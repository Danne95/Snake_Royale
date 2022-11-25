import java.util.LinkedList;

public class Snake {
    private int length = 3;
    private String color;
    private String headDir;
    private LinkedList<Point> body;
    private int potential = 0;
    private Map map;
    
    public Snake(String color, LinkedList<Point> body, String headDir, Map map){
        this.color = color;
        this.body = body;
        this.headDir = headDir;
        this.map = map;
    }

    public String toString(){
        return "Snake: \nlength: " + length + ", color: " + color + "\nbody: " + body + " looking " + headDir + "\n";
    }

    public String printSnakeBody(){
        return "body: " + body + " looking " + headDir + "\n";
    }

    public void moveRight(){
        System.out.println("moving right..");
        Point currHead = body.getFirst();
        Point temp = new Point(currHead.getx(), (currHead.gety()+1)%map.getMapSize());
        body.addFirst(temp);
        map.setBlock(temp, color);
        //snake moves
        if(potential == 0){
            map.setBlock(body.getLast(), "e");
            body.removeLast();
            
        }
        //snake grows
        else{
            length++;
            potential--;
        }

        headDir = "Right";
    }

    public int getLength(){ return length;}
    public String getColor(){ return color;}
    public LinkedList<Point> getBody(){ return body;}
    public Point getHead(){ return body.getFirst();}
    public String getheadDir(){ return headDir;}
    public int getPotential(){ return potential;}
    public Map getMap(){ return map;}

    public void changeLength(int delta){ this.length += delta;}
    public void setheadDir(String dir){ this.headDir = dir;}
    public void addPotential(int growth){ this.potential += potential;}
}


