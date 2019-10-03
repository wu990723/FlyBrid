package cn.tedu.bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirdGame extends JPanel{
public static void main(String[] args) throws Exception{
	//创建一个窗体
	JFrame frame=new JFrame("飞翔的小鸟");
	BirdGame game=new BirdGame();
	frame.add(game);
	//设置窗体大小
	frame.setSize(430, 670);
	//设置窗口显示在屏幕中央
	frame.setLocationRelativeTo(null);
	//用户单击窗体关闭按钮执行的操作
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//设置窗体可见
	frame.setVisible(true);
	
	game.action();
}
Bird bird;
Column column1;
Column column2;
Groud groud;
BufferedImage background;

//boolean gameOver;
BufferedImage gameOverImage;
BufferedImage startImage;
int state;
public static final int START=0;
public static final int RUNNING=1;
public static final int GAME_OVER=2;

int score;

/*初始化birdgame属性变量*/
public BirdGame()throws Exception{
	
	state=START;
	startImage=ImageIO.read(getClass().getResource("start.png"));
	//gameOver =false;
	gameOverImage=ImageIO.read(getClass().getResource("gameover.png"));
	
	bird=new Bird();
	column1=new Column(1);
	column2=new Column(2);
	groud=new Groud();
	background=ImageIO.read(getClass().getResource("bg.png"));
}

public void paint(Graphics g){
	
	
	switch(state){
	case GAME_OVER:
	g.drawImage(gameOverImage,0,0,null);
	break;
	case START:
	g.drawImage(startImage,0,0,null);
	break;
	}
	g.drawImage(background, 0, 0, null);
	g.drawImage(column1.image, column1.x-column1.width/2, column1.y-column1.height/2,null);
	g.drawImage(column2.image, column2.x-column2.width/2, column2.y-column2.height/2,null);
	g.drawImage(groud.image, groud.x, groud.y, null);
	g.drawImage(bird.image, bird.x-bird.width/2, bird.y-bird.height/2,null);
Graphics2D g2=(Graphics2D) g;
g2.rotate(-bird.alp,bird.x,bird.y);
g.drawImage(bird.image, bird.x-bird.width/2, bird.y-bird.height/2,null);
g2.rotate(bird.alp, bird.x, bird.y);

Font f=new Font(Font.SANS_SERIF,Font.BOLD,40);
g.setFont(f);
g.drawString(""+score,40,60);
g.setColor(Color.WHITE);
g.drawString(""+score, 40-3, 60-3);

}
public void action() throws Exception{
	MouseListener l=new MouseAdapter() {
		public void mousePressed(MouseEvent e){
			//bird.flappy();
			try{
				switch(state){
				case GAME_OVER:
					column1=new Column(1);
					column2=new Column(2);
					bird=new Bird();
					score=0;
					state=START;
					break;
				case START:
					state =RUNNING;
				case RUNNING:
					bird.flappy();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	};
	addMouseListener(l);
	
	
	while(true){	
		//if(!gameOver){
//groud.step();
//column1.step();
//column2.step();
//bird.step();
//bird.fly();
//}
	//if(bird.hit(groud) || bird.hit(column1) || bird.hit(column2)){
		//gameOver=true;
	//}
//if(bird.x==column1.x || bird.x==column2.x){
	//score++;
//}
		switch(state){
		case START:
			bird.fly();
			groud.step();
			break;
		case RUNNING:
			groud.step();
			column1.step();
			column2.step();
			bird.step();
			bird.fly();
			
			if(bird.x==column1.x || bird.x==column2.x){
				score++;
			}
			if(bird.hit(groud) || bird.hit(column1) || bird.hit(column2)){
				state=GAME_OVER;
			}
			break;
		}
repaint();
Thread.sleep(1000/30);
}
}



}
