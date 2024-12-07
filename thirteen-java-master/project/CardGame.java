
import java.util.*;
public abstract class CardGame extends MyObject {
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected int maxPlayers = 0;
	protected int numPlayers = 0; 
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
