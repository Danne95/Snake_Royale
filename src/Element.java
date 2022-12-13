public enum Element {
    VOID(0),
    SNAKE(-1),
    SNAKE_REMAINS(5),
    APPLE(10);



    private int value;

    Element(int value){
        this.value = value;
    }

    public int getValue(){return value;}

    static public int maxValue(){ return 3000;}
}

/*dependencies
    MapBlock.updateBlock    --> per case handling
    MapBlock.getElement     --> handles colors not associated with entities
    snake.move              --> many handles




*/