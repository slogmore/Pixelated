package Pixelated;

// TODO: Auto-generated Javadoc
/**
 * The Class RGBAColor.
 */
public class RGBAColor {
	
	/** The alpha. */
	float red, green, blue, alpha;	
	
	/**
	 * Instantiates a new RGBA color.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 * @param alpha the alpha
	 */
	RGBAColor(float red, float green, float blue, float alpha) {
		this.red = red; 
		this.green = green; 
		this.blue = blue;
		this.alpha = alpha;
	}
	
	/**
	 * Adds the to.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 * @param alpha the alpha
	 */
	public void addTo(float red, float green, float blue, float alpha) {
		this.red += red;
		this.green += green;
		this.blue += blue;
		this.alpha += alpha;
	}
}
