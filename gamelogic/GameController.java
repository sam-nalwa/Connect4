package gamelogic;

public class GameController{
	TokenState colourPlacing;
	public GameController(){
		Random generator = new Random();
		int firstMove = generator.nextInt(2 - 0);
		switch (firstMove) {
			case 0:
				colourPlacing = TokenState.RED;
			case 1:
				colourPlacing = TokenState.BLUE;
			default:
				System.out.println("generator failed");
		}
	}
}