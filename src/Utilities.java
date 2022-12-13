import java.util.Random;

public interface Utilities {

	public default boolean getRandomBoolean() {
		return new Random().nextBoolean();
	}

	public default int getRandomInt(int minimum, int maximum) {
		// (0,3) --> get randoms from [0,1,2]
		return new Random().nextInt(maximum-minimum)+minimum;
	}

	public default String appleTitle() {
		return " ####";
	}

	public default String snakeTitle() {
		return " @@@@";
	}
}
