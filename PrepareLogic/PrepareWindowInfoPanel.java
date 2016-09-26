package PrepareLogic;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PrepareWindowInfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel info;

	public PrepareWindowInfoPanel() {
		super();
		setSize(250, 25);
		setPreferredSize(new Dimension(250, 25));
		addComponents();
		setVisible(true);
	}
	
	
		
	private void addComponents() {
		
		info = new JLabel();
		info.setText("");
		info.setLocation(5, 5);
		info.setSize(230,40);
		info.setVisible(true);
		setBackground(Color.CYAN);
		add(info);
		
	}

	public void setTextDirectly(CellForPlaySetField cell) {
		info.setText(cell.whatGotClicked);
		
	}


}
