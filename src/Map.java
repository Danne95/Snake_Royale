import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class Map {
    private int mapSize;
    private App app;
    MapBlock[][] grid;

    public Map(App app, int mapSize){
        this.mapSize = mapSize;
        this.app = app;
        grid = new MapBlock[mapSize][mapSize];
        java.awt.Dimension dim = new java.awt.Dimension(160, 100); // block size
        app.getPanel().setLayout(new GridLayout(mapSize,mapSize));

        for(int i=0; i<mapSize; i++){
            for(int j=0; j<mapSize; j++){
                grid[i][j] = new MapBlock(Color.GRAY);
                grid[i][j].setMinimumSize(new java.awt.Dimension(dim));
                grid[i][j].setPreferredSize(new java.awt.Dimension(dim));
                grid[i][j].setMaximumSize(new java.awt.Dimension(dim));
                grid[i][j].setBorder(new LineBorder(Color.BLACK));
                grid[i][j].setOpaque(true);
                app.getPanel().add(grid[i][j]);
            }
        }
        app.getFrame().pack();
    }

    public String toString(){
        return "Printing Map object: ";
    }

    public void placeSnake(Snake snake){
        for(int i=0; i<snake.getLength()+1; i++){
            Point temp = snake.getBody().get(i);
            grid[temp.getx()][temp.gety()].setBackground(snake.getColor());
        }
    }

    public void setBlock(Point p, Color color){
        grid[p.getx()][p.gety()].setBackground(color);
    }

    public App getApp(){return app;}
    public int getMapSize(){ return mapSize;}
    public Color getBlockColor(int i, int j){return grid[i][j].getColor();}
    public MapBlock[][] getGrid(){return grid;}

    public void printGrid(){
        for(int i=0; i< mapSize; i++){
            for(int j=0; j< mapSize; j++)
                System.out.print(grid[i][j]);
        }
    }
}
