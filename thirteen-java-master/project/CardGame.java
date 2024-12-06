
import java.util.*;
import deck.ThirteenDeck;
import hand.Hand;
public abstract class CardGame extends MyObject {
	ArrayList<Hand> hands;
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected int maxPlayers = 0; //=0 mean there 0 limit
	protected int numPlayers = 0; //=0 mean there 0 limit
	Player currentPlayer;
	public int playersCount(){
		return players.size();
	}
	public void addPlayer(Player p){
		players.add(p);
	}
	public Player getPlayer(int i){
		return (Player) players.get(i);
	}
	public void removePlayer(int i){
		players.remove(i);
	}
	public void removePlayer(Player p){
		players.remove(p);
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
}
