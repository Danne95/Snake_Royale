import java.util.Random;

public interface Utilities {

	public default boolean getRandomBoolean() {
		return new Random().nextBoolean();
	}

	public default int getRandomInt(int minimum, int maximum) {
		// (0,3) --> get randoms from [0,1,2]
		return new Random().nextInt(maximum-minimum)+minimum;
	}

	public default String newTitle(Element el){
		if(el == Element.APPLE){
			return ", eater of apple";
		}
		else if(el == Element.SNAKE){
			return ", slayer of snake";
		}		
		else{
			return " ?";
		}
	}
}
