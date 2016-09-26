package AI;

import java.util.Random;
import java.util.TreeMap;

import Domain.ShipCountHandler;
import PrepareLogic.ShipCell;
import Ships.DoubleShip;
import Ships.QuadroShip;
import Ships.SoloShip;
import Ships.TrippleShip;

public class FieldCreator {
	
	private Integer mapKey;
	public TreeMap<Integer, ShipCell> placementTreeMap;
	public TreeMap<Integer, Boolean> possibleAligment;
	private Boolean canPlace=false;
	private ShipCell cell1;
	private ShipCell cell2;
	private ShipCell cell3;
	private ShipCell cell4;
	public ShipCountHandler sch;
	private Integer k=0;
	
		 /*0 - x+?
		 * 1 - x-?
		 * 2 - y+?
		 * 3 - y-?
		 */
	
	
	public FieldCreator () {
		
		possibleAligment = new TreeMap<Integer, Boolean>();
		possibleAligment.put(0, false);
		possibleAligment.put(1, false);
		possibleAligment.put(2, false);
		possibleAligment.put(3, false);
		placementTreeMap = new TreeMap<Integer, ShipCell>();
		for (Integer y=0; y<10; y++){
			for (Integer x=0; x<10; x++){
				
				final ShipCell shipCell = new ShipCell();
				
				shipCell.setxIndex(x);
				shipCell.setyIndex(y);
				
				mapKey=(y*10+x);
				placementTreeMap.put(mapKey, shipCell);
				shipCell.setTotalIndex(mapKey);
				
			}
			
		}
		sch = new ShipCountHandler();
		sch.setSoloShipCount(4);
		sch.setDoubleShipCount(3);
		sch.setTrippleShipCount(2);
		sch.setQuadroShipCount(1);
		
		Random r1=new Random();
		
		while(canPlace==false){
			k=r1.nextInt(100);
			manageShipPlacementForAI(k, 3);		
		}
		QuadroShip q = new QuadroShip();
		q.placeQuadroShipForAI(this, cell1, cell2, cell3, cell4);
		sch.quadroShipHandler.put(0, q);
		canPlace=false;
		cell1=null;
		cell2=null;
		cell3=null;
		cell4=null;
		
		for (Integer i=0; i<2; i++){
			while(canPlace==false){
				k=r1.nextInt(100);
				manageShipPlacementForAI(k, 2);		
			}
			TrippleShip t = new TrippleShip();
			t.placeTrippleShipForAI(this, cell1, cell2, cell3);
			sch.trippleShipHandler.put(i, t);
			canPlace=false;
			cell1=null;
			cell2=null;
			cell3=null;
			cell4=null;
		}
		
		
		for (Integer i=0; i<3; i++){
			while(canPlace==false){
				k=r1.nextInt(100);
				manageShipPlacementForAI(k, 1);		
			}
			DoubleShip d = new DoubleShip();
			d.placeDoubleShipForAI(this, cell1, cell2);
			sch.doubleShipHandler.put(i, d);
			canPlace=false;
			cell1=null;
			cell2=null;
			cell3=null;
			cell4=null;
		}
		
		for (Integer i=0; i<4; i++){
			while(canPlace==false){
				k=r1.nextInt(100);
				manageShipPlacementForAI(k, 0);		
			}
			SoloShip s = new SoloShip();
			s.placeSoloShipForAI(this, cell1);
			sch.soloShipHandler.put(i, s);
			canPlace=false;
			cell1=null;
			cell2=null;
			cell3=null;
			cell4=null;
		}
		
		
	}
	
	private void manageShipPlacementForAI(Integer position, Integer gap){
		Integer x=position%10;
		Integer y=position/10;
		
			if(placementTreeMap.get(position).isLocked==true){
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
					if(null!=placementTreeMap.get(position+i)){
						if(placementTreeMap.get(position+i).isLocked==true){
							possibleAligment.replace(0, false);
							break;
						}
					}
				}
			}
			
			if(possibleAligment.get(1)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=placementTreeMap.get(position-i)){
						if(placementTreeMap.get(position-i).isLocked==true){
							possibleAligment.replace(1, false);
							break;
						}
					}
				}
			}
			
			if(possibleAligment.get(2)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=placementTreeMap.get(position+(i*10))){
						if(placementTreeMap.get(position+(i*10)).isLocked==true){
							possibleAligment.replace(2, false);
							break;
						}
					}
					
				}
			}
			
			if(possibleAligment.get(3)==true){
				for (Integer i=1; i<=gap; i++){
					if(null!=placementTreeMap.get(position-(i*10))){
						if(placementTreeMap.get(position-(i*10)).isLocked==true){
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
					cell1=placementTreeMap.get(position);
					if (placementTreeMap.get(position+1)!=null){
						cell2=placementTreeMap.get(position+1);
					}
					if (placementTreeMap.get(position+2)!=null){
						cell3=placementTreeMap.get(position+2);
					}
					if (placementTreeMap.get(position+3)!=null){
						cell4=placementTreeMap.get(position+3);
					}
					canPlace=true;
					return;
				}
				if(rValue==1){
					if (placementTreeMap.get(position-gap)!=null){
						cell1=placementTreeMap.get(position-gap);
					}
					if (placementTreeMap.get(position-gap+1)!=null){
						cell2=placementTreeMap.get(position-gap+1);
					}
					if (placementTreeMap.get(position-gap+2)!=null){
						cell3=placementTreeMap.get(position-gap+2);
					}
					if (placementTreeMap.get(position-gap+3)!=null){
						cell4=placementTreeMap.get(position-gap+3);
					}
					canPlace=true;
					return;
				}
				if(rValue==2){
					cell1=placementTreeMap.get(position);
					if (placementTreeMap.get(position+10)!=null){
						cell2=placementTreeMap.get(position+10);
					}
					if (placementTreeMap.get(position+20)!=null){
						cell3=placementTreeMap.get(position+20);
					}
					if (placementTreeMap.get(position+30)!=null){
						cell4=placementTreeMap.get(position+30);
					}
					canPlace=true;
					return;
				}
				if(rValue==3){
					if (placementTreeMap.get(position-(gap*10))!=null){
						cell1=placementTreeMap.get(position-(gap*10));
					}
					if (placementTreeMap.get(position-(gap*10)+10)!=null){
						cell2=placementTreeMap.get(position-(gap*10)+10);
					}
					if (placementTreeMap.get(position-(gap*10)+20)!=null){
						cell3=placementTreeMap.get(position-(gap*10)+20);
					}
					if (placementTreeMap.get(position-(gap*10)+30)!=null){
						cell4=placementTreeMap.get(position-(gap*10)+30);
					}
					canPlace=true;
					return;
				}
			}
		
	}
	
	public void updatePlacement(Integer position, ShipCell cell) {
		placementTreeMap.replace(position, cell);
	}

}
