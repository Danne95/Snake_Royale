import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

public class Snake implements Utilities{
    private int length;
    private Color color;
    private String headDir;
    private LinkedList<Point> body; //(head of snake is last block)
    private int potential = 0;
    private Map map;

    // ctor (with a list of body segments) 
    public Snake(App app, Color color, LinkedList<Point> body, String headDir, Map map){
        this.color = color;
        this.length = body.size();
        this.headDir = headDir;
        this.map = map;
        this.body = body; 
        System.out.println("snake creation complete:\n" + this);
        map.placeSnake(this);
    }

    // ctor (with two lists of coordinates) 
    public Snake(App app, Color color, int[] xs, int[]ys, String headDir, Map map){
        this.color = color;
        this.length = xs.length;
        this.headDir = headDir;
        this.map = map;
        this.body = new LinkedList<>();
        for(int i=0; i<this.length; i++){
            this.body.add(new Point(xs[i], ys[i]));
        }
        System.out.println("snake creation complete, " + this);
        map.placeSnake(this);
    }

    public String toString(){
        return "Snake: \nlength: " + length + ", color: " + color + "\nbody: " + body + "\n";
    }
    
    public void move(){
        Point currHead = body.getLast();
        int x = currHead.getx();
        int y = currHead.gety();
        int mapSize = map.getMapSize();
        Point options[] = new Point[4]; // N,E,S,W
        int optionScores[] = new int[4];

        // potential points to move to
        options[0] = new Point((x+mapSize-1)% mapSize, y);      // north
        options[1] = new Point(x, (y+1)% mapSize);              // east
        options[2] = new Point((x+1)% mapSize, y);              // south
        options[3] = new Point(x, (y+mapSize-1)% mapSize);      // west

        // values for point options
        for(int i=0; i<4 ;i++){
            // check if detected snake is self, if true, give score in relation to segment index
            if(body.contains(options[i])){
                optionScores[i] = - (body.indexOf(options[i])+1) ;
            }
            // not self
            else{
                optionScores[i] = map.getBlockElement(options[i]).getValue();
            }
            System.out.print(options[i] + " is " + optionScores[i] + " ");
        }

        // holds index for the next move chosen (0=north,...)
        int bestMove = findBestDirection(optionScores);
        System.out.println("\n\t" + body.getLast() + " >> " + options[bestMove] + "\t" + body);

        body.add(options[bestMove]);
        map.setBlock(options[bestMove], color, Element.SNAKE);
        if(potential == 0){
            map.setBlock(body.getFirst(), Color.LIGHT_GRAY, Element.VOID);
            body.removeFirst();   
        }
        //snake grows forward
        else{
            length++;
            potential--;
        }
    }

    public int findBestDirection(int[] optionScores){
        int maxValue = optionScores[0]; // will hold the highest value from options
        List<Integer> maxIndexes = new ArrayList<>(); // holds indexes (0=north,...)
        for(int i=1; i<4; i++){
            if(optionScores[i] == maxValue){
                maxIndexes.add(i);
            }
            else if(optionScores[i] > maxValue){
                maxIndexes.clear();
                maxIndexes.add(i);
                maxValue = optionScores[i];
            }
        }
        // if there are multiple best options, get a random one
        int randChoice = getRandomInt(0, maxIndexes.size());
        return maxIndexes.get(randChoice);
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



        