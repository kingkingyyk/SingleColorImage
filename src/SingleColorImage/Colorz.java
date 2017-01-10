package SingleColorImage;

import java.awt.Color;

public class Colorz extends Color {
	private static final long serialVersionUID = 5179667856311388626L;
	private String n;
	
	public Colorz(int r, int g, int b) {
		super(r, g, b);
	}
	
	public Colorz (Color c, String name) {
		super(c.getRed(),c.getGreen(),c.getBlue());
		this.n=name;
	}
	
	public String toString() {
		return this.n;
	}
}
