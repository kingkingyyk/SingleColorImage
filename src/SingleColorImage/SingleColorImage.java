package SingleColorImage;

import java.awt.Color;

import javax.swing.UIManager;

public class SingleColorImage {
	
	public static Color [][] ImagePixels;
	public static int [][] ImagePixelsGreyscale;
	public static int [][] ImagePixelsBW;
	
	public static void calculateBW(int threshold) {
		ImagePixelsBW=new int [ImagePixelsGreyscale.length][ImagePixelsGreyscale[0].length];
		for (int i=0;i<ImagePixelsGreyscale.length;i++)
			for (int i2=0;i2<ImagePixelsGreyscale[0].length;i2++)
				ImagePixelsBW[i][i2]=(ImagePixelsGreyscale[i][i2]/threshold);
	}
	
	public static void main (String [] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		MainUI u=new MainUI();
		u.setLocationRelativeTo(null);
		u.setVisible(true);
	}
	
}
