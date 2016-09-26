package PrepareLogic;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ConnectLogic.Connector;
import Domain.ShipCountHandler;
import MainMenu.MainMenu;


public class PrepareWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public PrepareWindowInfoPanel tracker;
	public PlaceShipButton placeButton;
	public RemoveShipButton removeButton;
	public PlayButton playButton;
	public Integer selectedCellsCount; 
	public Integer selectedShipCellsCount; 
	public Boolean hundredTimesUpdateBlocker=false;
	public CellForPlaySetField lastSelectedCell;
	public ShipCountHandler shipCountHandler;
	public ShipsCanPlaceInfoLabel scpl;
	public PlaySetField psf;
	public RandomButton random;
	
	public RemoveShipButton getRemoveButton() {
		return removeButton;
	}
	
	public Integer getSelectedShipCellsCount() {
		return selectedShipCellsCount;
	}

	public void setSelectedShipCellsCount(Integer selectedShipCellsCount) {
		this.selectedShipCellsCount = selectedShipCellsCount;
	}

	public JButton getPlaceButton() {
		return placeButton;
	}
	
	public Integer getSelectedCellsCount() {
		return selectedCellsCount;
	}
	
	public void setSelectedCellsCount(Integer selectedCellsCount) {
		this.selectedCellsCount = selectedCellsCount;
	}

	public PrepareWindow() throws HeadlessException {
		super();
		
		setTitle("Sea Fight");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		selectedCellsCount=0;
		selectedShipCellsCount=0;
		ImageIcon icon = new ImageIcon("res/AppIcon.png");
		setIconImage(icon.getImage());
		setSize(810, 640);
		setPreferredSize(new Dimension(810, 640));
		setResizable(false);
		addComponents(getContentPane());
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		
	}

	private void addComponents(Container contentPane) {
		
		shipCountHandler = new ShipCountHandler();
		JButton back = new JButton("BACK");
		random = new RandomButton(this);
		JLabel imageHolder = new JLabel();
		tracker = new PrepareWindowInfoPanel();
		psf = new PlaySetField(tracker, this);
		scpl = new ShipsCanPlaceInfoLabel(this);
		playButton = new PlayButton(this);
		placeButton = new PlaceShipButton(psf, this);
		removeButton = new RemoveShipButton(psf, this);
		selectedCellsCount=psf.isSelectedList.size();
		
		setLayout(null);
		contentPane.setBackground(new Color(60, 190, 255));
		back.setSize(250, 80);
		back.setPreferredSize(new Dimension(250, 80));
		imageHolder.setSize(500, 60);
		imageHolder.setPreferredSize(new Dimension(500, 60));
		imageHolder.setIcon(new ImageIcon("res/SetWindowTitle.png"));

		
		playButton.setLocation(540, 135);
		placeButton.setLocation(540, 230);
		placeButton.setEnabled(false);
		removeButton.setLocation(540, 325);
		back.setLocation(540, 520);
		random.setLocation(540, 420);
		imageHolder.setLocation(20,20);
		tracker.setLocation(540, 20);
		scpl.setLocation(540, 50);
		psf.setLocation(20, 100);
		
		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new MainMenu();
				Connector.gameMode=0;
				dispose();
			}
		});
		
		//action listener for play
		
		add(tracker);
		add(psf);
		add(playButton);
		add(placeButton);
		add(removeButton);
		add(back);
		add(imageHolder);
		add(scpl);
		add(random);

		
	}




	
	
	
}
