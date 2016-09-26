package PrepareLogic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JButton;

import Ships.DoubleShip;
import Ships.QuadroShip;
import Ships.SoloShip;
import Ships.TrippleShip;

public class RandomButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private TreeMap<Integer, Boolean> possibleAligment;
	private boolean canPlace;
	private ShipCell cell1;
	private ShipCell cell2;
	private ShipCell cell3;
	private ShipCell cell4;
	private int k;
	private SoloShip s1;
	private DoubleShip s2;
	private TrippleShip s3;
	private QuadroShip s4;
	
	public RandomButton(final PrepareWindow pw){
		
		setText("ALL RANDOM");
		setSize(250, 80);
		setPreferredSize(new Dimension(250, 80));
		
		addActionListener(new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for (Integer i=0; i<100; i++){
					pw.psf.fieldTreeMap.get(i).isSelected=false;
				}
				
				pw.selectedCellsCount=0;
				pw.selectedShipCellsCount=0;
				pw.psf.isSelectedShipList.clear();
				pw.psf.isSelectedList.clear();
				
				for (Integer key = 0; key<4; key++){
					if (null!=pw.shipCountHandler.soloShipHandler.get(key)) {
						s1 = pw.shipCountHandler.soloShipHandler.get(key);
						s1.removeSoloShip(pw.psf);
						pw.shipCountHandler.soloShipHandler.remove(key);
						pw.psf.fieldTreeMap.get(s1.getLockedPosition()).isLocked=false;
						pw.psf.fieldTreeMap.get(s1.getLockedPosition()).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s1.getLockedPosition()).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s1.getLockedPosition()).setVisible(true);
						pw.psf.placementTreeMap.get(s1.getLockedPosition()).isSelected=false;
						pw.psf.placementTreeMap.get(s1.getLockedPosition()).updateSelection();
						
					}
				}
				
				for (Integer key = 0; key<3; key++){
					if (null!=pw.shipCountHandler.doubleShipHandler.get(key)) {
						s2 = pw.shipCountHandler.doubleShipHandler.get(key);
						s2.removeDoubleShip(pw.psf);
						pw.shipCountHandler.doubleShipHandler.remove(key);
						pw.psf.fieldTreeMap.get(s2.getLockedPosition()).isLocked=false;
						pw.psf.fieldTreeMap.get(s2.getLockedPosition()).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s2.getLockedPosition()).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s2.getLockedPosition()).setVisible(true);
						pw.psf.placementTreeMap.get(s2.getLockedPosition()).isSelected=false;
						pw.psf.placementTreeMap.get(s2.getLockedPosition()).updateSelection();
						pw.psf.fieldTreeMap.get(s2.position2).isLocked=false;
						pw.psf.fieldTreeMap.get(s2.position2).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s2.position2).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s2.position2).setVisible(true);
						pw.psf.placementTreeMap.get(s2.position2).isSelected=false;
						pw.psf.placementTreeMap.get(s2.position2).updateSelection();
						
					}
				}
				
				for (Integer key = 0; key<2; key++){
					if (null!=pw.shipCountHandler.trippleShipHandler.get(key)) {
						s3 = pw.shipCountHandler.trippleShipHandler.get(key);
						s3.removeTrippleShip(pw.psf);
						pw.shipCountHandler.trippleShipHandler.remove(key);
						pw.psf.fieldTreeMap.get(s3.getLockedPosition()).isLocked=false;
						pw.psf.fieldTreeMap.get(s3.getLockedPosition()).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s3.getLockedPosition()).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s3.getLockedPosition()).setVisible(true);
						pw.psf.placementTreeMap.get(s3.getLockedPosition()).isSelected=false;
						pw.psf.placementTreeMap.get(s3.getLockedPosition()).updateSelection();
						pw.psf.fieldTreeMap.get(s3.position2).isLocked=false;
						pw.psf.fieldTreeMap.get(s3.position2).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s3.position2).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s3.position2).setVisible(true);
						pw.psf.placementTreeMap.get(s3.position2).isSelected=false;
						pw.psf.placementTreeMap.get(s3.position2).updateSelection();
						pw.psf.fieldTreeMap.get(s3.position3).isLocked=false;
						pw.psf.fieldTreeMap.get(s3.position3).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s3.position3).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s3.position3).setVisible(true);
						pw.psf.placementTreeMap.get(s3.position3).isSelected=false;
						pw.psf.placementTreeMap.get(s3.position3).updateSelection();
						
					}
				}
				
				for (Integer key = 0; key<1; key++){
					if (null!=pw.shipCountHandler.quadroShipHandler.get(key)) {
						s4 = pw.shipCountHandler.quadroShipHandler.get(key);
						s4.removeQuadroShip(pw.psf);
						pw.shipCountHandler.quadroShipHandler.remove(key);
						pw.psf.fieldTreeMap.get(s4.getLockedPosition()).isLocked=false;
						pw.psf.fieldTreeMap.get(s4.getLockedPosition()).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s4.getLockedPosition()).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s4.getLockedPosition()).setVisible(true);
						pw.psf.placementTreeMap.get(s4.getLockedPosition()).isSelected=false;
						pw.psf.placementTreeMap.get(s4.getLockedPosition()).updateSelection();
						pw.psf.fieldTreeMap.get(s4.position2).isLocked=false;
						pw.psf.fieldTreeMap.get(s4.position2).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s4.position2).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s4.position2).setVisible(true);
						pw.psf.placementTreeMap.get(s4.position2).isSelected=false;
						pw.psf.placementTreeMap.get(s4.position2).updateSelection();
						pw.psf.fieldTreeMap.get(s4.position3).isLocked=false;
						pw.psf.fieldTreeMap.get(s4.position3).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s4.position3).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s4.position3).setVisible(true);
						pw.psf.placementTreeMap.get(s4.position3).isSelected=false;
						pw.psf.placementTreeMap.get(s4.position3).updateSelection();
						pw.psf.fieldTreeMap.get(s4.position4).isLocked=false;
						pw.psf.fieldTreeMap.get(s4.position4).setEnabledCell(true);
						pw.psf.fieldTreeMap.get(s4.position4).setDefaultIcon();
						pw.psf.fieldTreeMap.get(s4.position4).setVisible(true);
						pw.psf.placementTreeMap.get(s4.position4).isSelected=false;
						pw.psf.placementTreeMap.get(s4.position4).updateSelection();
						
					}
				}
				
				possibleAligment = new TreeMap<Integer, Boolean>();
				possibleAligment.put(0, false);
				possibleAligment.put(1, false);
				possibleAligment.put(2, false);
				possibleAligment.put(3, false);
				
				pw.shipCountHandler.setSoloShipCount(4);
				pw.shipCountHandler.setDoubleShipCount(3);
				pw.shipCountHandler.setTrippleShipCount(2);
				pw.shipCountHandler.setQuadroShipCount(1);
				
				Random r1=new Random();
				
				while(canPlace==false){
					k=r1.nextInt(100);
					manageShipPlacementForAI(pw, k, 3);		
				}
				QuadroShip q = new QuadroShip();
				q.placeQuadroShip(pw.psf, cell1.getTotalIndex(), cell2.getTotalIndex(), cell3.getTotalIndex(), cell4.getTotalIndex());
				pw.shipCountHandler.quadroShipHandler.put(0, q);
				canPlace=false;
				pw.psf.fieldTreeMap.get(q.getLockedPosition()).setEnabledCell(false);
				pw.psf.fieldTreeMap.get(q.getLockedPosition()).setDefaultIcon();
				pw.psf.fieldTreeMap.get(q.getLockedPosition()).setVisible(false);
				pw.psf.fieldTreeMap.get(q.getLockedPosition()).isSelected=false;
				pw.psf.fieldTreeMap.get(q.getLockedPosition()).isLocked=true;
				
				pw.psf.fieldTreeMap.get(q.position2).setEnabledCell(false);
				pw.psf.fieldTreeMap.get(q.position2).setDefaultIcon();
				pw.psf.fieldTreeMap.get(q.position2).setVisible(false);
				pw.psf.fieldTreeMap.get(q.position2).isSelected=false;
				pw.psf.fieldTreeMap.get(q.position2).isLocked=true;
				
				pw.psf.fieldTreeMap.get(q.position3).setEnabledCell(false);
				pw.psf.fieldTreeMap.get(q.position3).setDefaultIcon();
				pw.psf.fieldTreeMap.get(q.position3).setVisible(false);
				pw.psf.fieldTreeMap.get(q.position3).isSelected=false;
				pw.psf.fieldTreeMap.get(q.position3).isLocked=true;
				
				pw.psf.fieldTreeMap.get(q.position4).setEnabledCell(false);
				pw.psf.fieldTreeMap.get(q.position4).setDefaultIcon();
				pw.psf.fieldTreeMap.get(q.position4).setVisible(false);
				pw.psf.fieldTreeMap.get(q.position4).isSelected=false;
				pw.psf.fieldTreeMap.get(q.position4).isLocked=true;
				cell1=null;
				cell2=null;
				cell3=null;
				cell4=null;
				
				for (Integer i=0; i<2; i++){
					while(canPlace==false){
						k=r1.nextInt(100);
						manageShipPlacementForAI(pw, k, 2);		
					}
					TrippleShip t = new TrippleShip();
					t.placeTrippleShip(pw.psf, cell1.getTotalIndex(), cell2.getTotalIndex(), cell3.getTotalIndex());
					pw.shipCountHandler.trippleShipHandler.put(i, t);
					canPlace=false;
					pw.psf.fieldTreeMap.get(t.getLockedPosition()).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(t.getLockedPosition()).setDefaultIcon();
					pw.psf.fieldTreeMap.get(t.getLockedPosition()).setVisible(false);
					pw.psf.fieldTreeMap.get(t.getLockedPosition()).isSelected=false;
					pw.psf.fieldTreeMap.get(t.getLockedPosition()).isLocked=true;
					
					pw.psf.fieldTreeMap.get(t.position2).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(t.position2).setDefaultIcon();
					pw.psf.fieldTreeMap.get(t.position2).setVisible(false);
					pw.psf.fieldTreeMap.get(t.position2).isSelected=false;
					pw.psf.fieldTreeMap.get(t.position2).isLocked=true;
					
					pw.psf.fieldTreeMap.get(t.position3).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(t.position3).setDefaultIcon();
					pw.psf.fieldTreeMap.get(t.position3).setVisible(false);
					pw.psf.fieldTreeMap.get(t.position3).isSelected=false;
					pw.psf.fieldTreeMap.get(t.position3).isLocked=true;
					cell1=null;
					cell2=null;
					cell3=null;
					cell4=null;
				}
				
				
				for (Integer i=0; i<3; i++){
					while(canPlace==false){
						k=r1.nextInt(100);
						manageShipPlacementForAI(pw, k, 1);		
					}
					DoubleShip d = new DoubleShip();
					d.placeDoubleShip(pw.psf, cell1.getTotalIndex(), cell2.getTotalIndex());
					pw.shipCountHandler.doubleShipHandler.put(i, d);
					canPlace=false;
					pw.psf.fieldTreeMap.get(d.getLockedPosition()).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(d.getLockedPosition()).setDefaultIcon();
					pw.psf.fieldTreeMap.get(d.getLockedPosition()).setVisible(false);
					pw.psf.fieldTreeMap.get(d.getLockedPosition()).isSelected=false;
					pw.psf.fieldTreeMap.get(d.getLockedPosition()).isLocked=true;
					
					pw.psf.fieldTreeMap.get(d.position2).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(d.position2).setDefaultIcon();
					pw.psf.fieldTreeMap.get(d.position2).setVisible(false);
					pw.psf.fieldTreeMap.get(d.position2).isSelected=false;
					pw.psf.fieldTreeMap.get(d.position2).isLocked=true;
					cell1=null;
					cell2=null;
					cell3=null;
					cell4=null;
				}
				
				for (Integer i=0; i<4; i++){
					while(canPlace==false){
						k=r1.nextInt(100);
						manageShipPlacementForAI(pw, k, 0);		
					}
					SoloShip s = new SoloShip();
					s.placeSoloShip(pw.psf, cell1.getTotalIndex());
					pw.shipCountHandler.soloShipHandler.put(i, s);
					canPlace=false;
					pw.psf.fieldTreeMap.get(s.getLockedPosition()).setEnabledCell(false);
					pw.psf.fieldTreeMap.get(s.getLockedPosition()).setDefaultIcon();
					pw.psf.fieldTreeMap.get(s.getLockedPosition()).setVisible(false);
					pw.psf.fieldTreeMap.get(s.getLockedPosition()).isSelected=false;
					pw.psf.fieldTreeMap.get(s.getLockedPosition()).isLocked=true;
					cell1=null;
					cell2=null;
					cell3=null;
					cell4=null;
				}
				
				for (Integer i=0; i<100; i++){
					pw.psf.fieldTreeMap.get(i).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
					if(pw.psf.fieldTreeMap.get(i).isLocked==false){
						pw.psf.fieldTreeMap.get(i).setEnabled(true);
						pw.psf.fieldTreeMap.get(i).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
					}
				}
				
				pw.removeButton.updateEnble(pw.psf);
				pw.placeButton.setEnabled(false);
				pw.scpl.updateInfo(pw.shipCountHandler, pw);
			}
		});
		
	}
	
	private void manageShipPlacementForAI(PrepareWindow pw, Integer position, Integer gap){
		Integer x=position%10;
		Integer y=position/10;
		
			if(pw.psf.fieldTreeMap.get(position).isLocked==true){
				canPlace=false;
				return;
			}
			
			if(10>x+gap){
				possibleAligment.replace(0, true);
			} else {
				possibleAligment.replace(0, false);
			}
			if(-1<x-gap){
				possibleAligment.replace(1, true);
			} else {
				possibleAligment.replace(1, false);
			}
			if(10>y+gap){
				possibleAligment.replace(2, true);
			} else {
				possibleAligment.replace(2, false);
			}
			if(-1<y-gap){
				possibleAligment.replace(3, true);
			} else {
				possibleAligment.replace(3, false);
			}
			
			
			if(possibleAligment.get(0)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=pw.psf.fieldTreeMap.get(position+i)){
						if(pw.psf.fieldTreeMap.get(position+i).isLocked==true){
							possibleAligment.replace(0, false);
							break;
						}
					}
				}
			}
			
			if(possibleAligment.get(1)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=pw.psf.fieldTreeMap.get(position-i)){
						if(pw.psf.fieldTreeMap.get(position-i).isLocked==true){
							possibleAligment.replace(1, false);
							break;
						}
					}
				}
			}
			
			if(possibleAligment.get(2)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=pw.psf.fieldTreeMap.get(position+(i*10))){
						if(pw.psf.fieldTreeMap.get(position+(i*10)).isLocked==true){
							possibleAligment.replace(2, false);
							break;
						}
					}
					
				}
			}
			
			if(possibleAligment.get(3)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=pw.psf.fieldTreeMap.get(position-(i*10))){
						if(pw.psf.fieldTreeMap.get(position-(i*10)).isLocked==true){
							possibleAligment.replace(3, false);
							break;
						}
					}
				}
			}
			
			if((possibleAligment.get(0)==false) && (possibleAligment.get(1)==false) && (possibleAligment.get(2)==false) && (possibleAligment.get(3)==false)){
				canPlace=false;
				return;
			} else {
				Random r = new Random();
				Integer rValue;
				rValue=r.nextInt(4);
				while(possibleAligment.get(rValue)==false){
					rValue=r.nextInt(4);
				}
				if(rValue==0){
					cell1=pw.psf.placementTreeMap.get(position);
					if (pw.psf.fieldTreeMap.get(position+1)!=null){
						cell2=pw.psf.placementTreeMap.get(position+1);
					}
					if (pw.psf.fieldTreeMap.get(position+2)!=null){
						cell3=pw.psf.placementTreeMap.get(position+2);
					}
					if (pw.psf.fieldTreeMap.get(position+3)!=null){
						cell4=pw.psf.placementTreeMap.get(position+3);
					}
					canPlace=true;
					return;
				}
				if(rValue==1){
					if (pw.psf.fieldTreeMap.get(position-gap)!=null){
						cell1=pw.psf.placementTreeMap.get(position-gap);
					}
					if (pw.psf.fieldTreeMap.get(position-gap+1)!=null){
						cell2=pw.psf.placementTreeMap.get(position-gap+1);
					}
					if (pw.psf.fieldTreeMap.get(position-gap+2)!=null){
						cell3=pw.psf.placementTreeMap.get(position-gap+2);
					}
					if (pw.psf.fieldTreeMap.get(position-gap+3)!=null){
						cell4=pw.psf.placementTreeMap.get(position-gap+3);
					}
					canPlace=true;
					return;
				}
				if(rValue==2){
					cell1=pw.psf.placementTreeMap.get(position);
					if (pw.psf.fieldTreeMap.get(position+10)!=null){
						cell2=pw.psf.placementTreeMap.get(position+10);
					}
					if (pw.psf.fieldTreeMap.get(position+20)!=null){
						cell3=pw.psf.placementTreeMap.get(position+20);
					}
					if (pw.psf.fieldTreeMap.get(position+30)!=null){
						cell4=pw.psf.placementTreeMap.get(position+30);
					}
					canPlace=true;
					return;
				}
				if(rValue==3){
					if (pw.psf.fieldTreeMap.get(position-(gap*10))!=null){
						cell1=pw.psf.placementTreeMap.get(position-(gap*10));
					}
					if (pw.psf.fieldTreeMap.get(position-(gap*10)+10)!=null){
						cell2=pw.psf.placementTreeMap.get(position-(gap*10)+10);
					}
					if (pw.psf.fieldTreeMap.get(position-(gap*10)+20)!=null){
						cell3=pw.psf.placementTreeMap.get(position-(gap*10)+20);
					}
					if (pw.psf.fieldTreeMap.get(position-(gap*10)+30)!=null){
						cell4=pw.psf.placementTreeMap.get(position-(gap*10)+30);
					}
					canPlace=true;
					return;
				}
			}
		
	}
}
