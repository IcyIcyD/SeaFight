package PlayLogic;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Domain.ShipCountHandler;


public class ShipsInPlayInfoLabel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel info1;
	private JLabel info2;
	private JLabel info3;
	private String infoString1;
	private String infoString2;
	private String infoString3;
	
	public ShipsInPlayInfoLabel(ShipCountHandler sch) {
		super();
		setSize(400, 100);
		setPreferredSize(new Dimension(400, 100));
		addComponents(sch);
		setVisible(true);
	}
	
	
		
	private void addComponents(ShipCountHandler sch) {
		
		setLayout(null);
		infoString1 = new String("Player now has: ");
		infoString2 = new String(sch.getQuadroShipCount()+" quadro(s), "+sch.getTrippleShipCount()+" tripple(s), ");
		infoString3 = new String(sch.getDoubleShipCount()+" double(s), "+sch.getSoloShipCount()+" solo(es).");
		info1 = new JLabel();
		info1.setText(infoString1);
		info1.setLocation(20, 10);
		info1.setSize(360,20);
		info1.setVisible(true);
		info2 = new JLabel();
		info2.setText(infoString2);
		info2.setLocation(20, 40);
		info2.setSize(360,20);
		info2.setVisible(true);
		info3 = new JLabel();
		info3.setText(infoString3);
		info3.setLocation(20, 70);
		info3.setSize(360,20);
		info3.setVisible(true);
		setBackground(Color.CYAN);
		add(info1);
		add(info2);
		add(info3);
		
	}
	
	public void updateInfo(ShipCountHandler sch){
		infoString2 = sch.getQuadroShipCount()+" quadro(s), "+sch.getTrippleShipCount()+" tripple(s), ";
		infoString3 = sch.getDoubleShipCount()+" double(s), "+sch.getSoloShipCount()+" solo(es).";
		info2.setText(infoString2);
		info3.setText(infoString3);
		
	}

}
