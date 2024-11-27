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
		VALUEVALUES.put(Card.THREE,INTEGERS.get(0));
		VALUEVALUES.put(Card.FOUR,INTEGERS.get(1));
		VALUEVALUES.put(Card.FIVE,INTEGERS.get(2));
		VALUEVALUES.put(Card.SIX,INTEGERS.get(3));
		VALUEVALUES.put(Card.SEVEN,INTEGERS.get(4));
		VALUEVALUES.put(Card.EIGHT,INTEGERS.get(5));
		VALUEVALUES.put(Card.NINE,INTEGERS.get(6));
		VALUEVALUES.put(Card.TEN,INTEGERS.get(7));
		VALUEVALUES.put(Card.JACK,INTEGERS.get(8));
		VALUEVALUES.put(Card.QUEEN,INTEGERS.get(9));
		VALUEVALUES.put(Card.KING,INTEGERS.get(10));
		VALUEVALUES.put(Card.ACE,INTEGERS.get(11));
		VALUEVALUES.put(Card.TWO,INTEGERS.get(12));
	}
	public int getValueValue(){
		return VALUEVALUES.get(value);
	}
}
