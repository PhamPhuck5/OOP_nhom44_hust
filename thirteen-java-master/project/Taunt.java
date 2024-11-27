
public class Taunt {
	public static final Taunt NONE = new Taunt(0,"NONE"); 
	protected int tauntType;
	public static final int NEUTRAL = 0, ANGRY = 1, HAPPY = 2, 
		SAD = 3, EXCITED = 4, CONFIDENT = 5, TYPECOUNT=6;
	protected String tauntText;
	public Taunt(int type, String taunt){
		tauntType = type;
		tauntText = taunt; 
	}
	public String toString(){
		return tauntText;
	}
	public static int typeCount(){
		return TYPECOUNT;
	}
	public String getTauntText() {
		return tauntText;
	}
	public int getTauntType() {
		return tauntType;
	}
	public void setTauntText(String string) {
		tauntText = string;
	}
	public void setTauntType(int i) {
		tauntType = i;
	}
}
