package ConnectLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Domain.AbstractCell;
import Domain.ShipCountHandler;
import PlayLogic.GameLog;
import PlayLogic.PlayWindow;
import PlayLogic.ShipsInPlayInfoLabel;
import PlayLogic.TurnHandler;
import PrepareLogic.ShipCell;

public class PlayCell extends AbstractCell {

	private static final long serialVersionUID = 1L;
	private ShipCountHandler shipCountHandler;
	private ShipsInPlayInfoLabel shipsInPlayInfoLabel;
	private TreeMap<Integer, PlayCell> playField;
	private String player;
	private JDialog dialog;
	
	public PlayCell(final Side side, final PlayWindow plw){
		
		setSize(50, 50);
		setPreferredSize(new Dimension(50, 50));
		isSelected = false;
		isLocked=false;
		setVisible(true);
		
		if(Connector.gameMode==1){
			TurnHandler.possibleMap.put(0, this);
		}
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				action(side, plw);	
				

			}
		});
		setVisible(true);
	}
	
	public void action(Side side, PlayWindow plw){
		
		
		
		setVisible(false);
		isLocked=true;
		
		if(TurnHandler.whosTurn==1){
			shipCountHandler=plw.schYours;
			shipsInPlayInfoLabel=plw.yourShipsInfoLabel;
			playField=plw.playFieldTreeMapYours;
			player="Red";
		} else {
			shipCountHandler=plw.schEnemys;
			shipsInPlayInfoLabel=plw.enemyShipsInfoLabel;
			playField=plw.playFieldTreeMapEnemys;
			player="Green";
		}
		
		side.playPlacementTreeMap.get(totalIndex).setVisible(true);
		if (side.playPlacementTreeMap.get(totalIndex).containsShip==0){
			side.playPlacementTreeMap.get(totalIndex).setDisabledIcon(ShipCell.MISS);
			side.playPlacementTreeMap.get(totalIndex).setBorder(new LineBorder(Color.GREEN));
			side.playPlacementTreeMap.get(totalIndex).isLocked=true;
			TurnHandler.shipGotHit=false;
			
			synchronized (GameLog.logSync){
				
				plw.gameLog.logInfo(player + " player missed.");
				GameLog.logSync.notify();
			}
			
			
		} else {
			side.playPlacementTreeMap.get(totalIndex).setDisabledIcon(ShipCell.HIT);
			side.playPlacementTreeMap.get(totalIndex).setBorder(new LineBorder(Color.RED, 2));
			side.playPlacementTreeMap.get(totalIndex).shipGotHit=true;
			TurnHandler.shipGotHit=true;
			synchronized (GameLog.logSync){
				plw.gameLog.logInfo(player + " player just hit opponent's ship!");
				GameLog.logSync.notify();
			}
			if((Connector.gameMode==1) && (TurnHandler.whosTurn==1)){
				TurnHandler.howManyCellsGotHit+=1;
			}
			switch (side.playPlacementTreeMap.get(totalIndex).containsShip){
			
			case 1:
				
				for (Integer key = 0; key<4; key++){
					if(shipCountHandler.soloShipHandler.get(key).lockedPosition==totalIndex){
						
						shipCountHandler.setSoloShipCount(shipCountHandler.getSoloShipCount()-1);
						
						shipsInPlayInfoLabel.updateInfo(shipCountHandler);
						
						blockCellsAround(side, totalIndex, plw);
						synchronized (GameLog.logSync){
							plw.gameLog.logInfo(player + " player has DESTROYED opponent's boat!");
							GameLog.logSync.notify();
						}
						if((Connector.gameMode==1) && (TurnHandler.whosTurn==1)){
							TurnHandler.shipDestroyed=true;
							TurnHandler.howManyCellsGotHit-=1;
						}
						break;
					}
				}
				
				break;
				
			case 2:
				
				for (Integer key = 0; key<3; key++){
					if((shipCountHandler.doubleShipHandler.get(key).lockedPosition==totalIndex) || (shipCountHandler.doubleShipHandler.get(key).position2==totalIndex)){
						
						if((shipCountHandler.doubleShipHandler.get(key).shipCell1.shipGotHit==true) && (shipCountHandler.doubleShipHandler.get(key).shipCell2.shipGotHit==true)){
							shipCountHandler.setDoubleShipCount(shipCountHandler.getDoubleShipCount()-1);
							
							shipsInPlayInfoLabel.updateInfo(shipCountHandler);
							
							blockCellsAround(side, shipCountHandler.doubleShipHandler.get(key).lockedPosition, plw);
							blockCellsAround(side, shipCountHandler.doubleShipHandler.get(key).position2, plw);
							synchronized (GameLog.logSync){
								plw.gameLog.logInfo(player + " player has DESTROYED opponent's frigate! Well done!");
								GameLog.logSync.notify();
							}
							if((Connector.gameMode==1) && (TurnHandler.whosTurn==1)){
								TurnHandler.shipDestroyed=true;
								TurnHandler.howManyCellsGotHit-=2;
							}
							break;
						}
					}
				}
				
				break;
				
			case 3:
				
				for (Integer key = 0; key<2; key++){
					if((shipCountHandler.trippleShipHandler.get(key).lockedPosition==totalIndex) || (shipCountHandler.trippleShipHandler.get(key).position2==totalIndex) || (shipCountHandler.trippleShipHandler.get(key).position3==totalIndex)){
						
						if((shipCountHandler.trippleShipHandler.get(key).shipCell1.shipGotHit==true) && (shipCountHandler.trippleShipHandler.get(key).shipCell2.shipGotHit==true) && (shipCountHandler.trippleShipHandler.get(key).shipCell3.shipGotHit==true)){
							shipCountHandler.setTrippleShipCount(shipCountHandler.getTrippleShipCount()-1);
							
							shipsInPlayInfoLabel.updateInfo(shipCountHandler);
							
							blockCellsAround(side, shipCountHandler.trippleShipHandler.get(key).lockedPosition, plw);
							blockCellsAround(side, shipCountHandler.trippleShipHandler.get(key).position2, plw);
							blockCellsAround(side, shipCountHandler.trippleShipHandler.get(key).position3, plw);
							synchronized (GameLog.logSync){
								plw.gameLog.logInfo(player + " player has DESTROYED opponent's CRUISER!");
								GameLog.logSync.notify();
							}
							if((Connector.gameMode==1) && (TurnHandler.whosTurn==1)){
								TurnHandler.shipDestroyed=true;
								TurnHandler.howManyCellsGotHit-=3;
							}
							break;
						}
					}
				}
				
				break;
				
			case 4:
				
				for (Integer key = 0; key<1; key++){
					if((shipCountHandler.quadroShipHandler.get(key).lockedPosition==totalIndex) || (shipCountHandler.quadroShipHandler.get(key).position2==totalIndex) || (shipCountHandler.quadroShipHandler.get(key).position3==totalIndex) || (shipCountHandler.quadroShipHandler.get(key).position4==totalIndex)){
						
						if((shipCountHandler.quadroShipHandler.get(key).shipCell1.shipGotHit==true) && (shipCountHandler.quadroShipHandler.get(key).shipCell2.shipGotHit==true) && (shipCountHandler.quadroShipHandler.get(key).shipCell3.shipGotHit==true) && (shipCountHandler.quadroShipHandler.get(key).shipCell4.shipGotHit==true)){
							shipCountHandler.setQuadroShipCount(shipCountHandler.getQuadroShipCount()-1);
							
							shipsInPlayInfoLabel.updateInfo(shipCountHandler);
							
							blockCellsAround(side, shipCountHandler.quadroShipHandler.get(key).lockedPosition, plw);
							blockCellsAround(side, shipCountHandler.quadroShipHandler.get(key).position2, plw);
							blockCellsAround(side, shipCountHandler.quadroShipHandler.get(key).position3, plw);
							blockCellsAround(side, shipCountHandler.quadroShipHandler.get(key).position4, plw);
							synchronized (GameLog.logSync){
								plw.gameLog.logInfo("OMG! "+player + " player has DESTROYED opponent's CARRIER!!!");
								GameLog.logSync.notify();
							}
							if((Connector.gameMode==1) && (TurnHandler.whosTurn==1)){
								TurnHandler.shipDestroyed=true;
								TurnHandler.howManyCellsGotHit-=4;
							}
							break;
						}
					}
				}
				
				break;
				
			}
			if((shipCountHandler.getSoloShipCount()==0) && (shipCountHandler.getDoubleShipCount()==0) && (shipCountHandler.getTrippleShipCount()==0) && (shipCountHandler.getQuadroShipCount()==0)){
				dialog = new JDialog();
				dialog.setAlwaysOnTop(true);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dialog.setResizable(false);
				dialog.setSize(300,100);
				dialog.setPreferredSize(new Dimension(300,100));
				dialog.setTitle("Game over!");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setLayout(new GridLayout(2,2, 25, 25));
				JLabel textPanel = new JLabel();
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
				

				dialog.getContentPane().add(textPanel);
				dialog.getContentPane().add(okButton);
				dialog.pack();
				JPanel showTurnPanel = new JPanel();
				JLabel showTurnLabel = new JLabel();
				showTurnPanel.setLayout(null);
				showTurnPanel.setSize(180, 100);
				showTurnPanel.setPreferredSize(new Dimension(180,100));
				showTurnPanel.setVisible(true);
				showTurnLabel.setSize(180, 100);
				showTurnLabel.setPreferredSize(new Dimension(180,100));
				showTurnLabel.setVisible(true);
				showTurnLabel.setLocation(0,0);
				showTurnPanel.add(showTurnLabel);
				showTurnPanel.setLocation(440, 140);
				plw.add(showTurnPanel);
				okButton.setVisible(true);
				textPanel.setText(player+" player has won the game!");
				textPanel.setVisible(true);
				dialog.setVisible(true);
				TurnHandler.winCondition=true;
			}
			
		}
		
		
		
		botOptimisation(plw, getTotalIndex());
		
		TurnHandler.gotHit=true;
		TurnHandler.whatGotHit=getTotalIndex();
		
		synchronized (TurnHandler.o){
			TurnHandler.o.notify();
		}
		
		
	}
	
	public void blockCellsAround(Side side, Integer index, PlayWindow plw){
		side.playPlacementTreeMap.get(index).setDisabledIcon(ShipCell.DESTROYED);
		
		side.playPlacementTreeMap.get(index).setBorder(new LineBorder(Color.ORANGE, 2));
		side.playPlacementTreeMap.get(index).isLocked=true;
		
		if(side.playPlacementTreeMap.get(index).getxIndex()!=0){
			if(side.playPlacementTreeMap.get((index-1)).containsShip==0){
				side.playPlacementTreeMap.get((index-1)).setDisabledIcon(ShipCell.MISS);
				side.playPlacementTreeMap.get((index-1)).setBorder(new LineBorder(Color.GREEN));
				side.playPlacementTreeMap.get((index-1)).isLocked=true;
				
				playField.get((index-1)).setVisible(false);
				playField.get((index-1)).isLocked=true;
				botOptimisation(plw, index-1);
			}
		}
		
		if(side.playPlacementTreeMap.get(index).getxIndex()!=9){
			if(side.playPlacementTreeMap.get((index+1)).containsShip==0){
				side.playPlacementTreeMap.get((index+1)).setDisabledIcon(ShipCell.MISS);
				side.playPlacementTreeMap.get((index+1)).setBorder(new LineBorder(Color.GREEN));
				side.playPlacementTreeMap.get((index+1)).isLocked=true;
				
				playField.get((index+1)).setVisible(false);
				playField.get((index+1)).isLocked=true;
				botOptimisation(plw, index+1);
			}
		}
		
		if(side.playPlacementTreeMap.get(index).getyIndex()!=0){
			if(side.playPlacementTreeMap.get((index-10)).containsShip==0){
				side.playPlacementTreeMap.get((index-10)).setDisabledIcon(ShipCell.MISS);
				side.playPlacementTreeMap.get((index-10)).setBorder(new LineBorder(Color.GREEN));
				side.playPlacementTreeMap.get((index-10)).isLocked=true;
				
				playField.get((index-10)).setVisible(false);
				playField.get((index-10)).isLocked=true;
				botOptimisation(plw, index-10);
			}
		}
		
		if(side.playPlacementTreeMap.get(index).getyIndex()!=9){
			if(side.playPlacementTreeMap.get((index+10)).containsShip==0){
				side.playPlacementTreeMap.get((index+10)).setDisabledIcon(ShipCell.MISS);
				side.playPlacementTreeMap.get((index+10)).setBorder(new LineBorder(Color.GREEN));
				side.playPlacementTreeMap.get((index+10)).isLocked=true;
				
				playField.get((index+10)).setVisible(false);
				playField.get((index+10)).isLocked=true;
				botOptimisation(plw, index+10);
			}
		}
		
		if((side.playPlacementTreeMap.get(index).getxIndex()!=0) && (side.playPlacementTreeMap.get(index).getyIndex()!=0)){
			side.playPlacementTreeMap.get((index-11)).setDisabledIcon(ShipCell.MISS);
			side.playPlacementTreeMap.get((index-11)).setBorder(new LineBorder(Color.GREEN));
			side.playPlacementTreeMap.get((index-11)).isLocked=true;
			
			playField.get((index-11)).setVisible(false);
			playField.get((index-11)).isLocked=true;
			botOptimisation(plw, index-11);
		}
		
		if((side.playPlacementTreeMap.get(index).getxIndex()!=0) && (side.playPlacementTreeMap.get(index).getyIndex()!=9)){
			side.playPlacementTreeMap.get((index+9)).setDisabledIcon(ShipCell.MISS);
			side.playPlacementTreeMap.get((index+9)).setBorder(new LineBorder(Color.GREEN));
			side.playPlacementTreeMap.get((index+9)).isLocked=true;
			
			playField.get((index+9)).setVisible(false);
			playField.get((index+9)).isLocked=true;
			botOptimisation(plw, index+9);
		}
		
		if((side.playPlacementTreeMap.get(index).getxIndex()!=9) && (side.playPlacementTreeMap.get(index).getyIndex()!=0)){
			side.playPlacementTreeMap.get((index-9)).setDisabledIcon(ShipCell.MISS);
			side.playPlacementTreeMap.get((index-9)).setBorder(new LineBorder(Color.GREEN));
			side.playPlacementTreeMap.get((index-9)).isLocked=true;
			
			playField.get((index-9)).setVisible(false);
			playField.get((index-9)).isLocked=true;
			botOptimisation(plw, index-9);
		}
		
		if((side.playPlacementTreeMap.get(index).getxIndex()!=9) && (side.playPlacementTreeMap.get(index).getyIndex()!=9)){
			side.playPlacementTreeMap.get((index+11)).setDisabledIcon(ShipCell.MISS);
			side.playPlacementTreeMap.get((index+11)).setBorder(new LineBorder(Color.GREEN));
			side.playPlacementTreeMap.get((index+11)).isLocked=true;
			
			playField.get((index+11)).setVisible(false);
			playField.get((index+11)).isLocked=true;
			botOptimisation(plw, index+11);
		}
	}
	
	public void botOptimisation(PlayWindow plw, Integer index){
		if((Connector.gameMode==1) && (TurnHandler.whosTurn==1) && (null!=TurnHandler.possibleMap.get(index))){
			TurnHandler.possibleMap.remove(index, plw.playFieldTreeMapYours.get(index));
		}
	}
}
