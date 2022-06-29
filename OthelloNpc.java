import java.util.Random;

public class OthelloNpc extends Player{
	Random rd = new Random();
	public void putOthello(int setcolor){
        othello.isWhiteTurn = !othello.isWhiteTurn;
        othello.turnCount++;
        int maxx=0,maxy=0,maxcount=0,count =0;
        //Šp
        if(othello.canReverse(1,1,setcolor)){
            othello.reverse(1,1,setcolor);
            return ;
        }
        if(othello.canReverse(1,othello.SIZE,setcolor)){
            othello.reverse(1,othello.SIZE,setcolor);
            return ;
        }
        if(othello.canReverse(othello.SIZE,othello.SIZE,setcolor)){
            othello.reverse(othello.SIZE,othello.SIZE,setcolor);
            return ;
        }
        if(othello.canReverse(othello.SIZE,1,setcolor)){
            othello.reverse(othello.SIZE,1,setcolor);
            return ;
        }
        //—ñ
        for(int i = 1;i <= othello.SIZE;i++){
            if(othello.canReverse(i,1,setcolor)){
                othello.reverse(i,1,setcolor);
                return ;
            }
        }
        for(int i = 1;i <= othello.SIZE;i++){
            if(othello.canReverse(i,othello.SIZE,setcolor)){
                othello.reverse(i,othello.SIZE,setcolor);
                return ;
            }
        }
        for(int i = 1;i <= othello.SIZE;i++){
            if(othello.canReverse(1,i,setcolor)){
                othello.reverse(1,i,setcolor);
                return ;
            }
        }
        for(int i = 1;i <= othello.SIZE;i++){
            if(othello.canReverse(othello.SIZE,i,setcolor)){
                othello.reverse(othello.SIZE,i,setcolor);
                return ;
            }
        }
        //ˆê”Ô‘½‚­‚Æ‚ê‚é‚Æ‚±‚ë
        for(int y = 1+1;y <= othello.SIZE-1;y++){
            for(int x = 1+1;x <= othello.SIZE-1;x++){
                if(othello.canReverse(x,y,setcolor)){
                    count = othello.reverseCount(x,y,setcolor);
                    if(maxcount < count){maxx = x; maxy = y; maxcount = count;}
                    if(maxcount == count){
                        if(rd.nextInt(5) == 3){maxx = x; maxy = y;}
                        }
                }
            }
        }
        if(maxcount == 0){
            othello.passcount++;
            System.out.println("ƒpƒX"); 
            return;
        }
        othello.reverse(maxx,maxy,setcolor);
        return ;

	}
}