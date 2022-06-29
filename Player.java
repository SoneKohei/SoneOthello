public abstract class Player{
	public Othello othello = null;

	public void addOthello(Othello _othello){
		othello = _othello;
	}

	public abstract void putOthello(int setcolor);
}