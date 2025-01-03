package hand;

import java.util.*;

import card.Card;
public abstract class Hand extends BaseHand<Card> implements  Comparable<Hand> {
	protected static Hashtable<String,String> handTypes = new Hashtable<String,String>();
	protected static boolean initialized = false;
	public Hand(){
		super();
		initialized = true;
	}
	public Hand(ArrayList<Card> v){
		this();
		for(int i =0;i<v.size();i++){
			if(v.get(i).getClass().getName()=="Card")
				cards.add(v.get(i));
		}
	}
	public Hand(Card c){
		this();
		cards.add(c);
	}
	

	public void addCards(Hand h){
		for(int i=0;i<h.cardCount();i++)
			addCard(h.getCard(i));
	}
	public boolean hasCard(Card c){
		return cards.contains(c);
	}
	public int getCardValueInHand(int i){
		return getCard(i).getValueValue();
	}
	public Card removeCard(int i){
		return (Card) cards.remove(i);
	}
	public Card removeCard(Card c){
		cards.remove(c);
		return c;
	}	
	public void removeCards(Hand h){
		for(int i=0;i<h.cardCount();i++) {
			cards.remove(h.getCard(i));
		}
	}	
	
	  public static void quickSort(ArrayList<Hand> hands)
	  { if (! hands.isEmpty())
		{ 
			quickSort(hands, 0, hands.size()-1);
		}
	  }
	  private static void quickSort(ArrayList<Hand> hands, int lowIndex, int highIndex){
	        if (hands == null || hands.size() == 0 || lowIndex >= highIndex) {
	            return;
	        }

	        int lowToHighIndex = lowIndex;
	        int highToLowIndex = highIndex;
	        Hand pivotValue = hands.get((lowToHighIndex + highToLowIndex) / 2);

	        while (lowToHighIndex <= highToLowIndex) {
	            while (hands.get(lowToHighIndex).compareTo(pivotValue) < 0) {
	                lowToHighIndex++;
	            }
	            while (hands.get(highToLowIndex).compareTo(pivotValue) > 0) {
	                highToLowIndex--;
	            }

	            if (lowToHighIndex <= highToLowIndex) {
	                Collections.swap(hands, lowToHighIndex, highToLowIndex);
	                lowToHighIndex++;
	                highToLowIndex--;
	            }
	        }

	        if (lowIndex < highToLowIndex) {
	            quickSort(hands, lowIndex, highToLowIndex);
	        }
	        if (lowToHighIndex < highIndex) {
	            quickSort(hands, lowToHighIndex, highIndex);
	        }
	  }
	

	public String toString(){
		if(cards.size()==0) return "[skip]";
		String d = "[";
		for(int i = 0;i<cards.size();i++){
			d += getCard(i).toString();
			//if(i%5 == 0 && i!=0)
				//d += "\n";
		}
		return d+"]";
	}
	public static Card getLowestCard(Hand h){
		Card lowest = h.getCard(0);
		for(int i=1;i<h.cardCount();i++){
			if(lowest.evaluateCard()>h.getCard(i).evaluateCard()) {
				lowest = h.getCard(i);
			}
		}
		return lowest;
	}
	public static Card getHighestCard(Hand h){
		Card highest = h.getCard(0);
		for(int i=1;i<h.cardCount();i++){
			if(highest.evaluateCard()<h.getCard(i).evaluateCard());
				highest = h.getCard(i);
		}
		return highest;
	}

	public abstract void sort();
	public abstract Object clone();
	public abstract int compareTo(Hand o);


	public boolean equals(Hand h){
		if(cardCount() != h.cardCount()) return false;
		
		for(int i=0;i<cardCount();i++){
			if(!h.hasCard(getCard(i))) return false;
		}
		return true;
	}
	public static boolean allCardsEqualValue(Hand h){
		boolean equal = true;
		String lastValue;
		if(h.cardCount()==0) return true;
		else lastValue = h.getCard(0).getValue();
		for(int i = 1; equal&&i<h.cardCount(); i++){
			equal = (h.getCard(i).getValue()==lastValue);
		}
		return equal;
	}
	public static boolean allCardsEqualSuit(Hand h){
		boolean equal = true;
		String lastSuit;
		if(h.cardCount()==0) return true;
		else lastSuit = h.getCard(0).getSuit();
		for(int i=0;equal&&i<h.cardCount();i++){
			equal = (h.getCard(i).getSuit()==lastSuit);
		}
		return equal;
	}

	public abstract String getHandType(Hand h);
	public static boolean isSingle(Hand h){
		return h.cardCount()==1;
	}
	public static boolean isPair(Hand h){
		return h.cardCount()==2 && allCardsEqualValue(h);
	}
	public static boolean isThreeOfAKind(Hand h){
		return h.cardCount()==3 && allCardsEqualValue(h);
	}
	public static boolean isFourOfAKind(Hand h){
		return h.cardCount()==4 && allCardsEqualValue(h);
	}
	public static boolean isStraight(Hand h){
		//bucket sort
		
		int[] buckets = new int[13];
		int value;
		for(int i=0;i<13;i++) buckets[i] = 0;
		for(int i=0;i<h.cardCount();i++){
			value = h.getCardValueInHand(i);
			if(buckets[value]==0){
				buckets[value] = 1; 
			}
			else return false; // if there is a pair in the hand, it is not a straight
		}
		boolean flag = false;
		for(int i=0;i<13;i++){
			if(buckets[i]==1){
				flag = true;
			}
			else if(buckets[i]==0 && flag){//*note
				return false;
			}
		}
		return true;
	}
}
