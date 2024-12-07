
import hand.Hand;

public abstract class Player extends MyObject {
	protected String name;
	protected CardGame game;
	Hand hand;
	public Player() {
		this(null,"no name");
	}
	public Player(CardGame game, String name){
		this.name = name;
		this.game = game;
	}
	public String getName(){
		return name;
	}
	public Hand getHand(){
		return hand;
	}
	public void setHand(Hand hand){
		this.hand =  hand;
	}
	public abstract Hand getMove(IPlayerAction g);
	public CardGame getGame(){
		return game;
	}
	public Taunt getTaunt(){
		return Taunt.NONE;
	}
}
