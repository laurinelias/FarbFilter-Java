package Pixelfilter;

import java.awt.image.BufferedImage;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class MonochromeFilter extends PixelFilter {

	/**
	 * Berechnet den Helligkeitswert (Grauwert) einer Eingabefarbe
	 * 
	 * @param initColor Originalfarbwert eines Pixels
	 * @return Helligkeitswert des Originalfarbwerts eines Pixels
	 */
	@Override
	protected int calculate(int initColor) {

		int red;
		int blue;
		int green;

		int monochrome;
		int brightness;

		red = initColor / (256 * 256); // x^2
		green = (initColor / 256) % 256; // x
		blue = (initColor % 256); // c

		brightness = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
		monochrome = (brightness * (65536 + 256 + 1));

		return monochrome;
	}

	/**
	 * Methode, die 2. Methode aufruft, wenn keine Maske mitgegeben wurde
	 * 
	 * @param initImage Eingabebild
	 * @return Kopie des Eingabebilds, das aus den entsprechenden Helligkeitswerten
	 *         besteht
	 */
	@Override
	public BufferedImage process(BufferedImage initImage) {
		return this.process(initImage, null);
	}

	/**
	 * Konstruktor, der 2. Konstruktor aufruft, wenn keine Maske mitgegeben wurde
	 * 
	 * @param initImage Eingabebild
	 * @param mask      Maske
	 * @return Kopie des Eingabebilds, das aus den entsprechenden Helligkeitswerten
	 *         besteht
	 */
	@Override
	public BufferedImage process(BufferedImage initImage, BufferedImage mask) {
		int width;
		int height;
		int initColor;

		width = initImage.getWidth();
		height = initImage.getHeight();

		// set size and type of edit image
		BufferedImage editImage = new BufferedImage(width, height, initImage.getType());

		for (int y = 0; y < height; y++) {

			for (int x = 0; x < width; x++) {
				initColor = initImage.getRGB(x, y);
				if (mask == null) {
					editImage.setRGB(x, y, calculate(initColor));
				} else if (mask.getRGB(x, y) == -1) {
					editImage.setRGB(x, y, calculate(initColor));
				} else {
					editImage.setRGB(x, y, initColor);
				}
			}
		}
		return editImage;
	}

}
