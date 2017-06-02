package chessBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Bishop extends Piece {
	
	public Bishop(String position, Color color) {
		super(position, color);
	}

	@Override
	public boolean move(ArrayList<Piece> pieces, String newPos) {

		if (firstCheck(newPos)){
			return false;					// from here we know that coordinates are A1-H8
		}	
		
		if(checkMoveLogic(newPos,true)){			// can only make bishop moves from here
			return false;
		}
		
		if(moveOverPieces(pieces,newPos,true)){
			return false;
		}
		
		if(checkIfOccupied(pieces,newPos)){		// returns false if trying to move on own piece and removes piece if land on another color's piece
			return false;
		}
		
		
		this.setPosition(newPos);
		
		return true;
		
		
	}


	private boolean checkMoveLogic(String newPos, boolean realMove) {
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
				
			if(Onumber != Nnumber && Oletter != Nletter){
				if(((Onumber+Oletter)==(Nnumber+Nletter))){
			return false;
				}
				for(int i =0; i<10; i++){
					
					if((Onumber+Oletter)<(Nnumber+Nletter)){
						Onumber++;
						Oletter++;
						if((Onumber==Nnumber)&&(Oletter==Nletter)){							
							return false;
						}	
					}
					if((Onumber+Oletter)>(Nnumber+Nletter)){
						Nnumber++;
						Nletter++;
						if((Onumber==Nnumber)&&(Oletter==Nletter)){							
							return false;
					}	
				}
				
		}
	}
			if(realMove){
			System.out.println("Invalid move for a bishop");
			}
			return true;
}

	
	
	private boolean moveOverPieces(ArrayList<Piece> pieces, String newPos, boolean realMove) {
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
		ArrayList<String> list = new ArrayList<>();
		int WOnumber = Onumber;
		int WOletter = Oletter;
		
				if(Nnumber>Onumber && Nletter>Oletter){		
					
					for(int i=0; i<Nnumber-Onumber-1;i++){
						
						WOnumber ++;
						WOletter ++;
						String SOnumber = convertToLetter(WOletter);
						String SOletter = String.valueOf(WOnumber);
						String coordinate = SOnumber + SOletter;
						list.add(coordinate);
						
					}	
				}

				if(Nnumber>Onumber && Nletter<Oletter){	
					
					for(int i=0; i<Nnumber-Onumber-1;i++){
						
						WOnumber ++;
						WOletter --;
						String SOnumber = convertToLetter(WOletter);
						String SOletter = String.valueOf(WOnumber);
						String coordinate = SOnumber + SOletter;
						list.add(coordinate);
						
					}
				}
		
				if(Nnumber<Onumber && Nletter>Oletter){		
					for(int i=0; i<Onumber-Nnumber-1;i++){
						
						WOnumber --;
						WOletter ++;
						String SOnumber = convertToLetter(WOletter);
						String SOletter = String.valueOf(WOnumber);
						String coordinate = SOnumber + SOletter;
						list.add(coordinate);
						
					}	
				}				
				
				if(Nnumber<Onumber && Nletter<Oletter){		
					
					for(int i=0; i<Onumber-Nnumber-1;i++){
						
						WOnumber --;
						WOletter --;
						String SOnumber = convertToLetter(WOletter);
						String SOletter = String.valueOf(WOnumber);
						String coordinate = SOnumber + SOletter;
						list.add(coordinate);
						
					}	
				}
	
			for(Piece p: pieces){
				
				String Position= p.getPosition();
				
				for(int i=0; i<list.size();i++){
					
					if(Position.equals(list.get(i))){
						if(realMove){
						System.out.println("You can't move over pieces");
						}
						return true;
						
					}			
				}		
			}
				
				
		return false;
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
		
		
		for(String cor : wholeBoard){
			
			if(!(checkMoveLogic(cor,false)) && !(moveOverPieces(pieces,cor,false))){
				
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