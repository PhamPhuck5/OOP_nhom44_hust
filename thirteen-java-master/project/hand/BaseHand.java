package hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import card.BaseCard;
import card.Card;

public abstract class BaseHand<T extends BaseCard> implements ICardContainer <T>, Cloneable {

	protected ArrayList<T> cards = new ArrayList<T>();
	public BaseHand(){
		super();
	}
	public BaseHand (ArrayList<T> v){
		this();
		for(int i =0;i<v.size();i++){
			cards.add(v.get(i));
		}
	}
	public BaseHand (T c){
		this();
		cards.add(c);
	}
	
	public T getCard(int i){
		if(i>=cards.size()) {
			System.out.print("\n"+"i out of range of cards in hand"+"\n");
		}
		return (T) cards.get(i);
	}
	public ArrayList<T> getCards() {
		return cards;
	}
	public void addCard(T c){
		cards.add(c);
	}
	public boolean isEmpty(){
		return (cardCount()==0);
	}
	public int cardCount(){
		return cards.size();
	}
	public void clearHand(){
		cards.clear();
	}
	
	public abstract int evaluateHand();
	public static boolean isGreater(Hand a, Hand b){
		return a.evaluateHand() > b.evaluateHand();
	}
	public static boolean isEqual(Hand a, Hand b){
		return a.evaluateHand() == b.evaluateHand();
	}
	public static boolean isLess(Hand a, Hand b){
		return a.evaluateHand() < b.evaluateHand();
	}
	public static boolean isGreaterOrEqual(Hand a, Hand b){
		return isGreater(a,b) || isEqual(a,b);
	}
	public static boolean isLessOrEqual(Hand a, Hand b){
		return isLess(a,b)|| isEqual(a,b);
	}

	public abstract Object clone();
}
