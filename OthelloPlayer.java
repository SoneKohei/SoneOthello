
public class OthelloPlayer extends Player {
	int x,y;
        OthelloPlayer(){
	}
	public void putOthello(int setcolor){
		if(othello.canPut(setcolor)){
                      x = othello.selectx;
                      y = othello.selecty;
                      System.out.println("X:"+x + " Y:"+y);
                      if(othello.canReverse(x,y,setcolor)){
                          othello.reverse(x,y,setcolor);
                          othello.isWhiteTurn = !othello.isWhiteTurn;
                          othello.turnCount++;
                      }else{
                          System.out.println("入力エラー");
                      }
                    
                }else{  othello.passcount++; System.out.println("置けないのでパスします"); }
	}
}