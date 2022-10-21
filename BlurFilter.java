package Bereichsfilter;

import java.awt.image.BufferedImage;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class BlurFilter extends AreaFilter {

	private int radius;

	/**
	 * Konstruktor mit @param radius für den Radius der zu betrachteten Pixel
	 */
	public BlurFilter(int radius) {
		this.radius = radius;
	}

	/**
	 * process Methode mit @param initImage als Image und falls keine Maske
	 * mitübergeben wurde ist @return null für die Maske
	 */
	public BufferedImage process(BufferedImage initImage) {
		return this.process(initImage, null);

	}

	/**
	 * Methode, die für jeden Bildpunkt die Methode calculate aufruft
	 */
	@Override
	public BufferedImage process(BufferedImage initImage, BufferedImage mask) {

		int width = initImage.getWidth();
		int height = initImage.getHeight();

		BufferedImage editImage = new BufferedImage(width, height, initImage.getType());
		int[] maskPx;

		int[] initImgPx = initImage.getRGB(0, 0, width, height, null, 0, width);

		if (mask != null) {
			maskPx = mask.getRGB(0, 0, width, height, null, 0, width);
		} else {
			maskPx = new int[0];
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				editImage.setRGB(j, i, calculate(initImgPx, maskPx, j + (i * width), width, height));
			}
		}
		return editImage;

	}

	/**
	 * Ermittlung des Wertes der Durchschnittsfarbe der Umgebungspixel in
	 * Abhängigkeit von {@code radius}.
	 */
	@Override
	public int calculate(int[] pixel, int[] maskPx, int index, int width, int height) {

		if (maskPx.length<1 || maskPx[index] == -1) {
			int distance = (radius * width) + radius;
			int firstPos = index - distance;
			int lastPos = firstPos + radius * 2;
			int counter = (radius * 2 + 1);
			int pixelAmount = 0;
			int red = 0;
			int green = 0;
			int blue = 0;
			for (int i = firstPos; i <= lastPos; i++) {

				if (counter <= 0) {
					break;
				}

				if (i >= 0 && i < pixel.length) {
					red += pixel[i] >> 16 & 0xFF;
					green += pixel[i] >> 8 & 0xFF;
					blue += pixel[i] & 0xFF;
					pixelAmount++;
				}

				if (i == lastPos) {
					firstPos += width;
					lastPos += width;
					i = firstPos - 1;
					counter--;
				}
			}
			red = red / pixelAmount;
			green = green / pixelAmount;
			blue = blue / pixelAmount;

			return (red * 256 * 256 + green * 256 + blue);
		} else {
			return pixel[index];
		}
	}
}
