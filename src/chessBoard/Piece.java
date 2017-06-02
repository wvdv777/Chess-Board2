package chessBoard;

import java.util.ArrayList;
import java.util.Iterator;


public abstract class Piece {
	
	public Piece(String position, Color color){
		
	this.position = position;	
	this.color = color;
		
	}

	private String position;
	private Color color;
	
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract boolean move(ArrayList<Piece> pieces,  String newPos);	// return true if move was valid
	

	
	

	boolean firstCheck(String newPos){					// makes sure that the new coordinate is from a1 to h8
		
		if(getPosition().equals(newPos)){
			System.out.println("You can't say on the same spot");
			return true;
		}
		
		if(newPos.length() != 2){
			System.out.println("plz type in a valid coordinate to move to (not more than 2 characters)");
			return true;
		}
		if((newPos.substring(1, 2).equals("0") )||(newPos.substring(1, 2).equals("9"))){
			System.out.println("X0 & X9 are not valid coordinates");
			return true;
		}
		String test = newPos.substring(0, 1);
		if(test.equals("A")||test.equals("B")||test.equals("C")||test.equals("D")||test.equals("E")||test.equals("F")||test.equals("G")||test.equals("H")){
			return false;		
		}
		return true;	
	}
	
	
	
	
	
	boolean checkIfOccupied (ArrayList<Piece> pieces, String newPos){		// returns false if there's a piece of the same color and if there's piece of other color removes it
		
					
		for(Piece p: pieces){
			
			if(p.getPosition().equals(newPos)){
				
				if(this.getColor().equals(p.getColor())){	
					System.out.println("You can't move on top of your own pieces");
					return true;
				}
			}
		}

		Iterator<Piece> it = pieces.iterator();
		
		while(it.hasNext()){
			
			Piece check = it.next();

		    if (this.getColor() != check.getColor()  && newPos.equals(check.getPosition())) {
		          it.remove();
		    }
		}	
	return false;	
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
	
	
	protected String convertToLetter(int letterNR){
		
		String letter =  "";
		
		switch(letterNR){
		
		case 1: letter = "A";
		break;
		case 2: letter = "B";
		break;
		case 3: letter = "C";
		break;
		case 4: letter = "D";
		break;
		case 5: letter = "E";
		break;
		case 6: letter = "F";
		break;
		case 7: letter = "G";
		break;
		case 8: letter = "H";
		break;	
		
		}
		
		return letter;
		
		
		
	}
	
	
	
}