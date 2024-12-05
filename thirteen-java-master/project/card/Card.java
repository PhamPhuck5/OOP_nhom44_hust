package card;
import java.util.Hashtable;

public class Card extends BaseCard {
//thirteen card
	
	public static Hashtable<String,Integer> VALUEVALUES = new Hashtable<String,Integer>();
//	public Card() {
//		super();
//	}
	public Card(String suit, String value){
		super(suit,value);
	}
	public static void init(){
		BaseCard.init();
		VALUEVALUES.put(Card.THREE,Integer.valueOf(0));
		VALUEVALUES.put(Card.FOUR,Integer.valueOf(1));
		VALUEVALUES.put(Card.FIVE,Integer.valueOf(2));
		VALUEVALUES.put(Card.SIX,Integer.valueOf(3));
		VALUEVALUES.put(Card.SEVEN,Integer.valueOf(4));
		VALUEVALUES.put(Card.EIGHT,Integer.valueOf(5));
		VALUEVALUES.put(Card.NINE,Integer.valueOf(6));
		VALUEVALUES.put(Card.TEN,Integer.valueOf(7));
		VALUEVALUES.put(Card.JACK,Integer.valueOf(8));
		VALUEVALUES.put(Card.QUEEN,Integer.valueOf(9));
		VALUEVALUES.put(Card.KING,Integer.valueOf(10));
		VALUEVALUES.put(Card.ACE,Integer.valueOf(11));
		VALUEVALUES.put(Card.TWO,Integer.valueOf(12));
	}
	public int getValueValue(){
		return VALUEVALUES.get(value);
	}
}
