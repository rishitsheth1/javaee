package binary.search.game.exceptions;

public class GameLostException extends Exception {
	String exception;

	public GameLostException(String exception) {
		super();
		this.exception = exception;
		System.out.println(exception);
	}

}
