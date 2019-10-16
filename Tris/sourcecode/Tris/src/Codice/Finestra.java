package Codice;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Finestra implements ActionListener,KeyListener{
private JFrame frame;
private JPanel init;
private JPanel secPanel;
private boolean whosTime=true;
private Player p1,p2;
private JButton replayButton;
	
	public Finestra()
	{
		//iconX = new ImageIcon("x_icon.png");
		//iconO = new ImageIcon("o_icon.png");
		frame = new JFrame("Tris fim");
		modellaFinestra(frame);
		
	}
	
	public void modellaFinestra(JFrame frame)
	{

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int size = (int)screenSize.getHeight()/3;
		frame.setMinimumSize(new Dimension(size,size));	
		Center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InitPanel(frame,init);
		frame.setVisible(true);
	}
	
	public void Center(JFrame frame)
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = frame.getSize().width;
		int h = frame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		
		frame.setLocation(x, y);
		
	}
	public void InitPanel(JFrame frame,JPanel init)
	{
		JTextField t1 = new JTextField("Player 1");
		t1.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField t2 = new JTextField("Player 2");
		t1.setFont(new Font("Serif", Font.ITALIC, 20));
		t2.setHorizontalAlignment(JTextField.CENTER);
		t2.setFont(new Font("Serif", Font.ITALIC, 20));
		
		JLabel welcome = new JLabel("WELCOME TO TRIS");
		welcome.setFont(new Font("Serif", Font.BOLD, 20));
	//	welcome.setAlignmentX(CENTER_ALIGNMENT);
		//welcome.setHorizontalAlignment(JLabel.CENTER);
		//welcome.setVerticalAlignment(JLabel.CENTER);
		
		JLabel press = new JLabel("PRESS PLAY TO BEGIN");
		press.setFont(new Font("Serif", Font.PLAIN, 13));
		press.setHorizontalAlignment(JLabel.CENTER);
		//press.setVerticalAlignment(JLabel.CENTER);
		
		init = new JPanel();
		init.setLayout(new BoxLayout(init, BoxLayout.Y_AXIS));
		
		init.setBackground(Color.green);
		init.add(welcome);
		init.add(t1);
		init.add(t2);
		init.add(press);


		
		JButton b = new JButton(new AbstractAction("PLAY"){
			@Override
				public void actionPerformed(ActionEvent e)
				{
					SecondPanel(frame);
					p1 = new Player(t1.getText());
					p2 = new Player(t2.getText());
				}
		});
		b.setHorizontalAlignment(JButton.CENTER);
		b.setVerticalAlignment(JButton.CENTER);
		init.add(b);
		frame.add(init);
	}
	
	public void changeTime()
	{
		if(whosTime==true) whosTime=false;
		else
			whosTime=true;
	}
	
	public void SecondPanel(JFrame frame)
	{

		JButton[] buts = new JButton[9];
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
		secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(3, 3));
		secPanel.setBackground(Color.white);
		for(int i=0;i<9;i++)
		{
			Integer ab=i;
			buts[i] = new JButton(new AbstractAction(){
			@Override
				public void actionPerformed(ActionEvent e)
				{
				if(buts[ab].getName()!="") return;
				if(whosTime)
				{
					buts[ab].setName("x");
					buts[ab].setIcon(pathToImage("x_icon.png"));
					p1.setA(ab);
					if(p1.win()) 
						{
						System.out.println("p1 ha vinto");
						p1.incrementPoints();
						thirdPanel(frame, p1, p2);
						}
				}
					else
					{
						buts[ab].setIcon(pathToImage("o_icon.png"));
						buts[ab].setName("o");
						p2.setA(ab);
						if(p2.win())
							{
							System.out.println("p2 ha vinto");
							p2.incrementPoints();
							thirdPanel(frame, p1, p2);
							}
					}
				changeTime();
				if(p1.getFilled()>=5 || p2.getFilled()>=5)
					thirdPanel(frame, p1, p2);
					}
		});
			secPanel.add(buts[i]);
			buts[i].setBackground(Color.white);
			buts[i].setName("");
		}
		//resetButs(buts);
		frame.add(secPanel);
	}
	
	private void resetButs(JButton[] jb)
	{
		for(int i=0;i<9;i++)
		{
			jb[i].setName("");
		}
	}
	
	public void thirdPanel(JFrame frame,Player p1, Player p2)
	{
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
		JLabel lbl1 = new JLabel("WINNER - " + ((p1.win()||p2.win())?(p1.win()?p1.getName():p2.getName()):"NOBODY") );
		p1.resetPlayer();
		p2.resetPlayer();
		JPanel winnerP = new JPanel();
		//winnerP.setLayout(new BoxLayout(winnerP, BoxLayout.Y_AXIS));
		winnerP.setLayout(new GridLayout(3, 1));
		replayButton = new JButton(new AbstractAction("replay"){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SecondPanel(frame);
			}
	});
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		lbl1.setVerticalAlignment(JLabel.CENTER);
		
		JLabel lbl2 = new JLabel(p1.getName()+"("+p1.getPoints()+")"+"  -  "+p2.getName()+"("+p2.getPoints()+")" );
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		lbl2.setVerticalAlignment(JLabel.CENTER);
		
		lbl1.setFont(new Font("Serif", Font.BOLD, 30));
		//this.grabFocus();
		//frame.addKeyListener(this);
		
		replayButton.setText("Press me to replay");
		winnerP.add(lbl1);
		winnerP.add(lbl2);
		replayButton.setBackground(Color.GREEN);
		winnerP.add(replayButton);

		frame.add(winnerP);
		
	}
	
	public ImageIcon pathToImage(String path)
	{
		ImageIcon imageIcon=null;
		try {
			imageIcon = new ImageIcon(ImageIO.read(new File(path)));
		} catch (IOException e) {
			System.out.println("Can't find it");
			//File not found
		} 
		// load the image to a imageIcon
	    imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH)); 
	    return imageIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		System.out.println("amos!");
		if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
			System.out.println("amos");
			}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
