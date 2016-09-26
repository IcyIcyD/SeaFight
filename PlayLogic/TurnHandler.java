package PlayLogic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ConnectLogic.Connector;
import ConnectLogic.PlayCell;

public class TurnHandler {
	
	public static final ImageIcon GREEN_TURN = new ImageIcon("res/PlayWindowGreenTurn.png");
	public static final ImageIcon RED_TURN = new ImageIcon("res/PlayWindowRedTurn.png");
	
	public static Integer whosTurn; //0 - green turn, 1 - red turn
	public static Integer coin=-1;
	public JLabel textPanel;
	JLabel showTurnLabel;
	public static final Object o=0;
	private TurnHandlerThread thread;
	public static Boolean gotHit=false;
	public static Integer whatGotHit;
	public static Boolean winCondition=false;
	public static boolean shipGotHit=false;
	public static Boolean botCanPlay=false;
	public static Boolean shipDestroyed=false;
	public static Integer howManyCellsGotHit=0;
	private TreeMap<Integer, Integer> priorityMapRealization;
	TreeMap<Integer, PlayCell> priorityMap;
	private TreeMap<Integer, PlayCell> priorityMap1;
	private TreeMap<Integer, Integer> possibleMapRealization;
	public static TreeMap<Integer, PlayCell> possibleMap;
	public static Boolean turnQueue=true;
	private TreeMap<Integer, PlayCell> possibleMap1;
	public JDialog dialog;
	public static Boolean isThisFirstBotTurn=true;
	
	public TurnHandler(){
		
	}
	
