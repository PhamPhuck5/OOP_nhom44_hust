
import java.awt.*;
import javax.swing.*;

import deck.ThirteenDeck;
import hand.VietnameseThirteenHand;

import java.util.*;

public class ThirteenApplication extends JFrame implements IPlayerAction{
    private static final long serialVersionUID = 1L;//*note


	ThirteenGame game; JInternalFrame historyFrame, gameFrame;
	HumanPlayerView human;
	HintFrame hintFrame;
	JPanel controllingHand, messagePanel;
	ArrayList<PlayerView> opponentViews;
	int numberOfPlayers = 4;
	/**
	 * @throws java.awt.HeadlessException
	 */
	
	public ThirteenApplication(ThirteenGame g) throws HeadlessException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		opponentViews = new ArrayList<PlayerView>();
		this.setJMenuBar(new ThirteenApplicationMenuBar(this));
		int index = g.getPlayerIndex(g.getControllingPlayer());
		game = g;
		JDesktopPane desktop = new JDesktopPane();
		gameFrame = new JInternalFrame();

		JPanel p = (JPanel)gameFrame.getContentPane();

		//p.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		p.setLayout(new BorderLayout());
		
		JPanel middlepanels = new JPanel();
		middlepanels.setLayout(new BoxLayout(middlepanels,BoxLayout.Y_AXIS));
		middlepanels.setBackground(Color.GREEN);
		
		//p.add(hintFrame);
		
		opponentViews.add(new PlayerView(g.getPlayer((index+2)%g.playersCount())));
		middlepanels.add((JComponent)opponentViews.get(0));
		
		//p.add(new HandView(g.getPlayer((index+2)%g.playersCount()).getHand()),BorderLayout.NORTH);
		opponentViews.add(new PlayerView(
			g.getPlayer((index+3)%g.playersCount()),
			new HandView(g.getPlayer((index+3)%g.playersCount()).getHand(),270),
			true));
		p.add((JComponent) opponentViews.get(1),BorderLayout.EAST);
		human = new HumanPlayerView(g.getControllingPlayer());
		messagePanel = new MessagePanel(g.getMessages());
		middlepanels.add(messagePanel);
		
		controllingHand = new ControllingHandPanel(new HandView(g.getControllingHand()),g.getControllingPlayer());
		middlepanels.add(controllingHand);
		middlepanels.add(human);
		//p.add(new HandView(g.getControllingPlayer().getHand()),BorderLayout.SOUTH);
		p.add(middlepanels,BorderLayout.CENTER);
		
		opponentViews.add(new PlayerView(g.getPlayer((index+1)%g.playersCount()),
			new HandView(g.getPlayer((index+1)%g.playersCount()).getHand(),90),
			true));
		p.add((JComponent)opponentViews.get(2),BorderLayout.WEST);
		
		this.setBackground(Color.GREEN);
		this.setTitle("Thirteen");
		gameFrame.setLocation(0,0);
		gameFrame.setSize(780,600);
		gameFrame.setVisible(true);
		
		hintFrame = new HintFrame(g);
		hintFrame.setLocation(gameFrame.getWidth()+20,0);	
		
		historyFrame = new HistoryFrame(g);
		historyFrame.setLocation(gameFrame.getWidth()+20,hintFrame.getHeight()+20);
		
		desktop.add(hintFrame);
		desktop.add(gameFrame);
		desktop.add(historyFrame);
		setContentPane(desktop);
		desktop.setDragMode (JDesktopPane.OUTLINE_DRAG_MODE); //1.3 code
		desktop.setSize(500,500);

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset,inset,screenSize.width - inset*2, screenSize.height - inset*2);

		this.pack();
		//this.setSize(500,500);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		g.setListener(new ThirteenAppListener(this));
			
		//Thread updater = new Thread(new UpdateGui(this));
		//updater.start();
	}
	public ArrayList<VietnameseThirteenHand> legalMoves(){
		return getGame().legalMoves();
	}
	public ThirteenGame getGame(){
		return game;
	}
	public static void main(String[] args) {
		ThirteenGame foo = new ThirteenGame();
		
		// players
		Player[] players = new Player[4];
		players[0]=new GUIPlayer(foo,"Jamie");
		players[1]=new AIPlayer(foo,"Lisa");
		players[2]=new RandomPlayer(foo,"Yang");
		players[3]=new RandomPlayer(foo,"Peter");
		
		for(int i=0;i<players.length;i++)
			foo.addPlayer(players[i]);
		
		ThirteenDeck d = new ThirteenDeck();
		d.shuffle();
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++)
				foo.getPlayer(i).getHand().addCard(d.dealCard());
		}
		for(int i=0;i<13;i++){
			foo.getControllingHand().addCard(foo.getPlayer(0).getHand().getCard(i));
		}
		for(int i =0;i<4;i++){
			System.out.println("Player "+i+":"+foo.getPlayer(i).getHand());
			players[i].getHand().sort();
		}
		CardView.scale(.5);
		ThirteenApplication app = new ThirteenApplication(foo);
		((GUIPlayer)foo.getPlayer(0)).setApplicationLink(app);
		app.pack();
		app.setVisible(true);
		foo.play();
		System.exit(1);
		//for(int i=0;i<4;i++)
		//	foo.getPlayer(i).getHand().removeCard(0);
	}
	public MessagePanel getMessagePanel(){
		return (MessagePanel) messagePanel;
	}
	/**
	 * @return
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param i
	 */
	public void setNumberOfPlayers(int i) {
		numberOfPlayers = i;
	}
	public ArrayList<PlayerView> getOpponentViews(){
		return opponentViews;
	}
}
