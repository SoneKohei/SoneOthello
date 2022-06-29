import java.awt.Graphics;
public abstract class Game{
	public GameCanvas  gameCanvas = null;

	public abstract void drawGame(double width ,double height ,Graphics g);

	public abstract void play();

	public void addGameCanvas(GameCanvas _gameCanvas){
		gameCanvas = _gameCanvas;
	}

	public void notifyToCanvas(){
		gameCanvas.update(this);
	}
}