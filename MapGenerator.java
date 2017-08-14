import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mapgenerator {
public int map[][];
public int bwidth;
public int bheight;

public Mapgenerator(int row,int col)
{
map=new int [row][col];
for(int i=0;i<map[0].length;i++)
{
	for(int j=0;j<map[0].length;j++)
	{
		map[i][j]=1;
	}
}
bwidth=548/col;
bheight=150/row;
}
public void draw(Graphics2D g)
{
	
	for(int i=0;i<map[0].length;i++)
	{
		for(int j=0;j<map[0].length;j++)
		{
	if(map[i][j]>0)
	{
		g.setColor(Color.WHITE);
		g.fillRect(j*bwidth+80,i*bheight+50,bwidth,bheight );
	
	    g.setStroke(new BasicStroke(5));
	    g.setColor(Color.BLACK);
	    g.drawRect(j*bwidth+80,i*bheight+50,bwidth,bheight );

	}
		}
	}
}

public void setBrickvalue(int value,int row,int col)
{
	map[row][col]=value;
}

}
