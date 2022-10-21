import java.util.HashMap;

import Bereichsfilter.BlurFilter;
import Bereichsfilter.PixelGraphicFilter;
import KombinatorischeFilter.ChainFilter;
import Main.Filter;
import Pixelfilter.ColorBandFilter;
import Pixelfilter.ColorReplacementFilter;
import Pixelfilter.Colorband;
import Pixelfilter.MonochromeFilter;
import Pixelfilter.ThresholdFilter;

/**
 * Verwaltet Filter in einer Map
 * 
 * @author Chris
 *
 */
public class Filterverwaltung {
	public HashMap<String, Filter> getFilterMap() {
		return filterMap;
	}

	private HashMap<String, Filter> filterMap = new HashMap<>();

	public HashMap<String, Filter> getMap() {
		return filterMap;
	}

	public Filterverwaltung() {
		filterMap.put("blur_3", new BlurFilter(3));
		filterMap.put("blur_5", new BlurFilter(5));
		filterMap.put("monochrom", new MonochromeFilter());
		filterMap.put("colorband_red", new ColorBandFilter(Colorband.RED));
		filterMap.put("colorband_green", new ColorBandFilter(Colorband.GREEN));
		filterMap.put("colorband_blue", new ColorBandFilter(Colorband.BLUE));
		filterMap.put("threshold_128", new ThresholdFilter(128));
		filterMap.put("threshold_192", new ThresholdFilter(192));
		filterMap.put("multithreshold", new ThresholdFilter(new int[] { 64, 128, 192 }));
		filterMap.put("colorreplacement_98", new ColorReplacementFilter(0xFF626262));
		filterMap.put("colorreplacement_160", new ColorReplacementFilter(0xFFA0A0A0));
		filterMap.put("colorreplacement_255", new ColorReplacementFilter(0xFFFFFFFF));
		filterMap.put("pixel_20", new PixelGraphicFilter(20));
		filterMap.put("pixel_40", new PixelGraphicFilter(40));
		filterMap.put("pixel_60", new PixelGraphicFilter(60));

		filterMap.put("warhol",
				new ChainFilter(new Filter[] { new ThresholdFilter(new int[] { 64, 128, 192 }),
						new ColorReplacementFilter(0xFF000000), new ColorReplacementFilter(0xFF626262),
						new ColorReplacementFilter(0xFFA0A0A0), new ColorReplacementFilter(0xFFFFFFFF) }));

	

	}
}

