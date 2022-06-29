import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGamePlay{
	public static void main(String args[]){

		JFrame jf = new JFrame("othello");
		GameCanvas  gameCanvas = new GameCanvas();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameCanvas.setPreferredSize(new Dimension(700,700));
		jf.getContentPane().add(gameCanvas);
		jf.pack();
		jf.setVisible(true);

		Player player1 = null;
		Player player2 = null;
		Game game = null;
		
		player1 = new OthelloNpc();
		player2 = new OthelloPlayer();

		game = new Othello(player1,player2,gameCanvas);

	}
}