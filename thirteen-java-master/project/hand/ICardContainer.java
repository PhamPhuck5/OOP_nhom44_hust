package hand;
import card.BaseCard;

public interface ICardContainer <T extends BaseCard> {
	public int cardCount();
	public boolean hasCard(T c);
	public T getCard(int i);
}

