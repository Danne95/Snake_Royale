public enum Element {
    VOID(2),
    SNAKE(1),
    APPLE(3);

    private int value;

    Element(int value){
        this.value = value;
    }

    public int getValue(){return value;}
}
