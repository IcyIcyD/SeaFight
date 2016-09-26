package PrepareLogic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Ships.DoubleShip;
import Ships.QuadroShip;
import Ships.SoloShip;
import Ships.TrippleShip;

public class PlaceShipButton extends JButton {

	private static final long serialVersionUID = 1L;

	public PlaceShipButton(final PlaySetField psf, final PrepareWindow pw) {
		
		setText("PLACE SHIP");
		setSize(250, 80);
		setPreferredSize(new Dimension(250, 80));
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch(pw.selectedCellsCount){
				
				case 1:
					
					if (pw.shipCountHandler.getSoloShipCount()<4) {
						SoloShip solo = new SoloShip();

						for (Integer key = 0; key<4; key++){
							if((pw.shipCountHandler.soloShipHandler.containsKey(key))==false){
								pw.shipCountHandler.soloShipHandler.put(key, solo);

								break;
							}
						}

						solo.placeSoloShip(psf, psf.isSelectedList.pollFirst());
						pw.selectedCellsCount-=1;
						pw.shipCountHandler.setSoloShipCount(pw.shipCountHandler.getSoloShipCount()+1);
						
						psf.fieldTreeMap.get(solo.getLockedPosition()).setEnabledCell(false);
						psf.fieldTreeMap.get(solo.getLockedPosition()).setDefaultIcon();
						psf.fieldTreeMap.get(solo.getLockedPosition()).setVisible(false);
						psf.fieldTreeMap.get(solo.getLockedPosition()).isSelected=false;
						psf.fieldTreeMap.get(solo.getLockedPosition()).isLocked=true;
						pw.scpl.updateInfo(pw.shipCountHandler, pw);
						
						for (Integer i=0; i<100; i++){
							if(psf.fieldTreeMap.get(i).isLocked==false){
								psf.fieldTreeMap.get(i).setEnabled(true);
							}
						}
						
					} else {
						
						errorDialog();

					}
					break;
					
				case 2:
					
					if (pw.shipCountHandler.getDoubleShipCount()<3) {
						
						DoubleShip doubleShip = new DoubleShip();
						doubleShip.placeDoubleShip(psf, psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst());
						pw.selectedCellsCount-=2;
						pw.shipCountHandler.setDoubleShipCount(pw.shipCountHandler.getDoubleShipCount()+1);
						
						for (Integer key = 0; key<3; key++){
							if((pw.shipCountHandler.doubleShipHandler.containsKey(key))==false){
								pw.shipCountHandler.doubleShipHandler.put(key, doubleShip);

								break;
							}
						}
						
						psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setEnabledCell(false);
						psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setDefaultIcon();
						psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setVisible(false);
						psf.fieldTreeMap.get(doubleShip.getLockedPosition()).isSelected=false;
						psf.fieldTreeMap.get(doubleShip.getLockedPosition()).isLocked=true;
						
						psf.fieldTreeMap.get(doubleShip.position2).setEnabledCell(false);
						psf.fieldTreeMap.get(doubleShip.position2).setDefaultIcon();
						psf.fieldTreeMap.get(doubleShip.position2).setVisible(false);
						psf.fieldTreeMap.get(doubleShip.position2).isSelected=false;
						psf.fieldTreeMap.get(doubleShip.position2).isLocked=true;
						
						pw.scpl.updateInfo(pw.shipCountHandler, pw);
						
						for (Integer i=0; i<100; i++){
							if(psf.fieldTreeMap.get(i).isLocked==false){
								psf.fieldTreeMap.get(i).setEnabled(true);
							}
						}
					} else {
						errorDialog();
					}
					break;
					
				case 3:
					
					if (pw.shipCountHandler.getTrippleShipCount()<2) {
						
						TrippleShip trippleShip = new TrippleShip();
						trippleShip.placeTrippleShip(psf, psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst());
						pw.selectedCellsCount-=3;
						pw.shipCountHandler.setTrippleShipCount(pw.shipCountHandler.getTrippleShipCount()+1);
						
						for (Integer key = 0; key<2; key++){
							if((pw.shipCountHandler.trippleShipHandler.containsKey(key))==false){
								pw.shipCountHandler.trippleShipHandler.put(key, trippleShip);

								break;
							}
						}
						
						psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setEnabledCell(false);
						psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setDefaultIcon();
						psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setVisible(false);
						psf.fieldTreeMap.get(trippleShip.getLockedPosition()).isSelected=false;
						psf.fieldTreeMap.get(trippleShip.getLockedPosition()).isLocked=true;
						
						psf.fieldTreeMap.get(trippleShip.position2).setEnabledCell(false);
						psf.fieldTreeMap.get(trippleShip.position2).setDefaultIcon();
						psf.fieldTreeMap.get(trippleShip.position2).setVisible(false);
						psf.fieldTreeMap.get(trippleShip.position2).isSelected=false;
						psf.fieldTreeMap.get(trippleShip.position2).isLocked=true;
						
						psf.fieldTreeMap.get(trippleShip.position3).setEnabledCell(false);
						psf.fieldTreeMap.get(trippleShip.position3).setDefaultIcon();
						psf.fieldTreeMap.get(trippleShip.position3).setVisible(false);
						psf.fieldTreeMap.get(trippleShip.position3).isSelected=false;
						psf.fieldTreeMap.get(trippleShip.position3).isLocked=true;
						
						pw.scpl.updateInfo(pw.shipCountHandler, pw);
						
						for (Integer i=0; i<100; i++){
							if(psf.fieldTreeMap.get(i).isLocked==false){
								psf.fieldTreeMap.get(i).setEnabled(true);
							}
						}
					} else {
						errorDialog();
					}
					break;
					
				case 4: 
					
					if (pw.shipCountHandler.getQuadroShipCount()<1) {
						
						QuadroShip quadroShip = new QuadroShip();
						quadroShip.placeQuadroShip(psf, psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst(), psf.isSelectedList.pollFirst());
						pw.selectedCellsCount-=4;
						pw.shipCountHandler.setQuadroShipCount(pw.shipCountHandler.getQuadroShipCount()+1);

						for (Integer key = 0; key<1; key++){
							if((pw.shipCountHandler.quadroShipHandler.containsKey(key))==false){
								pw.shipCountHandler.quadroShipHandler.put(key, quadroShip);

								break;
							}
						}
						
						psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setEnabledCell(false);
						psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setDefaultIcon();
						psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setVisible(false);
						psf.fieldTreeMap.get(quadroShip.getLockedPosition()).isSelected=false;
						psf.fieldTreeMap.get(quadroShip.getLockedPosition()).isLocked=true;
						
						psf.fieldTreeMap.get(quadroShip.position2).setEnabledCell(false);
						psf.fieldTreeMap.get(quadroShip.position2).setDefaultIcon();
						psf.fieldTreeMap.get(quadroShip.position2).setVisible(false);
						psf.fieldTreeMap.get(quadroShip.position2).isSelected=false;
						psf.fieldTreeMap.get(quadroShip.position2).isLocked=true;
						
						psf.fieldTreeMap.get(quadroShip.position3).setEnabledCell(false);
						psf.fieldTreeMap.get(quadroShip.position3).setDefaultIcon();
						psf.fieldTreeMap.get(quadroShip.position3).setVisible(false);
						psf.fieldTreeMap.get(quadroShip.position3).isSelected=false;
						psf.fieldTreeMap.get(quadroShip.position3).isLocked=true;
						
						psf.fieldTreeMap.get(quadroShip.position4).setEnabledCell(false);
						psf.fieldTreeMap.get(quadroShip.position4).setDefaultIcon();
						psf.fieldTreeMap.get(quadroShip.position4).setVisible(false);
						psf.fieldTreeMap.get(quadroShip.position4).isSelected=false;
						psf.fieldTreeMap.get(quadroShip.position4).isLocked=true;
						
						pw.scpl.updateInfo(pw.shipCountHandler, pw);
						
						for (Integer i=0; i<100; i++){
							if(psf.fieldTreeMap.get(i).isLocked==false){
								psf.fieldTreeMap.get(i).setEnabled(true);
							}
						}
						
					} else {
						errorDialog();
					}
					break;
					
				}
				
			}
		});
		
	}
	
	public void errorDialog() {

		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setSize(300,100);
		dialog.setPreferredSize(new Dimension(300,100));
		dialog.setTitle("Warning!");
		dialog.setLocationRelativeTo(null);
		dialog.getContentPane().setLayout(new GridLayout(2,2, 25, 25));
		JLabel textPanel = new JLabel("Cannot place any more ships of this type.");
		textPanel.setSize(250,50);
		textPanel.setPreferredSize(new Dimension(250,50));
		textPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		JButton okButton = new JButton("OK");
		okButton.setSize(150,50);
		okButton.setPreferredSize(new Dimension(150, 50));
		okButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				dialog.dispose();
			}
		});
		
		okButton.setVisible(true);
		textPanel.setVisible(true);
		
		dialog.getContentPane().add(textPanel);
		dialog.getContentPane().add(okButton);
		dialog.pack();
		dialog.setVisible(true);
		
	}
}
