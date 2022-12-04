
public class Point{
    private int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other){
        Point o = (Point) other;
		if (this.x==o.getx() && this.y==o.gety())
			return true;
		else 
            return false;
    }

    public String toString(){
        return "Point (" + x + ", " + y + ")";
    }

    public int getx(){ return x;}
    public int gety(){ return y;}

    public void setx(int x){ this.x = x;}
    public void sety(int y){ this.y = y;}
}
