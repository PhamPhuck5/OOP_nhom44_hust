import java.util.*;

/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 14, 2004
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
public class Deck extends MyObject implements ICardContainer{
	static Deck originalDeck = new Deck();//*note
	ArrayList<Card> cards = new ArrayList<Card>();
	public Deck(){
		String suit, value;
		Card c;
//		debugOut(Card.SUITS.values().toString());
		if(originalDeck == null){//add 52 la bai
			Card.init();
			for(Iterator<String> i=Card.SUITS.keySet().iterator();i.hasNext();){
				suit = (String) i.next();
				debugOut(Card.VALUES.values().toString());
				for(Iterator<String> j=Card.VALUES.values().iterator();j.hasNext();){
					value = (String) j.next();
//					debugOut(value);
					c = new Card(suit,value);
					//echo("Adding card:"+c);
					cards.add(c);
				}
			}
		}
		else{
			cards.clear();
			for(int i=0;i<originalDeck.cardCount();i++){
				cards.add(originalDeck.getCard(i));
			}
		}
	}
	public String toString(){
		String d = "("+cardCount()+")";
		for(int i = 0;i<cards.size();i++){
			d += "(" + i + ") " + getCard(i);
//			if(i%5 == 0 && i!=0)
				d += "\n";
		}
		return d;
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
	public boolean hasCard(Card c){
		return cards.contains(c);
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
		
		
		//echo("Before deal:"+getCard(0));
				
		return c;
	}
	public int cardCount(){
		return cards.size();
	}
	public static void main(String args[]){
		Deck d = new Deck();
		System.out.println(d);
		d.shuffle();
		System.out.println("Shuffled\n"+d);
		
		for(int i=0;i<10;i++){
			System.out.println("Deal card:"+d.dealCard()+" Card Count:"+d.cardCount());
		}
	}
	public void sort(){
		ArrayList<Hand> v=new ArrayList<Hand>();
		for(int i=0;i<cards.size();i++){
			v.add(new VietnameseThirteenHand(cards.get(i)));
		}
		quickSort(v);
		cards.clear();
		for(int i=0;i<v.size();i++){
			cards.add((v.get(i)).getCard(0));
		}
	}
}
