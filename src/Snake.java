import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

public class Snake extends Entity implements Utilities{
    private int length;
    private String headDir;
    private LinkedList<Point> body; //(head of snake is last block)
    private int potential = 0;

    // ctor (with a list of body segments) 
    public Snake(App app, Color color, LinkedList<Point> body, String headDir, Map map){
        super(color, body.getLast(), map);
        this.length = body.size();
        this.headDir = headDir;
        this.body = body; 
        System.out.println("snake creation complete:\n" + this);
        map.placeSnake(this);
    }

    // ctor (with two lists of coordinates) 
    public Snake(App app, Color color, int[] xs, int[]ys, String headDir, Map map){
        super(color, new Point(xs[xs.length-1], ys[ys.length-1]), map);
        this.length = xs.length;
        this.headDir = headDir;
        this.body = new LinkedList<>();
        for(int i=0; i<this.length; i++){
            this.body.add(new Point(xs[i], ys[i]));
        }
        System.out.println("snake creation complete, " + this);
        map.placeSnake(this);
    }

    public int move(){
        Point currHead = body.getLast();
        int x = currHead.getx();
        int y = currHead.gety();
        int mapSize = this.getMap().getMapSize();
        Point options[] = new Point[4]; // N,E,S,W
        int optionScores[] = new int[4];

        // potential points to move to
        options[0] = new Point((x+mapSize-1)% mapSize, y);      // north
        options[1] = new Point(x, (y+1)% mapSize);              // east
        options[2] = new Point((x+1)% mapSize, y);              // south
        options[3] = new Point(x, (y+mapSize-1)% mapSize);      // west

        // values for move options
        for(int i=0; i<4 ;i++){
            // check if detected snake is self, if true, give score in relation to segment index
            if(body.contains(options[i])){
                optionScores[i] = - (body.indexOf(options[i])+1) ;
            }
            // not self
            else{
                optionScores[i] = this.getMap().getBlockElement(options[i]).getValue();
                if(optionScores[i] != Element.VOID.getValue()){
                    potential++;
                }
            }
        }
        // holds index for the next move chosen (0=north,...)
        int bestMove = findBestDirection(optionScores);

        // if snake bites itself
        if(body.contains(options[bestMove])){
            System.out.println(" ouch");
            bitten(options[bestMove]);
        }

        // [tail] if snake moves forward move tail
        if(potential == 0){
            if(this.getMap().getBlockColor(body.getFirst()) == this.getColor()){
                this.getMap().setBlock(body.getFirst(), Color.LIGHT_GRAY, Element.VOID);
            }
            body.removeFirst();   
        }
        // [tail] snake grows its tail (tail remains in place)
        else{
            length++;
            potential--;
        }

        // [head] add the new head block to the snake body
        body.add(options[bestMove]);
        this.setMainBlock(options[bestMove]);
        this.getMap().setBlock(options[bestMove], this.getColor(), Element.SNAKE);
        
        // if the code runs in low delay head and tail point to same block after self-bite
        // python too fast??? quick!! call stackoverflow!!
        if(body.getFirst() == body.getLast()){
            body.removeFirst();
        }

        System.out.println(body + " len= " + length);
        return bestMove;
    }

    @Override
    public void bitten(Point p){
        int temp = body.indexOf(p);
        // if head of snake is bitten (dead)
        if(temp == length-1){
            System.out.print(this.getColor() + " snake has been eaten.. ");
            super.bitten(p);
        }
        // some of snake remains
        else{
            System.out.println(this.getColor() + " snake has been bitten");
            for(int i=0; i<temp+1; i++){
                // remove tail block from map (set void) but check if the block is not overwritten by something else first (check if self color) just in case
                if(this.getMap().getBlockColor(p) == this.getColor()){
                    this.getMap().setBlock(body.get(i), Color.LIGHT_GRAY, Element.VOID);
                }
                body.removeFirst();
                length--;
            }
        }
    }

    public int findBestDirection(int[] optionScores){
        int maxValue = -(this.length + 5); // will hold the highest value from options
        List<Integer> maxIndexes = new ArrayList<>(); // holds indexes (0=north,...)
        for(int i=0; i<4; i++){
            if(optionScores[i] == maxValue){
                maxIndexes.add(i);
            }
            else if(optionScores[i] > maxValue){
                maxIndexes.clear();
                maxIndexes.add(i);
                maxValue = optionScores[i];
            }
        }
        if(maxValue<1){
            System.out.print(" options: " + optionScores[0] + " " + optionScores[1] + " " + optionScores[2] + " " + optionScores[3] + " " + "\tchosen " + maxValue + " ");
        }
        // if there are multiple best options, get a random one
        int randChoice = getRandomInt(0, maxIndexes.size());
        return maxIndexes.get(randChoice);
    }

    public void changeLength(int delta){ this.length += delta;}
    public void setheadDir(String dir){ this.headDir = dir;}
    public void addPotential(int growth){ this.potential += potential;}

    public int getLength(){ return length;}
    public LinkedList<Point> getBody(){ return body;}
    public Point getHead(){ return body.getFirst();}
    public String getheadDir(){ return headDir;}
    public int getPotential(){ return potential;}

    @Override
    public String toString(){
        return "Snake: \nlength: " + length + ", color: " + this.getColor() + "\nbody: " + body + "\n";
    }
}



        