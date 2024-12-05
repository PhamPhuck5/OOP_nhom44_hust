package card;
import java.util.Hashtable;

public class BaseCard {
	protected String suit, value;
	static protected boolean initialized = false;
	static public final String SPADE="spade",CLUB="club",DIAMOND="diamond",HEART="heart";
	static public final String THREE="3", FOUR="4", FIVE="5", SIX="6",
		SEVEN="7", EIGHT="8", NINE="9", TEN="10", JACK="J", QUEEN="Q",
		KING="K", ACE="A",TWO="2";
	public static Hashtable<String,Integer> SUITS = new Hashtable<String,Integer>();
	public static Hashtable<String,String> VALUES = new Hashtable<String,String>();


	public BaseCard(String suit, String value){
		init();
		this.suit = SUITS.containsKey(suit)?suit:SPADE;
		this.value = VALUES.contains(value)?value:THREE;
	}
	public static void init(){
		if (initialized) return;
		initialized = true;
		
		SUITS.put(Card.SPADE,Integer.valueOf(0));
		SUITS.put(Card.CLUB,Integer.valueOf(1));
		SUITS.put(Card.DIAMOND,Integer.valueOf(2));
		SUITS.put(Card.HEART,Integer.valueOf(3));
		
		VALUES.put(ACE,ACE);
		VALUES.put(TWO,TWO);
		VALUES.put(THREE,THREE);
		VALUES.put(FOUR,FOUR);
		VALUES.put(FIVE,FIVE);
		VALUES.put(SIX,SIX);
		VALUES.put(SEVEN,SEVEN);
		VALUES.put(EIGHT,EIGHT);
		VALUES.put(NINE,NINE);
		VALUES.put(TEN,TEN);
		VALUES.put(JACK,JACK);
		VALUES.put(QUEEN,QUEEN);
		VALUES.put(KING,KING);
	}
	public String toString(){
        return String.format("(%s, %s)", suit, value);
	}

	public String getSuit(){
		return suit;
	}
	public int getSuitValue(){
		return SUITS.get(suit);
	}
	public String getValue(){
		return value;
	}
	/**
	 * get the int value of card
	 * @return
	 */
	public int getValueValue(){
		return -1;
	}
	public static int valueCount(){
		return VALUES.size();
	}
	public static int suitCount(){
		return SUITS.size();
	}
	public int evaluateCard(){
		return getValueValue()*4+getSuitValue();
	}
}