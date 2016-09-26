package PrepareLogic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Ships.DoubleShip;
import Ships.QuadroShip;
import Ships.SoloShip;
import Ships.TrippleShip;

public class RemoveShipButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private ShipCell cellForClear;
	private SoloShip solo;
	private DoubleShip doubleShip;
	private TrippleShip trippleShip;
	private QuadroShip quadroShip;
	
	public RemoveShipButton(final PlaySetField psf, final PrepareWindow pw){
		
		setText("REMOVE SHIP");
		setSize(250, 80);
		setPreferredSize(new Dimension(250, 80));
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cellForClear=psf.placementTreeMap.get(psf.isSelectedShipList.first());
				
				switch(cellForClear.containsShip){
				
				case 0:
					
					break;
					
				case 1:
					
					// IF SHIP IS SOLO
					
					for (Integer key = 0; key<4; key++){
						if (null!=pw.shipCountHandler.soloShipHandler.get(key)) {
							if((pw.shipCountHandler.soloShipHandler.get(key).getLockedPosition()==cellForClear.getTotalIndex())){
								solo = pw.shipCountHandler.soloShipHandler.get(key);
								pw.shipCountHandler.soloShipHandler.remove(key);

								break;
							}
						}
					}

					solo.removeSoloShip(psf);
					
					pw.selectedShipCellsCount-=1;
					pw.shipCountHandler.setSoloShipCount(pw.shipCountHandler.getSoloShipCount()-1);
					
					psf.fieldTreeMap.get(solo.getLockedPosition()).isLocked=false;
					psf.fieldTreeMap.get(solo.getLockedPosition()).setEnabledCell(true);
					psf.fieldTreeMap.get(solo.getLockedPosition()).setDefaultIcon();
					psf.fieldTreeMap.get(solo.getLockedPosition()).setVisible(true);
					psf.placementTreeMap.get(solo.getLockedPosition()).isSelected=false;
					psf.placementTreeMap.get(solo.getLockedPosition()).updateSelection();
					
					psf.isSelectedShipList.remove(psf.isSelectedShipList.first());

					pw.scpl.updateInfo(pw.shipCountHandler, pw);
					
					updateEnble(psf);
					
					break;
					
				case 2:
					
					//IF SHIP IS DOUBLE
					
					for (Integer key = 0; key<3; key++){
						if (null!=pw.shipCountHandler.doubleShipHandler.get(key)) {
							if((pw.shipCountHandler.doubleShipHandler.get(key).getLockedPosition()==cellForClear.getTotalIndex())){
								doubleShip = pw.shipCountHandler.doubleShipHandler.get(key);
								pw.shipCountHandler.doubleShipHandler.remove(key);

								break;
							}
						}
					}

					doubleShip.removeDoubleShip(psf);
					
					pw.shipCountHandler.setDoubleShipCount(pw.shipCountHandler.getDoubleShipCount()-1);
					
					pw.selectedShipCellsCount-=2;
					
					psf.fieldTreeMap.get(doubleShip.getLockedPosition()).isLocked=false;
					psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setEnabledCell(true);
					psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setDefaultIcon();
					psf.fieldTreeMap.get(doubleShip.getLockedPosition()).setVisible(true);
					psf.placementTreeMap.get(doubleShip.getLockedPosition()).isSelected=false;
					psf.placementTreeMap.get(doubleShip.getLockedPosition()).updateSelection();
				
					psf.fieldTreeMap.get(doubleShip.position2).isLocked=false;
					psf.fieldTreeMap.get(doubleShip.position2).setEnabledCell(true);
					psf.fieldTreeMap.get(doubleShip.position2).setDefaultIcon();
					psf.fieldTreeMap.get(doubleShip.position2).setVisible(true);
					psf.placementTreeMap.get(doubleShip.position2).isSelected=false;
					psf.placementTreeMap.get(doubleShip.position2).updateSelection();
					
					psf.isSelectedShipList.remove(doubleShip.shipCell1.getTotalIndex());
					psf.isSelectedShipList.remove(doubleShip.shipCell2.getTotalIndex());

					pw.scpl.updateInfo(pw.shipCountHandler, pw);
					
					updateEnble(psf);
					
					break;
					
				case 3:
					
					//IF SHIP IS TRIPPLE
					
					for (Integer key = 0; key<2; key++){
						if (null!=pw.shipCountHandler.trippleShipHandler.get(key)) {
							if((pw.shipCountHandler.trippleShipHandler.get(key).getLockedPosition()==cellForClear.getTotalIndex())){
								trippleShip = pw.shipCountHandler.trippleShipHandler.get(key);
								pw.shipCountHandler.trippleShipHandler.remove(key);

								break;
							}
						}
					}

					trippleShip.removeTrippleShip(psf);
					
					pw.shipCountHandler.setTrippleShipCount(pw.shipCountHandler.getTrippleShipCount()-1);
					
					pw.selectedShipCellsCount-=3;
					
					psf.fieldTreeMap.get(trippleShip.getLockedPosition()).isLocked=false;
					psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setEnabledCell(true);
					psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setDefaultIcon();
					psf.fieldTreeMap.get(trippleShip.getLockedPosition()).setVisible(true);
					psf.placementTreeMap.get(trippleShip.getLockedPosition()).isSelected=false;
					psf.placementTreeMap.get(trippleShip.getLockedPosition()).updateSelection();
				
					psf.fieldTreeMap.get(trippleShip.position2).isLocked=false;
					psf.fieldTreeMap.get(trippleShip.position2).setEnabledCell(true);
					psf.fieldTreeMap.get(trippleShip.position2).setDefaultIcon();
					psf.fieldTreeMap.get(trippleShip.position2).setDefaultDisabledIcon();
					psf.fieldTreeMap.get(trippleShip.position2).setVisible(true);
					psf.placementTreeMap.get(trippleShip.position2).isSelected=false;
					psf.placementTreeMap.get(trippleShip.position2).updateSelection();
					
					psf.fieldTreeMap.get(trippleShip.position3).isLocked=false;
					psf.fieldTreeMap.get(trippleShip.position3).setEnabledCell(true);
					psf.fieldTreeMap.get(trippleShip.position3).setDefaultIcon();
					psf.fieldTreeMap.get(trippleShip.position3).setVisible(true);
					psf.placementTreeMap.get(trippleShip.position3).isSelected=false;
					psf.placementTreeMap.get(trippleShip.position3).updateSelection();
					
					psf.isSelectedShipList.remove(trippleShip.shipCell1.getTotalIndex());
					psf.isSelectedShipList.remove(trippleShip.shipCell2.getTotalIndex());
					psf.isSelectedShipList.remove(trippleShip.shipCell3.getTotalIndex());

					pw.scpl.updateInfo(pw.shipCountHandler, pw);
					
					updateEnble(psf);
					
					break;
					
				case 4:
					
					//IF SHIP IS QUADRO
					
					for (Integer key = 0; key<1; key++){
						if (null!=pw.shipCountHandler.quadroShipHandler.get(key)) {
							if((pw.shipCountHandler.quadroShipHandler.get(key).getLockedPosition()==cellForClear.getTotalIndex())){
								quadroShip = pw.shipCountHandler.quadroShipHandler.get(key);
								pw.shipCountHandler.quadroShipHandler.remove(key);

								break;
							}
						}
					}

					quadroShip.removeQuadroShip(psf);
					
					pw.shipCountHandler.setQuadroShipCount(pw.shipCountHandler.getQuadroShipCount()-1);
					
					pw.selectedShipCellsCount-=4;
					
					psf.fieldTreeMap.get(quadroShip.getLockedPosition()).isLocked=false;
					psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setEnabledCell(true);
					psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setDefaultIcon();
					psf.fieldTreeMap.get(quadroShip.getLockedPosition()).setVisible(true);
					psf.placementTreeMap.get(quadroShip.getLockedPosition()).isSelected=false;
					psf.placementTreeMap.get(quadroShip.getLockedPosition()).updateSelection();
				
					psf.fieldTreeMap.get(quadroShip.position2).isLocked=false;
					psf.fieldTreeMap.get(quadroShip.position2).setEnabledCell(true);
					psf.fieldTreeMap.get(quadroShip.position2).setDefaultDisabledIcon();
					psf.fieldTreeMap.get(quadroShip.position2).setDefaultIcon();
					psf.fieldTreeMap.get(quadroShip.position2).setVisible(true);
					psf.placementTreeMap.get(quadroShip.position2).isSelected=false;
					psf.placementTreeMap.get(quadroShip.position2).updateSelection();
					
					psf.fieldTreeMap.get(quadroShip.position3).isLocked=false;
					psf.fieldTreeMap.get(quadroShip.position3).setEnabledCell(true);
					psf.fieldTreeMap.get(quadroShip.position3).setDefaultIcon();
					psf.fieldTreeMap.get(quadroShip.position3).setDefaultDisabledIcon();
					psf.fieldTreeMap.get(quadroShip.position3).setVisible(true);
					psf.placementTreeMap.get(quadroShip.position3).isSelected=false;
					psf.placementTreeMap.get(quadroShip.position3).updateSelection();
					
					psf.fieldTreeMap.get(quadroShip.position4).isLocked=false;
					psf.fieldTreeMap.get(quadroShip.position4).setEnabledCell(true);
					psf.fieldTreeMap.get(quadroShip.position4).setDefaultIcon();
					psf.fieldTreeMap.get(quadroShip.position4).setVisible(true);
					psf.placementTreeMap.get(quadroShip.position4).isSelected=false;
					psf.placementTreeMap.get(quadroShip.position4).updateSelection();
					
					psf.isSelectedShipList.remove(quadroShip.shipCell1.getTotalIndex());
					psf.isSelectedShipList.remove(quadroShip.shipCell2.getTotalIndex());
					psf.isSelectedShipList.remove(quadroShip.shipCell3.getTotalIndex());
					psf.isSelectedShipList.remove(quadroShip.shipCell4.getTotalIndex());

					pw.scpl.updateInfo(pw.shipCountHandler, pw);
					
					updateEnble(psf);
					
					break;
						
				}
				
			}
		});
	}
	
	public void updateEnble(PlaySetField psf){
		if (psf.isSelectedShipList.size()==0){
			setEnabled(false);
		} else {
			setEnabled(true);
		}
	}

}


