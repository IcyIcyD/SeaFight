package Ships;

import AI.FieldCreator;
import Domain.AbstractShip;
import PrepareLogic.CellForPlaySetField;
import PrepareLogic.PlaySetField;
import PrepareLogic.ShipCell;

public class TrippleShip extends AbstractShip{
	
	public Integer position2;
	public Integer position3;
	public ShipCell shipCell1;
	public ShipCell shipCell2;
	public ShipCell shipCell3;
	private Boolean isHorizontalAlign;


	public void placeTrippleShip(PlaySetField psf, Integer position, Integer secondPosition, Integer thirdPosition){
		
		setLockedPosition(position);
		position2=secondPosition;
		position3=thirdPosition;
		if ((position==secondPosition+10)||(position==secondPosition-10)){
			isHorizontalAlign=false;
		} else {
			isHorizontalAlign=true;
		}
		
		shipCell1 = psf.placementTreeMap.get(position);
		shipCell1.isLocked=false;
		shipCell1.containsShip=3;
		shipCell2 = psf.placementTreeMap.get(secondPosition);
		shipCell2.isLocked=false;
		shipCell2.containsShip=3;
		shipCell3 = psf.placementTreeMap.get(thirdPosition);
		shipCell3.isLocked=false;
		shipCell3.containsShip=3;
		psf.fieldTreeMap.get(shipCell1.getTotalIndex()).isLocked=true;
		psf.fieldTreeMap.get(shipCell2.getTotalIndex()).isLocked=true;
		psf.fieldTreeMap.get(shipCell3.getTotalIndex()).isLocked=true;
		
		if (isHorizontalAlign==true){
			shipCell1.setIconCell(SHIP_TRIPPLE_1);
			shipCell1.setDisabledIcon(SHIP_TRIPPLE_1);
			shipCell2.setIconCell(SHIP_TRIPPLE_2);
			shipCell2.setDisabledIcon(SHIP_TRIPPLE_2);
			shipCell3.setIconCell(SHIP_TRIPPLE_3);
			shipCell3.setDisabledIcon(SHIP_TRIPPLE_3);
			
		} else {
			shipCell1.setIconCell(SHIP_TRIPPLE_1_ROTATED);
			shipCell1.setDisabledIcon(SHIP_TRIPPLE_1_ROTATED);
			shipCell2.setIconCell(SHIP_TRIPPLE_2_ROTATED);
			shipCell2.setDisabledIcon(SHIP_TRIPPLE_2_ROTATED);
			shipCell3.setIconCell(SHIP_TRIPPLE_3_ROTATED);
			shipCell3.setDisabledIcon(SHIP_TRIPPLE_3_ROTATED);
		}
		
		shipCell1.setVisible(true);
		shipCell1.setEnabledCell(true);
		shipCell2.setVisible(true);
		shipCell2.setEnabledCell(true);
		shipCell3.setVisible(true);
		shipCell3.setEnabledCell(true);
		psf.switchCard(position);
		psf.switchCard(secondPosition);
		psf.switchCard(thirdPosition);

		//Block cells around it
		lockCell(psf, shipCell1.yIndex, shipCell1.xIndex-1);
		lockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex);
		lockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+1);
		lockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex-1);
		lockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+1);
		lockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex-1);
		if(isHorizontalAlign==true){
			lockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex);
			lockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+2);
			lockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+3);
			lockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+2);
			lockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+3);
			lockCell(psf, shipCell1.yIndex, shipCell1.xIndex+3);
		} else {
			lockCell(psf, shipCell1.yIndex, shipCell1.xIndex+1);
			lockCell(psf, shipCell1.yIndex+2, shipCell1.xIndex-1);
			lockCell(psf, shipCell1.yIndex+2, shipCell1.xIndex+1);
			lockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex+1);
			lockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex);
			lockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex-1);
		}
				
	}
	
			
	private void lockCell(PlaySetField psf, Integer y, Integer x) {
		if (null!=psf.fieldTreeMap.get((y*10)+x)){
					
			if ((x<10) && (x>-1) && (y<10) && (y>-1)) {
				psf.fieldTreeMap.get((y*10)+x).setEnabledCell(false);
				psf.fieldTreeMap.get((y*10)+x).isLocked=true;
			}
					
		}
				
	}
	
	public void removeTrippleShip(PlaySetField psf) {
		
		
		shipCell1 = psf.placementTreeMap.get(getLockedPosition());
		psf.fieldTreeMap.get(shipCell1.getTotalIndex()).isLocked=false;
		shipCell1.containsShip=0;
		shipCell1.setIconCell(ShipCell.DEFAULT);
		shipCell1.setDisabledIcon(ShipCell.DISABLED);
		shipCell1.setVisible(false);
		shipCell1.setEnabledCell(false);
		shipCell1.isLocked=true;
		psf.switchCard(getLockedPosition());
		
		shipCell2 = psf.placementTreeMap.get(position2);
		psf.fieldTreeMap.get(shipCell2.getTotalIndex()).isLocked=false;
		shipCell2.containsShip=0;
		shipCell2.setIconCell(ShipCell.DEFAULT);
		shipCell2.setDisabledIcon(ShipCell.DISABLED);
		shipCell2.setVisible(false);
		shipCell2.setEnabledCell(false);
		shipCell2.isLocked=true;
		psf.switchCard(position2);
		
		shipCell3 = psf.placementTreeMap.get(position3);
		psf.fieldTreeMap.get(shipCell3.getTotalIndex()).isLocked=false;
		shipCell3.containsShip=0;
		shipCell3.setIconCell(ShipCell.DEFAULT);
		shipCell3.setDisabledIcon(ShipCell.DISABLED);
		shipCell3.setVisible(false);
		shipCell3.setEnabledCell(false);
		shipCell3.isLocked=true;
		psf.switchCard(position3);
		
		// Unlock cells around it
		unlockCell(psf, shipCell1.yIndex, shipCell1.xIndex-1);
		unlockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex);
		unlockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+1);
		unlockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex-1);
		unlockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+1);
		unlockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex-1);
		if(isHorizontalAlign==true){
			unlockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex);
			unlockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+2);
			unlockCell(psf, shipCell1.yIndex-1, shipCell1.xIndex+3);
			unlockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+2);
			unlockCell(psf, shipCell1.yIndex+1, shipCell1.xIndex+3);
			unlockCell(psf, shipCell1.yIndex, shipCell1.xIndex+3);
		} else {
			unlockCell(psf, shipCell1.yIndex, shipCell1.xIndex+1);
			unlockCell(psf, shipCell1.yIndex+2, shipCell1.xIndex-1);
			unlockCell(psf, shipCell1.yIndex+2, shipCell1.xIndex+1);
			unlockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex+1);
			unlockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex);
			unlockCell(psf, shipCell1.yIndex+3, shipCell1.xIndex-1);
		}
	
}
	
	private void unlockCell(PlaySetField psf, Integer y, Integer x) {
		if (null!=psf.fieldTreeMap.get((y*10)+x)){
			
			//everything but corners
			
			if ( 
					(((x<10) && (x>-1) && (y<10) && (y>-1) && (psf.placementTreeMap.get((y*10)+x).containsShip==0))) && (
						
						(
								
							(
								(((y<11) && (x<11) && (y>0) && (x>0))&&(psf.placementTreeMap.get(((y-1)*10)+x-1).containsShip==0)) &&
								(((y<11) && (x<10) && (y>0) && (x>-1))&&(psf.placementTreeMap.get(((y-1)*10)+x).containsShip==0)) &&
								(((y<11) && (x<9) && (y>0) && (x>-2))&&(psf.placementTreeMap.get(((y-1)*10)+x+1).containsShip==0)) &&
								(((y<10) && (x<11) && (y>-1) && (x>0))&&(psf.placementTreeMap.get((y*10)+x-1).containsShip==0)) &&
										
								(((y<10) && (x<9) && (y>-1) && (x>-2))&&(psf.placementTreeMap.get((y*10)+x+1).containsShip==0)) &&
								(((y<9) && (x<11) && (y>-2) && (x>0))&&(psf.placementTreeMap.get(((y+1)*10)+x-1).containsShip==0)) &&
								(((y<9) && (x<10) && (y>-2) && (x>-1))&&(psf.placementTreeMap.get(((y+1)*10)+x).containsShip==0)) &&
								(((y<9) && (x<9) && (y>-2) && (x>-2))&&(psf.placementTreeMap.get(((y+1)*10)+x+1).containsShip==0))  
						
							)
						)
					)
					
				){
				
				psf.fieldTreeMap.get((y*10)+x).isLocked=false;
				psf.fieldTreeMap.get((y*10)+x).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
				psf.fieldTreeMap.get((y*10)+x).setEnabledCell(true);
				
			} else if (
					//first
					
					(null!=psf.placementTreeMap.get(((y-1)*10)+x)) &&
						
							((x<10) && (x>-1) && (y<10) && (y>-1) && (psf.placementTreeMap.get((y*10)+x).containsShip==0))
							
							&&
							
							((y!=9)||((x==9)&&(y==9))) 
							
							&&
							
//							(
									(
											(null==psf.placementTreeMap.get(((y-1)*10)+x)) ||
											(
													(null!=psf.placementTreeMap.get(((y-1)*10)+x))&&
													((y==0)||(psf.placementTreeMap.get(((y-1)*10)+x).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get(((y-1)*10)+x+1)) ||
											(
													(null!=psf.placementTreeMap.get(((y-1)*10)+x+1))&&
													(((y==0)||(x==9))||(psf.placementTreeMap.get(((y-1)*10)+x+1).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get(((y-1)*10)+x-1)) ||
											(
													(null!=psf.placementTreeMap.get(((y-1)*10)+x-1))&&
													(((y==0)||(x==0))||(psf.placementTreeMap.get(((y-1)*10)+x-1).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get(((y+1)*10)+x)) ||
											(
													(null!=psf.placementTreeMap.get(((y+1)*10)+x))&&
													((y==9)||(psf.placementTreeMap.get(((y+1)*10)+x).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get(((y+1)*10)+x+1)) ||
											(
													(null!=psf.placementTreeMap.get(((y+1)*10)+x+1))&&
													(((y==9)||(x==9))||(psf.placementTreeMap.get(((y+1)*10)+x+1).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get(((y+1)*10)+x-1)) ||
											(
													(null!=psf.placementTreeMap.get(((y+1)*10)+x-1))&&
													(((y==9)||(x==0))||(psf.placementTreeMap.get(((y+1)*10)+x-1).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get((y*10)+x+1)) ||
											(
													(null!=psf.placementTreeMap.get((y*10)+x+1))&&
													((x==9)||(psf.placementTreeMap.get((y*10)+x+1).containsShip==0))
											)
									)
									&&
									(
											(null==psf.placementTreeMap.get((y*10)+x-1)) ||
											(
													(null!=psf.placementTreeMap.get((y*10)+x-1))&&
													((x==0)||(psf.placementTreeMap.get((y*10)+x-1).containsShip==0))
											)
									)
									 
//							)

							){
				
								psf.fieldTreeMap.get((y*10)+x).isLocked=false;
								psf.fieldTreeMap.get((y*10)+x).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
								psf.fieldTreeMap.get((y*10)+x).setEnabledCell(true);

			} else if (
					//second
					(null!=psf.placementTreeMap.get(((y+1)*10)+x)) &&
											
							((x<10) && (x>-1) && (y<10) && (y>-1) && (psf.placementTreeMap.get((y*10)+x).containsShip==0))
							
							&&
							
							((y!=0)||((x==0)&&(y==0)))
							
							&&
							
//							(
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x))&&
											((y==0)||(psf.placementTreeMap.get(((y-1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x+1))&&
											(((y==0)||(x==9))||(psf.placementTreeMap.get(((y-1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x-1))&&
											(((y==0)||(x==0))||(psf.placementTreeMap.get(((y-1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x))&&
											((y==9)||(psf.placementTreeMap.get(((y+1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x+1))&&
											(((y==9)||(x==9))||(psf.placementTreeMap.get(((y+1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x-1))&&
											(((y==9)||(x==0))||(psf.placementTreeMap.get(((y+1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x+1))&&
											((x==9)||(psf.placementTreeMap.get((y*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x-1))&&
											((x==0)||(psf.placementTreeMap.get((y*10)+x-1).containsShip==0))
									)
							)
									 
//							)

							){
				
								psf.fieldTreeMap.get((y*10)+x).isLocked=false;
								psf.fieldTreeMap.get((y*10)+x).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
								psf.fieldTreeMap.get((y*10)+x).setEnabledCell(true);

			} else if (
					//third
					(null!=psf.placementTreeMap.get((y*10)+x+1)) &&

							((x<10) && (x>-1) && (y<10) && (y>-1) && (psf.placementTreeMap.get((y*10)+x).containsShip==0))
							
							&&
							
							(x!=0) 
							
							&&
							
//							(
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x))&&
											((y==0)||(psf.placementTreeMap.get(((y-1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x+1))&&
											(((y==0)||(x==9))||(psf.placementTreeMap.get(((y-1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x-1))&&
											(((y==0)||(x==0))||(psf.placementTreeMap.get(((y-1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x))&&
											((y==9)||(psf.placementTreeMap.get(((y+1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x+1))&&
											(((y==9)||(x==9))||(psf.placementTreeMap.get(((y+1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x-1))&&
											(((y==9)||(x==0))||(psf.placementTreeMap.get(((y+1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x+1))&&
											((x==9)||(psf.placementTreeMap.get((y*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x-1))&&
											((x==0)||(psf.placementTreeMap.get((y*10)+x-1).containsShip==0))
									)
							)
									 
//							)

							){
				
								psf.fieldTreeMap.get((y*10)+x).isLocked=false;
								psf.fieldTreeMap.get((y*10)+x).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
								psf.fieldTreeMap.get((y*10)+x).setEnabledCell(true);


			} else if (
					//fourth
					(null!=psf.placementTreeMap.get((y*10)+x-1)) &&
											
							((x<10) && (x>-1) && (y<10) && (y>-1) && (psf.placementTreeMap.get((y*10)+x).containsShip==0))
							
							&&
							
							(x!=9) 
							
							&&
							
//							(
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x))&&
											((y==0)||(psf.placementTreeMap.get(((y-1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x+1))&&
											(((y==0)||(x==9))||(psf.placementTreeMap.get(((y-1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y-1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y-1)*10)+x-1))&&
											(((y==0)||(x==0))||(psf.placementTreeMap.get(((y-1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x))&&
											((y==9)||(psf.placementTreeMap.get(((y+1)*10)+x).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x+1))&&
											(((y==9)||(x==9))||(psf.placementTreeMap.get(((y+1)*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get(((y+1)*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get(((y+1)*10)+x-1))&&
											(((y==9)||(x==0))||(psf.placementTreeMap.get(((y+1)*10)+x-1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x+1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x+1))&&
											((x==9)||(psf.placementTreeMap.get((y*10)+x+1).containsShip==0))
									)
							)
							&&
							(
									(null==psf.placementTreeMap.get((y*10)+x-1)) ||
									(
											(null!=psf.placementTreeMap.get((y*10)+x-1))&&
											((x==0)||(psf.placementTreeMap.get((y*10)+x-1).containsShip==0))
									)
							)
									 
//							)

							){
				
								psf.fieldTreeMap.get((y*10)+x).isLocked=false;
								psf.fieldTreeMap.get((y*10)+x).setIcon(CellForPlaySetField.NOT_SELECTED_ICON);
								psf.fieldTreeMap.get((y*10)+x).setEnabledCell(true);


			}
			
		}
		
	}	

	public void placeTrippleShipForAI(FieldCreator fc, ShipCell sc1, ShipCell sc2, ShipCell sc3){
		
		setLockedPosition(fc.placementTreeMap.get(sc1.getTotalIndex()).getTotalIndex());
		position2=fc.placementTreeMap.get(sc2.getTotalIndex()).getTotalIndex();
		position3=fc.placementTreeMap.get(sc3.getTotalIndex()).getTotalIndex();
		if ((lockedPosition==position2+10)||(lockedPosition==position2-10)){
			isHorizontalAlign=false;
		} else {
			isHorizontalAlign=true;
		}
		
		shipCell1 = sc1;
		shipCell1.isLocked=false;
		shipCell1.containsShip=3;
		shipCell2 = sc2;
		shipCell2.isLocked=false;
		shipCell2.containsShip=3;
		shipCell3 = sc3;
		shipCell3.isLocked=false;
		shipCell3.containsShip=3;
		
		if (isHorizontalAlign==true){
			shipCell1.setIconCell(SHIP_TRIPPLE_1);
			shipCell1.setDisabledIcon(SHIP_TRIPPLE_1);
			shipCell2.setIconCell(SHIP_TRIPPLE_2);
			shipCell2.setDisabledIcon(SHIP_TRIPPLE_2);
			shipCell3.setIconCell(SHIP_TRIPPLE_3);
			shipCell3.setDisabledIcon(SHIP_TRIPPLE_3);
			
		} else {
			shipCell1.setIconCell(SHIP_TRIPPLE_1_ROTATED);
			shipCell1.setDisabledIcon(SHIP_TRIPPLE_1_ROTATED);
			shipCell2.setIconCell(SHIP_TRIPPLE_2_ROTATED);
			shipCell2.setDisabledIcon(SHIP_TRIPPLE_2_ROTATED);
			shipCell3.setIconCell(SHIP_TRIPPLE_3_ROTATED);
			shipCell3.setDisabledIcon(SHIP_TRIPPLE_3_ROTATED);
		}
		
		shipCell1.setVisible(true);
		shipCell1.isLocked=true;
		shipCell2.setVisible(true);
		shipCell2.isLocked=true;
		shipCell3.setVisible(true);
		shipCell3.isLocked=true;
		
		fc.placementTreeMap.get(sc1.getTotalIndex()).isLocked=true;
		fc.placementTreeMap.get(sc2.getTotalIndex()).isLocked=true;
		fc.placementTreeMap.get(sc3.getTotalIndex()).isLocked=true;
		
		//Block cells around it
		lockCellForAI(fc, shipCell1.yIndex, shipCell1.xIndex-1);
		lockCellForAI(fc, shipCell1.yIndex-1, shipCell1.xIndex);
		lockCellForAI(fc, shipCell1.yIndex+1, shipCell1.xIndex+1);
		lockCellForAI(fc, shipCell1.yIndex+1, shipCell1.xIndex-1);
		lockCellForAI(fc, shipCell1.yIndex-1, shipCell1.xIndex+1);
		lockCellForAI(fc, shipCell1.yIndex-1, shipCell1.xIndex-1);
		if(isHorizontalAlign==true){
			lockCellForAI(fc, shipCell1.yIndex+1, shipCell1.xIndex);
			lockCellForAI(fc, shipCell1.yIndex-1, shipCell1.xIndex+2);
			lockCellForAI(fc, shipCell1.yIndex-1, shipCell1.xIndex+3);
			lockCellForAI(fc, shipCell1.yIndex+1, shipCell1.xIndex+2);
			lockCellForAI(fc, shipCell1.yIndex+1, shipCell1.xIndex+3);
			lockCellForAI(fc, shipCell1.yIndex, shipCell1.xIndex+3);
		} else {
			lockCellForAI(fc, shipCell1.yIndex, shipCell1.xIndex+1);
			lockCellForAI(fc, shipCell1.yIndex+2, shipCell1.xIndex-1);
			lockCellForAI(fc, shipCell1.yIndex+2, shipCell1.xIndex+1);
			lockCellForAI(fc, shipCell1.yIndex+3, shipCell1.xIndex+1);
			lockCellForAI(fc, shipCell1.yIndex+3, shipCell1.xIndex);
			lockCellForAI(fc, shipCell1.yIndex+3, shipCell1.xIndex-1);
		}
				
	}
	
	private void lockCellForAI(FieldCreator fc, Integer y, Integer x) {
		if (null!=fc.placementTreeMap.get((y*10)+x)){
					
			if ((x<10) && (x>-1) && (y<10) && (y>-1)) {
				fc.placementTreeMap.get((y*10)+x).isLocked=true;
			}
					
		}
				
	}
	
}
