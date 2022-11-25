import java.util.Arrays;

public class Map {
    private int mapSize;
    private String[][] map;

    public Map(int mapSize){
        this.mapSize = mapSize;
        map = new String[mapSize][mapSize];
        for(int i=0; i<mapSize; i++){
            Arrays.fill(map[i],"e");
        }
    }

    public void placeSnake(Snake snake){
        for(int i=0; i<snake.getLength()+1; i++){
            Point temp = snake.getBody().get(i);
            map[temp.getx()][temp.gety()] = "R";
        }
    }

    public void setBlock(Point p, String s){
        System.out.println("setting " + p.getx() + ", " + p.gety() + " with " + s);
        map[p.getx()][p.gety()] = s;
    }

    public int getMapSize(){ return mapSize;}

    public String toString(){
        return Arrays.deepToString(map);
    }

    public void printBlocky(){
        for(int i=0; i< mapSize; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
