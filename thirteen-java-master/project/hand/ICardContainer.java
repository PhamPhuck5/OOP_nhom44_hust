package hand;
import card.Card;

public interface ICardContainer {
	public int cardCount();
	public boolean hasCard(Card c);
	public Card getCard(int i);
}

