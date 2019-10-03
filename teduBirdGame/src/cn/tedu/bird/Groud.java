package cn.tedu.bird;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Groud extends JPanel{
BufferedImage image;
int x,y;
int width,height;

public Groud()throws Exception{
	image=ImageIO.read(getClass().getResource("ground.png"));
	width=image.getWidth();
	height=image.getHeight();
	x=0;
	y=500;
}
public void step(){
	x--;
	if(x==-109){
		x=0;
	}
}
}
