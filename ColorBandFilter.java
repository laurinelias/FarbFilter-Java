package Pixelfilter;

import java.awt.image.BufferedImage;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */

public class ColorBandFilter extends PixelFilter {

	private Colorband colorBand;

	public ColorBandFilter(Colorband colorBand) {
		this.colorBand = colorBand;
	}

	/**
	 * process Methode mit @param image1 als Image und falls keine Maske
	 * mitübergeben wurde ist @return null für die Maske
	 */
	@Override
	public BufferedImage process(BufferedImage image1) {

		return process(image1, null);
	}

	/**
	 * Methode um das @param Image1 oder @param mask mit einem ColorBand zu
	 * übermalen.
	 */
	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage mask) {

		BufferedImage edit = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
		int height = image1.getHeight();
		int width = image1.getWidth();

		for (int a = 0; a < height; a++) {
			for (int b = 0; b < width; b++) {

				if (mask == null) {
					edit.setRGB(b, a, calculate(image1.getRGB(b, a)));
				} else if (mask.getRGB(b, a) == -1) {
					edit.setRGB(b, a, calculate(image1.getRGB(b, a)));
				} else {
					edit.setRGB(b, a, image1.getRGB(b, a));
				}
			}

		}
		return edit;
	}

	/**
	 * Methode, bei welcher der ROT, GRÜN, BLAU Wert des @param pixelColor
	 * extrahiert wird und auf das Pixel angewendet wird
	 */
	@Override
	public int calculate(int pixelColor) {

		switch (colorBand) {
		case RED:
			int r = (pixelColor >> 16) & 0xFF;
			return r << 16;
		case GREEN:
			int g = (pixelColor >> 8) & 0xFF;
			return g << 8;
		case BLUE:
			int b = (pixelColor & 0xFF);
			return b;
		default:
			return pixelColor;
		}

	}

}