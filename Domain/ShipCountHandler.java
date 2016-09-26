package Domain;

import java.util.TreeMap;

import Ships.DoubleShip;
import Ships.QuadroShip;
import Ships.SoloShip;
import Ships.TrippleShip;

public class ShipCountHandler {
	public Integer quadroShipCount=0;
	public Integer trippleShipCount=0;
	public Integer doubleShipCount=0;
	public Integer soloShipCount=0;
	public TreeMap<Integer, SoloShip> soloShipHandler;
	public TreeMap<Integer, DoubleShip> doubleShipHandler;
	public TreeMap<Integer, TrippleShip> trippleShipHandler;
	public TreeMap<Integer, QuadroShip> quadroShipHandler;
	
	public ShipCountHandler(){
		
		soloShipHandler = new TreeMap<Integer, SoloShip>();
		doubleShipHandler = new TreeMap<Integer, DoubleShip>();
		trippleShipHandler = new TreeMap<Integer, TrippleShip>();
		quadroShipHandler = new TreeMap<Integer, QuadroShip>();
	}
	
	public Integer getQuadroShipCount() {
		return quadroShipCount;
	}
	public void setQuadroShipCount(Integer quadroShipCount) {
		this.quadroShipCount = quadroShipCount;
	}
	public Integer getTrippleShipCount() {
		return trippleShipCount;
	}
	public void setTrippleShipCount(Integer trippleShipCount) {
		this.trippleShipCount = trippleShipCount;
	}
	public Integer getDoubleShipCount() {
		return doubleShipCount;
	}
	public void setDoubleShipCount(Integer doubleShipCount) {
		this.doubleShipCount = doubleShipCount;
	}
	public Integer getSoloShipCount() {
		return soloShipCount;
	}
	public void setSoloShipCount(Integer soloShipCount) {
		this.soloShipCount = soloShipCount;
	}
}
