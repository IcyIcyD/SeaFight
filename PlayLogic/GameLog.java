package PlayLogic;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class GameLog extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextArea log;
	private Dimension d;
	private JLabel logLabel;
	public static Object logSync;
	
	public GameLog(){
		
		logSync=new Object();
		setSize(800,100);
		d = new Dimension(1000,100);
		setPreferredSize(new Dimension(800,100));
		setBackground(Color.CYAN);
		setLayout(null);
		setBorder(new LineBorder(Color.CYAN, 3));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(800,100);
		scrollPane.setLocation(0,0);
		logLabel = new JLabel();
		logLabel.setSize(800,100);
		logLabel.setPreferredSize(new Dimension(800, 100));
		logLabel.setVisible(true);

		
		log = new JTextArea();
		log.setSize(1000,100);
		log.setLocation(0,0);
		log.setVisible(true);
		log.setEditable(false);
		log.setCaretPosition(0);
		synchronized (GameLog.logSync){
			logInfo("==GAME LOG==");
			logInfo("The game has begun!");
		}
		
		logLabel.add(log);
		scrollPane.setViewportView(logLabel);
		
		add(scrollPane);
		setVisible(true);
		
	}
	
	public void logInfo(final String s) {
		
		Thread T = new Thread(){
			@Override
			public void run(){
				synchronized(logSync){
					d.height+=12;
					logLabel.setPreferredSize(d);
					log.setSize(d);
					log.insert(s, log.getCaretPosition());
					log.insert(System.lineSeparator(), log.getCaretPosition());
					logSync.notifyAll();
				}
			}
		};
		
		T.start();
		

	}
	
}
