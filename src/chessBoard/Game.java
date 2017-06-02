package chessBoard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Game {

	ArrayList<Piece> pieces = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	
	String oldPos;
	String newPos;
	int counter = 0;
	

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Game(){
		
		pieces.add(new Pawn("A2", Color.White));
		pieces.add(new Pawn("B2", Color.White));
		pieces.add(new Pawn("C2", Color.White));
		pieces.add(new Pawn("D2", Color.White));
		pieces.add(new Pawn("E2", Color.White));
		pieces.add(new Pawn("F2", Color.White));
		pieces.add(new Pawn("G2", Color.White));
		pieces.add(new Pawn("H2", Color.White));
		pieces.add(new Rook("H1", Color.White));
		pieces.add(new Rook("A1", Color.White));		
		pieces.add(new Knight("B1", Color.White));
		pieces.add(new Knight("G1", Color.White));
		pieces.add(new Bishop("C1", Color.White));
		pieces.add(new Bishop("F1", Color.White));
		pieces.add(new King("E1", Color.White));
		pieces.add(new Queen("D1", Color.White));
		
		pieces.add(new Pawn("A7", Color.Black));
		pieces.add(new Pawn("B7", Color.Black));
		pieces.add(new Pawn("C7", Color.Black));
		pieces.add(new Pawn("D7", Color.Black));
		pieces.add(new Pawn("E7", Color.Black));
		pieces.add(new Pawn("F7", Color.Black));
		pieces.add(new Pawn("G7", Color.Black));
		pieces.add(new Pawn("H7", Color.Black));
		pieces.add(new Rook("H8", Color.Black));
		pieces.add(new Rook("A8", Color.Black));
		pieces.add(new Knight("B8", Color.Black));
		pieces.add(new Knight("G8", Color.Black));
		pieces.add(new Bishop("C8", Color.Black));
		pieces.add(new Bishop("F8", Color.Black));
		pieces.add(new King("E8", Color.Black));
		pieces.add(new Queen("D8", Color.Black));
		
	}

		public void turn(boolean whiteTurn){
			
		int pieceNumber=100;
			
			if(whiteTurn){
				boolean flag = false;
				
				System.out.println("UPPER CASE plz enter your piece to move");
				oldPos = scanner.next().toUpperCase();
				
				for(int i=0; i<pieces.size();i++){
					
					if(pieces.get(i).getPosition().equals(oldPos) && pieces.get(i).getColor()== Color.White){
						flag =true;
						pieceNumber = i;
						
					}
				}
					
				if(pieceNumber != 100){
				System.out.println("Plz enter where to move");
						newPos = scanner.next().toUpperCase();
						if(pieces.get(pieceNumber).move(pieces,newPos)){					// if move executed correctly then counter ++
							counter++;			
						}
				}

				if(!flag){
					System.out.println("plz enter a valid piece");
				}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
				for(Piece p: pieces){
					
					if(p.getColor()==Color.White || !(p instanceof King)){
						continue;						
					}
					King king = (King) p;
					Set<String> moveList = king.movesOpponent(pieces);
					for(String cor: moveList){
						
						if(cor.equals(king.getPosition())){
							System.out.println("Lower case you are checked, protect your king");
						}				
					}	
				}
			}
			
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			if(!whiteTurn){
				boolean flag = false;
				
				System.out.println("lower case plz enter your piece to move");
				oldPos = scanner.next().toUpperCase();
				
				for(int i=0; i<pieces.size();i++){
					
					if(pieces.get(i).getPosition().equals(oldPos) && pieces.get(i).getColor()== Color.Black){
						flag =true;
						pieceNumber = i;
						
					}
				}
					
				if(pieceNumber != 100){
				System.out.println("Plz enter where to move");
						newPos = scanner.next().toUpperCase();
						if(pieces.get(pieceNumber).move(pieces,newPos)){					// if move executed correctly then counter ++
							counter++;			
						}
				}

				if(!flag){
					System.out.println("plz enter a valid piece");
				}
				
			for(Piece p: pieces){
					
					if(p.getColor()==Color.Black || !(p instanceof King)){
						continue;						
					}
					King king = (King) p;
					Set<String> moveList = king.movesOpponent(pieces);
					for(String cor: moveList){
						
						if(cor.equals(king.getPosition())){
							System.out.println("Upper case you are checked, protect your king");
						}				
					}	
				}
			}
			}	
			
		///////////////////////////////////////////////////////////////       Win condition              ///////////////////////////////////////////////////////////
		
		
		public void checkUpperCaseWin(){
			
			for(Piece p: pieces){
				
				if(p instanceof King && p.getColor() == Color.Black){
				return;
				}
			}
			System.out.println("Congratulations upper case you win");
			System.exit(1);
		}
		
		
		public void checkLowerCaseWin(){
			
		for(Piece p: pieces){
				
				if(p instanceof King && p.getColor() == Color.White){
				return;
				}
			}
			System.out.println("Congratulations lower case you win");
			System.exit(1);
		}
	
		
		
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////  Below here print board implementation
	//////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
			public void printBoard(){
				
				String[][] array = new String[8][8];

				for(int i =0; i<array.length; i++){
					for(int ii = 0; ii<array.length;ii++){
						
					array[i][ii] = "O";	
					
					}
				}
				
				for(Piece p: pieces){
					
					int column = lettersToInteger(p.getPosition().substring(0,1));
					int row= Integer.parseInt(p.getPosition().substring(1,2));
					
					if (p instanceof Rook){
						
						if(p.getColor()==Color.Black){
					
					array[row-1][column-1] = "r";
					}	
					
					if(p.getColor()==Color.White){
							
					array[row-1][column-1] = "R";		
							
						}
						}
					
					if (p instanceof Pawn){
					
						if(p.getColor()==Color.Black){
							
							array[row-1][column-1] = "p";
							}	
							
							if(p.getColor()==Color.White){
									
							array[row-1][column-1] = "P";		
									
								}
					
					}
					
					if (p instanceof Knight){
						
						if(p.getColor()==Color.Black){
							
							array[row-1][column-1] = "h";
							}	
							
							if(p.getColor()==Color.White){
									
							array[row-1][column-1] = "H";		
									
								}
					
					}
					
					if (p instanceof Queen){
						
						if(p.getColor()==Color.Black){
							
							array[row-1][column-1] = "q";
							}	
							
							if(p.getColor()==Color.White){
									
							array[row-1][column-1] = "Q";		
									
								}
					
					}
					
					if (p instanceof Bishop){
						
						if(p.getColor()==Color.Black){
							
							array[row-1][column-1] = "b";
							}	
							
							if(p.getColor()==Color.White){
									
							array[row-1][column-1] = "B";		
									
								}
					
					}
					
					if (p instanceof King){
						
						if(p.getColor()==Color.Black){
							
							array[row-1][column-1] = "k";
							}	
							
							if(p.getColor()==Color.White){
									
							array[row-1][column-1] = "K";		
									
								}
					
					}
					
					
					
					}
					
				
				System.out.println("    ABCDEFGH\n");
				
				
				for(int i=0; i<8;i++){
					System.out.print((i+1) + " : ");
					for(int ii =0; ii<8; ii++){
						
							
								
							System.out.print(array[i][ii]);

						
					}
					System.out.println();
								}	
			}
			

int lettersToInteger(String letter){
	
	int number=0;
	
	switch(letter){
	case "A" : number =1;
	break;
	case "B" : number =2;
	break;
	case "C" : number =3;
	break;
	case "D" : number =4;
	break;
	case "E" : number =5;
	break;
	case "F" : number =6;
	break;
	case "G" : number =7;
	break;
	case "H" : number =8;
	break;
	}
	
	return number;
	
	
}


}