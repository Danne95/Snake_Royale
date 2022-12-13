import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

public class Snake extends Entity implements Utilities{
    private int length;
    private String headDir;
    private LinkedList<Point> body; //(head of snake is last block)
    private int potential = 0;
    private String titles;

    // ctor (with a list of body segments(Points)) 
    public Snake(App app, Color color, LinkedList<Point> body, String headDir, Map map){
        super(Element.SNAKE, color, body.getLast(), map);
        this.length = body.size();
        this.headDir = headDir;
        this.body = body; 
        this.titles = "";
        System.out.println("snake creation complete:\n" + this);
        map.placeSnake(this);
    }

    // ctor (with two lists of coordinates) 
    public Snake(App app, Color color, int[] xs, int[]ys, String headDir, Map map) throws ArrayIndexOutOfBoundsException{
        super(Element.SNAKE, color, new Point(xs[xs.length-1], ys[ys.length-1]), map);
        this.length = xs.length;
        this.headDir = headDir;
        this.titles = "";
        this.body = new LinkedList<>();
        if(xs.length != ys.length){
            throw new ArrayIndexOutOfBoundsException("snake x and y array lengths do not match !!!");
        }
        for(int i=0; i<this.length; i++){
            this.body.add(new Point(xs[i], ys[i]));
        }
        System.out.println("snake creation complete, " + this);
        map.placeSnake(this);
    }

    public Point move(){
        System.out.println("-1--------------------------------------");
        System.out.println(this);
        System.out.println("-2--------------------------------------");
        System.out.println(this.body);
        System.out.println("-3--------------------------------------");
        System.out.println(this.getBody());
        System.out.println("-4--------------------------------------");
        System.out.println();this.getAlive();
        System.out.println("-5--------------------------------------");
        Point currHead = body.getLast();
        int x = currHead.getx();
        int y = currHead.gety();
        int mapSize = this.getMap().getMapSize();
        Point options[] = new Point[4]; // N,E,S,W
        int optionScores[] = new int[4];

        // handles a bug of a dead snake not realizing its dead yet
        if(this.getAlive() == false){
            return null;
        }

        // potential points to move to
        options[0] = new Point((x+mapSize-1)% mapSize, y);      // north
        options[1] = new Point(x, (y+1)% mapSize);              // east
        options[2] = new Point((x+1)% mapSize, y);              // south
        options[3] = new Point(x, (y+mapSize-1)% mapSize);      // west

        // values for move options
        for(int i=0; i<4 ;i++){
            // check if detected snake is self, if true, give score in relation to segment index
            if(this.contains(options[i])){
                optionScores[i] = - (body.indexOf(options[i])+1) ;
            }
            // not self
            else{
                optionScores[i] = this.getMap().getBlockElement(options[i]).getValue();
            }
        }
        // holds index for the next move chosen (0=north,...)
        int bestMove = findBestDirection(optionScores);

        // if snake bites itself
        if(optionScores[bestMove] < 0){
            System.out.println("\t" + options[bestMove] + " selfbite");
            bitten(options[bestMove]);
        }
        // else add to snake the potential he ate
        else{
            this.potential += optionScores[bestMove]-Element.VOID.getValue();
        }

        // [tail] if snake moves forward move tail
        if(potential == 0){
            if(this.getMap().getBlockColor(body.getFirst()) == this.getColor()){
                this.getMap().setBlock(body.getFirst(), null, Element.VOID);
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
        this.getMap().setBlock(options[bestMove], this, Element.SNAKE);
        
        // if the code runs in low delay head and tail point to same block after self-bite
        // python too fast??? quick!! call stackoverflow!!
        if(body.getFirst().equals(body.getLast())){
            body.removeFirst();
        }

        // return point to handle only if impacted a different entity
        if (optionScores[bestMove] == Element.APPLE.getValue() || optionScores[bestMove] == Element.SNAKE.getValue()){
            return options[bestMove];
        }
        // self, void or snake_remains
        else{
            return null;
        }
    }

    @Override
    public boolean bitten(Point p){
        int temp = body.indexOf(p);
        boolean dead = false;
        System.out.println("handling a bite " + p + " to " + body);
        // if head of snake is bitten (dead)
        if(p.equals(body.getLast())){
            System.out.println("Friendly Fire!!");
            dead = super.bitten(p);
        }
        // handle bitten parts
        for(int i=0; i<temp+1; i++){
            // set as snake_remains element, but check if the block is not overwritten by something else first (check if self color) just in case
            if(this.getMap().getBlockColor(body.getFirst()) == this.getColor()){
                try{
                    this.getMap().setBlock(body.getFirst(), null, Element.SNAKE_REMAINS);
                }
                catch (IndexOutOfBoundsException Ioobe){
                    System.out.print("\n\n@@@@@@@@@@@IndexOutOfBoundsException@@@@@@@@@@@" + body + body.size() + "\n" + i);
                    System.exit(1);
                }
            }
            body.removeFirst();
            length--;
        }
        return dead;
    }

    public int findBestDirection(int[] optionScores){
        int maxValue = -(this.length + Element.maxValue()); // will hold the highest value from options
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
            System.out.println(" options: " + optionScores[0] + " " + optionScores[1] + " " + optionScores[2] + " " + optionScores[3] + " " + "\tchosen " + maxValue + " ");
        }
        // if there are multiple best options, get a random one
        int randChoice = getRandomInt(0, maxIndexes.size());
        return maxIndexes.get(randChoice);
    }
    
    public void addTitle(String s){ this.titles += s;}
    public void changeLength(int delta){ this.length += delta;}
    public void setheadDir(String dir){ this.headDir = dir;}
    public void addPotential(int growth){ this.potential += potential;}

    public int getLength(){ return length;}
    public LinkedList<Point> getBody(){ return body;}
    public Point getHead(){ return body.getFirst();}
    public String getheadDir(){ return headDir;}
    public int getPotential(){ return potential;}
    public String getTitles(){ return titles;}

    @Override
    public String toString(){
        return "Snake:\n" + super.toString() + ", length: " + length + "\nbody: " + body + "\n";
    }

    @Override
    public boolean contains(Point point){
        return body.contains(point);
    }
}