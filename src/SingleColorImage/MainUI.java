package SingleColorImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JSlider;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MainUI extends JFrame {
	private static final long serialVersionUID = 6309515259530069697L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblPicture;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblLuminosity;
	private JLabel lblRed;
	private JLabel lblGreen;
	private JLabel lblBlue;
	private JSlider sliderRed;
	private JSlider sliderGreen;
	private JSlider sliderBlue;
	private JLabel lblSensitivity;
	private JLabel lblLuminosityValue;
	private JLabel lblRedValue;
	private JLabel lblGreenValue;
	private JLabel lblBlueValue;
	private JSlider sliderLuminosity;
	private BufferedImage originalImage;
	private BufferedImage resultImage;
	private JComboBox<Colorz> comboBoxColor;

	public MainUI() {
		setTitle(Constants.APP_NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 300, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 26, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		lblSensitivity = new JLabel("Sensitivity");
		GridBagConstraints gbc_lblSensitivity = new GridBagConstraints();
		gbc_lblSensitivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblSensitivity.gridx = 0;
		gbc_lblSensitivity.gridy = 0;
		panel_2.add(lblSensitivity, gbc_lblSensitivity);
		
		lblLuminosity = new JLabel("Luminosity");
		lblLuminosity.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblLuminosity = new GridBagConstraints();
		gbc_lblLuminosity.anchor = GridBagConstraints.EAST;
		gbc_lblLuminosity.insets = new Insets(0, 0, 5, 5);
		gbc_lblLuminosity.gridx = 0;
		gbc_lblLuminosity.gridy = 1;
		panel_2.add(lblLuminosity, gbc_lblLuminosity);
		
		sliderLuminosity = new JSlider();
		GridBagConstraints gbc_sliderLuminosity = new GridBagConstraints();
		gbc_sliderLuminosity.fill = GridBagConstraints.BOTH;
		gbc_sliderLuminosity.insets = new Insets(0, 0, 5, 5);
		gbc_sliderLuminosity.gridx = 1;
		gbc_sliderLuminosity.gridy = 1;
		panel_2.add(sliderLuminosity, gbc_sliderLuminosity);
		sliderLuminosity.setMinimum(1);
		sliderLuminosity.setSnapToTicks(true);
		sliderLuminosity.setPaintLabels(true);
		sliderLuminosity.setValue(128);
		sliderLuminosity.setMaximum(255);
		sliderLuminosity.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateSliderValue(1);
				if (originalImage!=null) {
					calculateImageMatrix();
					SingleColorImage.calculateBW(sliderLuminosity.getValue());
					drawImageInFrame();
				}
			};
		
		});
		
		lblLuminosityValue = new JLabel("128");
		GridBagConstraints gbc_lblLuminosityValue = new GridBagConstraints();
		gbc_lblLuminosityValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblLuminosityValue.gridx = 2;
		gbc_lblLuminosityValue.gridy = 1;
		panel_2.add(lblLuminosityValue, gbc_lblLuminosityValue);
		
		lblRed = new JLabel("Red");
		lblRed.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblRed = new GridBagConstraints();
		gbc_lblRed.anchor = GridBagConstraints.EAST;
		gbc_lblRed.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed.gridx = 0;
		gbc_lblRed.gridy = 2;
		panel_2.add(lblRed, gbc_lblRed);
		
		sliderRed = new JSlider();
		sliderRed.setMinimum(1);
		sliderRed.setValue(22);
		sliderRed.setSnapToTicks(true);
		sliderRed.setPaintLabels(true);
		sliderRed.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateSliderValue(2);
				if (originalImage!=null) {
					calculateImageMatrix();
					SingleColorImage.calculateBW(sliderLuminosity.getValue());
					drawImageInFrame();
				}
			};
		
		});
		
		GridBagConstraints gbc_sliderRed = new GridBagConstraints();
		gbc_sliderRed.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderRed.insets = new Insets(0, 0, 5, 5);
		gbc_sliderRed.gridx = 1;
		gbc_sliderRed.gridy = 2;
		panel_2.add(sliderRed, gbc_sliderRed);
		
		lblRedValue = new JLabel("New label");
		GridBagConstraints gbc_lblRedValue = new GridBagConstraints();
		gbc_lblRedValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblRedValue.gridx = 2;
		gbc_lblRedValue.gridy = 2;
		panel_2.add(lblRedValue, gbc_lblRedValue);
		
		lblGreen = new JLabel("Green");
		lblGreen.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGreen = new GridBagConstraints();
		gbc_lblGreen.anchor = GridBagConstraints.EAST;
		gbc_lblGreen.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreen.gridx = 0;
		gbc_lblGreen.gridy = 3;
		panel_2.add(lblGreen, gbc_lblGreen);
		
		sliderGreen = new JSlider();
		sliderGreen.setMinimum(1);
		sliderGreen.setValue(71);
		GridBagConstraints gbc_sliderGreen = new GridBagConstraints();
		gbc_sliderGreen.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderGreen.insets = new Insets(0, 0, 5, 5);
		gbc_sliderGreen.gridx = 1;
		gbc_sliderGreen.gridy = 3;
		panel_2.add(sliderGreen, gbc_sliderGreen);
		sliderGreen.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateSliderValue(3);
				if (originalImage!=null) {
					calculateImageMatrix();
					SingleColorImage.calculateBW(sliderLuminosity.getValue());
					drawImageInFrame();
				}
			};
		
		});
		
		lblGreenValue = new JLabel("New label");
		GridBagConstraints gbc_lblGreenValue = new GridBagConstraints();
		gbc_lblGreenValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblGreenValue.gridx = 2;
		gbc_lblGreenValue.gridy = 3;
		panel_2.add(lblGreenValue, gbc_lblGreenValue);
		
		lblBlue = new JLabel("Blue");
		GridBagConstraints gbc_lblBlue = new GridBagConstraints();
		gbc_lblBlue.anchor = GridBagConstraints.EAST;
		gbc_lblBlue.insets = new Insets(0, 0, 0, 5);
		gbc_lblBlue.gridx = 0;
		gbc_lblBlue.gridy = 4;
		panel_2.add(lblBlue, gbc_lblBlue);
		
		sliderBlue = new JSlider();
		sliderBlue.setMinimum(1);
		sliderBlue.setValue(7);
		GridBagConstraints gbc_sliderBlue = new GridBagConstraints();
		gbc_sliderBlue.insets = new Insets(0, 0, 0, 5);
		gbc_sliderBlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderBlue.gridx = 1;
		gbc_sliderBlue.gridy = 4;
		panel_2.add(sliderBlue, gbc_sliderBlue);
		sliderBlue.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateSliderValue(4);
				if (originalImage!=null) {
					calculateImageMatrix();
					SingleColorImage.calculateBW(sliderLuminosity.getValue());
					drawImageInFrame();
				}
			};
		
		});
		
		lblBlueValue = new JLabel("New label");
		GridBagConstraints gbc_lblBlueValue = new GridBagConstraints();
		gbc_lblBlueValue.gridx = 2;
		gbc_lblBlueValue.gridy = 4;
		panel_2.add(lblBlueValue, gbc_lblBlueValue);

		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblPicture = new JLabel("");
		lblPicture.setForeground(Color.WHITE);
		lblPicture.setBackground(Color.WHITE);
		lblPicture.setBounds(2, 2, 432, 229);
		panel.add(lblPicture);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{179, 65, 0, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnImport = new JButton("Import");
		GridBagConstraints gbc_btnImport = new GridBagConstraints();
		gbc_btnImport.insets = new Insets(0, 0, 0, 5);
		gbc_btnImport.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnImport.gridx = 0;
		gbc_btnImport.gridy = 0;
		panel_1.add(btnImport, gbc_btnImport);
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc=new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (fc.showOpenDialog(MainUI.this)==JFileChooser.APPROVE_OPTION) {
					try {
						originalImage=ImageIO.read(fc.getSelectedFile());
						calculateImageMatrix();
						SingleColorImage.calculateBW(sliderLuminosity.getValue());
						drawImageInFrame();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,"Error reading picture. "+e.getMessage(),Constants.APP_NAME,JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		comboBoxColor = new JComboBox<>();
		comboBoxColor.addItem(Constants.COLOR_BLACK);
		comboBoxColor.addItem(Constants.COLOR_BLUE);
		comboBoxColor.addItem(Constants.COLOR_CYAN);
		comboBoxColor.addItem(Constants.COLOR_DARK_GRAY);
		comboBoxColor.addItem(Constants.COLOR_GREEN);
		comboBoxColor.addItem(Constants.COLOR_MAGENTA);
		comboBoxColor.addItem(Constants.COLOR_ORANGE);
		comboBoxColor.addItem(Constants.COLOR_PINK);
		comboBoxColor.addItem(Constants.COLOR_RED);
		comboBoxColor.addItem(Constants.COLOR_YELLOW);
		comboBoxColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (resultImage!=null) drawImageInFrame();
			}
			
		});
		
		GridBagConstraints gbc_comboBoxColor = new GridBagConstraints();
		gbc_comboBoxColor.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxColor.gridx = 1;
		gbc_comboBoxColor.gridy = 0;
		panel_1.add(comboBoxColor, gbc_comboBoxColor);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (resultImage==null) JOptionPane.showMessageDialog(null,"No image to export!",Constants.APP_NAME,JOptionPane.ERROR_MESSAGE);
				else {
					JFileChooser fc=new JFileChooser();
					fc.setFileFilter(new FileNameExtensionFilter("PNG Image","png"));
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					if (fc.showSaveDialog(MainUI.this)==JFileChooser.APPROVE_OPTION) {
						try {
							File f=fc.getSelectedFile();
							if (!f.getAbsolutePath().endsWith(".png")) f=new File(f.getAbsolutePath()+".png");
							if (f.exists()) Files.delete(f.toPath());
							ImageIO.write(resultImage, "png", f);
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,"Error exporting picture. "+ex.getMessage(),Constants.APP_NAME,JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.anchor = GridBagConstraints.EAST;
		gbc_btnExport.fill = GridBagConstraints.VERTICAL;
		gbc_btnExport.gridx = 2;
		gbc_btnExport.gridy = 0;
		panel_1.add(btnExport, gbc_btnExport);
		
		updateSliderValue(0);
	}

	private void calculateImageMatrix() {
		SingleColorImage.ImagePixels=new Color[originalImage.getWidth()][originalImage.getHeight()];
		SingleColorImage.ImagePixelsGreyscale=new int[originalImage.getWidth()][originalImage.getHeight()];
		for (int i=0;i<SingleColorImage.ImagePixels.length;i++) {
			for (int i2=0;i2<SingleColorImage.ImagePixels[i].length;i2++) {
				SingleColorImage.ImagePixels[i][i2]=new Color(originalImage.getRGB(i,i2));
				SingleColorImage.ImagePixelsGreyscale[i][i2]=(int)((SingleColorImage.ImagePixels[i][i2].getRed()*sliderRed.getValue())
														+  SingleColorImage.ImagePixels[i][i2].getGreen()*sliderGreen.getValue()
														+  SingleColorImage.ImagePixels[i][i2].getBlue()*sliderBlue.getValue())/(sliderRed.getValue()+sliderGreen.getValue()+sliderBlue.getValue());
				//0.2126R + 0.7152G + 0.0722B
			}
		}
		SingleColorImage.calculateBW(sliderLuminosity.getValue());
	}
	
	private void drawImageInFrame() {
		resultImage = new BufferedImage(SingleColorImage.ImagePixelsBW.length,SingleColorImage.ImagePixelsBW[0].length, BufferedImage.TYPE_INT_ARGB);
		for (int i=0;i<SingleColorImage.ImagePixelsBW.length;i++) {
			for (int i2=0;i2<SingleColorImage.ImagePixelsBW[0].length;i2++) {
				if (SingleColorImage.ImagePixelsBW[i][i2]==0) resultImage.setRGB(i,i2,((Colorz)comboBoxColor.getSelectedItem()).getRGB());
				else  resultImage.setRGB(i,i2,Color.WHITE.getRGB());
			}
		}
		
		double resizeFactor=Math.min((panel.getWidth()+0.0)/resultImage.getWidth(),(panel.getHeight()+0.0)/resultImage.getHeight());
		ImageIcon ic=new ImageIcon(resultImage.getScaledInstance((int)(resultImage.getWidth()*resizeFactor), (int)(resultImage.getHeight()*resizeFactor),Image.SCALE_SMOOTH));
		lblPicture.setSize(ic.getIconWidth(),ic.getIconHeight());
		lblPicture.setText("");
		lblPicture.setIcon(ic);
		
		int offsetX=(panel.getWidth()-ic.getIconWidth())/2;
		int offsetY=(panel.getHeight()-ic.getIconHeight())/2;
		lblPicture.setLocation(offsetX+2, offsetY+2);
	}
	
	private void updateSliderValue (int id) {
		switch (id) {
			case 0 : {
				lblLuminosityValue.setText(String.format("%.2f%%", (sliderLuminosity.getValue()*100)/(double)sliderLuminosity.getMaximum()));
				lblRedValue.setText(String.format("%.2f%%", (sliderRed.getValue()*100)/(double)sliderRed.getMaximum()));
				lblGreenValue.setText(String.format("%.2f%%", (sliderGreen.getValue()*100)/(double)sliderGreen.getMaximum()));
				lblBlueValue.setText(String.format("%.2f%%", (sliderBlue.getValue()*100)/(double)sliderBlue.getMaximum()));
				break;
			}
			case 1 : {
				lblLuminosityValue.setText(String.format("%.2f%%", (sliderLuminosity.getValue()*100)/(double)sliderLuminosity.getMaximum()));
				break;
			}
			case 2 : {
				lblRedValue.setText(String.format("%.2f%%", (sliderRed.getValue()*100)/(double)sliderRed.getMaximum()));
				break;
			}
			case 3 : {
				lblGreenValue.setText(String.format("%.2f%%", (sliderGreen.getValue()*100)/(double)sliderGreen.getMaximum()));
				break;
			}
			case 4 : {
				lblBlueValue.setText(String.format("%.2f%%", (sliderBlue.getValue()*100)/(double)sliderBlue.getMaximum()));
				break;
			}
		}
	}
	
}
