import java.util.*;
import java.util.Stack;
public class Thirteen extends CardGame implements IPlayerAction{
	public static final int SPADE = 0, CLUB = 1, DIAMOND = 2, HEART = 3;
	boolean[] passed;
	HandHistory history;
	Hand controllingHand;
	Player controllingPlayer;
	ICardGameListener listener;
	boolean gameInProgress = false;
	Stack<String> messages;
	public Thirteen(){
		maxPlayers = 4;
		controllingHand = new VietnameseThirteenHand();
		listener = new ThirteenAdapter();//*note
		messages = new Stack<String>();
		history = new HandHistory();
	}
	public Stack<String> getMessages(){
		return messages;
	}
	public void addMessage(String s){
		messages.add(s);
	}
	public String getLastMessage(){
		if(messageCount()==0) return "no messages";
		return (String) messages.peek();
	}
	public int messageCount(){
		return messages.size();
	}
	public ArrayList<VietnameseThirteenHand> legalMoves(){
		ArrayList<VietnameseThirteenHand> hands = ((VietnameseThirteenHand)currentPlayer.getHand()).getPossibleHands();
		VietnameseThirteenHand h;
		//hands.add(new VietnameseThirteenHand());		
		for(int i=hands.size()-1;i>=0;i--){
			h = (VietnameseThirteenHand)hands.get(i);
			if(!canPlay(currentPlayer,h)){
				//System.out.println("REMOVING Hand:"+h.getHandType(h) +" Control:"+h.getHandType(controllingHand));
				hands.remove(h);
			}
		}
		return hands;
	}
	public void play(){
		listener.newGameStarted();
		if(playersCount() < 2) {
			System.out.println("Not enough players playing.");
			return;
		}
		controllingPlayer = getPlayer(0);
		controllingHand.clearHand();
		deck = new Deck();
		deck.shuffle();
		
		for(int i=0;i<players.size();i++)
			getPlayer(i).getHand().clearHand();
		
		// deal
		for(int i=0;i<13;i++)
			for(int j=0;j<players.size();j++){
				currentPlayer = getPlayer(j);
				currentPlayer.getHand().addCard(deck.dealCard());
			}
		listener.playerHandsDealt();
				
		//System.out.println("Player 1:"+getPlayer(0).getHand() + "Player2:"+getPlayer(1).getHand());
		
		passed = new boolean[players.size()];
		for(int i=0;i<players.size();i++){
			passed[i]=false;
		}
		ArrayList<Player> winners = new ArrayList<Player>();
		
		
		gameInProgress = true;
		while(!terminalTest(this)){
			listener.newRound();
			for(int i=0;i<players.size();i++){
				if(terminalTest(this)) break;
				currentPlayer = getPlayer(i);
				
				if(currentPlayer.getHand().isEmpty() && getControllingPlayer()==currentPlayer){
					setControllingPlayer(getPlayer((i+1)%players.size()));
					setPassed(currentPlayer);
					continue;
				}
				else if(currentPlayer.getHand().isEmpty()){
					setPassed(currentPlayer);
					continue;
				}else if(hasPassed(currentPlayer)){
					System.out.println(currentPlayer.getName() + " passed.");
					continue;}
				
				if(allOthersPassed()){
					listener.newRound();
					controllingHand.clearHand();
					//System.out.println("Setting all not passed.");
					setAllNotPassed();	
				}

				
				
				if(hasPassed(currentPlayer)){
					if(winners.indexOf(currentPlayer)==-1) winners.add(currentPlayer);
					continue;
				}
				
				//System.out.println(currentPlayer.getName()+"'s Turn");
				// if the player is out of cards he should stop
				
				//System.out.println("ControllingHand:"+controllingHand);
				listener.gettingMove();
				
				
				Hand move = getPlayer(i).getMove(this);
				
				//echo(currentPlayer.getClass().getName());
				
				
				if(!move.isEmpty()){ // player beats controlling hand
					setControllingPlayer(currentPlayer);
					playHand(currentPlayer,move);
					listener.handPlayed(move);
					history.addHand(move); // add hand to history list
					Taunt t = currentPlayer.getTaunt();
					if(t != Taunt.NONE){
						String taunt = currentPlayer.getName() + ": " + t;
						echo("Taunt:"+taunt);
						addMessage(taunt);
						listener.newMessage(currentPlayer,taunt);
					}
				}else{
					setPassed(currentPlayer); // player passes
					listener.playerPassed();
					addMessage(currentPlayer.getName() + ": I'll pass.");
					listener.newMessage(currentPlayer,"I'll pass.");
				}
				if(currentPlayer.getHand().isEmpty()){
					if(winners.indexOf(currentPlayer)==-1){
						winners.add(currentPlayer);
						listener.playerWon(currentPlayer);
					}
					//setPassed(currentPlayer);
				}
				listener.nextPlayer();
			}
		}
		gameInProgress=false;
		System.out.println("***Winners");
		for(int i=0;i<winners.size();i++)
			System.out.println(" ("+i+") "+((Player)winners.get(i)).getName());
		// get moves until 3 peopel without cards
		for(int i=0;i<playersCount();i++){
			System.out.println(getPlayer(i).getName()+" cards:"+getPlayer(i).getHand().cardCount());
		}
	}
	public boolean hasPassed(Player p){
		return passed[players.indexOf(p)];
	}
	public void setPassed(Player p){
		passed[players.indexOf(p)] = true;
	}
	public void setNotPassed(Player p){
		passed[players.indexOf(p)] = false;
	}
	public void setAllNotPassed(){
		for(int i=0;i<passed.length;i++) passed[i] = false;
	}
	public int numberPassed(){
		int sum = 0;
		for(int i=0;i<playersCount();i++){
			if(passed[i]) sum++;
		}
		return sum;
	}
	/**
	 * Returns true if all players except 1 has passed
	 * @return
	 */
	public boolean allOthersPassed(){
		return numberPassed()==players.size()-1; 
	}
	public void playHand(Player p, Hand h){
		if(canPlay(p,h)) setControllingHand(h);	
	}
	public Hand getControllingHand(){
		return controllingHand;
	}
	public void setControllingHand(Hand h){
		controllingHand.clearHand();
		controllingHand.addCards(h);
	}
	public boolean terminalTest(Thirteen t){
		int sum = 0;
		for(int i=0;i<players.size();i++){
			if(getPlayer(i).getHand().isEmpty()) sum++;
		}
		//echo("Players done: "+sum);
		return sum >=1 && gameInProgress;
		//return sum == players.size()-1;
	}
	public void addPlayer(Player p){
		if(controllingPlayer == null) setControllingPlayer(p);
		players.add(p);
		p.setHand(new VietnameseThirteenHand());
	}
	public static void main(String[] args) {
		Thirteen foo = new Thirteen();
		//foo.addPlayer(new QueryPlayer(foo,"Jamie"));
		foo.addPlayer(new RandomPlayer(foo,"Jamie"));
		foo.addPlayer(new AIPlayer(foo,"Lisa"));
		foo.addPlayer(new RandomPlayer(foo,"Yang"));
		foo.addPlayer(new RandomPlayer(foo,"Peter"));
		//foo.addPlayer(new QueryPlayer(foo,"Lisa"));
		//foo.addPlayer(new QueryPlayer(foo,"Yang"));
		//foo.addPlayer(new QueryPlayer(foo,"Peter"));
		foo.play();
	}
	public int playersCount(){
		return players.size();
	}
	public boolean canPlay(Player p, Hand h){
		/*System.out.println(h+":"+h.getHandType(h)+" score:"+h.evaluateHand()+" "
			+controllingHand+":"+h.getHandType(controllingHand)+" score:"+
			controllingHand.evaluateHand());
		*/	
		return (h.isEmpty() && p!=getControllingPlayer()) ||
			(p==getControllingPlayer() && getControllingHand().isEmpty() && !h.isEmpty()) ||
			(h.getHandType(h) == h.getHandType(controllingHand) 
			&& h.evaluateHand() > getControllingHand().evaluateHand());
	}
	public void setControllingPlayer(Player p){
		controllingPlayer = p;
	}
	public Player getControllingPlayer(){
		return controllingPlayer;
	}
	public int getPlayerIndex(Player p){
		return players.indexOf(p);
	}
	/**
	 * @return
	 */
	public boolean isGameInProgress() {
		return gameInProgress;
	}

	/**
	 * @return
	 */
	public ICardGameListener getListener() {
		return listener;
	}


	/**
	 * @param listener
	 */
	public void setListener(ICardGameListener listener) {
		this.listener = listener;
	}

	/**
	 * @return
	 */
	public HandHistory getHistory() {
		return history;
	}

	/**
	 * @param history
	 */
	public void setHistory(HandHistory history) {
		this.history = history;
	}

	/**
	 * @return
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param player
	 */
	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}

}
