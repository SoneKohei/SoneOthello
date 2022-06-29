import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameCanvas extends JPanel{
	//ÉfÅ[É^
	Game game = null;
	
	//
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.green);
			if(game == null) return;
		game.drawGame(getWidth(),getHeight(),g);
	}

	public void update(Game _game){
		game = _game;
		repaint();
	}

}