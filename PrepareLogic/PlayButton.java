package PrepareLogic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ConnectLogic.Connector;
import PlayLogic.PlayWindow;

public class PlayButton extends JButton {
	
	private static final long serialVersionUID = 1L;

	public PlayButton(final PrepareWindow pw) {
		
		setText("PLAY");
		setSize(250, 80);
		setPreferredSize(new Dimension(250, 80));
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				switch(Connector.gameMode){
				
				case 1:
					
					Connector.catchField(pw);
					pw.dispose();
					new PlayWindow(pw);
					
					break;
				
				case 2:
					
					if(Connector.forOfflineMode_isThisTheFirstPW==false){
						
						Connector.catchField(pw);
						pw.dispose();
						new PrepareWindow();
						
					} else {
						
						Connector.catchField(pw);
						pw.dispose();
						new PlayWindow(pw);

					}
					
					break;

				}
			}
		});
		
	}
}
