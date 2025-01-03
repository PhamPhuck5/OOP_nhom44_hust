package deck;
import java.util.*;
import card.Card;



public class ThirteenDeck{
	ArrayList<Card> cards = new ArrayList<Card>();
	public ThirteenDeck(){		
		String suit, value;
		Card c;
		Card.init();
		for(Iterator<String> i=Card.SUITS.keySet().iterator();i.hasNext();){
			suit = (String) i.next();
			for(Iterator<String> j=Card.VALUES.values().iterator();j.hasNext();){
				value = (String) j.next();
				c = new Card(suit,value);
				cards.add(c);
			}
		}
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
