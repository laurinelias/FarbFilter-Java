package Pixelfilter;

import Main.*;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public abstract class PixelFilter implements Filter {

	protected abstract int calculate(int pixelColor);

}
