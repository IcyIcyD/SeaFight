package MainMenu;
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
import javax.swing.JPanel;

import ConnectLogic.Connector;
import PrepareLogic.PrepareWindow;


public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MainMenu();

	}

	public MainMenu() throws HeadlessException {
		super();

		setTitle("Sea Fight");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ImageIcon icon = new ImageIcon("res/AppIcon.png");
		setIconImage(icon.getImage());
		setSize(300, 625);
		setPreferredSize(new Dimension(300, 625));
		setResizable(false);
		addComponents(getContentPane());
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		
	}

	private void addComponents(Container contentPane) {

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(60, 190, 255));
		JButton play1 = new JButton("1 PLAYER (but AI is drunk)");
		JButton play2 = new JButton("2 PLAYERS (OFFLINE)");
		JButton play3 = new JButton("Here will be online mode (probably not)");
		JButton exit = new JButton("EXIT");
		JLabel titleLabel = new JLabel();
		
		exit.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		
		play1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				new PrepareWindow();
				Connector.gameMode=1;
				dispose();
				}
		});
		
		play2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				new PrepareWindow();
				Connector.gameMode=2;
				dispose();
				}
		});
		
		//TODO play3 button
		play3.setEnabled(false);
		
		contentPane.add(menuPanel);
		
		titleLabel.setLocation(20, 20);
		titleLabel.setIcon(new ImageIcon("res/SpriteTitle.png"));
		titleLabel.setSize(255, 80);
		play1.setLocation(20, 120);
		play1.setSize(255, 100);
		play2.setLocation(play1.getX(), play1.getY()+120);
		play2.setSize(255, 100);
		play3.setLocation(play2.getX(), play2.getY()+120);
		play3.setSize(255, 100);
		exit.setLocation(play3.getX(), play3.getY()+120);
		exit.setSize(255, 100);
		
		play1.setAlignmentX(CENTER_ALIGNMENT);
		play2.setAlignmentX(CENTER_ALIGNMENT);
		play3.setAlignmentX(CENTER_ALIGNMENT);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		menuPanel.add(titleLabel);
		menuPanel.add(play1);
		menuPanel.add(play2);
		menuPanel.add(play3);
		menuPanel.add(exit);
		
	}

}
