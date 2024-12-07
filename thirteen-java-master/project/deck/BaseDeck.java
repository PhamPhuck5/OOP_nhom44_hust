package deck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import card.Card;

public class BaseDeck {
	private static BaseDeck instance;
	ArrayList<Card> cards = new ArrayList<Card>();
	public BaseDeck(){
		if(instance != null){
			cards.clear();
			for(int i=0;i<instance.cardCount();i++){
				cards.add(instance.getCard(i));
			}
		}
	}
	public BaseDeck getInstance() {
        if (instance == null) {
            synchronized (ThirteenDeck.class) { // Đảm bảo thread-safety
                if (instance == null) {
                    instance = new BaseDeck();
                }
            }
        }
        return instance;
	}

	public void shuffle(){
	    Random rand = new Random();
	    Card temp;
	    for (int i = cards.size() - 1; i > 0; i--) {
	        int j = rand.nextInt(i + 1);
	        // Hoán đổi cards[i] với cards[j]
	        temp = cards.get(i);
	        cards.set(i, cards.get(j));
	        cards.set(j, temp);
	    }
	}

	public Card getCard(int i){
		return (Card) cards.get(i);	
	}
	public Card dealCard(){//*note
		Card c;
		if(cards.size() == 0) return null;
		c = (Card)cards.get(cards.size()-1);
		cards.remove(c);
//		c = (Card) cards.remove(0);
		
		
				
		return c;
	}
	public int cardCount(){
		return cards.size();
	}
	public static void main(String args[]){
		ThirteenDeck d = new ThirteenDeck();
		System.out.println(d);
		d.shuffle();
		System.out.println("Shuffled\n"+d);
		
		for(int i=0;i<10;i++){
			System.out.println("Deal card:"+d.dealCard()+" Card Count:"+d.cardCount());
		}
	}
}
