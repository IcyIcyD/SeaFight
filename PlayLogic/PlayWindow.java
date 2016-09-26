package PlayLogic;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ConnectLogic.Connector;
import ConnectLogic.PlayCell;
import ConnectLogic.Side;
import Domain.ShipCountHandler;
import PrepareLogic.PrepareWindow;

public class PlayWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public Side yourSide;
	public Side enemySide;
	public ShipsInPlayInfoLabel yourShipsInfoLabel;
	public ShipsInPlayInfoLabel enemyShipsInfoLabel;
	public GameLog gameLog;
	public ShipCountHandler schYours;
	public ShipCountHandler schEnemys;
	public TurnHandler th;
	public TreeMap<Integer, PlayCell> playFieldTreeMapYours;
	public TreeMap<Integer, PlayCell> playFieldTreeMapEnemys;
	private Integer mapKey;

	public PlayWindow(PrepareWindow pw) throws HeadlessException {
		
		super();
		new TurnHandler();
		TurnHandler.possibleMap=new TreeMap<Integer, PlayCell>();
		setTitle("Sea Fight");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("res/AppIcon.png");
		setIconImage(icon.getImage());
		setSize(1060, 920);
		setPreferredSize(new Dimension(1060, 920));
		setResizable(false);
		addComponents(getContentPane());
		setLocationRelativeTo(null);
		pack();
		
		schYours=Connector.pp1.getSch();
		schEnemys=Connector.pp2.getSch();
		

		createPlayFields();
		th = new TurnHandler(this);
		setVisible(true);
		
	}

	private void addComponents(Container contentPane) {
		
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(60, 190, 255));
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel();
		JPanel yourSidePanel = new JPanel();
		JPanel enemySidePanel = new JPanel();
		gameLog = new GameLog();
		yourShipsInfoLabel = new ShipsInPlayInfoLabel(Connector.pp1.getSch());
		enemyShipsInfoLabel = new ShipsInPlayInfoLabel(Connector.pp2.getSch());
		yourSide = Connector.pp1.getSide(); 
		enemySide = Connector.pp2.getSide(); 
		JButton exit = new JButton("EXIT");
		
		
		
		yourSidePanel.setSize(500,500);
		yourSidePanel.setPreferredSize(new Dimension(500,500));
		enemySidePanel.setSize(500,500);
		enemySidePanel.setPreferredSize(new Dimension(500,500));
		titleLabel.setSize(1020, 100);
		titleLabel.setPreferredSize(new Dimension(1020,100));
		titlePanel.setSize(1020, 100);
		titlePanel.setLayout(null);
		yourSidePanel.setLayout(null);
		enemySidePanel.setLayout(null);
		titlePanel.setPreferredSize(new Dimension(1020,100));	
		exit.setSize(200,100);
		exit.setPreferredSize(new Dimension(200, 100));
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		
		titleLabel.setIcon(new ImageIcon("res/PlayWindowTitle.png"));
		
		yourSidePanel.setLocation(20, 260);
		yourSide.setBackground(Color.GREEN);
		yourSide.setBorder(new LineBorder(Color.GREEN, 3));
		enemySidePanel.setLocation(540, 260);
		enemySide.setBackground(Color.RED);
		enemySide.setBorder(new LineBorder(Color.RED, 3));
		yourSide.setLocation(0,0);
		enemySide.setLocation(0,0);
		yourShipsInfoLabel.setLocation(20, 140);
		yourShipsInfoLabel.setBackground(new Color (140, 230, 120));
		enemyShipsInfoLabel.setBackground(new Color (255, 140, 140));
		enemyShipsInfoLabel.setLocation(640, 140);
		exit.setLocation(840, 780);
		titleLabel.setLocation(0,0);
		titlePanel.setLocation(20, 20);
		gameLog.setLocation(20, 780);
		
		add(yourShipsInfoLabel);
		add(enemyShipsInfoLabel);
		add(yourSidePanel);
		add(enemySidePanel);
		add(titlePanel);
		add(exit);
		add(yourSide);
		add(gameLog);
		titlePanel.add(titleLabel);
		yourSidePanel.add(yourSide);
		enemySidePanel.add(enemySide);
		
		yourSide.setVisible(true);
		enemySide.setVisible(true);
		yourSidePanel.setVisible(true);
		enemySidePanel.setVisible(true);
		titlePanel.setVisible(true);
		exit.setVisible(true);
		titleLabel.setVisible(true);
		
		
	}
	
	private void createPlayFields(){
		playFieldTreeMapYours = new TreeMap<Integer, PlayCell>();
		playFieldTreeMapEnemys = new TreeMap<Integer, PlayCell>();
		for (Integer y=0; y<10; y++){
			for (Integer x=0; x<10; x++){
				
				mapKey=((y*10)+x);
				
				final PlayCell cell1 = new PlayCell(yourSide, this);
				cell1.setxIndex(x);
				cell1.setyIndex(y);
				cell1.setTotalIndex(mapKey);
				playFieldTreeMapYours.put(mapKey,cell1);
				yourSide.oneCellPanelTreeMap.get(mapKey).add(cell1,1);
				yourSide.sideCardTreeMap.get(mapKey).first(yourSide.oneCellPanelTreeMap.get(mapKey));
				
				final PlayCell cell2 = new PlayCell(enemySide, this);
				cell2.setxIndex(x);
				cell2.setyIndex(y);
				cell2.setTotalIndex(mapKey);
				playFieldTreeMapEnemys.put(mapKey,cell2);
				enemySide.oneCellPanelTreeMap.get(mapKey).add(cell2,1);
				enemySide.sideCardTreeMap.get(mapKey).first(enemySide.oneCellPanelTreeMap.get(mapKey));
				
				
				
			}
		}
	}

}
