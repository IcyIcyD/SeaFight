package Ships;

import AI.FieldCreator;
import Domain.AbstractShip;
import PrepareLogic.CellForPlaySetField;
import PrepareLogic.PlaySetField;
import PrepareLogic.ShipCell;

public class SoloShip extends AbstractShip {
	
	public ShipCell shipCell;


	public void placeSoloShip(PlaySetField psf, Integer position){
		
		setLockedPosition(position);
		shipCell = psf.placementTreeMap.get(position);
		psf.fieldTreeMap.get(shipCell.getTotalIndex()).isLocked=true;
		shipCell.containsShip=1;
		shipCell.isLocked=false;
		shipCell.setIconCell(SHIP_SOLO);
		shipCell.setDisabledIcon(SHIP_SOLO);
		shipCell.setVisible(true);
		shipCell.setEnabledCell(true);
		psf.switchCard(position);
		
		// Block cells around it
		lockCell(psf, shipCell.yIndex, shipCell.xIndex+1);
		lockCell(psf, shipCell.yIndex, shipCell.xIndex-1);
		lockCell(psf, shipCell.yIndex+1, shipCell.xIndex);
		lockCell(psf, shipCell.yIndex-1, shipCell.xIndex);
		lockCell(psf, shipCell.yIndex+1, shipCell.xIndex+1);
		lockCell(psf, shipCell.yIndex+1, shipCell.xIndex-1);
		lockCell(psf, shipCell.yIndex-1, shipCell.xIndex+1);
		lockCell(psf, shipCell.yIndex-1, shipCell.xIndex-1);
		
	}
	
	private void lockCell(PlaySetField psf, Integer y, Integer x) {
		if (null!=psf.fieldTreeMap.get((y*10)+x)){
			
			if ((x<10) && (x>-1) && (y<10) && (y>-1)){
				psf.fieldTreeMap.get((y*10)+x).setEnabledCell(false);
				psf.fieldTreeMap.get((y*10)+x).isLocked=true;
			}
			
		}
		
	}

	public void removeSoloShip(PlaySetField psf) {
		
		
		shipCell = psf.placementTreeMap.get(getLockedPosition());
		psf.fieldTreeMap.get(shipCell.getTotalIndex()).isLocked=false;
		shipCell.containsShip=0;
		shipCell.setIconCell(ShipCell.DEFAULT);
		shipCell.setDisabledIcon(ShipCell.DISABLED);
		shipCell.setVisible(false);
		shipCell.setEnabledCell(false);
		shipCell.isLocked=true;
		psf.switchCard(getLockedPosition());
		
		// Unlock cells around it
		unlockCell(psf, shipCell.yIndex, shipCell.xIndex+1);
		unlockCell(psf, shipCell.yIndex, shipCell.xIndex-1);
		unlockCell(psf, shipCell.yIndex+1, shipCell.xIndex);
		unlockCell(psf, shipCell.yIndex-1, shipCell.xIndex);
		unlockCell(psf, shipCell.yIndex+1, shipCell.xIndex+1);
		unlockCell(psf, shipCell.yIndex+1, shipCell.xIndex-1);
		unlockCell(psf, shipCell.yIndex-1, shipCell.xIndex+1);
		unlockCell(psf, shipCell.yIndex-1, shipCell.xIndex-1);
		
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

	private void lockCellForAI(FieldCreator fc, Integer y, Integer x) {
		if (null!=fc.placementTreeMap.get((y*10)+x)){
					
			if ((x<10) && (x>-1) && (y<10) && (y>-1)) {
				fc.placementTreeMap.get((y*10)+x).isLocked=true;
			}
					
		}
		
	}
	
	public void placeSoloShipForAI(FieldCreator fc, ShipCell sc1){
		
		setLockedPosition(fc.placementTreeMap.get(sc1.getTotalIndex()).getTotalIndex());
		shipCell = sc1;
		shipCell.containsShip=1;
		shipCell.isLocked=false;
		shipCell.setIconCell(SHIP_SOLO);
		shipCell.setDisabledIcon(SHIP_SOLO);
		shipCell.setVisible(true);
		shipCell.setEnabledCell(true);
		shipCell.isLocked=true;
		fc.placementTreeMap.get(sc1.getTotalIndex()).isLocked=true;

		
		// Block cells around it
		lockCellForAI(fc, shipCell.yIndex, shipCell.xIndex+1);
		lockCellForAI(fc, shipCell.yIndex, shipCell.xIndex-1);
		lockCellForAI(fc, shipCell.yIndex+1, shipCell.xIndex);
		lockCellForAI(fc, shipCell.yIndex-1, shipCell.xIndex);
		lockCellForAI(fc, shipCell.yIndex+1, shipCell.xIndex+1);
		lockCellForAI(fc, shipCell.yIndex+1, shipCell.xIndex-1);
		lockCellForAI(fc, shipCell.yIndex-1, shipCell.xIndex+1);
		lockCellForAI(fc, shipCell.yIndex-1, shipCell.xIndex-1);
		
	}
}
