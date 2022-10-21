package Pixelfilter;
/**
	@author Oliver Keune, Christian Supp, Laurin Schubert
	@version 1.0
*/
	/**
	Enum wird mit den entsprechenden Farbwerten initialisiert
	*/
 public enum Colorband {
	RED(0xFF0000), GREEN(0x00FF00), BLUE (0x0000FF);
	 protected final int farbwert;
	
	/**
	Konstruktor des Enums ColorBand
	*/
	private Colorband(int farbwert) {
		this.farbwert = farbwert;
	}
	/**
	Methode um den Farbwert zu bekommen
	*/
	public int getFarbwert(){
		return farbwert;
	}
}
	

