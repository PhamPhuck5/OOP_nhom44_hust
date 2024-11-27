import java.awt.LayoutManager;
import javax.swing.*;
import java.awt.*;
//import java.io.File;

public class CardView extends JPanel {
	private static final long serialVersionUID = 1L;//*note
	static boolean imagesLoaded = false;
	static final int DEFAULTWIDTH = 100, DEFAULTHEIGHT=150,
		DEFAULTBORDER=2, DEFAULTARCWIDTH = 15,DEFAULTARCHEIGHT=25;
	static int cardwidth=DEFAULTWIDTH, cardheight=DEFAULTHEIGHT, 
		border=DEFAULTBORDER, arcwidth=DEFAULTARCWIDTH, archeight=DEFAULTARCHEIGHT;
	protected Card card;
	public static Image[] suitImages = new Image[4]; 
	
	public CardView(Card c) {
		super();
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(DEFAULTWIDTH,DEFAULTHEIGHT));
		this.setBackground(Color.WHITE);
		card = c;
		cardwidth = DEFAULTWIDTH;
		cardheight = DEFAULTHEIGHT;
		border = DEFAULTBORDER;
		arcwidth = DEFAULTARCWIDTH;
		archeight = DEFAULTARCHEIGHT;
		System.out.println("Cardwidht:"+cardwidth+" Cardheight:"+cardheight);
		
		
	}


	public CardView(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	public CardView(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	public CardView(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public static void paintCard(int x, int y, Graphics g,Card c){
		paintCard(x,y,g,c,Color.WHITE);	
	}
	public static void paintCard(int x, int y, Graphics g,Card c,Color cardColor){
		paintCard(x,y,g,c,Color.WHITE,true);	
	}
	public static void paintCard(int x, int y, Graphics g,Card c,Color cardColor, boolean viewable){
		
		if(!imagesLoaded){
			suitImages[Thirteen.SPADE]= Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"images/spade.gif");
			suitImages[Thirteen.CLUB]= Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"images/club.gif");
			suitImages[Thirteen.DIAMOND]= Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"images/diamond.gif");
			suitImages[Thirteen.HEART]= Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"images/heart.gif");
//			System.out.println("Current directory: " + new File(".").getAbsolutePath());		
			imagesLoaded = true;
		}
		g.setColor(Color.BLACK);
		g.drawRect(0,0,100,100);//
		g.drawRoundRect(x,y,cardwidth,cardheight,15,25);//
		g.fillRoundRect(x,y,cardwidth,cardheight,15,25);
		
		g.setColor(cardColor);
		g.fillRoundRect(x+border,y+border,cardwidth-2*border,
			cardheight-2*border,15,25);
		
		if(!viewable) return;
		
		g.setColor(Color.BLACK);//
//		g.drawRect(x + 20, y + 30, 60, 90);	//
			
		if(c.getSuit() == Card.SPADE || c.getSuit() == Card.CLUB)
			g.setColor(Color.BLACK);
		else g.setColor(Color.RED);		
		g.drawString(c.getValue(),x+8,y+22);
		int suitValue = 0;
		if(c.getSuit().equals("club")) suitValue = 1;
		else if(c.getSuit().equals("diamond")) suitValue = 2;
		else if(c.getSuit().equals("heart")) suitValue = 3;
//		System.out.print("\n"+suitValue);
		g.drawImage(suitImages[suitValue],x+8,y+25,cardColor,null);
//		g.drawString(c.getSuit(),x+8,y+35);
		System.out.println("****************");//
	}
	public void paintCard(Graphics g, Card c){
		paintCard(0,0,g,card);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintCard(g,card);
	}

	public static void main(String[] args) {
		JFrame frmMain = new JFrame("CardTest");
		frmMain.getContentPane().add(new CardView(new Card("diamond","8")));
		frmMain.pack();
		frmMain.setVisible(true);
	}
	public static void scale(double percentage){
		if(percentage <= 0) return;
		border = (int) Math.ceil(percentage * DEFAULTBORDER);
		cardwidth = (int) Math.ceil(percentage * DEFAULTWIDTH);
		cardheight = (int) Math.ceil(percentage * DEFAULTHEIGHT);
		arcwidth = (int) Math.ceil(percentage * DEFAULTARCWIDTH);
		archeight = (int) Math.ceil(percentage * DEFAULTARCHEIGHT);
	}
	public static int getCardHeight() {
		return cardheight;
	}
	public static int getCardWidth() {
		return cardwidth;
	}
	public Card getCard() {
		return card;
	}
	public static void setCardHeight(int i) {
		cardheight = i;
	}
	public static void setCardWidth(int i) {
		cardwidth = i;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public static int getCardBorder() {
		return border;
	}
	public static void setCardBorder(int i) {
		border = i;
	}
}
