package Pixelated;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

// TODO: Auto-generated Javadoc
/**
 * The Class PixelatedText.
 */
public class PixelatedText extends Pixelated {
	
	/** The text color. */
	private int textColor;
	
	/** The font. */
	private PFont font; 
	
	/** The text. */
	private String text; 
	
	/** The pixelation tolerance. */
	private float pixelationTolerance; 
	
	/**
	 * Instantiates a new pixelated text.
	 *
	 * @param parent the parent
	 * @param font the font
	 * @param textColor the text color
	 * @param text the text
	 */
	PixelatedText(PApplet parent, PFont font, int textColor, String text) {
		super(parent);
		this.textColor = textColor;
		this.text = text;
		this.font = font;
		this.pixelationTolerance = 0.8f;
		
		setDimensions(resolution);
		pGraphicsBuffer = parent.createGraphics(dimensions.width, dimensions.height, parent.JAVA2D);
		drawToBuffer();
		convertToIntArray();
	}
	
	/**
	 * Sets the pixelation tolerance.
	 *
	 * @param pixelationTolerance the new pixelation tolerance
	 */
	public void setPixelationTolerance(float pixelationTolerance) {
		if(pixelationTolerance < 0.0)
			this.pixelationTolerance = 0.0f;
		else if(pixelationTolerance > 1.0) 
			this.pixelationTolerance = 1.0f;
		else
			this.pixelationTolerance = pixelationTolerance;
	}
	
	/**
	 * Gets the pixelation tolerance.
	 *
	 * @return the pixelation tolerance
	 */
	public float getPixelationTolerance() {
		return pixelationTolerance;
	}
	
	/* (non-Javadoc)
	 * @see Pixelated.Pixelated#setDimensions(int)
	 */
	protected void setDimensions(int resolution) {
		this.resolution = resolution;
		parent.textFont(font);
		dimensions = new Dimensions((int)(parent.textWidth(text)/resolution), (int)(parent.textAscent())/resolution);
	}
	
	/* (non-Javadoc)
	 * @see Pixelated.Pixelated#drawToBuffer()
	 */
	protected void drawToBuffer() {
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.noStroke();
		pGraphicsBuffer.textFont(font);
		pGraphicsBuffer.fill(textColor);
		pGraphicsBuffer.text(text, 0, (float)(dimensions.height - parent.textAscent() * .1));
		pGraphicsBuffer.endDraw();
	}
	
	/* (non-Javadoc)
	 * @see Pixelated.Pixelated#averageColor(int, int)
	 */
	protected int averageColor(int currentX, int currentY) {
		int pixelsPer = resolution * resolution;
		currentX = currentX * resolution;
		currentY = currentY * resolution;
		int backgroundColorCount = 0;
		int textColorCount = 0;
		for(int x = currentX; x < currentX + resolution; x++)
			for(int y = currentY; y < currentY + resolution; y++) 
				if(pGraphicsBuffer.get(x, y) == textColor) 
					textColorCount += 1;

		if((float)textColorCount/pixelsPer > 1 - pixelationTolerance)
			return textColor;
		return parent.color(0, 0, 0, 0);
		
	}
	
}
