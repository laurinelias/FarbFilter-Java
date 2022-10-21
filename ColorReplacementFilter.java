package Pixelfilter;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.color.*;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class ColorReplacementFilter extends PixelFilter {
	private int colorToChangeTo;// ROT, BLAU, etc...
	private int colorThatGetsChanged; // Farbe des Pixels
	private static final Color GREEN;  
	private static int randomColor = ((int) (Math.random() * 0xFFFFFF)) + 0xFF000000;

	/**
	 * Konstruktor, der 2. Konstruktor aufruft, wenn keine zu ersetzende Farbe
	 * {@code replacementColor} gesetzt wurde. Für replacementColor wird eine
	 * zufällige Farbe {@code randomColor} gesetzt.
	 * 
	 * @param targetColor Farbe des zu ersetztenden Pixels
	 */
	public ColorReplacementFilter(int targetColor) {
		this(targetColor, randomColor);
	}

	/**
	 * Konstruktor, der den Attributen die entsprechenden Werte zuweist.
	 * 
	 * @param targetColor      Farbe des zu ersetztenden Pixels
	 * @param replacementColor Farbe die gesetzt werden soll
	 */
	public ColorReplacementFilter(int targetColor, int replacementColor) {
		this.colorToChangeTo = replacementColor;
		this.colorThatGetsChanged = targetColor;
	}
	
	public ColorReplacementFilter(color) {
		
	}

	/**
	 * Methode, die 2. Methode process aufruft, wenn keine Maske (@code mask)
	 * mitgegeben wurde.
	 * 
	 * 
	 * @param image1 zu bearbeitendes Bild
	 * @return Bildkopie, bei der die zu ersetzende Farbe ersetzt wurde.
	 */
	@Override
	public BufferedImage process(BufferedImage image1) {
		return this.process(image1, null);

	}

	/**
	 * Methode, die eine Kopie eines Eingabebilds bearbeitet zurückgibt.
	 * 
	 * @param image1 zu bearbeitendes Bild
	 * @param mask   Bild, das angibt, welche Bildpunkte bearbeitet werden sollen
	 *               und welche nicht.
	 * @return Bildkopie, bei der die zu ersetzende Farbe ersetzt wurde.
	 */
	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage mask) {
		BufferedImage edit = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());

		int height = image1.getHeight();
		int width = image1.getWidth();

		int pixelColor;

		for (int b = 0; b < height; b++) {
			for (int a = 0; a < width; a++) {
				pixelColor = image1.getRGB(a, b);
				if (mask == null) {
					edit.setRGB(a, b, calculate(pixelColor));
				} else if (mask.getRGB(a, b) == -1) {
					edit.setRGB(a, b, calculate(pixelColor));
				} else {
					edit.setRGB(a, b, pixelColor);
				}
			}
		}
		return edit;
	}

	/**
	 * Methode um den Farbwert des @param pixelColor zu ändern, wenn dieser mit der
	 * Farbe übereinstimmt, welche geändert werden soll
	 */
	protected int calculate(int pixelColor) {
		if (pixelColor == colorThatGetsChanged) {

			return colorToChangeTo;
		}

		return pixelColor;
	}
	protected int calculate(int pixelColor,Color farbe) {
		if (pixelColor == colorThatGetsChanged) {

			return farbe.getRGB();
		}

		return pixelColor;
	}

}
