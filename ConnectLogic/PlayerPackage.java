package ConnectLogic;

import AI.FieldCreator;
import Domain.ShipCountHandler;
import PrepareLogic.PrepareWindow;

public class PlayerPackage {
	
	private Side side;
	private ShipCountHandler sch;
	
	public PlayerPackage(PrepareWindow pw){
		
		side = new Side(pw);
		sch = pw.shipCountHandler;
		
	}
	
	public PlayerPackage(FieldCreator fc){
		
		side = new Side(fc);
		sch = fc.sch;
		
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;

	}

	public ShipCountHandler getSch() {
		return sch;
	}

	public void setSch(ShipCountHandler sch) {
		this.sch = sch;
	}
}
