import hand.Hand;

class ThirteenAppListener implements ICardGameListener{
	ThirteenApplication appp;
	public ThirteenAppListener(ThirteenApplication t){
		app = t;
	}
	public void handPlayed(Hand h){	
		app.repaint();
		app.human.updateAvailableMoves();
		
			
		//((ControllingHandPanel) app.controllingHand).setControllingPlayer(app.getGame().getControllingPlayer());
		((ControllingHandPanel) app.controllingHand).update();
		((ControllingHandPanel) app.controllingHand).setControllingPlayer(app.getGame().getControllingPlayer());
		((HistoryFrame)app.historyFrame).addHand(h);
		pause();
		
	}
	
	public void newGameStarted() {}
	public void playerHandsDealt() {}
	public void nextPlayer() {}
	public void playerWon(Player p) {}


	public synchronized void pause(){
		try{wait(1000);}
		catch(InterruptedException e){
		}
	}
	public void playerPassed(){//*note
		app.human.updateAvailableMoves();
	}
	public void newRound(){
		app.human.updateAvailableMoves();
//		update hint
	}
	public void gettingMove(){
		app.human.updateAvailableMoves();
		if(app.getGame().getCurrentPlayer() == app.human.getPlayer())
			app.hintFrame.updateHint(app.human.getPlayer().getHand());
	}
	public void newMessage(Player p, String t){
		app.getMessagePanel().update();
	}
	public void gameEnded() {	}
}
class UpdateGui implements Runnable{
	ThirteenApplication app;
	
	public UpdateGui(ThirteenApplication t){
		app = t;
	}
	public synchronized void run(){
		//System.out.println("Updater running.");
		while(true){
			if(app.getGame().terminalTest(app.getGame()))
				break;
			app.repaint();
			try{
				wait(500);
				//System.out.println("Gui repainted");
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
		System.out.println("UPDATER SHUT DOWN!");
	}
}
