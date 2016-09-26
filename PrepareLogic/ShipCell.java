package PrepareLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import Domain.AbstractCell;

public class ShipCell extends AbstractCell {

	private static final long serialVersionUID = 1L;

	public static final ImageIcon DISABLED = new ImageIcon(
			"res/SpriteEmptyCellDisabled.png");
	public static final ImageIcon DEFAULT = new ImageIcon(
			"res/SpriteEmptyCell.png");
	public static final ImageIcon MISS = new ImageIcon("res/SpriteMissCell.png");
	public static final ImageIcon HIT = new ImageIcon("res/SpriteHitCell.png");
	public static final ImageIcon DESTROYED = new ImageIcon(
			"res/SpriteExplodeCell.png");

	public Integer containsShip = 0;
	public Boolean shipGotHit=false;

	public ShipCell(final PlaySetField psf, final PrepareWindow pw,
			final TreeSet<Integer> isSelectedShipList) {

		setSize(50, 50);
		setPreferredSize(new Dimension(50, 50));
		isSelected = false;
		isLocked = false;
		setVisible(false);
		setDefaultIcon(DEFAULT);
		setDefaultDisabledIcon(DISABLED);
		setBorder(new LineBorder(Color.BLACK, 1));
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected == false) {

					if (containsShip != 0) {
						switch (containsShip) {
						case 1:

							pw.setSelectedShipCellsCount(pw
									.getSelectedShipCellsCount() + 1);
							isSelected = true;
							updateSelection();
							isSelectedShipList.add(getTotalIndex());

							break;

						case 2:

							for (Integer key = 0; key < 3; key++) {
								if (null != pw.shipCountHandler.doubleShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.doubleShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.doubleShipHandler
													.get(key).position2 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() + 2);
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell1.isSelected = true;
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.doubleShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell2.isSelected = true;
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.doubleShipHandler
														.get(key).shipCell2
														.getTotalIndex());

										break;
									}
								}
							}

							break;

						case 3:

							for (Integer key = 0; key < 2; key++) {
								if (null != pw.shipCountHandler.trippleShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.trippleShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.trippleShipHandler
													.get(key).position2 == getTotalIndex())
											|| (pw.shipCountHandler.trippleShipHandler
													.get(key).position3 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() + 3);
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell1.isSelected = true;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell2.isSelected = true;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell2
														.getTotalIndex());
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell3.isSelected = true;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell3
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell3
														.getTotalIndex());

										break;
									}
								}
							}

							break;

						case 4:

							for (Integer key = 0; key < 1; key++) {
								if (null != pw.shipCountHandler.quadroShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.quadroShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position2 == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position3 == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position4 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() + 4);
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell1.isSelected = true;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell2.isSelected = true;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell2
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell3.isSelected = true;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell3
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell3
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell4.isSelected = true;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell4
												.updateSelection();
										isSelectedShipList
												.add(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell4
														.getTotalIndex());

										break;
									}
								}
							}

							break;
						}
					}

				} else {
					if (containsShip != 0) {

						switch (containsShip) {

						case 1:
							pw.setSelectedShipCellsCount(pw
									.getSelectedShipCellsCount() - 1);
							isSelected = false;
							updateSelection();
							isSelectedShipList.remove(getTotalIndex());
							break;

						case 2:
							for (Integer key = 0; key < 3; key++) {
								if (null != pw.shipCountHandler.doubleShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.doubleShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.doubleShipHandler
													.get(key).position2 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() - 2);
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell1.isSelected = false;
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.doubleShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell2.isSelected = false;
										pw.shipCountHandler.doubleShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.doubleShipHandler
														.get(key).shipCell2
														.getTotalIndex());

										break;
									}
								}
							}

							break;

						case 3:

							for (Integer key = 0; key < 2; key++) {
								if (null != pw.shipCountHandler.trippleShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.trippleShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.trippleShipHandler
													.get(key).position2 == getTotalIndex())
											|| (pw.shipCountHandler.trippleShipHandler
													.get(key).position3 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() - 3);
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell1.isSelected = false;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell2.isSelected = false;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell2
														.getTotalIndex());
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell3.isSelected = false;
										pw.shipCountHandler.trippleShipHandler
												.get(key).shipCell3
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.trippleShipHandler
														.get(key).shipCell3
														.getTotalIndex());

										break;
									}
								}
							}

							break;

						case 4:

							for (Integer key = 0; key < 1; key++) {
								if (null != pw.shipCountHandler.quadroShipHandler
										.get(key)) {
									if ((pw.shipCountHandler.quadroShipHandler
											.get(key).getLockedPosition() == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position2 == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position3 == getTotalIndex())
											|| (pw.shipCountHandler.quadroShipHandler
													.get(key).position4 == getTotalIndex())) {

										pw.setSelectedShipCellsCount(pw
												.getSelectedShipCellsCount() - 4);
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell1.isSelected = false;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell1
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell1
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell2.isSelected = false;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell2
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell2
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell3.isSelected = false;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell3
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell3
														.getTotalIndex());
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell4.isSelected = false;
										pw.shipCountHandler.quadroShipHandler
												.get(key).shipCell4
												.updateSelection();
										isSelectedShipList
												.remove(pw.shipCountHandler.quadroShipHandler
														.get(key).shipCell4
														.getTotalIndex());

										break;
									}
								}
							}

							break;
						}
					}
				}

				pw.getRemoveButton().updateEnble(psf);

			}
		});

	}

	public ShipCell(final PrepareWindow pw) {

		setSize(50, 50);
		setPreferredSize(new Dimension(50, 50));
		isSelected = false;
		setVisible(false);
		setIcon((pw.psf.placementTreeMap.get(totalIndex).getIcon()));
		setDisabledIcon((pw.psf.placementTreeMap.get(totalIndex).getDisabledIcon()));
		setBorder(new LineBorder(Color.BLACK, 1));
		isLocked = true;
	}
	
	public ShipCell() {
		
		isLocked = false;
		setSize(50, 50);
		setPreferredSize(new Dimension(50, 50));
		isSelected = false;
		setVisible(false);
		setIcon(DEFAULT);
		setDisabledIcon(DEFAULT);
		setBorder(new LineBorder(Color.BLACK, 1));
		
	}

	public void updateSelection() {
		if (isSelected == false) {
			setBorder(new LineBorder(Color.BLACK, 1));
		} else {
			setBorder(new LineBorder(Color.RED, 3));
		}
	}

}
