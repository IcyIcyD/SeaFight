package PrepareLogic;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.ImageIcon;

import Domain.AbstractCell;


public class CellForPlaySetField extends AbstractCell {

	private static final long serialVersionUID = 1L;
	public static final ImageIcon NOT_SELECTED_ICON = new ImageIcon("res/SpriteEmptyCell.png");
	public static final ImageIcon SELECTED_ICON = new ImageIcon("res/SpriteEmptyCellSelected.png");
	public static final ImageIcon POSSIBLE_ICON = new ImageIcon("res/SpriteEmptyCellPossible.png");
	public static final ImageIcon SELECTED_DISABLE_ICON = new ImageIcon("res/SpriteEmptyCellSelectedDisable.png");
	public static final ImageIcon DISABLE_ICON = new ImageIcon("res/SpriteEmptyCellDisabled.png");
	public String whatGotClicked;
	
	private CellForPlaySetField cell = this;
	
	public String getWhatGotClicked() {
		return whatGotClicked;
	}

	public CellForPlaySetField(final TreeMap<Integer, CellForPlaySetField> fieldTreeMap, final PrepareWindow pw, final TreeSet<Integer> isSelectedList) {
		
		
		setDefaultIcon(NOT_SELECTED_ICON);
		setDefaultDisabledIcon(DISABLE_ICON);
		setSize(50,50);
		setPreferredSize(new Dimension(50,50));
		isSelected=false;
		isLocked=false;
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isSelected==false) {
					pw.setSelectedCellsCount(pw.getSelectedCellsCount()+1);
					setIconCell(SELECTED_ICON);
					isSelected=true;
					isSelectedList.add(getTotalIndex());
					

				} else {
					pw.setSelectedCellsCount(pw.getSelectedCellsCount()-1);
					setIconCell(NOT_SELECTED_ICON);
					isSelected=false;
					isSelectedList.remove(getTotalIndex());
					

				}

