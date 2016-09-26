package PrepareLogic;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Domain.ShipCountHandler;


public class ShipsCanPlaceInfoLabel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel info1;
	private JLabel info2;
	private JLabel info3;
	private String infoString1;
	private String infoString2;
	private String infoString3;
	
	public ShipsCanPlaceInfoLabel(PrepareWindow pw) {
		super();
		setSize(250, 70);
		setPreferredSize(new Dimension(250, 70));
		addComponents(pw.shipCountHandler);
		setVisible(true);
	}
	
	
		
	private void addComponents(ShipCountHandler sch) {
		
		infoString1 = new String("Possible to place: ");
		infoString2 = new String((1-sch.getQuadroShipCount())+" quadro(s), "+(2-sch.getTrippleShipCount())+" tripple(s), ");
		infoString3 = new String((3-sch.getDoubleShipCount())+" double(s), "+(4-sch.getSoloShipCount())+" solo(es).");
		info1 = new JLabel();
		info1.setText(infoString1);
		info1.setLocation(10, 10);
		info1.setSize(230,14);
		info1.setVisible(true);
		info2 = new JLabel();
		info2.setText(infoString2);
		info2.setLocation(10, 24);
		info2.setSize(230,13);
		info2.setVisible(true);
		info3 = new JLabel();
		info3.setText(infoString3);
		info3.setLocation(10, 37);
		info3.setSize(230,13);
		info3.setVisible(true);
		setBackground(Color.CYAN);
		add(info1);
		add(info2);
		add(info3);
		
	}
	
	public void updateInfo(ShipCountHandler sch, PrepareWindow pw){
		infoString2 = (1-sch.getQuadroShipCount())+" quadro(s), "+(2-sch.getTrippleShipCount())+" tripple(s), ";
		infoString3 = (3-sch.getDoubleShipCount())+" double(s), "+(4-sch.getSoloShipCount())+" solo(es).";
		info2.setText(infoString2);
		info3.setText(infoString3);
		if ((sch.getSoloShipCount()==4)&&(sch.getDoubleShipCount()==3)&&(sch.getTrippleShipCount()==2)&&(sch.getQuadroShipCount()==1)){
			pw.playButton.setEnabled(true);
		} else {
			pw.playButton.setEnabled(false);
		}
	}

}
