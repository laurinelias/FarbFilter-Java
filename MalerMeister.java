import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import Main.*;
import Pixelfilter.*;
import KombinatorischeFilter.*;
import Bereichsfilter.*;

/**
 * @author Oliver Keune, Christian Supp, Laurin Schubert
 * @version 1.0 Programm, um Bild {@code image} eine Maske {@code mask} und mit
 *          Filtern {@code filter} zu versehen.
 */
// Verarbeitet Startparameter zum Filteraufruf
public class MalerMeister {
	public static void main(String[] args) {
		String filtername = args[0];
		String bild = args[1];
		String maske = "";
		if (args[2].equals("-m"))
			maske = args[3];
		String ausgabe = args[args.length - 1];
		BufferedImage image = null;
		BufferedImage mask = null;
		Filterverwaltung manager = new Filterverwaltung();
		if(filtername.equals("test")) {
			testeFilter(args);
		}
		try {
			// Bild wird eingelesen
			image = ImageIO.read(new File(bild));
			if (maske!="") {
				mask = ImageIO.read(new File(maske));
			}
			for (String key : manager.getMap().keySet()) {
				if (key.equals(filtername)) {
					Filter filter = manager.getMap().get(key);
					BufferedImage neuesBild;
					if (mask == null) {
						neuesBild = filter.process(image);
					} else {
						neuesBild = filter.process(image, mask);
					}
					// Bild wird ausgegeben
					ImageIO.write(neuesBild, "bmp", new File(ausgabe));
				}
			}

			/*
			 * BufferedImage neuesBild = filter.process(image); ImageIO.write(neuesBild,
			 * "bmp", new File(key+"output_image.bmp"));
			 */

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public static void testeFilter(String[] args) {
		String filtername;
		String output=args[args.length-1];
		Filterverwaltung verwalter = new Filterverwaltung();
		int iterator = 0;
		for(String key : verwalter.getMap().keySet()) {
			iterator++;
			filtername =key;
			args[args.length-1] = output.replace(".bmp", "_"+key+iterator+".bmp");
			args[0] = filtername;
			main(args);
		}
		
	}
}
