import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlayofWallBreaker extends JPanel implements KeyListener,ActionListener  {

private boolean play=false;
	private int score=0;
	private int bricksno=18;
	
	private Timer t;
	private int delay=5;
	
	private int playerx=310;
	
	private int ballposx=320;
	private int ballposy=350;
	private int ballxdir=-1;
	private int ballydir=-2;
	
	private Mapgenerator mapg;
	
	public GamePlayofWallBreaker()
	{
		mapg=new Mapgenerator(6,3);
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	t=new Timer(delay,this);
	t.start();
	}
	
	public void paint(Graphics g){
		
		//background
		g.setColor(Color.BLACK);
		g.fillRect(-1,-1,692,592);
		
		//drawing map
		mapg.draw((Graphics2D) g);
		
		//boarders
		g.setColor(Color.green);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		//score
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,25));;
		g.drawString(""+score,590,30);
		
		//the paddle
		g.setColor(Color.BLUE);
		g.fillRect(playerx,550,100,8);
		
		//ball
		g.setColor(Color.MAGENTA);
		g.fillOval(ballposx,ballposy,20,20);
	
	   //game over
		if(ballposy>570){
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,30));;
			g.drawString("Game Over , Score:"+score,190,300);
		
			g.setFont(new Font("arial",Font.BOLD,25));;
			g.drawString("Press Enter to Restart",230,350);
			
		}
		
        g.dispose();	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		
		if(play)
		{
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8)))
			{
				ballydir=-ballydir;
			}
			
			for(int i=0;i<mapg.map.length;++i)
			{
				A:for(int j=0;j<mapg.map[0].length;++j)
				{
					
				if(mapg.map[i][j]>0)
				{int brickx=j*mapg.bwidth+80;
				int bricky=i*mapg.bheight+50;
				int bwidth=mapg.bwidth;
				int bheight=mapg.bheight;
				
				Rectangle rect=new Rectangle(brickx,bricky,bwidth,bheight);
				Rectangle ballrect=new Rectangle(ballposx,ballposy,20,20);
				Rectangle brickrect=rect;
				
				if(ballrect.intersects(brickrect))
				{
				mapg.setBrickvalue(0,i,j);
				bricksno--;
				score+=5;
				
				if(ballposx+ 19<=brickrect.x || ballposx+1>=brickrect.x + brickrect.width)
				{
					ballxdir=-ballxdir;
				}
				else
				{
					ballydir=-ballydir;
				}
				
				break A;
				
				}
				}
				}	
			}
			
			ballposx+=ballxdir;
			ballposy+=ballydir;
			//left
			if(ballposx<0)
			{ballxdir=-ballxdir;}
            //right
			if(ballposy<0)
			{ballydir=-ballydir;}
            //up
			if(ballposx>690)
			{ballxdir=-ballxdir;}

		}
			
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(!play)
			{
				play=true;
				score=0;
				ballposx=320;
				ballposy=350;
				ballxdir=-1;
				ballydir=-2;
				playerx=310;
				bricksno=18;
				mapg=new Mapgenerator(9,5);
				repaint();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerx>=600)
			{playerx=600;}
			else
			{moveright();}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerx<=10)
			{playerx=10;}
			else
			{moveleft();}
		}
	}

	public void moveright()
	{
		play=true;
		playerx+=20;
		}

	public void moveleft()
	{
		play=true;
		playerx-=20;
		}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	}
