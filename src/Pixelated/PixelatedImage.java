package Pixelated;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class PixelatedImage extends Pixelated {
	
	PixelatedImage(PApplet parent, PImage image) {
		super(parent);
		pGraphicsBuffer = parent.createGraphics(image.width, image.height, parent.JAVA2D);
		setDimensions(resolution);
		drawToBuffer(image); 
		convertToIntArray();
	}
	
	protected void drawToBuffer(PImage image) {
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.background(image);
		pGraphicsBuffer.endDraw();
	}
	
	protected void setDimensions(int resolution) {
		this.resolution = resolution;
		dimensions = new Dimensions((int)(pGraphicsBuffer.width/resolution), (int)(pGraphicsBuffer.height/resolution));
	}

}