				whatGotClicked = "x: "+getxIndex()+" y: "+getyIndex()+" Selected: "+pw.getSelectedCellsCount()+" Cell#: "+getTotalIndex();
				pw.tracker.setTextDirectly(cell);
			}
			
			
				
		});
		
		addPropertyChangeListener(new PropertyChangeListener() {
			
			
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if(e.getPropertyName()=="icon"){
					if ((e.getNewValue()==CellForPlaySetField.POSSIBLE_ICON)||(cell.isLocked==true)){
						
					}
					if (e.getNewValue()==CellForPlaySetField.SELECTED_ICON){
						
						switch(pw.getSelectedCellsCount()) {
						case 1:
							
							pw.placeButton.setEnabled(true);
							if (pw.hundredTimesUpdateBlocker==false){
								
								pw.hundredTimesUpdateBlocker=true;
								
								for (Map.Entry<Integer, CellForPlaySetField> entry : fieldTreeMap.entrySet()){
									entry.getValue().setEnabledCell(false);
								}
								
								cell.setEnabledCell(true);
								
								if ((cell.getxIndex()==9)==false) {
									fieldTreeMap.get(cell.getTotalIndex()+1).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									fieldTreeMap.get(cell.getTotalIndex()+1).setEnabledCell(true);
									
								}
																	
								if ((cell.getxIndex()==0)==false) {
									fieldTreeMap.get(cell.getTotalIndex()-1).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									fieldTreeMap.get(cell.getTotalIndex()-1).setEnabledCell(true);
									
								}
													
								if ((cell.getyIndex()==9)==false) {
									fieldTreeMap.get(cell.getTotalIndex()+10).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									fieldTreeMap.get(cell.getTotalIndex()+10).setEnabledCell(true);
									
								}
																	
								if ((cell.getyIndex()==0)==false) {
									fieldTreeMap.get(cell.getTotalIndex()-10).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									fieldTreeMap.get(cell.getTotalIndex()-10).setEnabledCell(true);
									
								}
								pw.hundredTimesUpdateBlocker=false;
							}
							

							
							pw.lastSelectedCell = fieldTreeMap.get(cell.getTotalIndex());
							
							break;
							
						case 2: 
							
							if (pw.hundredTimesUpdateBlocker==false){
								
								pw.hundredTimesUpdateBlocker=true;
								//RIGHT
								
								if ((pw.lastSelectedCell.getxIndex()+1==cell.getxIndex())==true) {
									if ((pw.lastSelectedCell.getxIndex()==8)==false){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setEnabledCell(true);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									}
									if (pw.lastSelectedCell.getyIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setEnabledCell(false);
									}
									if (pw.lastSelectedCell.getyIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setEnabledCell(false);
									}
									
								}
								
								//LEFT
								
								if ((pw.lastSelectedCell.getxIndex()-1==cell.getxIndex())==true) {
									if ((pw.lastSelectedCell.getxIndex()==1)==false){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setEnabledCell(true);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									}
									if (pw.lastSelectedCell.getyIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setEnabledCell(false);
									}
									if (pw.lastSelectedCell.getyIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setEnabledCell(false);
									}
									
								}
								
								//BOTTOM
								
								if ((pw.lastSelectedCell.getyIndex()+1==cell.getyIndex())==true) {
									if ((pw.lastSelectedCell.getyIndex()==8)==false){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setEnabledCell(true);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									}
									if (pw.lastSelectedCell.getxIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setEnabledCell(false);
									}
									if (pw.lastSelectedCell.getxIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setEnabledCell(false);
									}
									
								}
																	
								//UPPER
								
								if ((pw.lastSelectedCell.getyIndex()-1==cell.getyIndex())==true) {
									if ((pw.lastSelectedCell.getyIndex()==1)==false){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setEnabledCell(true);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									}
									if (pw.lastSelectedCell.getxIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setEnabledCell(false);
									}
									if (pw.lastSelectedCell.getxIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setEnabledCell(false);
									}
									
								}
								pw.hundredTimesUpdateBlocker=false;
								
							}

							
							break;
							
						case 3:
							
							if (pw.hundredTimesUpdateBlocker==false){
								
								pw.hundredTimesUpdateBlocker=true;
							
								//X axis
																	
								if ((pw.lastSelectedCell.getxIndex()+2==cell.getxIndex())==true) {
									if (pw.lastSelectedCell.getxIndex()!=7) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+3).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+3).setEnabledCell(true);
									}
									if (pw.lastSelectedCell.getxIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setEnabledCell(false);
									}
								}
								
								if ((pw.lastSelectedCell.getxIndex()+1==cell.getxIndex())==true){
									if (pw.lastSelectedCell.getxIndex()!=8) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setEnabledCell(true);
									}
									
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
									
								}
								
								if (pw.lastSelectedCell.getxIndex()-2==cell.getxIndex()){
									if (pw.lastSelectedCell.getxIndex()!=2) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-3).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-3).setEnabledCell(true);
									}
									if (pw.lastSelectedCell.getxIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setEnabledCell(false);
									}
								}
								
								if (pw.lastSelectedCell.getxIndex()-1==cell.getxIndex()==true){
									if (pw.lastSelectedCell.getxIndex()!=1) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setEnabledCell(true);
									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								
								//Y
								
								if (pw.lastSelectedCell.getyIndex()+2==cell.getyIndex()==true) {
									if (pw.lastSelectedCell.getyIndex()!=7) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+30).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+30).setEnabledCell(true);
									}
									if (pw.lastSelectedCell.getyIndex()!=9) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setEnabledCell(false);
									}
								}
								
								if (pw.lastSelectedCell.getyIndex()+1==cell.getyIndex()==true){
									if (pw.lastSelectedCell.getyIndex()!=8) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setEnabledCell(true);
									}
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
									}
								
								if (pw.lastSelectedCell.getyIndex()-2==cell.getyIndex()==true) {
									if (pw.lastSelectedCell.getyIndex()!=2) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-30).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-30).setEnabledCell(true);
									}
									if (pw.lastSelectedCell.getyIndex()!=0) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setEnabledCell(false);
									}
								}
								
								if (pw.lastSelectedCell.getyIndex()-1==cell.getyIndex()==true) {
									if (pw.lastSelectedCell.getyIndex()!=1) {
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setEnabledCell(true);
									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								pw.hundredTimesUpdateBlocker=false;
							}
							
							break;
							
						case 4: 
							
							if (pw.hundredTimesUpdateBlocker==false){
								
								pw.hundredTimesUpdateBlocker=true;
							
								//X axis
								
								if ((pw.lastSelectedCell.getxIndex()+3==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getxIndex()+2==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getxIndex()+1==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-3)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-3).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-3).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getxIndex()-1==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+3)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+3).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+3).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getxIndex()-2==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+2).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-1).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getxIndex()-3==cell.getxIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+1).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-2).setEnabledCell(false);
								}
								
								//Y axis
								
								if ((pw.lastSelectedCell.getyIndex()+1==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-30)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-30).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-30).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getyIndex()+2==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getyIndex()+3==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getyIndex()-1==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+30)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+30).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+30).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getyIndex()-2==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+20).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-10).setEnabledCell(false);
								}
								
								if ((pw.lastSelectedCell.getyIndex()-3==cell.getyIndex())==true){
									if (null != fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10)){
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setEnabledCell(false);
										fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()+10).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);

									}
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setDisabledIcon(CellForPlaySetField.SELECTED_DISABLE_ICON);
									fieldTreeMap.get(pw.lastSelectedCell.getTotalIndex()-20).setEnabledCell(false);
								}
								pw.hundredTimesUpdateBlocker=false;
							}

							break;
						
						}
						

					} else if (e.getNewValue()==CellForPlaySetField.NOT_SELECTED_ICON) {
						
						//AND THIS HUGE BLOCK REMOVES SELECTED CELLS
						
						switch(pw.selectedCellsCount){
							

							case 0:
								
								pw.placeButton.setEnabled(false);
								//works perfectly
								
								if(pw.hundredTimesUpdateBlocker==false){
									
									pw.hundredTimesUpdateBlocker=true;
									
									for (Map.Entry<Integer, CellForPlaySetField> entry : fieldTreeMap.entrySet()){
										entry.getValue().setEnabledCell(true);
										entry.getValue().setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);	
									}
									
									pw.hundredTimesUpdateBlocker=false;
								}
																		
								break;
								
							case 1:
								
								//works perfectly
								
								if (pw.hundredTimesUpdateBlocker==false){
									
									pw.hundredTimesUpdateBlocker=true;
									
									fieldTreeMap.get(cell.getTotalIndex()).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									
									if((null!=fieldTreeMap.get(cell.getTotalIndex()+1))&&(fieldTreeMap.get(cell.getTotalIndex()+1).isSelected==true)){
										//RIGHT CELL IS STILL SELECTED
										
										pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+1);

										if ((cell.getyIndex()!=9) && (cell.getxIndex()!=9)) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										}
										if ((cell.getyIndex()!=0) && (cell.getxIndex()!=9)) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										}
										if (cell.getxIndex()!=0) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setEnabledCell(false);
										}
										
									} else if ((null!=fieldTreeMap.get(cell.getTotalIndex()-1))&&(fieldTreeMap.get(cell.getTotalIndex()-1).isSelected==true)){
										//LEFT CELL IS STILL SELECTED
										pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-1);

										if ((cell.getyIndex()!=9) && (cell.getxIndex()!=0)) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										}
										if ((cell.getyIndex()!=0) && (cell.getxIndex()!=0)) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										}
										if (cell.getxIndex()!=9) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setEnabledCell(false);
										}

									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()+10))&&(fieldTreeMap.get(cell.getTotalIndex()+10).isSelected==true)){
										//BOTTOM CELL IS STILL SELECTED
										pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+10);

										if ((cell.getyIndex()!=9) && (cell.getxIndex()!=0)) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										}
										if ((cell.getyIndex()!=9) && (cell.getxIndex()!=9)) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										}
										if (cell.getyIndex()!=0) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex() )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex() )).setEnabledCell(false);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()-10))&&(fieldTreeMap.get(cell.getTotalIndex()-10).isSelected==true)){
										//UPPER CELL IS STILL SELECTED
										pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-10);

										if ((cell.getyIndex()!=0) && (cell.getxIndex()!=0)) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										}
										if ((cell.getyIndex()!=0) && (cell.getxIndex()!=9)) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										}
										if (cell.getyIndex()!=9) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex() )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex() )).setEnabledCell(false);
										}
										
									}
									
									pw.hundredTimesUpdateBlocker=false;
								}
								
								break;
								
							case 2:
								
								if (pw.hundredTimesUpdateBlocker==false){
									
									//works perfeclty
									
									pw.hundredTimesUpdateBlocker=true;
									fieldTreeMap.get(cell.getTotalIndex()).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									
									if((null!=fieldTreeMap.get(cell.getTotalIndex()+1))&&(fieldTreeMap.get(cell.getTotalIndex()+1).isSelected==true)){
										//RIGHT CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()+2))&&(fieldTreeMap.get(cell.getTotalIndex()+2).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+2);
										}

										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										if (cell.getxIndex()!=0) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setEnabledCell(false);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()-1))&&(fieldTreeMap.get(cell.getTotalIndex()-1).isSelected==true)){
										//LEFT CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()-2))&&(fieldTreeMap.get(cell.getTotalIndex()-2).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-2);
										}

										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										if (cell.getxIndex()!=9) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setEnabledCell(false);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()+10))&&(fieldTreeMap.get(cell.getTotalIndex()+10).isSelected==true)){
										//BOTTOM CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()+20))&&(fieldTreeMap.get(cell.getTotalIndex()+20).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+20);
										}

										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setEnabledCell(true);
										if (cell.getyIndex()!=0) {
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setEnabledCell(false);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()-10))&&(fieldTreeMap.get(cell.getTotalIndex()-10).isSelected==true)){
										//UPPER CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()-20))&&(fieldTreeMap.get(cell.getTotalIndex()-20).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-20);
										}

										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setEnabledCell(true);
										if (cell.getyIndex()!=9) {
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.NOT_SELECTED_ICON);
											fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setEnabledCell(false);
										}
										
									}
									pw.hundredTimesUpdateBlocker=false;
								}
								break;
							
							case 3:
								
								//works perfectly
								
								if (pw.hundredTimesUpdateBlocker==false){
									
									pw.hundredTimesUpdateBlocker=true;
									
									fieldTreeMap.get(cell.getTotalIndex()).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
									
									if((null!=fieldTreeMap.get(cell.getTotalIndex()+1))&&(fieldTreeMap.get(cell.getTotalIndex()+1).isSelected==true)){
										//RIGHT CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()+3))&&(fieldTreeMap.get(cell.getTotalIndex()+3).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+3);
										}

										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+1 )).setEnabledCell(true);
										if (cell.getxIndex()!=6) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+4)).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()+4)).setEnabledCell(true);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()-1))&&(fieldTreeMap.get(cell.getTotalIndex()-1).isSelected==true)){
										//LEFT CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()-3))&&(fieldTreeMap.get(cell.getTotalIndex()-3).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-3);
										}

										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-1 )).setEnabledCell(true);
										if (cell.getxIndex()!=3) {
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-4)).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex() )*10)+(cell.getxIndex()-4)).setEnabledCell(true);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()+10))&&(fieldTreeMap.get(cell.getTotalIndex()+10).isSelected==true)){
										//BOTTOM CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()+30))&&(fieldTreeMap.get(cell.getTotalIndex()+30).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()+30);
										}

										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex()+1 )*10)+(cell.getxIndex())).setEnabledCell(true);
										if (cell.getyIndex()!=6) {
											fieldTreeMap.get(((cell.getyIndex()+4 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()+4 )*10)+(cell.getxIndex())).setEnabledCell(true);
										}
										
									} else if((null!=fieldTreeMap.get(cell.getTotalIndex()-10))&&(fieldTreeMap.get(cell.getTotalIndex()-10).isSelected==true)){
										//UPPER CELL IS STILL SELECTED
										if((null!=fieldTreeMap.get(cell.getTotalIndex()-30))&&(fieldTreeMap.get(cell.getTotalIndex()-30).isSelected==true)){
											pw.lastSelectedCell=fieldTreeMap.get(cell.getTotalIndex()-30);
										}

										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.SELECTED_ICON);
										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setDisabledIcon(CellForPlaySetField.DISABLE_ICON);
										fieldTreeMap.get(((cell.getyIndex()-1 )*10)+(cell.getxIndex())).setEnabledCell(true);
										if (cell.getyIndex()!=3) {
											fieldTreeMap.get(((cell.getyIndex()-4 )*10)+(cell.getxIndex())).setIconCell(CellForPlaySetField.POSSIBLE_ICON);
											fieldTreeMap.get(((cell.getyIndex()-4 )*10)+(cell.getxIndex())).setEnabledCell(true);
										}
										
									}
									
									pw.hundredTimesUpdateBlocker=false;
								}
								break;
								
							}
								
						}
						
					}
					
				}
										
			
		});
		
	}

}
