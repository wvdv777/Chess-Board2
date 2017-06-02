package chessBoard;

import java.util.ArrayList;

public class Pawn extends Piece {
	
	boolean FMflag = true;

	public Pawn(String position, Color color) {
		super(position, color);
	}

	@Override
	public boolean move(ArrayList<Piece> pieces, String newPos) {
		
		if (firstCheck(newPos)){
			return false;					// from here we know that coordinates are A1-H8
		}	
		
		if(checkMoveLogic(newPos)){			// from here we now that the move is only forward and only 2 spaces first turn 1 space second turn. Also we know they can't move back
			return false;
		}
		
		if(SecondLogicCheck(pieces, newPos)){			// can only move diagonal when there is a piece of the opposite color// can't move more than two spaces sidewards
			return false;								// can't move over other pieces on first turn// can't capture forward
		}
		
		if(checkIfOccupied(pieces,newPos)){		// returns false if trying to move on own piece and removes piece if land on another color's piece
			return false;
		}
		
		
		this.setPosition(newPos);
		FMflag = false;
		
		return true;
	}


	
	
	
	private boolean checkMoveLogic(String newPos) {
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
		if(getColor() == Color.White){
			
			if(Onumber >= Nnumber){					// if the white piece doesn't move forward or goes backward then return false
				System.out.println("You can't move pawns Backward/Sidewards");
				return true;
			}
			
			if(FMflag){
	
				if((Onumber+2)<Nnumber){
					System.out.println("A pawn can't move this far (FM)");
					return true;
					
				}
				
			}else{
				if((Onumber+1)<Nnumber){
					System.out.println("A pawn can't move this far (NFM)");
					return true;	
				}
			}			
		}
		
		if(getColor() == Color.Black){
			
			if(Onumber <= Nnumber){					// if the white piece doesn't move forward or goes backward then return false
				System.out.println("You can't move pawns backwards/sidewards");
				return true;
				
			}
			
			if(FMflag){
				
				System.out.println(Onumber);
				
				if((Onumber-2)>Nnumber){
					System.out.println("A pawn can't move this far (FM)");
					return true;
			}
				}else{
						
						if((Onumber-1)>Nnumber){
							System.out.println("A pawn can't move this far (NFM)");
							return true;
				}
			}
		}
	return false;
	}

	
	
	private boolean SecondLogicCheck (ArrayList<Piece> pieces, String newPos){
		
		int Nletter = lettersToInteger(newPos.substring(0, 1));
		int Nnumber = Integer.parseInt(newPos.substring(1, 2));
		
		int Oletter = lettersToInteger(getPosition().substring(0,1));
		int Onumber = Integer.parseInt(getPosition().substring(1,2));
		
		
		if(getColor() == Color.White){
			
			if((Nletter != Oletter)&&(Onumber+1<Nnumber)){
				System.out.println("Can't move this far sideward and forward with a pawn");
				return true;	
			}
			
			if((Math.abs(Nletter -Oletter)>1)){
				
				System.out.println("Can't move this far sideward with a pawn");
				return true;
			}

			if(((Onumber +1) == Nnumber) &&(Math.abs(Nletter -Oletter)==1)){
				
				boolean flag = true;
				for(int i = 0; i<pieces.size();i++){
					
						if(newPos.equals(pieces.get(i).getPosition())){					
						flag =false;
						}
				}	
				if(flag){
					System.out.println("Can only make this move on enemy pawn");
					return true;
				}
			}
			
			if(FMflag){   
				
				if(Onumber  == Nnumber -2){
					
					for(Piece p: pieces){
						
						int letter = lettersToInteger(p.getPosition().substring(0, 1));
						int number = Integer.parseInt(p.getPosition().substring(1, 2));
																
						if(((Nnumber -1) == number)&&(letter == Nletter)){
							System.out.println("you can't move over pieces");
							return true;
						}
					}	
				}
			}
			
		
			
		}
		
		if(getColor() == Color.Black){
			
			if((Nletter != Oletter)&&(Onumber-1>Nnumber)){
				System.out.println("Can't move this far sideward and forward with a pawn");
				return true;			
			}
			if((Math.abs(Nletter -Oletter)>1)){
				
				System.out.println("Can't move this far sideward with a pawn");
				return true;
			}
			
			if(((Onumber -1) == Nnumber) &&(Math.abs(Nletter -Oletter)==1)){
				
				boolean flag = true;
				
				for(Piece p:pieces){				
						if(newPos.equals(p.getPosition()) && p.getColor()!= getColor()){
						flag =false;
					}	
				}	
				if(flag){
					System.out.println("Can only make this move on enemy pawn");
					return true;
				}
			}
			
			if(FMflag){   
				
				if(Onumber  == Nnumber +2){
					
					for(Piece p: pieces){
						
						int letter = lettersToInteger(p.getPosition().substring(0, 1));
						int number = Integer.parseInt(p.getPosition().substring(1, 2));
																
						if(((Nnumber +1) == number)&&(letter == Nletter)){
							System.out.println("you can't move over pieces");
							return true;
						}
					}	
				}
			}

		}
		
		for(Piece p: pieces){
			
			int letter = lettersToInteger(p.getPosition().substring(0, 1));
			
			if(letter == Nletter){
			if(newPos.equals(p.getPosition())&&p.getColor()!=getColor()){
				
				System.out.println("Can't capture forward");
				return true;		
				}
			}
		}
		

		return false;
	}
	
	
	
	
	
	
}//c
