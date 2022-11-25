public enum Directions {
	north(1), east(2), south(3), west(4);
	
	private int dirVal;
	
	Directions(int dirVal) {
		this.dirVal=dirVal; 
	}

	public int getDirVal() {
		return dirVal;
	}
}
