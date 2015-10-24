package mulderslab.ArcxelSimulator;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int DELAY = 100;
    private Timer timer;
    public BufferedImage image ;
    public static int sizex= 400;
    public static int sizey = 400;
    int lastx = 0, lasty = 0;
    int c = 0;
    public Surface() throws IOException {
//    	image =  new ImageIcon("science.jpg").getImage();
    	image =(BufferedImage) (ImageCompressor.resizeImageIcon(new ImageIcon("science.jpg"), new Integer(40), new Integer( 40)).getImage());
        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {
    	c = c+1;
    	Color color = Color.WHITE;
    	switch(c%3)
    	{
    	case 0:
    		color = Color.RED;
    		break;
    	case 1: 
    		color = Color.GREEN;
    		break;
    	case 2: 
    		color = Color.BLUE;
    		break;
    	}
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(color);

        int w = getWidth();
        int h = getHeight();

        Random r = new Random();

        for (int i = 0; i < w/10; i++) {
        	int x = Math.abs(i) % w*10;
        	for(int j=0; j<h/10;j++)
        	{
        		int y =  Math.abs(j) % h*10;
        		g2d.setPaint(Color.white);
        		g2d.drawRect(x, y, 9, 9);
        		
        		int[][] result = convertTo2DUsingGetRGB(image);
        		if(i<image.getHeight() && j < image.getWidth())
        		color = new Color(result[i][j]);
        		g2d.setPaint(color);
        		g2d.fillRect(x, y, 9, 9);
//        		if(i==c%40 && j ==i)
//            		g2d.fillRect(x, (c%40) % h*10, 9, 9);
        	}
        	
            
        }
//        g2d.drawImage(image, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
           for (int col = 0; col < width; col++) {
              result[row][col] = image.getRGB(row, col);
           }
        }

        return result;
     }

}

public class Main extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() throws IOException {

        initUI();
    }

    private void initUI() throws IOException {

        final Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Points");
        setSize(Surface.sizex, Surface.sizey);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex;
				try {
					ex = new Main();
					ex.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
    }
}