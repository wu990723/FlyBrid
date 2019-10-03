package cn.tedu.bird;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bird extends JPanel{
	BufferedImage image;
	int x,y;
	int width,height;
	int size;
	
	double g;
	double t;
	double v0;
	double speed;
	double s;
	double alp;
	
	BufferedImage[] images;
	int index;
	public Bird()throws Exception{
		image=ImageIO.read(getClass().getResource("0.png"));
		width=image.getWidth();
		height=image.getHeight();
		x=132;
		y=280;
		size=40;
		
		g=4;
		v0=20;
		t=0.25;
		speed=v0;
		s=0;
		alp=0;
		
		images=new BufferedImage[8];
		for(int i=0;i<images.length;i++){
			images[i]=ImageIO.read(getClass().getResource(i+".png"));
		}
		index=0;
	}
	
	public void step(){
double v0=speed;
s=v0*t+g*t*t/2;
y=y-(int)s;
double v=v0-g*t;
speed=v;
alp=Math.atan(s/8);
}
	public void fly(){
		index++;
		image=images[index/12%8];
	}
	public void flappy(){
		speed=v0;
	}
	
	public boolean hit(Groud groud){
		boolean hit=y+size/2>groud.y;
		if(hit){
			y=groud.y-size/2;
		}
		return hit;
		
	}
	
	public boolean hit(Column column){
		if(x>column.x-column.width/2-size/2 && x<column.x+column.width/2+size/2){
			if(y>column.y-column.gap/2+size/2 && y<column.y+column.gap/2-size/2){
				return false;
			}
			return true;
		}
		return false;
		
	}
}
