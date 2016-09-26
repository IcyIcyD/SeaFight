package ConnectLogic;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.TreeMap;

import javax.swing.JPanel;

import AI.FieldCreator;
import Domain.ShipCountHandler;
import PrepareLogic.PrepareWindow;
import PrepareLogic.ShipCell;

public class Side extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public TreeMap<Integer, ShipCell> playPlacementTreeMap;
	public TreeMap<Integer, CardLayout> sideCardTreeMap;
	public TreeMap<Integer, JPanel> oneCellPanelTreeMap;
	public ShipCountHandler sch;
	public JPanel onePlayCellPanel;
	private CardLayout card;
	private Integer mapKey;
	private ShipCell shipCell;

	public Side(PrepareWindow pw){
		
		setLayout(new GridLayout(10, 10));
		setSize(500,500);
		setPreferredSize(new Dimension(500, 500));
		
		sch = pw.shipCountHandler;
		playPlacementTreeMap = new TreeMap<Integer, ShipCell>();
		sideCardTreeMap = new TreeMap<Integer, CardLayout>();
		oneCellPanelTreeMap = new TreeMap<Integer, JPanel>();
		
		for (Integer y=0; y<10; y++){
			for (Integer x=0; x<10; x++){
				
				mapKey=((y*10)+x);
				
				shipCell=pw.psf.placementTreeMap.get(mapKey);
				if (shipCell.containsShip == 0){
					shipCell.setDisabledIcon(ShipCell.DEFAULT);
				}
				shipCell.setEnabled(false);
				onePlayCellPanel = new JPanel();
				
				onePlayCellPanel.setSize(50,50);
				onePlayCellPanel.setPreferredSize(new Dimension(50,50));
				card = new CardLayout(2,2);
				onePlayCellPanel.setLayout(card);
				shipCell.setxIndex(x);
				shipCell.setyIndex(y);
				
				playPlacementTreeMap.put(mapKey, shipCell);
				oneCellPanelTreeMap.put(mapKey, onePlayCellPanel);
				sideCardTreeMap.put(mapKey, card);
				shipCell.setTotalIndex(mapKey);
				
				onePlayCellPanel.add(shipCell, 0);
				add(onePlayCellPanel);
				card.first(onePlayCellPanel);
				
			}
			
		}
		
	}
	
	public Side(FieldCreator fc){
		
		setLayout(new GridLayout(10, 10));
		setSize(500,500);
		setPreferredSize(new Dimension(500, 500));
		
		sch = fc.sch;
		playPlacementTreeMap = new TreeMap<Integer, ShipCell>();
		sideCardTreeMap = new TreeMap<Integer, CardLayout>();
		oneCellPanelTreeMap = new TreeMap<Integer, JPanel>();
		
		for (Integer y=0; y<10; y++){
			for (Integer x=0; x<10; x++){
				
				mapKey=((y*10)+x);
				
				shipCell=fc.placementTreeMap.get(mapKey);
				shipCell.isLocked=false;
				
				if (shipCell.containsShip == 0){
					shipCell.setDisabledIcon(ShipCell.DEFAULT);
				}
				
				shipCell.setEnabled(false);
				onePlayCellPanel = new JPanel();
				
				onePlayCellPanel.setSize(50,50);
				onePlayCellPanel.setPreferredSize(new Dimension(50,50));
				card = new CardLayout(2,2);
				onePlayCellPanel.setLayout(card);
				shipCell.setxIndex(x);
				shipCell.setyIndex(y);
				
				playPlacementTreeMap.put(mapKey, shipCell);
				oneCellPanelTreeMap.put(mapKey, onePlayCellPanel);
				sideCardTreeMap.put(mapKey, card);
				shipCell.setTotalIndex(mapKey);
				
				onePlayCellPanel.add(shipCell, 0);
				add(onePlayCellPanel);
				card.first(onePlayCellPanel);
				
			}
			
		}
		
	}
	
	public void switchCard(Integer position){
		sideCardTreeMap.get(position).next(oneCellPanelTreeMap.get(position));
	}

}
