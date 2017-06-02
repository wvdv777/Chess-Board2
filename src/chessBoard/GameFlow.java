package chessBoard;

import java.util.Scanner;

public class GameFlow {
	
	
	Game game = new Game();


	public GameFlow(){
		
		int printCounter = 0;
		game.printBoard();
		
		while(true){	
			
			
			if(game.getCounter()%2 == 0){
				
				game.turn(true);
				if(game.getCounter()>printCounter){
				game.printBoard();
				printCounter++;
				game.checkUpperCaseWin();
				}
			}
			if(game.getCounter()%2 == 1){
				
				game.turn(false);
				if(game.getCounter()>printCounter){
				game.printBoard();
				printCounter++;
				game.checkLowerCaseWin();
				}
				
			}
		}	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	



}
