public enum Element {
    VOID(0),
    SNAKE(1),
    APPLE(2);

    private int value;

    Element(int value){
        this.value = value;
    }

    public int getValue(){return value;}
}
