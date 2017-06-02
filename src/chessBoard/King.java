package chessBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class King extends Piece {
	
	
	public King(String position, Color color) {
		super(position, color);
	}

	@Override
	public boolean move(ArrayList<Piece> pieces, String newPos) {

		if (firstCheck(newPos)){
			return false;					// from here we know that coordinates are A1-H8
		}
		
		if(moveLogic(newPos,true)){
			return false;
		}
	
		
		if(checkForSuicide(pieces,newPos)){
			return false;
		}
		
		if(checkIfOccupied(pieces,newPos)){		// returns false if trying to move on own piece and removes piece if land on another color's piece
			return false;
		}	
		
		
		this.setPosition(newPos);
		
		return true;
	}

	

	private boolean moveLogic(String newPos, boolean realMove) {
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
		if(Math.abs(Nletter-Oletter)>1||Math.abs(Nnumber-Onumber)>1){
			if(realMove){
			System.out.println("incorrect move for a King");
			}
			return true;
		}
		
		return false;
	}

	
	
	
	
	
	
	public boolean checkForSuicide(ArrayList<Piece> pieces, String newPos) {
		
		Set<String> possibleMoves = movesOpponent(pieces);
		for(String dangerCoords: possibleMoves){
			
			if(newPos.equals(dangerCoords)){
				System.out.println("You can't move here with the king");
				return true;
			}	
		}
		return false;
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////  Implementation of possible moves below
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Set<String> movesOpponent(ArrayList<Piece> pieces){
		
		Set<String> possibleMoves = new HashSet<String>();
		
		possibleMoves.addAll(pawnMoves(pieces));
		possibleMoves.addAll(rookMoves(pieces));
		possibleMoves.addAll(knightMoves(pieces));
		possibleMoves.addAll(bishopMoves(pieces));
		possibleMoves.addAll(queenMoves(pieces));
		possibleMoves.addAll(kingMoves(pieces));
		
		
		return possibleMoves;
		
	}
	
	
	private Set<String> pawnMoves(ArrayList<Piece> pieces){
		
		Set<String> set = new HashSet<String>();
		
		if(getColor() == Color.White){
			
		for(Piece p:pieces){
			if(p.getColor() == Color.Black && p instanceof Pawn){
				String coordinates = p.getPosition();
				int letter = lettersToInteger(coordinates.substring(0,1));
				int number = Integer.parseInt(coordinates.substring(1,2));
				int newLetterL = letter -1;
				int newNumberL = number -1;
				
				if(newLetterL>0 && newNumberL>0){
					
					String newLetter1 = convertToLetter(newLetterL);
					String newNumber1 = String.valueOf(newNumberL);
					String newCor     = newLetter1+newNumber1;
					if(newCor.length() ==2){
					set.add(newCor);
					}
				}
				
				int newLetterR = letter +1;
				int newNumberR = number -1;
				
				if(newLetterL<8 && newNumberL>0){
				
					String newLetter1 = convertToLetter(newLetterR);
					String newNumber1 = String.valueOf(newNumberR);
					String newCor     = newLetter1+newNumber1;
					if(newCor.length() ==2){
						set.add(newCor);
						}
					}
				}	
			}
		}
			
		
		if(getColor() == Color.Black){
			
			for(Piece p:pieces){
				if(p.getColor() == Color.White && p instanceof Pawn){
					String coordinates = p.getPosition();
					
					int letter = lettersToInteger(coordinates.substring(0,1));
					int number = Integer.parseInt(coordinates.substring(1,2));
					int newLetterL = letter -1;
					int newNumberL = number +1;
					
					if(newLetterL>0 && newNumberL>0){
						
						String newLetter1 = convertToLetter(newLetterL);
						String newNumber1 = String.valueOf(newNumberL);
						String newCor     = newLetter1+newNumber1;
						if(newCor.length() ==2){
						set.add(newCor);
						}
					}
					
					int newLetterR = letter +1;
					int newNumberR = number +1;
					
					if(newLetterL<8 && newNumberL>0){
					
						String newLetter1 = convertToLetter(newLetterR);
						String newNumber1 = String.valueOf(newNumberR);
						String newCor     = newLetter1+newNumber1;
						if(newCor.length() ==2){
							set.add(newCor);
							}
						}
					}	
				}			
			}
			
		return set;
	}
	
	
	
	private Set<String> rookMoves(ArrayList<Piece> pieces){
		
		Set<String> set = new HashSet<String>();
		
		for(Piece p: pieces){
			
			if(!(p instanceof Rook) || p.getColor() == getColor()){
				continue;
			}
			Rook rook;
			rook = (Rook) p;
			set.addAll(rook.possibleMoves(pieces));
			
		}
		return set;	
	}
	
	
	
	private Set<String> knightMoves(ArrayList<Piece> pieces){
		
		Set<String> set = new HashSet<String>();
		
		for(Piece p: pieces){
			
			if(!(p instanceof Knight) || p.getColor() == getColor()){
				continue;
			}
			Knight knight;
			knight = (Knight) p;
			set.addAll(knight.possibleMoves(pieces));
			
		}
		return set;
	}
	
	
	
	private Set<String> bishopMoves(ArrayList<Piece> pieces){
		Set<String> set = new HashSet<String>();
		
		for(Piece p: pieces){
			
			if(!(p instanceof Bishop) || p.getColor() == getColor()){
				continue;
			}
			Bishop bishop;
			bishop = (Bishop) p;
			set.addAll(bishop.possibleMoves(pieces));
			
		}
		return set;
		
	}
	
	
	
	private Set<String> queenMoves(ArrayList<Piece> pieces){
	Set<String> set = new HashSet<String>();
		
		for(Piece p: pieces){
			
			if(!(p instanceof Queen) || p.getColor() == getColor()){
				continue;
			}
			Queen queen;
			queen = (Queen) p;
			set.addAll(queen.possibleMoves(pieces));		
		}
		return set;
		
	}
	
	
	private Set<String> kingMoves(ArrayList<Piece> pieces){

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
		
		for(Piece p : pieces){
		for(String cor: wholeBoard){
			if(!(p instanceof King && p.getColor() != getColor())){
				continue;
			}
			King king;
			king = (King) p;
			
			
			if(!king.moveLogic(cor,false)){
				
				possibleMoves.add(cor);		
			}
			}
		}
		
		Set<String> toBeRemoved = new TreeSet<>();
		
		for(Piece p: pieces){		
			for(String cor :possibleMoves){
				
				if(p.getPosition().equals(cor)&& p.getColor()!=getColor()){
					toBeRemoved.add(cor);
				}	
			}
		}
		possibleMoves.removeAll(toBeRemoved);
	
		return possibleMoves;
		
	}

	
}
