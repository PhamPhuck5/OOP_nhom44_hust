package art;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 31, 2004
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
public class NewGameDialog extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;//*note

	int numberOfPlayers = 4; // by default, 4 is set.
	PlayerChooser[] players; // holds radio buttons
	ButtonGroup group; // holds radios
	JButton submit;
	/**
	 * @throws java.awt.HeadlessException
	 */
	public NewGameDialog() throws HeadlessException {
		super();
		this.setTitle("New Game");
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JLabel("Select the number of players."),BorderLayout.NORTH);
		
		// options panel
		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options,BoxLayout.Y_AXIS));
	
		// options 
		
		players = new PlayerChooser[3];
		players[0] = new PlayerChooser(2);
		players[1] = new PlayerChooser(3);
		players[2] = new PlayerChooser(4);
		for(int i=0;i<3;i++){
			options.add(players[i]);
			players[i].getComboBox().addActionListener(this);
			players[i].getComboBox().setActionCommand(players[i].getLabel().getText());
		}
		//players[0].setSelected(1);
		//players[numberOfPlayers-2].setSelected(true); // set 4 as the default selection
		
		// submit button
		submit = new JButton("New Game");
		submit.addActionListener(this);
		
		mainPanel.add(options,BorderLayout.CENTER);
		mainPanel.add(submit,BorderLayout.SOUTH);
		setContentPane(mainPanel);
		this.setLocation(100,100);
		pack();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().getClass().getName().equals("javax.swing.JButton")){
				this.setVisible(false);
		}
		else{
			numberOfPlayers = Integer.parseInt(e.getActionCommand());
		}
	}

	public static void main(String[] args) {
		JDialog foo = new NewGameDialog();
		foo.setVisible(true);
	}
}
