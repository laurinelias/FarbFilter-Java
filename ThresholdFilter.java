package Pixelfilter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Zeichnet das Bild in Grauabstufungen je nachdem wie der Threshold gesetzt
 * wurde
 * 
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class ThresholdFilter extends PixelFilter {
	private int[] thresholds;
	private int[] colors;

	public ThresholdFilter(int threshold) {
		setThreshold(threshold);
	}

	public ThresholdFilter(int[] threshold) {
		setThresholds(threshold);
	}

	/**
	 * Geht das Bild Pixel für Pixel durch
	 * 
	 * @param image1 Bild welcehs verabeiet wird
	 */
	@Override
	public BufferedImage process(BufferedImage image1) {
		return process(image1, null);
	}

	/**
	 * Geht das Bild Pixel für Pixel durch unter Berücksichtigung der Maske
	 * 
	 * @param image1 Bild welcehs verabeiet wird
	 */
	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage mask) {
		BufferedImage newImage = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
		for (int y = 0; y < image1.getHeight(); y++) {
			for (int x = 0; x < image1.getWidth(); x++) {
				if (mask == null || (mask.getRGB(x, y) & 0x00FFFFFF) == 0x00FFFFFF) {
					newImage.setRGB(x, y, calculate(image1.getRGB(x, y)));
				} else
					newImage.setRGB(x, y, image1.getRGB(x, y));
			}
		}
		return newImage;
	}

	@Override
	protected int calculate(int pixelColor) {
		if (thresholds == null) {
			setThreshold(128);
		}
		int r = (pixelColor >> 16) & 0xFF;
		int g = (pixelColor >> 8) & 0xFF;
		int b = (pixelColor & 0xFF);
		int avg = (r + g + b) / 3;
		for (int i = 0; i < thresholds.length; i++) {
			if (avg < thresholds[i]) {
				return colors[i];
			}
		}
		return 0xFFFFFF;
	}

	public void setThreshold(int threshold) {
		setThresholds(new int[] { threshold });
	}

	public void setThresholds(int[] thresholds) {
		this.thresholds = thresholds;
		Arrays.sort(thresholds);
		int color = 0xFFFFFF / thresholds.length;
		colors = new int[thresholds.length];
		if(thresholds.length==3) {
			colors[0]=0x626262;
			colors[1]=0xA0A0A0;
			colors[2]=0xFFFFFF;
		}else {
			for (int i = 0; i < colors.length; i++) {
				colors[i] = color * i;
			}
		}
	}
}
