package Bereichsfilter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class PixelGraphicFilter extends AreaFilter {
	int blockSize = 20;

	/*
	 * 
	 */
	public PixelGraphicFilter(int blocksize) {
		this.blockSize = blocksize;
	}

	private int getBounds(int index, int size) {
		if (index + blockSize < size) {
			return blockSize;
		}
		return size % blockSize;
	}

	@Override
	public BufferedImage process(BufferedImage image1) {
		return this.process(image1, null);
	}

	/**
	 * Sammelt die Pixel aus den Blöcken
	 * 
	 * @param image1 Bild das verarbeitet wird
	 * @param image2 Maske welche auf das Bild angewendet wird
	 */
	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage image2) {
		int width = image1.getWidth();
		int height = image1.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, image1.getType());
		for (int y = 0; y + blockSize < height; y += blockSize)
			for (int x = 0; x + blockSize < width; x += blockSize) {

				int avgPixel;
				int[] blockPixel = image1.getRGB(x, y, getBounds(x, width), getBounds(y, height), null, 0,
						getBounds(x, width));
				avgPixel = calculate(blockPixel, null, x, width, height);
				for (int x2 = x; x2 < x + getBounds(x2, width); x2++) {
					for (int y2 = y; y2 < y + getBounds(y2, height); y2++) {
						if (image2 == null || image2.getRGB(x2, y2)==-1) {
							newImage.setRGB(x2, y2, avgPixel);
						} else {
							newImage.setRGB(x2, y2, image1.getRGB(x2, y2));
						}
					}
				}
			}
		return newImage;
	}

	@Override
	protected int calculate(int[] pixel, int[] maskPixel, int index, int width, int height) {
		int red = 0;
		int green = 0;
		int blue = 0;
		for (int i = 0; i < pixel.length; i++) {
			red += pixel[i] >> 16 & 0xFF;
			green += pixel[i] >> 8 & 0xFF;
			blue += pixel[i] & 0xFF;
		}
		red = red / pixel.length;
		green = green / pixel.length;
		blue = blue / pixel.length;

		return (red * 256 * 256 + green * 256 + blue);
	}

}
