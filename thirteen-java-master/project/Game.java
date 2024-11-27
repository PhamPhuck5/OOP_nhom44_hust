/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 15, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author DaAznAngel
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.util.*;
public abstract class Game extends MyObject {
	ArrayList<Player> players = new ArrayList<Player>();
	int maxPlayers = 0; //=0 mean there 0 limit
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
//	public void setPlayers(ArrayList<Player> p){
//		players = p;
//	}
}
