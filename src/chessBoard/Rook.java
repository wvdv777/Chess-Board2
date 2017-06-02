package chessBoard;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Rook extends Piece {
	
	
	public Rook(String position, Color color) {
		super(position, color);
	
	}
	

	@Override
	public boolean move(ArrayList<Piece> pieces, String newPos) {
		
		if (firstCheck(newPos)){
			return false;					// from here we know that coordinates are A1-H8
		}								
		
		if(checkMoveLogic(newPos)){		// from here we now that the move is logical
			return false;
		}
		
		if (moveOverPieces(pieces,  newPos,true)){	// now we now that it can't move over other pieces, can still move on top of other pieces
			return false;
		}
		if(checkIfOccupied(pieces,newPos)){		// returns false if trying to move on own piece and removes piece if land on another color's piece
			return false;
		}
		
		this.setPosition(newPos);
		
		return true;
		
		
	}

	
	boolean checkMoveLogic(String newPos){				// returns true if the rook doesn't try to move in strange ways like diagonal
		
		if((!getPosition().substring(0,1).equals(newPos.substring(0,1)))&&(!getPosition().substring(1,2).equals(newPos.substring(1,2)))){
			
			System.out.println("A rook can't make this move");
			return true;
		}

		return false;
	}
	
	
	
	
	boolean moveOverPieces(ArrayList<Piece> pieces, String newPos, boolean realMove){	//returns true if rook doesn't try to go over pieces, can however land on pieces
		
		String oldPos = getPosition();
		int upperBound;
		int lowerBound;
		
		if(oldPos.substring(0, 1).equals(newPos.substring(0, 1))){
			
			int firstBound = Integer.parseInt(oldPos.substring(1, 2));
			int secondBound = Integer.parseInt(newPos.substring(1, 2));
			
			if(firstBound>secondBound){
				 upperBound = firstBound;
				 lowerBound = secondBound;
			}else{
				 upperBound= secondBound;
				 lowerBound= firstBound;
			}
			
			for(Piece p :pieces){
				
				if(p.getPosition().substring(0,1).equals(oldPos.substring(0, 1))){		// only iterates through the pieces with the same letter
					
					int position = Integer.parseInt(p.getPosition().substring(1,2));
														
					if(position > lowerBound && position < upperBound){
						if(realMove){
						System.out.println("Can't move over your pieces ^^");
						}
						return true;
					}	
				}	
			}
		}
		
		if(oldPos.substring(1, 2).equals(newPos.substring(1, 2))){
		
			int firstBound = lettersToInteger(oldPos.substring(0,1));
			int secondBound = lettersToInteger(newPos.substring(0,1));
			
			if(firstBound>secondBound){
				 upperBound = firstBound;
				 lowerBound = secondBound;
			}else{
				 upperBound= secondBound;
				 lowerBound= firstBound;
			}
			
			for(Piece p :pieces){
				
				if(p.getPosition().substring(1,2).equals(oldPos.substring(1,2))){		// only iterates through pieces with the same number
					
					int position = lettersToInteger(p.getPosition().substring(0,1));	
		
					if(position > lowerBound && position < upperBound){	
						if(realMove){
						System.out.println("Can't move over your pieces <>");
						}
						return true;
					}	
				}
			}
			
		}
		return false;	
		}
	
	
		public Set<String> possibleMoves(ArrayList<Piece> pieces){
			
			Set<String> rookMoves = new TreeSet<>();
			Set<String> boardPos  = new TreeSet<>();
								
	
		for(int i = 0; i<8; i++){
			
			String coordinate = getPosition().substring(0,1) + (i+1);
			rookMoves.add(coordinate);
		}
		
		for(int i=0; i<8; i++){
			
			String coordinate = convertToLetter(i+1) + getPosition().substring(1,2);
			rookMoves.add(coordinate);
		}
		
		rookMoves.remove(getPosition());
		
		Set<String> possibleMoves = new TreeSet<>();
		
		for(String cor : rookMoves){
			if(!moveOverPieces(pieces,cor,false)){
				
				possibleMoves.add(cor);

			}
			
		
		for(Piece p: pieces){
			
			if(p.getColor()==this.getColor()){
				
				String coor = p.getPosition();
				possibleMoves.remove(coor);
				
			}
			
		}
			
			
		}
		return possibleMoves;
			
			
		}
		
	}
	
	
	
	