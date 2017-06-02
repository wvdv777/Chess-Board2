package chessBoard;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Knight extends Piece {
	
	public Knight(String position, Color color) {
		super(position, color);
	}
	
	@Override
	public boolean move(ArrayList<Piece> pieces, String newPos) {
		
		if (firstCheck(newPos)){
			return false;					// from here we know that coordinates are A1-H8
		}		
		
		if(moveLogic(newPos, true)){
			return false;						
		}

		if(checkIfOccupied(pieces,newPos)){		// returns false if trying to move on own piece and removes piece if land on another color's piece
			return false;
		}
		
		
		this.setPosition(newPos);
		
		return true;
		

		}

	

	private boolean moveLogic(String newPos,boolean realMove) {
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
		if(Math.abs(Nnumber-Onumber)+Math.abs(Nletter-Oletter) ==3){
			
			if((Math.abs(Nnumber-Onumber) ==3 || Math.abs(Nletter-Oletter) ==3)){
				return true;
			}
			
			
			return false;
	
		}
		if(realMove){
		System.out.println("Can't make this move with a knight");
		}
		return true;
	}
	
	
	
	public Set<String> possibleMoves(ArrayList<Piece> pieces){
		
		Set<String> possibleMoves = new TreeSet<>();
		ArrayList<String>wholeBoard = new ArrayList<>();		
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				
				String letter = convertToLetter(i+1);
				String number = String.valueOf(j+1);
				String coordinate = letter+number;
				
				wholeBoard.add(coordinate);		
			}
		}
		
		for(String cor: wholeBoard){
			
			if(!moveLogic(cor, false)){
				
				possibleMoves.add(cor);		
			}
		}
		
		Set<String> toBeRemoved = new TreeSet<>();
		
		for(Piece p: pieces){		
			for(String cor :possibleMoves){
				
				if(p.getPosition().equals(cor)&& p.getColor()==getColor()){
					toBeRemoved.add(cor);
				}	
			}
		}
		possibleMoves.removeAll(toBeRemoved);
		
		return possibleMoves;
	
	}

}
