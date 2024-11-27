import javax.swing.*;
import java.awt.*;
//import java.util.*;
public class HistoryFrame extends JInternalFrame {
    private static final long serialVersionUID = 1L;//*note

	ImageIcon[] suits = new ImageIcon[4];
	JPanel contentPane;
	protected Color[] colors = new Color[2];
	protected HandHistory history;

	protected Thirteen game;
	protected JLabel label;
	/**
	 * 
	 */
	public HistoryFrame(Thirteen game) {
		super();
		colors[0] = Color.WHITE;
		colors[1] = Color.YELLOW;
		history = new HandHistory();
		contentPane = new JPanel(); 
		JScrollPane scroller = new JScrollPane(contentPane);
		getContentPane().add(scroller);
		label = new JLabel("History");
		contentPane.add(label);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		contentPane.setBackground(Color.ORANGE);
		this.game = game;
		this.setLocation(550,0);
		this.setSize(200,400);
		this.show();
		// load image icons for the suits
		try{
			suits[Thirteen.SPADE] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/spade.gif"));
			suits[Thirteen.CLUB] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/club.gif"));
			suits[Thirteen.DIAMOND] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/diamond.gif"));
			suits[Thirteen.HEART] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/heart.gif"));  
		} catch(Exception e){
			System.out.println("Could not load suit images.");
			System.exit(1);
		}
		// end load suit icons
		
		setTitle("Hand History");
	}
	
	public JLabel getCardLabel(Card c){
		JLabel n = new JLabel(c.getValue(), getSuitIcon(c.getSuitValue()), SwingConstants.LEFT);
		return n;
	}

	public JPanel getHandPanel(Hand h){
		JPanel outer = new JPanel();
		outer.setLayout(new BorderLayout());
		
		JPanel ret = new JPanel();		
		ret.setLayout(new GridLayout(0,5));
		
		
		for(int i=0;i<h.cardCount();i++) ret.add(getCardLabel(h.getCard(i)));
		
		// margins
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.NORTH);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.SOUTH);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.EAST);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.WEST);
		// end margins
		
		outer.add(ret,BorderLayout.CENTER);
		
		int c = history.handCount()%2;
		ret.setBackground(colors[c]);
		outer.setBackground(colors[c]);
		outer.setBorder(BorderFactory.createLineBorder(Color.black));
		outer.setToolTipText(h.getHandType(h));
		return outer;
	}
	
	public void addHand(Hand h){
		contentPane.add(getHandPanel(h));
		history.addHand(h);
		
		//this.paintImmediately(0,0,200,600);
		//try{wait(1000);}catch(InterruptedException e){}
	}

	public static void main(String[] args) {
		Deck d = new Deck();
//		Vector hands = new Vector();
		Hand h;
		HistoryFrame history = new HistoryFrame(null);
		int cardCount = 0, max=7;
		JDesktopPane desktop = new JDesktopPane();
		JFrame frm = new JFrame("HandHistory Test");
		desktop.add(history);
		frm.getContentPane().add(desktop);
		frm.pack();
		frm.setVisible(true);
		while(d.cardCount() > 0){
			h= new VietnameseThirteenHand();
			cardCount = (int) Math.floor(max * Math.random()) + 1;
			for(int i=0;i<cardCount;i++){
				if(d.cardCount() > 0)
					h.addCard(d.dealCard()); 
			}
			history.addHand(h);
			System.out.println("Hand: "+h);
		}
		
		
	}

	public ImageIcon getSuitIcon(int i){
		return suits[i];
	}
}
