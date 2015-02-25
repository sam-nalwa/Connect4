package gamelogic;

public class Client{
	public static void main(String[] args) {
		GameController game = new GameController(false);
		printBoard(game.getBoard());
		System.out.println(game.insertPiece(0,0));
		printBoard(game.getBoard());
		System.out.println(game.insertPiece(0,1));
		printBoard(game.getBoard());
		System.out.println(game.insertPiece(3,0));
		printBoard(game.getBoard());
		System.out.println(game.insertPiece(4,0));
		printBoard(game.getBoard());
	}
	private static void printBoard(Token[][] brdArr){
		// for (Token[] col: brdArr){
		// 	for (Token row: col){
		// 		System.out.println(row.getState());
		// 	}
		// }
		String[] rows = new String[6];
		for (int i = 0; i < rows.length;i++){
			rows[i] = "";
		}
		for (int i = 0; i < brdArr.length; i++){
			for (int j = 0; j < brdArr[i].length; j++){
				if (brdArr[i][j].getState() == TokenState.RED){
					rows[j] += " R ";
				}else if (brdArr[i][j].getState() == TokenState.BLUE){
					rows[j] += " B ";
				}else if (brdArr[i][j].getState() == TokenState.EMPTY){
					rows[j] += " X ";
				}else{
					System.out.println("What? " + brdArr[i][j].getState());
					System.out.println("What2? " + brdArr[i][j].getState());
					System.out.println("What3? " + brdArr[i][j].getState());
					System.out.println("What4? " + brdArr[i][j].getState());
				}
			}
		}
		System.out.println("");
		System.out.println("===========================");
		for (int i = rows.length - 1; i >= 0; i--){
			System.out.println(rows[i]);
		}
		System.out.println("===========================");
		System.out.println("");
	}
}