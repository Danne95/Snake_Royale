public enum Element {
    VOID(1),
    SNAKE(2),
    APPLE(3);

    private int value;

    Element(int value){
        this.value = value;
    }

    public int getValue(){return value;}
}