	public TurnHandler(PlayWindow plw){
		
		Random r = new Random(); 
		TurnHandler.coin = r.nextInt(2);
		
		if(Connector.gameMode==1){
			priorityMap=new TreeMap<Integer, PlayCell>();
			
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapEnemys.entrySet()){
				plw.playFieldTreeMapEnemys.get(entry.getKey()).setVisibleLocked(true);
				plw.enemySide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(false);
				
			}
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapYours.entrySet()){
				
				plw.playFieldTreeMapYours.get(entry.getKey()).setVisibleLocked(false);
				plw.yourSide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(true);
			}
			for(Integer i=0; i<100; i++){
				possibleMap.put(i, plw.playFieldTreeMapYours.get(i));
			}
		}
		if (coin==0){
			
			if(Connector.gameMode==1){
				whosTurn=0;
			} else {
				whosTurn=1;
			}
			
		} else {
			if(Connector.gameMode==1){
				whosTurn=1;
			} else {
				whosTurn=0;
			}
		}
		JPanel showTurnPanel = new JPanel();
		showTurnLabel = new JLabel();
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
		if(Connector.gameMode==1){
			showTurnLabel.setIcon(TurnHandler.GREEN_TURN);
		}
		
		firstTurn(plw);
	}
	
	public void showDialog(PlayWindow plw){
		dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setSize(300,100);
		dialog.setPreferredSize(new Dimension(300,100));
		dialog.setTitle("Sea Fight");
		dialog.setLocationRelativeTo(null);
		dialog.getContentPane().setLayout(new GridLayout(2,2, 25, 25));
		textPanel = new JLabel();
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
		
		okButton.setVisible(true);
	
		if (TurnHandler.whosTurn==0){
			synchronized (GameLog.logSync){
				plw.gameLog.logInfo("Green's player turn started.");
			}
			textPanel.setText("Green player's turn. (Decided by a coin flip)");
			showTurnLabel.setIcon(TurnHandler.GREEN_TURN);

				
		} else {
			synchronized (GameLog.logSync){
				plw.gameLog.logInfo("Red's player turn started.");
			}
			textPanel.setText("Red player's turn. (Decided by a coin flip)");
			showTurnLabel.setIcon(TurnHandler.RED_TURN);
			
			
		}
		
		textPanel.setVisible(true);
		dialog.setVisible(true);
	}
	
	public void setFieldVisibility(PlayWindow plw){
		if( TurnHandler.whosTurn==1){
			//CODE FOR GREEN PLAYER ACTIVE
			
			//Set side visible for active player
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapEnemys.entrySet()){
				plw.playFieldTreeMapEnemys.get(entry.getKey()).setVisibleLocked(true);
				plw.enemySide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(false);
				
			}
			whosTurn=0;
			showDialog(plw);
			
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapYours.entrySet()){
					
				plw.playFieldTreeMapYours.get(entry.getKey()).setVisibleLocked(false);
				plw.yourSide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(true);
			}
			
		} else {
			//CODE FOR RED PLAYER ACTIVE

			//Set side visible for active player
			
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapYours.entrySet()){
				plw.playFieldTreeMapYours.get(entry.getKey()).setVisibleLocked(true);
				plw.yourSide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(false);
				
				
			}
			whosTurn=1;
			
			showDialog(plw);
			
			
			for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapEnemys.entrySet()){
				plw.playFieldTreeMapEnemys.get(entry.getKey()).setVisibleLocked(false);
				plw.enemySide.playPlacementTreeMap.get(entry.getKey()).setVisibleLocked(true);
			}
		}
	}
	
	public void firstTurn(PlayWindow plw){
		
		thread = new TurnHandlerThread(plw, this);
		thread.start();
		
		if(Connector.gameMode==1){
			if(TurnHandler.whosTurn==0){
				
					synchronized (GameLog.logSync){
						plw.gameLog.logInfo("Green's player turn started (Decided by a coin flip).");
						GameLog.logSync.notify();
					}
				
			} else {
				synchronized (GameLog.logSync){
					plw.gameLog.logInfo("Red's player turn started (Decided by a coin flip).");
					GameLog.logSync.notify();
				}
				
					AITurn(plw);
					
					switchTurn(plw);
					
			}
			
		} else {
			setFieldVisibility(plw);
		}
		
		
	}
	
	public void switchTurn(PlayWindow plw){
		
		if(Connector.gameMode==1){
			if(TurnHandler.whosTurn==1){
				
				TurnHandler.whosTurn=0;
					synchronized (GameLog.logSync){
						
						plw.gameLog.logInfo("Green's player turn started.");
						
						GameLog.logSync.notify();
						
					}
					
			} else {
				
				TurnHandler.whosTurn=1;
				AITurn(plw);
				
				switchTurn(plw);
				
			}
			
		} else {
			setFieldVisibility(plw);
		}
		
	}
	
	public void AITurn(PlayWindow plw) {
//		for(Map.Entry<Integer, PlayCell> entry : plw.playFieldTreeMapEnemys.entrySet()){
//			plw.playFieldTreeMapEnemys.get(entry.getKey()).setEnabled(false);
//		}
		while(turnQueue==false){
			
		}
		turnQueue=false;
		synchronized (GameLog.logSync){
			
			plw.gameLog.logInfo("Red's player turn started.");
			GameLog.logSync.notify();
		}
		
		
		Integer i;
		Random r = new Random();
		botCanPlay=true;
		if(priorityMap.size()==0){
			possibleMapRealization = new TreeMap<Integer, Integer>();
			possibleMap1 = new TreeMap<Integer, PlayCell>();
			possibleMap1.putAll(possibleMap);
			
			Integer n1=possibleMap1.size();
			for(Integer n=0; n<n1; n++){
				
				possibleMapRealization.put(n, possibleMap1.navigableKeySet().pollFirst());
				
				
			}
			i=100;
			while(i==100){
				i=r.nextInt(possibleMapRealization.size());
				i=possibleMapRealization.get(i);
			}
			
		} else {
			priorityMapRealization = new TreeMap<Integer, Integer>();
			priorityMap1 = new TreeMap<Integer, PlayCell>();
			priorityMap1.putAll(priorityMap);
			
			Integer n1=priorityMap1.size()-1;
			for(Integer n=0; n<n1; n++){
				
				priorityMapRealization.put(n, priorityMap1.navigableKeySet().pollFirst());
				
				
			}
			i=100;
			while(i==100){
				i=r.nextInt(priorityMapRealization.size());
				i=priorityMapRealization.get(i);
				
			}
		}
		
		while(botCanPlay==true){
			while(plw.playFieldTreeMapYours.get(i).isLocked==true){
				//TODO OR POLL FROM PRIORITY MAP
				if(priorityMap.size()==0){
					i=r.nextInt(100);
					
				} else {
					priorityMapRealization = new TreeMap<Integer, Integer>();
					priorityMap1 = new TreeMap<Integer, PlayCell>();
					priorityMap1.putAll(priorityMap);
					
					Integer n1=priorityMap1.size()-1;
					for(Integer n=0; n<n1; n++){
						
						priorityMapRealization.put(n, priorityMap1.navigableKeySet().pollFirst());
						
					}
					i=100;
					while(i==100){
						i=r.nextInt(priorityMapRealization.size());
						i=priorityMapRealization.get(i);
					}
				}
			}
			
			plw.playFieldTreeMapYours.get(i).action(plw.yourSide, plw); // ACTION HERE ========================
			
			if (winCondition==true){
				break;
			}
			if(shipGotHit==false){

				if(priorityMap.get(i)!=null){
					priorityMap.remove(i);
				}
				TurnHandler.botCanPlay=false;
				
			} else {

				if(plw.yourSide.playPlacementTreeMap.get(i).containsShip!=0){
					
					switch(TurnHandler.howManyCellsGotHit) {
					
					case 0:
						if(TurnHandler.shipDestroyed==true){
							priorityMap.clear();
							TurnHandler.shipDestroyed=false;

						}
						break;
						
					case 1:
						
						priorityMap.remove(i);
						
						if(TurnHandler.shipDestroyed==false){
							priorityMap.put(100, (plw.playFieldTreeMapYours.get(i)));
							//100 is cell that was clicked first
							if((plw.playFieldTreeMapYours.get(i+1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=9) && (plw.playFieldTreeMapYours.get(i+1).isLocked==false)){
								priorityMap.put(i+1, plw.playFieldTreeMapYours.get(i+1));
							}
							if((plw.playFieldTreeMapYours.get(i-1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=0) && (plw.playFieldTreeMapYours.get(i-1).isLocked==false)){
								priorityMap.put(i-1, plw.playFieldTreeMapYours.get(i-1));
							}
							if((plw.playFieldTreeMapYours.get(i+10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=9) && (plw.playFieldTreeMapYours.get(i+10).isLocked==false)){
								priorityMap.put(i+10, plw.playFieldTreeMapYours.get(i+10));
							} else {

							}
							if((plw.playFieldTreeMapYours.get(i-10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=0) && (plw.playFieldTreeMapYours.get(i-10).isLocked==false)){
								priorityMap.put(i-10, plw.playFieldTreeMapYours.get(i-10));
							}

						} else {
							
							priorityMap.clear();
							TurnHandler.shipDestroyed=false;
						}
						
						break;
						
					case 2:
						
						if(TurnHandler.shipDestroyed==false){
							
							priorityMap.remove(i);
							
							//ALIGN #0
							if((plw.playFieldTreeMapYours.get(i-1)==priorityMap.get(100))){
								
								
								if((plw.playFieldTreeMapYours.get(i+1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=9) && (plw.playFieldTreeMapYours.get(i+1).isLocked==false)){
									priorityMap.put(i+1, plw.playFieldTreeMapYours.get(i+1));
								}
								
								if(priorityMap.get(i+9)!=null){
									priorityMap.remove(i+9);
								}
								if(priorityMap.get(i-11)!=null){
									priorityMap.remove(i-11);
								}
							}
							//ALIGN #1
							
							if((plw.playFieldTreeMapYours.get(i+1)==priorityMap.get(100))){
								
								if((plw.playFieldTreeMapYours.get(i-1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=0) && (plw.playFieldTreeMapYours.get(i-1).isLocked==false)){
									priorityMap.put(i-1, plw.playFieldTreeMapYours.get(i-1));
								}
								
								if(priorityMap.get(i-9)!=null){
									priorityMap.remove(i-9);
								}
								if(priorityMap.get(i+11)!=null){
									priorityMap.remove(i+11);
								}
							}
							//ALIGN #2
							if((plw.playFieldTreeMapYours.get(i-10)==priorityMap.get(100))){
								
								if((plw.playFieldTreeMapYours.get(i+10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=9) && (plw.playFieldTreeMapYours.get(i+10).isLocked==false)){
									priorityMap.put(i+10, plw.playFieldTreeMapYours.get(i+10));
								}
								
								if(priorityMap.get(i-9)!=null){
									priorityMap.remove(i-9);
								}
								if(priorityMap.get(i-11)!=null){
									priorityMap.remove(i-11);
								}
							}
							//ALIGN #3
							if((plw.playFieldTreeMapYours.get(i+10)==priorityMap.get(100))){
								
								if((plw.playFieldTreeMapYours.get(i-10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=0) && (plw.playFieldTreeMapYours.get(i-10).isLocked==false)){
									priorityMap.put(i-10, plw.playFieldTreeMapYours.get(i-10));
								}
								
								if(priorityMap.get(i+9)!=null){
									priorityMap.remove(i+9);
								}
								if(priorityMap.get(i+11)!=null){
									priorityMap.remove(i+11);
								}
							}
							
						} else {
							priorityMap.clear();
							TurnHandler.shipDestroyed=false;
						}
						break;
						
					case 3:
						
						if(TurnHandler.shipDestroyed==false){
							
							priorityMap.remove(i);
							
							if(plw.playFieldTreeMapYours.get(i-1)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i+1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=9) && (plw.playFieldTreeMapYours.get(i+1).isLocked==false)){
									priorityMap.put(i+1, plw.playFieldTreeMapYours.get(i+1));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i+1)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i-1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=0) && (plw.playFieldTreeMapYours.get(i-1).isLocked==false)){
									priorityMap.put(i-1, plw.playFieldTreeMapYours.get(i-1));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i+2)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i-1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=0) && (plw.playFieldTreeMapYours.get(i-1).isLocked==false)){
									priorityMap.put(i-1, plw.playFieldTreeMapYours.get(i-1));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i-2)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i+1)!=null) && (plw.playFieldTreeMapYours.get(i).getxIndex()!=9) && (plw.playFieldTreeMapYours.get(i+1).isLocked==false)){
									priorityMap.put(i+1, plw.playFieldTreeMapYours.get(i+1));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i-10)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i+10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=9) && (plw.playFieldTreeMapYours.get(i+10).isLocked==false)){
									priorityMap.put(i+10, plw.playFieldTreeMapYours.get(i+10));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i+10)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i-10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=0) && (plw.playFieldTreeMapYours.get(i-10).isLocked==false)){
									priorityMap.put(i-10, plw.playFieldTreeMapYours.get(i-10));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i-20)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i+10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=9) && (plw.playFieldTreeMapYours.get(i+10).isLocked==false)){
									priorityMap.put(i+10, plw.playFieldTreeMapYours.get(i+10));
								}
							}
							
							if(plw.playFieldTreeMapYours.get(i+20)==priorityMap.get(100)){
								if((plw.playFieldTreeMapYours.get(i-10)!=null) && (plw.playFieldTreeMapYours.get(i).getyIndex()!=0) && (plw.playFieldTreeMapYours.get(i-10).isLocked==false)){
									priorityMap.put(i-10, plw.playFieldTreeMapYours.get(i-10));
								}
							}
							
						} else {
							priorityMap.clear();
							TurnHandler.shipDestroyed=false;
						}
						
						break;
					case 4:
						priorityMap.clear();
						TurnHandler.shipDestroyed=false;
						break;
					}
					
				}
			}
			turnQueue=true;
			
		}
	
	}
	
}
