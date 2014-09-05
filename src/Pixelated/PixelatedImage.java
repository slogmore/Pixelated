package Pixelated;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

// TODO: Auto-generated Javadoc
/**
 * The Class PixelatedImage.
 */
public class PixelatedImage extends Pixelated {
	
	/**
	 * Instantiates a new pixelated image.
	 *
	 * @param parent the parent
	 * @param image the image
	 */
	PixelatedImage(PApplet parent, PImage image) {
		super(parent);
		pGraphicsBuffer = parent.createGraphics(image.width, image.height, parent.JAVA2D);
		setDimensions(resolution);
		drawToBuffer(image); 
		convertToIntArray();
	}
	
	/**
	 * Draw to buffer.
	 *
	 * @param image the image
	 */
	protected void drawToBuffer(PImage image) {
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.background(image);
		pGraphicsBuffer.endDraw();
	}
	
	/* (non-Javadoc)
	 * @see Pixelated.Pixelated#setDimensions(int)
	 */
	protected void setDimensions(int resolution) {
		this.resolution = resolution;
		dimensions = new Dimensions((int)(pGraphicsBuffer.width/resolution), (int)(pGraphicsBuffer.height/resolution));
	}

}
