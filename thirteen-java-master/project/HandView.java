import java.awt.event.*;
//import java.awt.geom.*;
import javax.swing.*;

import card.Card;
import deck.ThirteenDeck;
import hand.Hand;
import hand.VietnameseThirteenHand;

import java.awt.*;
//import java.util.*;

public class HandView extends JPanel implements MouseListener{
    private static final long serialVersionUID = 1L;//*note

	protected Hand selected;
	protected boolean selectable = false, openHand = false;
	public static final int DEFAULTSPACING = 20;
	protected Hand hand;
	int rotation=0;
	/**
	 * 
	 */
	public HandView(Hand h) {
		super();
		this.setPreferredSize(new Dimension((h.cardCount()-1)*DEFAULTSPACING+CardView.getCardWidth(),CardView.getCardHeight()));
//		this.setPreferredSize(new Dimension((12)*DEFAULTSPACING+CardView.getCardWidth(),CardView.getCardHeight()));
//		this.setPreferredSize(new Dimension(500,500));
		this.setBackground(Color.GREEN);
		hand = h;
		
		selected = (Hand) h.clone();
		selected.clearHand();//*note
		addMouseListener(this);
	}
	public HandView(Hand h,int rotation) {
		this(h);
		if(rotation % 360 > 0){
			this.rotation = rotation % 360;
//			this.setPreferredSize(new Dimension(500,500));
//			this.setPreferredSize(new Dimension(CardView.getCardHeight(),(h.cardCount()-1)*DEFAULTSPACING+CardView.getCardWidth()));
			this.setPreferredSize(new Dimension(CardView.getCardHeight(),(12)*DEFAULTSPACING+CardView.getCardWidth()));
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.clearRect(0,0,this.getPreferredSize().width,this.getPreferredSize().height);
		if(rotation > 0) paintHandRotated(g,hand,rotation);
		else paintHand(g,hand);
		//System.out.println("Painted hand:"+hand);
		
	}
	public static void rotateHand(Graphics g, Hand hand, int degrees){
//		AffineTransform trans = new AffineTransform();//*note
//		((Graphics2D) g).getTransform();
//		AffineTransformOp op = new AffineTransformOp(trans,AffineTransformOp.Type)
//		trans.rotate(Math.toRadians(degrees));
//		int xoffset = ((hand.cardCount()-1)*DEFAULTSPACING+CardView.getCardWidth())/2, yoffset = CardView.getCardHeight()/2;
		//*note
//		((Graphics2D)g).translate(-xoffset,-yoffset);
		((Graphics2D)g).rotate(Math.toRadians(degrees));
		if(degrees <= 90){
			((Graphics2D)g).translate(0,-500);
			((Graphics2D)g).translate(0,500 - CardView.getCardHeight());
		}else{
			((Graphics2D)g).translate(-500,0);
			((Graphics2D)g).translate(CardView.getCardHeight()+DEFAULTSPACING/2+120,0);
			//((Graphics2D)g).translate(0,500 + CardView.getCardHeight());			
		}
	}
	public void paintHandRotated(Graphics g,Hand h,int degrees){
		paintHandRotated(0,0,g,h,degrees);
	}
	public void paintHandRotated(int x, int y, Graphics g, Hand h, int degrees){
		rotateHand(g, h, degrees);
		paintHand(x,y,g,h);
		//this.setPreferredSize(new Dimension(CardView.getCardHeight(),(h.cardCount()-1)*DEFAULTSPACING+CardView.getCardWidth()));
	}
	public void paintHand(Graphics g, Hand h){
		paintHand(0,0,g,h);
	}
	public void paintHand(int x, int y, Graphics g, Hand h){
		if(h.cardCount() == 0) return;
		
		Color color = Color.RED;
		
		if(isOpenHand())
			color = selected.hasCard(h.getCard(0))?Color.LIGHT_GRAY:Color.WHITE;
		
		CardView.paintCard(0,y,g,h.getCard(0),color,isOpenHand());
		int cardx;
		for(int i=1;i<h.cardCount();i++){
			cardx = DEFAULTSPACING*i;
			color = Color.RED;
			if(isOpenHand())
				color = selected.hasCard( h.getCard(i))?Color.LIGHT_GRAY:Color.WHITE;
			CardView.paintCard(cardx,y,g,h.getCard(i),color,isOpenHand());
			//System.out.println("x:"+cardx+" y:"+y);
		}
	}
	public static void main(String[] args) {
		ThirteenDeck d = new ThirteenDeck();
		d.shuffle();
		Hand h = new VietnameseThirteenHand(),h2=new VietnameseThirteenHand();
		for(int i=0;i<13;i++){
			h.addCard(d.dealCard());
			h2.addCard(d.dealCard());
		}
		JFrame frmMain = new JFrame("CardTest");
		//CardView.scale(.7);
		frmMain.getContentPane().add(new HandView(h));
		frmMain.pack();
		frmMain.setVisible(true);		
		System.out.println("Hand:"+h);
	}
	public Hand getHand(){
		return hand;
	}
	public Hand getSelected(){
		return selected;
	}
	public void addToSelected(Card c){
		selected.addCard(c);
	}

	public void addToSelected(Hand h){
		selected.addCards(h);
	}
	public void clearSelected(){
		selected.clearHand();
	}
	public boolean isSelectable() {
		return selectable;
	}
	public void setSelectable(boolean b) {
		selectable = b;
	}

	public void mouseClicked(MouseEvent e) {
		if(!selectable) return;
		
		int x = e.getX(), y = e.getY();
		int card = x/HandView.DEFAULTSPACING;
		if(card == hand.cardCount()) card = card - 1;
		else if(card >= hand.cardCount() || hand.cardCount()==0) return;
		
		System.out.println("Click! Card:"+card + " ("+x+","+y+")");
		
		Card c = hand.getCard(card);
		if(getSelected().hasCard(c)) selected.removeCard(c);
		else addToSelected(c);
		System.out.println("Selected:"+getSelected());
		repaint();
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
	public boolean isOpenHand() {
		return openHand;
	}
	public void setOpenHand(boolean b) {
		openHand = b;
	}
	public void setSelected(Hand hand) {
		selected = hand;
	}

}