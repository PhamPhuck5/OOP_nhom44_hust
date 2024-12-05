import java.util.*;

import hand.Hand;

import java.io.*;
public class ComputerPlayer extends Player {
	protected ArrayList<ArrayList<Taunt>> tauntList = new ArrayList<ArrayList<Taunt>>();
	

	public ComputerPlayer(ThirteenGame g, String n) {
		super(g, n);
		loadTaunts();
	}

	public Hand getMove(IPlayerAction g) {
		return null;
	}

	public void loadTaunts(){
		for(int i=0;i<Taunt.TYPECOUNT;i++){
			tauntList.add(new ArrayList<Taunt>());
		}
		try (BufferedReader in = new BufferedReader(
				
	            new InputStreamReader(new FileInputStream(GlobalVariabal.projectAddrest+"taunts/" + this.getClass().getName() + ".taunts")))) 
			{
	        String line;
	        while ((line = in.readLine()) != null && !line.isEmpty()) {
	            try {
	                String[] info = line.split(";");
	                int type = Integer.parseInt(info[0]);
	                String message = info[1];

	                // Kiểm tra loại taunt có hợp lệ không
	                if (type < 0 || type >= Taunt.TYPECOUNT) {
	                    System.err.println("Invalid taunt type: " + type);
	                    continue;
	                }

	                // Thêm taunt vào danh sách
	                Taunt t = new Taunt(type, message);
	                tauntList.get(type).add(t);

	            } catch (Exception e) {
	                System.err.println("Error parsing line: " + line + " - " + e.getMessage());
	            }
	        }
	    } catch (FileNotFoundException e) {
	        System.err.println("Taunt file not found: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error reading taunt file: " + e.getMessage());
	    }
	}
	/**
	 * get an random taunt by type 
	 * @param type
	 * @return
	 */
	public Taunt getTaunt(int type){
		ArrayList<Taunt> taunts = tauntList.get(type);
		int index2 = (int) Math.floor(Math.random()*taunts.size());
		
		System.out.println("Getting taunt: type="+type+" number="+index2+" text="+taunts.get(index2));
		return (Taunt) taunts.get(index2); 
	}
	/**
	 * get random taunt by number of cards remain(for ai)
	 */
	public Taunt getTaunt(){
	
		int cards = getHand().cardCount();
		System.out.println("My taunts:"+tauntList+ " cards:"+cards);
		if(cards == 1){
			int choice = (int) Math.random()*3;
			switch(choice){
				case 0: return getTaunt(Taunt.CONFIDENT);
				case 1: return getTaunt(Taunt.HAPPY);
				case 2: return getTaunt(Taunt.EXCITED);
			}
		}
		else if(cards == 6 || cards == 7){
			return getTaunt(Taunt.NEUTRAL);
		}
		else if(cards < 6){
			return getTaunt(Math.random()<.5?Taunt.HAPPY:Taunt.EXCITED);
		}
		
		return getTaunt(Math.random()<.5?Taunt.SAD:Taunt.ANGRY);
		
	}
	public ArrayList<ArrayList<Taunt>> getAllTaunts(){
		return tauntList;
	}
	
	public static void main(String[] args) {
		ComputerPlayer p = new ComputerPlayer(null,"Jay");
		System.out.println(p.getAllTaunts());
	}
}