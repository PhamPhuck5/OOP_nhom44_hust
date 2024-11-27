import java.util.Hashtable;

public class BaseCard extends MyObject {
	String suit, value;
	static boolean initialized = false;
	static final String SPADE="spade",CLUB="club",DIAMOND="diamond",HEART="heart";
	static final String THREE="3", FOUR="4", FIVE="5", SIX="6",
		SEVEN="7", EIGHT="8", NINE="9", TEN="10", JACK="J", QUEEN="Q",
		KING="K", ACE="A",TWO="2";
	static Hashtable<String,Integer> SUITS = new Hashtable<String,Integer>();
	{}
	static Hashtable<String,String> VALUES = new Hashtable<String,String>();
	{
		
	}

//	public BaseCard() {
//		this(" "," ");
//		
//	}
	public BaseCard(String suit, String value){
		init();
		System.out.print("\n"+suit);
		System.out.print(SUITS);
		this.suit = SUITS.containsKey(suit)?suit:SPADE;
		this.value = VALUES.contains(value)?value:THREE;
		System.out.print("\n"+this.suit);
	}
	public static void init(){
		if (initialized) return;
		initialized = true;
		
		SUITS.put(Card.SPADE,INTEGERS.get(0));
		SUITS.put(Card.CLUB,INTEGERS.get(1));
		SUITS.put(Card.DIAMOND,INTEGERS.get(2));
		SUITS.put(Card.HEART,INTEGERS.get(3));
		
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