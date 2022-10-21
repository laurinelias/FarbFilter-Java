package KombinatorischeFilter;

import java.awt.image.BufferedImage;
import Main.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0
 */
public class ChainFilter implements Filter {
	private BufferedImage edit;
	List<Filter> filterList = new ArrayList<Filter>();
	
	public ChainFilter(Filter[] filterarray){
		
		for(Filter filter : filterarray){
			filterList.add(filter);
		}
	}
	
	

	/**
	 * Methode um Filter zu der oben erzeugten Liste hizuzufügen
	 */
	public void add(Filter filter) {
		filterList.add(filter);
	}

	@Override
	public BufferedImage process(BufferedImage image1) {
		return this.process(image1, null);
	}

	/**
	 * Methode um die Filter mit einander zu verknüpfen und auf ein Image anzuwenden
	 */
	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage mask) {
		for (int i = 0; i < filterList.size(); i++) {
			edit = filterList.get(i).process(image1,mask);
			image1 = edit;
		}
		return edit;
	}

}
