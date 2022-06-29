import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Othello extends Game implements MouseListener{
    final int SIZE = 8;
    int[][] othellodata;
    int piece = 0;
    int passcount = 0;
    int turnCount = 0;
    int doX ; int doY;
    int selectx;
    int selecty;
    double massw = 0 ;
    double massh = 0 ;
    boolean isWhiteTurn = true;
    private Player player1 = null;
    private Player player2 = null;

    Othello(Player _player1,Player _player2,GameCanvas _gameCanvas){
        player1 = _player1;
        player2 = _player2;
        player1.addOthello(this);
        player2.addOthello(this);
        addGameCanvas(_gameCanvas);
        OthelloStart();
        gameCanvas.update(this);
	gameCanvas.addMouseListener(this);
    }

    private void OthelloStart(){
        othellodata = new int[SIZE+2][SIZE+2];
        othellodata[SIZE/2][SIZE/2] = 2;
        othellodata[SIZE/2+1][SIZE/2] = 1;
        othellodata[SIZE/2][SIZE/2+1] = 1;
        othellodata[SIZE/2+1][SIZE/2+1] = 2;
        turnCount = 1;
    }

    public void play(){
            if(isWhiteTurn){System.out.println("白："+ turnCount +"ターン目です");}
            else{System.out.println("黒："+ turnCount +"ターン目です");}
            if(isWhiteTurn){
                player1.putOthello(1);
            }else{
                player2.putOthello(2);
            }
            gameCanvas.update(this);
            if(isFinished()){ System.out.println("終了"); }
    }

    boolean isFinished(){
        if(SIZE*SIZE <= piece || passcount >= 2){
           return true;
        }
        return false;
    }

    boolean canPut(int setcolor){
        for(int y = 1;y <= SIZE;y++){
            for(int x = 1;x <= SIZE;x++){
                if(canReverse(x,y,setcolor)){
                    return true;
                }
            }
        }
        return false;
    }

    void printBoard(){
        for(int i = 1;i <= SIZE;i++){
            System.out.printf("%2d",i);
        }
        System.out.println();
        for(int y = 1;y <= SIZE;y++){
            for(int x = 1;x <= SIZE    ;x++){
                switch(othellodata[x][y]){
                    case 0: System.out.print("?@"); break;
                    case 1: System.out.print("??"); break;
                    case 2: System.out.print("?Z"); break;
                }
            }
            System.out.printf("%2d\n",y);
        }
        for(int i = 1;i <= SIZE;i++){
            System.out.print("__");
        }
        System.out.println();
    }

    public void drawGame(double width ,double height ,Graphics g){
        double dx1,dy1;
        massw= width/SIZE;
        massh = height/SIZE;
        for(int i = 0;i <= SIZE;i++){
            dx1 =massw*i;
            g.drawLine((int)dx1,0,(int)dx1,(int)height);
        }
        for(int i = 0;i <= SIZE;i++){
            dy1 =massh*i;
            g.drawLine(0,(int)dy1,(int)width,(int)dy1);
        }
        for(int y = 1;y <= SIZE;y++){
            for(int x = 1;x <= SIZE;x++){
                switch(othellodata[x][y]){
                    case 1:
                        g.setColor(Color.white);
                        dx1 = massw*(x - 1);
                        dy1 = massh*(y - 1);
                        g.fillOval((int)dx1,(int)dy1,(int)massw,(int)massh);
                        break;
                    case 2:
                        g.setColor(Color.black);
                        dx1 = massw*(x - 1);
                        dy1 = massh*(y - 1);
                        g.fillOval((int)dx1,(int)dy1,(int)massw,(int)massh);
                        break;
                    default:;
                    }
            }
        }
    }

    void reverse(int x,int y,int setcolor){
        reverseLine(x,y,setcolor,0,-1);
        reverseLine(x,y,setcolor,1,-1);
        reverseLine(x,y,setcolor,1,0);
        reverseLine(x,y,setcolor,1,+1);
        reverseLine(x,y,setcolor,0,+1);
        reverseLine(x,y,setcolor,-1,+1);
        reverseLine(x,y,setcolor,-1,0);
        reverseLine(x,y,setcolor,-1,-1);
        piece++;
        passcount = 0;
    }

    void reverseLine(int x,int y,int setcolor,int addx,int addy){
        if(canReverseLine(x,y,setcolor,addx,addy) == false){
            return;
        }
        do{othellodata[x][y] = setcolor;
        x += addx; y += addy;
        }while(othellodata[x][y] != setcolor);
        return;
    }



    boolean canReverse(int x,int y,int setcolor){
       if(x <= 0 || SIZE < x || y <= 0 || SIZE < y){
            return false;
        }
        if(othellodata[x][y] != 0){
            return false;
        }
        if(canReverseLine(x,y,setcolor,0,-1)||
            canReverseLine(x,y,setcolor,1,-1)||
            canReverseLine(x,y,setcolor,1,0)||
            canReverseLine(x,y,setcolor,1,+1)||
            canReverseLine(x,y,setcolor,0,+1)||
            canReverseLine(x,y,setcolor,-1,+1)||
            canReverseLine(x,y,setcolor,-1,0)||
            canReverseLine(x,y,setcolor,-1,-1)){
                return true;
        }
        return false;
    }

    boolean canReverseLine(int x,int y,int setcolor,int addx,int addy){

        x += addx; y += addy;
        if(othellodata[x][y] == 0 ||othellodata[x][y] == setcolor){
            return false;
        }
        while(othellodata[x][y] != 0){
            if(othellodata[x][y] == setcolor){
                return true;
            }
        x += addx; y += addy;
        }
        return false;
    }

    int reverseLineCount(int x,int y,int setcolor,int addx,int addy){
        int count = 0;
        if(canReverseLine(x,y,setcolor,addx,addy) == false){
            return count;
        }
        x += addx; y += addy;
        while(othellodata[x][y] != setcolor){
            count++;
            x += addx; y += addy;
        }
        return count;
    }

    int reverseCount(int x,int y,int setcolor){
        return 1 +
        reverseLineCount(x,y,setcolor,0,-1)+
        reverseLineCount(x,y,setcolor,1,-1)+
        reverseLineCount(x,y,setcolor,1,0)+
        reverseLineCount(x,y,setcolor,1,+1)+
        reverseLineCount(x,y,setcolor,0,+1)+
        reverseLineCount(x,y,setcolor,-1,+1)+
        reverseLineCount(x,y,setcolor,-1,0)+
        reverseLineCount(x,y,setcolor,-1,-1);
    }	
	public void mousePressed(MouseEvent e) {
		doX = e.getX();
		doY = e.getY();
                selectx = (doX/(int)massw)+1;
                selecty = (doY/(int)massh)+1;
		play();
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}