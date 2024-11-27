import java.util.*;
public class VietnameseThirteenHand extends PokerHand {
	public static String STRAIGHT3 = "3-card straight", STRAIGHT4 ="4-card straight",
		STRAIGHT5="5-card straight",STRAIGHT6="6-card straight",
		STRAIGHT7="7-card straight",STRAIGHT8="8-card straight",
		STRAIGHT9="9-card straight",STRAIGHT10="10-card straight",
		STRAIGHT11="11-card straight",STRAIGHT12="12-card straight",
		STRAIGHT13="13-card straight",CUT="cut",SUPERCUT="supercut";	

		private ArrayList<ArrayList<Card>> buckets = new ArrayList<>();
		private boolean bucketsRight = false;

	public VietnameseThirteenHand(){
		super();
		init();
		createBucket();
		debug = false;
	}
	public VietnameseThirteenHand(Card c){
		super(c);
		init();
		createBucket();
	}
	public VietnameseThirteenHand(ArrayList<Card> v){
		super(v);
		init();
		createBucket();
	}
//	public static Hashtable<String,Integer> VALUEVALUES = new Hashtable<String,Integer>();
	public static void init(){
	
//		VALUEVALUES.put(Card.THREE,INTEGERS.get(0));
//		VALUEVALUES.put(Card.FOUR,INTEGERS.get(1));
//		VALUEVALUES.put(Card.FIVE,INTEGERS.get(2));
//		VALUEVALUES.put(Card.SIX,INTEGERS.get(3));
//		VALUEVALUES.put(Card.SEVEN,INTEGERS.get(4));
//		VALUEVALUES.put(Card.EIGHT,INTEGERS.get(5));
//		VALUEVALUES.put(Card.NINE,INTEGERS.get(6));
//		VALUEVALUES.put(Card.TEN,INTEGERS.get(7));
//		VALUEVALUES.put(Card.JACK,INTEGERS.get(8));
//		VALUEVALUES.put(Card.QUEEN,INTEGERS.get(9));
//		VALUEVALUES.put(Card.KING,INTEGERS.get(10));
//		VALUEVALUES.put(Card.ACE,INTEGERS.get(11));
//		VALUEVALUES.put(Card.TWO,INTEGERS.get(12));

		handTypes.put(SINGLE,SINGLE); handTypes.put(PAIR,PAIR);
		handTypes.put(THREEOFAKIND,THREEOFAKIND);
		handTypes.put(FOUROFAKIND,FOUROFAKIND);

		// straights
		handTypes.put(STRAIGHT3,STRAIGHT3); handTypes.put(STRAIGHT4,STRAIGHT4);
		handTypes.put(STRAIGHT5,STRAIGHT5); handTypes.put(STRAIGHT6,STRAIGHT6);
		handTypes.put(STRAIGHT7,STRAIGHT7); handTypes.put(STRAIGHT8,STRAIGHT8);
		handTypes.put(STRAIGHT9,STRAIGHT9); handTypes.put(STRAIGHT10,STRAIGHT10);
		handTypes.put(STRAIGHT11,STRAIGHT11); handTypes.put(STRAIGHT12,STRAIGHT12);
		handTypes.put(STRAIGHT13,STRAIGHT13); 		 
		
		// cuts
		handTypes.put(CUT,CUT); handTypes.put(SUPERCUT,SUPERCUT);	
		
		
	}
	private void createBucket() {
		int size = Card.VALUEVALUES.size();
		for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<Card>());
        }
	}
	public String getHandType(Hand h){
		String type="INVALID";
		if(isSingle(h)) type = SINGLE;
		else if(isPair(h)) type = PAIR;
		else if(isThreeOfAKind(h)) type = THREEOFAKIND;
		else if(isFourOfAKind(h)) type = FOUROFAKIND;
		else if(isCut(h)) type = CUT;
		else if(isSuperCut(h)) type = SUPERCUT;
		else if( PokerHand.isStraight(h)){
			switch(h.cardCount()){
				 case 3: type = STRAIGHT3; break;
				 case 4: type = STRAIGHT4; break;
				 case 5: type = STRAIGHT5; break;
				 case 6: type = STRAIGHT6; break;
				 case 7: type = STRAIGHT7; break;
				 case 8: type = STRAIGHT8; break;
				 case 9: type = STRAIGHT9; break;
				 case 10: type = STRAIGHT10; break;
				 case 11: type = STRAIGHT11; break;
				 case 12: type = STRAIGHT12; break;
				 case 13: type = STRAIGHT13; break;				 
			}
		}
		return type;
	}
	public void addCard(Card c){
		super.addCard(c);
		bucketsRight = false;
	}
	public Card removeCard(int i){//ham nay voi ham sau cai thien dc *note
		bucketsRight = false;
		return (Card) cards.remove(i);
	}
	public Card removeCard(Card c){
		bucketsRight = false;
		cards.remove(c);
		return c;
	}
	public void removeCards(Hand h){// trong co ve trung vs cai duoi *note
		for(int i=0;i<h.cardCount();i++)
			cards.remove(h.getCard(i));
		bucketsRight = false;
	}
	public void clearHand(){
		bucketsRight = false;
		cards.clear();
	}//*note 4 ham tren
	public static boolean isCut(Hand h){
		return h.cardCount()==6 && true;
	}//*note
	public static boolean isSuperCut(Hand h){
		return h.cardCount()==8 && true;
	}


	public int evaluateHand(){// return the value of the highest card
		int val;
		if(cardCount() == 0) val = 0;
		else val = getHighestCard(this).evaluateCard();
		return val; 
	}
	public static void main(String args[]){
		Deck d = new Deck();
		echo("Original\n"+d);
		d.shuffle();
		echo("Shuffled\n"+d);
		Hand h = new VietnameseThirteenHand();
		
		Card a;
		for(int i=0;i<13;i++){
			a = d.dealCard();//*note
			//echo("Dealing card:"+a);
			h.addCard(a);
		}
		
		d.sort();
		
		echo("Sorted deck:" + d);
		
		System.out.println("Hand:"+h+(PokerHand.isSingle(h)?"IS SINGLE":"IS NOT SINGLE"));
		h.sort();
		System.out.println("Sorted:" + h);
		Card c = h.getCard(0);
		echo("HighestCard:"+getHighestCard(h)+" Lowest Card:"+getLowestCard(h));
		
		System.out.println("Suit:"+c.getSuit());
		System.out.println("Suit Val:"+c.getSuitValue()+" Val Val:"+c.getValueValue());
		System.out.println((Hand.isGreaterOrEqual(h,h))?"0 is greater than or equal to 1":"1 is greater than 0");
		System.out.println("Highest cArd:"+getHighestCard(h));
		System.out.println("Lowest cArd:"+getLowestCard(h));
		for(int i=0;i<h.cardCount();i++)
		System.out.println(h.getCard(i) + " eval:" + h.getCard(i).evaluateCard());
			
		// test evaluation functions
		
		//Hand s1 = new VietnameseThirteenHand();
		//s1.addCard(new Card(Card.SPADE,Card.EIGHT));
		//s1.addCard(new Card(Card.CLUB,Card.EIGHT));
		//s1.addCard(new Card(Card.HEART,Card.EIGHT));
		//s1.addCard(new Card(Card.DIAMOND,Card.TEN));
		
		//System.out.println("Hand test:" + s1 + " Type:" + s1.getHandType(s1));
		//System.out.println("is straight?" + (isStraight(s1)?"Yes":"No"));
		echo("Possible fours:" + ((VietnameseThirteenHand)h).getPossibleFours());
		echo("Possible pairs:" + ((VietnameseThirteenHand)h).getPossiblePairsAlt());
		echo("Possible cuts:" + ((VietnameseThirteenHand)h).getPossibleCuts());
		echo("\n\nAll possible hands:\n");
		ArrayList<VietnameseThirteenHand> v = ((VietnameseThirteenHand)h).getPossibleHands();
		for(int i=0;i<v.size();i++)
			echo(""+v.get(i));
		//echo("Possible straights:" + ((VietnameseThirteenHand)h).getPossibleStraightsAlt());
	}

	/**
	 * Returns true if hand a can beat hand b.
	 */
	public boolean canBeatHand(Hand a, Hand b){
		if(!sameHandType(a,b)) return false; // make sure they are of same type
		if(isLess(a,b)) return false; // make sure that b > a
		return true;
	}
	public boolean sameHandType(Hand a, Hand b){
		return getHandType(a) == getHandType(b);
	}
	public void sort(){
		ArrayList<Hand> v = new ArrayList<Hand>();
		for(int i =0;i<cardCount();i++){
			Hand h = new VietnameseThirteenHand();
			v.add(h);
			h.addCard(getCard(i));
		}
		cards.clear();
		quickSort(v);
		for(int i=0;i<v.size();i++)
			addCard(((Hand)v.get(i)).getCard(0));
	}
