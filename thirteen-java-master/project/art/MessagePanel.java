package art;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MessagePanel extends JPanel {
    private static final long serialVersionUID = 1L;//*note
	int displayCount = 3;
	Stack<String> messages;
	ArrayList<JLabel> labels;
	Color[] availableColors;

	public MessagePanel(Stack<String> m) {
		super();
		labels = new ArrayList<>();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		messages = m;
		JLabel main = new JLabel("Messages:");
		add(main);
		setBackground(Color.GREEN);
		JLabel l;
		for(int i=0;i<displayCount;i++){
			l = new JLabel();
			labels.add(l);
			add(l);
			l.setVisible(false);;
			l.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		}
		availableColors = new Color[3];
		availableColors[0] = Color.BLUE;
		availableColors[1] = Color.DARK_GRAY;
		availableColors[2] = Color.WHITE;
		setLabelColors();
	}
	public static void main(String[] args) {
	}
	public void update(){
		JLabel j;
		for(int i=0;i<labels.size();i++){
			j = (JLabel) labels.get(i);
			int index = messages.size()-labels.size()+i;
			if(messages.size() > index && 0 <= index){
				j.setText((String) messages.get(index));
				j.setForeground(availableColors[i%availableColors.length]);
				j.setVisible(true);
			}
			else
				j.setVisible(false);
		}
	}
	protected void setLabelColors(){
		JLabel label;
		for(int i=0;i<labels.size();i++){
			label = (JLabel) labels.get(i);
			label.setBackground(availableColors[i%availableColors.length]);
		}
	}
	public void setDisplayCount(int count){
		int oldCount = displayCount;
		if(count < 3) displayCount = 3;
		else displayCount = count;
		if(oldCount > displayCount){
			for(int i=0;i<oldCount;i++){
				if(i < displayCount) continue;
				else{
					this.remove((JLabel) labels.get(i));
					labels.remove(i);
				}
			}
		}else if(oldCount < displayCount){
			JLabel l;
			for(int i=oldCount;i<displayCount;i++){
				l = new JLabel();
				labels.add(l);
				add(l);
				l.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			}
		}
		setLabelColors();
	}
}
