package Bereichsfilter;

import Main.*;
import java.awt.image.BufferedImage;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */

public abstract class AreaFilter implements Filter {
	
	/**
	 * abstrakte Methode calculate, wird später in den anderen AreaFiltern
	 * implementiert
	 * 
	 * @param int[]pixel     Array der Pixel des Image
	 * @param int[]maskPixel Array der Pixel der Maske
	 * @param index          Gibt die Position des zu verändernden Pixels an
	 * @param width          Breite des Images
	 * @param height         Höhe des Images
	 */
	protected abstract int calculate(int[] pixel, int[] maskPixel, int index, int width, int height);

}
