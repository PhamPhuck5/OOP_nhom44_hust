package game;
import hand.Hand;
import player.Player;

public interface ICardGameListener {
	public void newGameStarted();
	public void playerHandsDealt();
	public void newRound();
	public void nextPlayer();
	public void handPlayed(Hand h);
	public void playerPassed();
	public void gameEnded();
	public void gettingMove();
	public void playerWon(Player p);
	public void newMessage(Player p, String s);
}