//	public static boolean isStraight(Hand h){
//		boolean ret = true;
//		ArrayList<VietnameseThirteenHand> v = new ArrayList<VietnameseThirteenHand>();
//		VietnameseThirteenHand tmp;
//		for(int i=0;i<h.cardCount();i++){
//			tmp = new VietnameseThirteenHand();
//			v.add(tmp);
//			tmp.addCard(h.getCard(i));
//		}
//		quickSort(v);
//		Hand tmp2 = new VietnameseThirteenHand();
//		for(int i =0;i<v.size();i++){
//			tmp2.addCard(((Hand)v.get(i)).getCard(0));
//		}
//		//echo("tmp2 hand:"+tmp2);
//		if(tmp2.cardCount() == 0) return false;
//		int val = tmp2.getCard(0).getValueValue();
//		for(int i =1;i<tmp2.cardCount();i++){
//			val++;
//			ret = ret && tmp2.getCard(i).getValueValue()==(val);
//		}
//		return ret;
//	}
	/**
	 * compare the most value cảd
	 */
	public int compareTo(Hand o){
		if(evaluateHand() == ((VietnameseThirteenHand)o).evaluateHand()) return 0;
		else if(evaluateHand() > ((VietnameseThirteenHand)o).evaluateHand()) return 1;
		else return -1;
	}
	public ArrayList<VietnameseThirteenHand> getPossibleHands(){
		// create single-card hands
		ArrayList<VietnameseThirteenHand> hands = new ArrayList<VietnameseThirteenHand>();
		hands.add(new VietnameseThirteenHand());
		hands.addAll(getPossibleSingles());
		hands.addAll(getPossibleFours());//*note
		hands.addAll(getPossiblePairsAlt());
		hands.addAll(getPossibleStraightsAlt());
		hands.addAll(getPossibleCuts());
		// create pairs
		
		return hands;
	}

	public ArrayList<VietnameseThirteenHand> getPossibleStraightsAlt(){
		ArrayList<VietnameseThirteenHand> ret= new ArrayList<VietnameseThirteenHand>();
		int size = Card.VALUEVALUES.size();//13
		Map<Integer, List<Card>> buckets = new HashMap<>();
		for (int i = 0; i < size; i++) {
            buckets.put(i, new ArrayList<>());
        }
        Card tempCard;
        for(int i=0;i<cardCount();i++){
        	tempCard = getCard(i);
			buckets.get(tempCard.getValueValue()).add(tempCard);
		}
        Hand h;
		ArrayList<VietnameseThirteenHand> tmp, hands = new ArrayList<VietnameseThirteenHand>();
		hands.add(new VietnameseThirteenHand());
		
		
		int last = -1;
		for(int i=0;i<size-1;i++){ // avoid 2
			if( buckets.get(i).size() > 0 && (i == last + 1 || last == -1)){
				tmp = new ArrayList<VietnameseThirteenHand>();
				for(int k=1;k<buckets.get(i).size();k++){ // for each number in the bucket beyond the first
					for(int m=0;m<hands.size();m++){ // clone each hand
						h =  hands.get(m);
						tmp.add((VietnameseThirteenHand)h);
					}
				}
				hands.addAll(tmp);
				int x;
				for(int j=0;j<hands.size();j++){
					h = (VietnameseThirteenHand) hands.get(j);
					x=j%buckets.get(i).size();
					h.addCard((Card)buckets.get(i).get(x));
				}
				h = (VietnameseThirteenHand) hands.get(0);
				if(h.cardCount() >= 3){
					for(int j=0;j<hands.size();j++){
						h = (VietnameseThirteenHand) hands.get(j);
						if(h.cardCount()>=3)
							ret.add((VietnameseThirteenHand)h.clone());
					}
				}	
			}
			else{
				hands.clear();
				hands.add(new VietnameseThirteenHand());
			}
			last = i;
		}
		return ret;		
	}
		

	public ArrayList<VietnameseThirteenHand> getPossibleFours(){
		ArrayList<Hand> hands=new ArrayList<Hand>();
		ArrayList<VietnameseThirteenHand> ret= new ArrayList<VietnameseThirteenHand>();
		String lastValue;
		Card c;
		if(cardCount()==0) return ret;
		Hand h;
		hands.addAll(getPossibleSingles());
		quickSort(hands);
		//hands = handsToCards(hands);
		h = hands.get(0);
		lastValue=h.getCard(0).getValue();
		int count = 1;
		for(int i =1;i<hands.size();i++){
			c = (Card) ( hands.get(i)).getCard(0);
			if(c.getValue() == lastValue){
				count++;
				h = new VietnameseThirteenHand();
				for(int j=0;j<count;j++){
					h.addCard((Card) ( hands.get(i-j)).getCard(0));	
				}
				if(h.cardCount() > 2)//*note
					ret.add((VietnameseThirteenHand)h);
			}
			else{
				count = 1;
			}
			lastValue = c.getValue();
		}
		return ret;
	}
	private ArrayList<ArrayList<Card>> getBuckets(){
		if(buckets.size()==0) createBucket();
		if (bucketsRight) return buckets;
		Card temp;
		for(int i=0;i<cardCount();i++){
			temp = getCard(i);
			buckets.get(temp.getValueValue()).add(temp);
		}
		return buckets;
	}
	

	public ArrayList<VietnameseThirteenHand> getPossiblePairsAlt(){
		ArrayList<VietnameseThirteenHand> ret = new ArrayList<VietnameseThirteenHand>();
		VietnameseThirteenHand h= new VietnameseThirteenHand();
		getBuckets();
		for(int i=0;i<buckets.size();i++){
			if(buckets.get(i).size() < 2) continue;
			for(int j=0;j<buckets.get(i).size();j++){
				for(int k=j+1;k<buckets.get(i).size();k++){
					h= new VietnameseThirteenHand();
					h.addCard((Card)buckets.get(i).get(j));
					h.addCard( (Card)buckets.get(i).get(k) );
					ret.add((VietnameseThirteenHand)h);
				}
				
			}
		}
		return ret;
	}
	public ArrayList<VietnameseThirteenHand> getPossibleSingles(){
		ArrayList<VietnameseThirteenHand> hands = new ArrayList<VietnameseThirteenHand>();
		for(int i =0;i<cardCount();i++){
			hands.add(new VietnameseThirteenHand(getCard(i))); 
		}		
		return hands;
	}
	public ArrayList<VietnameseThirteenHand> getPossibleCuts(){
		ArrayList<VietnameseThirteenHand> ret = new ArrayList<VietnameseThirteenHand>(), pairs = getPossiblePairsAlt();
		int last = 0;
		Card c;
		Hand h ,tmp=new VietnameseThirteenHand();
		last = -1;
		for(int i=0;i<pairs.size();i++){ // will never include 2 pair in a cut, b/c of NOTE
			h = (Hand)pairs.get(i);
			c = h.getCard(0);
			if(last + 1 == c.getValueValue() || last == -1){//thiếu trường hợp
				for(int j=0;j<h.cardCount();j++){
					tmp.addCard(h.getCard(j));
				}
			}else{
				getPossibleCutsHelper(tmp,ret);
				tmp = new VietnameseThirteenHand();
			}
			last = c.getValueValue();
		}
		return ret;
	}
	/**
	 * from a list of VNThand (sorted, continues value) hash it to cut
	 */
	public void getPossibleCutsHelper(Hand tmp,ArrayList<VietnameseThirteenHand> ret){
		Hand tempHand;
		for(int j=0;j<tmp.cardCount();j+=2){
			tempHand = new VietnameseThirteenHand();
			for(int k=j;k<tmp.cardCount();k+=2){
				tempHand.addCard(tmp.getCard(k));
				tempHand.addCard(tmp.getCard(k+1));
				if(tempHand.cardCount() == 6 || tempHand.cardCount() == 8)
					ret.add((VietnameseThirteenHand)tempHand.clone());
			}
		}
	}

	public Object clone(){
		Hand h = new VietnameseThirteenHand();
		for(int i=0;i<cardCount();i++){
			h.addCard(getCard(i));
		}
		return h;
	}
}
