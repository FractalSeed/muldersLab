package mulderslab.ArcxelSimulator;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements Runnable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long DELAY =10000;
    public BufferedImage image ;
    public static int sizex = 200;
    public static int sizey = 200;
    public static int CELLSIZE = 10;
    public static int nCELL = sizex/CELLSIZE;
    int lastx = 0, lasty = 0;
    int c = 0;
    int[][] result;
    Thread t;
    public Surface() throws IOException {
//    	image =  new ImageIcon("science.jpg").getImage();
    	image =(BufferedImage) (ImageCompressor.resizeImageIcon(new ImageIcon("science.jpg"), new Integer(nCELL), new Integer( nCELL)).getImage());
    	result = convertTo2DUsingGetRGB(image);
    	t = new Thread(this);
    	t.start();
    }


    private void doDrawing(Graphics g) {
    	c = (c+1)%nCELL;
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
        g2d.clearRect(0, 0, w, h);
        for (int i = 0; i < w/CELLSIZE; i++) {
        	int x = i % w*CELLSIZE;
        	for(int j=c; j<h/CELLSIZE;j++)
        	{
        		int y = j % h*CELLSIZE;
        		g2d.setPaint(Color.white);
        		g2d.drawRect(x, y, CELLSIZE, CELLSIZE);
        		
        		
        		if(c==j)
        		color = new Color(result[i][j]);
        		else
        		color = Color.white;
        		g2d.setPaint(color);
        		g2d.fillRect(x, y, CELLSIZE, CELLSIZE);
        	}
        g2d.setPaint(Color.RED);
        byte[] data = Long.toString(DELAY).getBytes();
        g2d.drawBytes(data, 0	, data.length, 10, 10);
            
        }
       
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
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

	@Override
	public void run() {
		while(true) {
			repaint();
			DELAY = DELAY+1000;
			long i = DELAY * 1;
			while(i>0) {
				i--;
			}
		
		}
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