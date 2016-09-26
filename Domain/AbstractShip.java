package Domain;

import javax.swing.ImageIcon;

public abstract class AbstractShip {
	public static final ImageIcon SHIP_SOLO = new ImageIcon("res/SpriteSolo.png");
	public static final ImageIcon SHIP_DOUBLE_1 = new ImageIcon("res/SpriteDouble_1.png");
	public static final ImageIcon SHIP_DOUBLE_2 = new ImageIcon("res/SpriteDouble_2.png");
	public static final ImageIcon SHIP_DOUBLE_1_ROTATED = new ImageIcon("res/SpriteDoubleRotated_1.png");
	public static final ImageIcon SHIP_DOUBLE_2_ROTATED = new ImageIcon("res/SpriteDoubleRotated_2.png");
	public static final ImageIcon SHIP_TRIPPLE_1 = new ImageIcon("res/SpriteTripple_1.png");
	public static final ImageIcon SHIP_TRIPPLE_2 = new ImageIcon("res/SpriteTripple_2.png");
	public static final ImageIcon SHIP_TRIPPLE_3 = new ImageIcon("res/SpriteTripple_3.png");
	public static final ImageIcon SHIP_TRIPPLE_1_ROTATED = new ImageIcon("res/SpriteTrippleRotated_1.png");
	public static final ImageIcon SHIP_TRIPPLE_2_ROTATED = new ImageIcon("res/SpriteTrippleRotated_2.png");
	public static final ImageIcon SHIP_TRIPPLE_3_ROTATED = new ImageIcon("res/SpriteTrippleRotated_3.png");
	public static final ImageIcon SHIP_QUADRO_1 = new ImageIcon("res/SpriteQuadro_1.png");
	public static final ImageIcon SHIP_QUADRO_2 = new ImageIcon("res/SpriteQuadro_2.png");
	public static final ImageIcon SHIP_QUADRO_3 = new ImageIcon("res/SpriteQuadro_3.png");
	public static final ImageIcon SHIP_QUADRO_4 = new ImageIcon("res/SpriteQuadro_4.png");
	public static final ImageIcon SHIP_QUADRO_1_ROTATED = new ImageIcon("res/SpriteQuadroRotated_1.png");
	public static final ImageIcon SHIP_QUADRO_2_ROTATED = new ImageIcon("res/SpriteQuadroRotated_2.png");
	public static final ImageIcon SHIP_QUADRO_3_ROTATED = new ImageIcon("res/SpriteQuadroRotated_3.png");
	public static final ImageIcon SHIP_QUADRO_4_ROTATED = new ImageIcon("res/SpriteQuadroRotated_4.png");
	public Integer lockedPosition;
	
	public Integer getLockedPosition() {
		return lockedPosition;
	}
	public void setLockedPosition(Integer lockedPosition) {
		this.lockedPosition = lockedPosition;
	}
}
