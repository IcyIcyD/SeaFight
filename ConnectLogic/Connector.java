package ConnectLogic;

import AI.FieldCreator;
import PrepareLogic.PrepareWindow;


public class Connector {
	
	public static Integer gameMode;
	public static Boolean forOfflineMode_isThisTheFirstPW=false;
	/*
	 * game mode:
	 * 0 - you are in main menu, no mode chosen
	 * 1 - 1 player AI mode
	 * 2 - 2 players offline mode
	 * 3 - 2 players online mode
	 */
	
	public static PlayerPackage pp1;
	public static PlayerPackage pp2;

	
	public Connector() {
		
	}
	
	public static void catchField(PrepareWindow pw) {
		
		switch (gameMode){
		
		case 1:
			
			pp1 = new PlayerPackage(pw);
			FieldCreator fc = new FieldCreator();
			pp2 = new PlayerPackage(fc);
			break;
		
		case 2:
			
			if(forOfflineMode_isThisTheFirstPW==false){
				pp1 = new PlayerPackage(pw);
				forOfflineMode_isThisTheFirstPW=true;
			} else {			
				pp2 = new PlayerPackage(pw);
			}
			break;
		
		}
		
	}
	
	
}
