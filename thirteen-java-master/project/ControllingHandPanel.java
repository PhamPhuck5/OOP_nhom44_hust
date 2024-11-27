import java.awt.*;

import javax.swing.*;

/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 28, 2004
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
public class ControllingHandPanel extends JPanel {
    private static final long serialVersionUID = 1L;//*note

	protected static String handTypePrefix = "Hand Type - "; 
	protected HandView controllingHand;
	protected Player controllingPlayer;
	protected JLabel lblHandType, lblControllingPlayer;
	
	/**
	 * 
	 */
	public ControllingHandPanel(HandView h, Player p) {
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		lblHandType = new JLabel(handTypePrefix);
		lblControllingPlayer = new JLabel("Control: "+p.getName());
		add(lblControllingPlayer);
		add(h);
		add(lblHandType);
		controllingHand = h;
		controllingHand.setOpenHand(true);
		this.setBackground(Color.GREEN);
	}
	public void setControllingPlayer(Player p){
		controllingPlayer = p;
		lblControllingPlayer.setText("Control: "+p.getName());
	}
	
	/**
	 * @param arg0
	 */
	public ControllingHandPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0z
	 */
	public ControllingHandPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ControllingHandPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	/*
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try{
			lblHandType.setText(handTypePrefix + controllingHand.getHand().getHandType(controllingHand.getHand()));
		}catch(Exception e){
			//
		}
	}*/
	public synchronized void update(){
		lblHandType.setText(handTypePrefix + controllingHand.getHand().getHandType(controllingHand.getHand()));
	}
	public static void main(String[] args) {
	}
}
