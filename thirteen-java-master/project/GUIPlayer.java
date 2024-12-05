//import java.util.*;

import hand.Hand;

public class GUIPlayer extends Player {
	ThirteenApplication app;
	Hand selectedHand = null;

	public GUIPlayer(ThirteenGame g, String n) {
		super(g, n);

	}
	public void setApplicationLink(ThirteenApplication a){
		app = a;
	}

	public Hand getMove(IPlayerAction g) {
		hand.sort();
//		ArrayList<VietnameseThirteenHand> moves = g.legalMoves();//*note
		waitForSelectedHand();
		Hand move = getSelectedHand();
		hand.removeCards(move);
		setSelectedHand(null);
		return move;
	}

	public synchronized void waitForSelectedHand(){
		while(!selectedHandIsSet()) 
			try{
				wait(200);
				//System.out.println("Waiting for move.");
			}
			catch(InterruptedException e){	}
	}
	public static void main(String[] args) {
	}
	/**
	 * @return
	 */
	public Hand getSelectedHand() {
		return selectedHand;
	}

	/**
	 * @param hand
	 */
	public void setSelectedHand(Hand hand) {
		selectedHand = hand;
	}
	public boolean selectedHandIsSet(){
		return selectedHand != null;
	}
}
