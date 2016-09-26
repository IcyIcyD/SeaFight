package PrepareLogic;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JPanel;


public class PlaySetField extends JPanel {

	private static final long serialVersionUID = 1L;
	

	private Integer mapKey;
	final PlaySetField playsetfield = this;
	public TreeMap<Integer, CellForPlaySetField> fieldTreeMap;
	public TreeSet<Integer> isSelectedList;
	public TreeSet<Integer> isSelectedShipList;
	public TreeMap<Integer, ShipCell> placementTreeMap;
	public TreeMap<Integer, CardLayout> cardTreeMap;
	public TreeMap<Integer, JPanel> oneCellPanellTreeMap;

	private CardLayout card;


	private JPanel oneCellPanel;



	
	PlaySetField (PrepareWindowInfoPanel panel, final PrepareWindow prepareWindow) {
		
		setLayout(new GridLayout(10, 10));
		setSize(500,500);
		setPreferredSize(new Dimension(500, 500));
		fieldTreeMap = new TreeMap<Integer, CellForPlaySetField>();
		placementTreeMap = new TreeMap<Integer, ShipCell>();
		cardTreeMap = new TreeMap<Integer, CardLayout>();
		isSelectedList = new TreeSet<Integer>();
		isSelectedShipList = new TreeSet<Integer>();
		oneCellPanellTreeMap = new TreeMap<Integer, JPanel>();
		for (Integer y=0; y<10; y++){
			for (Integer x=0; x<10; x++){
				
				final CellForPlaySetField cell = new CellForPlaySetField(fieldTreeMap, prepareWindow, isSelectedList);
				final ShipCell shipCell = new ShipCell(this, prepareWindow, isSelectedShipList);
				oneCellPanel = new JPanel();
				
				oneCellPanel.setSize(50,50);
				oneCellPanel.setPreferredSize(new Dimension(50,50));
				card = new CardLayout(2,2);
				oneCellPanel.setLayout(card);
				cell.setxIndex(x);
				cell.setyIndex(y);
				shipCell.setxIndex(x);
				shipCell.setyIndex(y);
				
				mapKey=(cell.getyIndex()*10+cell.getxIndex());
				fieldTreeMap.put(mapKey,cell);
				placementTreeMap.put(mapKey, shipCell);
				oneCellPanellTreeMap.put(mapKey, oneCellPanel);
				cardTreeMap.put(mapKey, card);
				cell.setTotalIndex(mapKey);
				shipCell.setTotalIndex(mapKey);
				
				oneCellPanel.add(cell, 0);
				oneCellPanel.add(shipCell, 1);
				add(oneCellPanel);
				card.first(oneCellPanel);
				
			}
			
		}
		
	}
	
	public void updatePlacement(Integer position, ShipCell cell) {
		placementTreeMap.replace(position, cell);
	}
	
	public void switchCard(Integer position){
		cardTreeMap.get(position).next(oneCellPanellTreeMap.get(position));
	}
		
}
