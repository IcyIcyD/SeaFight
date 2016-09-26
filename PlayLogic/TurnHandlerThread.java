package PlayLogic;


import ConnectLogic.Connector;
import MainMenu.MainMenu;

public class TurnHandlerThread extends Thread{
	
	private TurnHandler th;
	private PlayWindow plw;

	public TurnHandlerThread(PlayWindow pw, TurnHandler t) {
		
		this.plw=pw;
		this.th=t;

	}
	
	@Override
	public void run(){
		
			synchronized (TurnHandler.o){
				
				while(TurnHandler.winCondition==false){
					
					while (TurnHandler.gotHit==false){
						try {
							TurnHandler.o.wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					
					if (TurnHandler.shipGotHit==false){
						
						if((Connector.gameMode!=1) || ((Connector.gameMode==1) && (TurnHandler.whosTurn==0))){
							th.switchTurn(plw);
							
						} 
						
					} else {
						
					}
					
					TurnHandler.gotHit=false;
					
				}
				
				plw.dispose();
				Connector.gameMode=0;
				Connector.forOfflineMode_isThisTheFirstPW=false;
				Connector.pp1=null;
				Connector.pp2=null;	
				TurnHandler.whosTurn=2;
				TurnHandler.coin=-1;
				TurnHandler.gotHit=false;
				TurnHandler.whatGotHit=null;
				TurnHandler.winCondition=false;
				TurnHandler.botCanPlay=false;
				TurnHandler.shipDestroyed=false;
				TurnHandler.howManyCellsGotHit=0;
				TurnHandler.shipGotHit=false;
				TurnHandler.turnQueue=true;
				TurnHandler.possibleMap.clear();
				TurnHandler.isThisFirstBotTurn=true;
//				th.priorityMap.clear();
				
				new MainMenu();
				
		}

	}
	
}
	
	