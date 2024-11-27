import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class HumanPlayerView extends PlayerView implements ActionListener{
    private static final long serialVersionUID = 1L;//*note

	protected JButton btnSubmit;
	protected JComboBox<VietnameseThirteenHand> cmbxMoves;//*note
//	protected ComboBoxModel<> cmbxMovesModel; //*note dcm xoa bien di van chay bth
	boolean ready = false;
	HandView handView;
	//protected Hand selectedHand;

	public HumanPlayerView(Player p){
		this(p,false);
	}
	public HumanPlayerView(Player p, boolean vertical){
		super();
		player = p;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//if(vertical) this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//else this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		upperPanel.add(new Avatar(p,0));
		upperPanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		handView = new HandView(p.getHand());
		handView.setSelectable(true);
		handView.setOpenHand(true);
		
		upperPanel.add(handView);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		upperPanel.add(btnSubmit);
		
		upperPanel.setBackground(Color.GREEN);
		
		cmbxMoves = new JComboBox<VietnameseThirteenHand>();
		lowerPanel.add(cmbxMoves);
		lowerPanel.setBackground(Color.GREEN);
//		cmbxMoves.addItem("Test0");
//		cmbxMoves.addItem("Test1");
//		cmbxMoves.addItem("Test2");
		cmbxMoves.addActionListener(this);
		
		add(upperPanel);
		add(lowerPanel);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	public synchronized void updateAvailableMoves(){
		ready = false;
		cmbxMoves.removeAllItems();
		//System.out.println("Current:"+player.getGame().currentPlayer.getName());
		if(((Thirteen)player.getGame()).currentPlayer == player){
			ArrayList<VietnameseThirteenHand> v = ((Thirteen)player.getGame()).legalMoves();//*note
			//System.out.println("available moves:"+v);
			/*
			if(v.size()==1)
			{
				setSelectedHand((Hand)v.get(0));
				return;
			} */
			for(int i=0;i<v.size();i++){
				cmbxMoves.addItem(v.get(i));
				// find event to display tool tip, possibly set tool tip diff when mouse move
				cmbxMoves.setToolTipText(v.get(i).toString()); 
			}	
					
			cmbxMoves.setVisible(true);
		}else{
			//cmbxMoves.addItem("Waiting for your turn");
			cmbxMoves.setVisible(false);
		}
		ready = true;
	}	
	public synchronized boolean isReady(){
		return ready;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==cmbxMoves && isReady()){
			JComboBox cb = (JComboBox) e.getSource();
			setSelectedHand((Hand)cb.getSelectedItem());
			cb.setVisible(false);
			handView.clearSelected();
		}
		else if(e.getSource() == btnSubmit){
			System.out.println("SUBMIT");
			ArrayList<VietnameseThirteenHand> v = ((Thirteen)player.getGame()).legalMoves();//*note
			boolean isLegal = false;
			for(int i=0;i<v.size();i++){
				isLegal = isLegal || ((Hand)v.get(i)).equals(handView.getSelected());
				System.out.println(v.get(i) + ":" + handView.getSelected());
			}
			if(isLegal){
				setSelectedHand((Hand) handView.getSelected().clone());
				handView.clearSelected();
			}
		}
	}
	public static void main(String[] args) {
	}

	/**
	 * @param hand
	 */
	public void setSelectedHand(Hand hand) {
		//selectedHand = hand;
		//System.out.println("Set selected hand.");
		((GUIPlayer)player).setSelectedHand(hand);
	}

}