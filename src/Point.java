
public class Point{
    private int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getx(){ return x;}
    public int gety(){ return y;}

    public void setx(int x){ this.x = x;}
    public void sety(int y){ this.y = y;}

    public boolean equals(Point other) {
		if (this.x==other.x && this.y==other.y)
			return true;
		else 
            return false;
	}

    public String toString(){
        return "Point (" + x + ", " + y + ")";
    }
}
