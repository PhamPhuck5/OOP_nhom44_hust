/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 15, 2004
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
import java.util.*;

import hand.Hand;
import hand.VietnameseThirteenHand;

import java.io.*;
public class QueryPlayer extends Player {

	/**
	 * @param g
	 * @param n
	 */
	public QueryPlayer(ThirteenGame g, String n) {
		super(g, n);
	}

	/* (non-Javadoc)
	 * @see Player#getMove(java.lang.String)
	 */
	public Hand getMove(IPlayerAction g) {
		hand.sort();
		ArrayList<VietnameseThirteenHand> v = g.legalMoves();//*note
		if(v.size()==1){
			System.out.println("You pass.");
			return (Hand)v.get(0);
		}
		// begin menu
		for(int i=0;i<v.size();i++){
			System.out.println("   ("+i+") "+v.get(i));	
		}
		System.out.print(">");
		// end menu
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String selection="0";
		try{
			
			selection = in.readLine();
			int index = Integer.parseInt(selection);
			if(index <0 || index >= v.size()) {
				System.out.println("Invalid move.");
				return getMove(g);
			} 
			Hand h = (Hand) v.get(index);
			hand.removeCards(h);
			return h;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return getMove(g);
		}
		
	}

	public static void main(String[] args) {
	}
}
