package cn.tedu.bird;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Column extends JPanel{
	BufferedImage image;
	int x,y;
	int width,height;
	int gap;
	int distance;
	
	Random rm=new Random();
	public Column(int n)throws Exception{
	image=ImageIO.read(getClass().getResource("column.png"));
	width=image.getWidth();
	height=image.getHeight();
	gap=144;
	distance=245;
	x=550+(n-1)*distance;
	y=rm.nextInt(218)+132;
	}
	
	public void step(){
		x--;
		if(x==-width/2){
			x=distance*2-width/2;
			y=rm.nextInt(218)+123;
		}
	}
}
