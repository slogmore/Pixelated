package Pixelated;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

public class PixelatedText extends Pixelated {
	private int textColor;
	private PFont font; 
	private String text; 
	private float pixelationTolerance; 
	
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
	
	public void setPixelationTolerance(float pixelationTolerance) {
		if(pixelationTolerance < 0.0)
			this.pixelationTolerance = 0.0f;
		else if(pixelationTolerance > 1.0) 
			this.pixelationTolerance = 1.0f;
		else
			this.pixelationTolerance = pixelationTolerance;
	}
	
	public float getPixelationTolerance() {
		return pixelationTolerance;
	}
	
	protected void setDimensions(int resolution) {
		this.resolution = resolution;
		parent.textFont(font);
		dimensions = new Dimensions((int)(parent.textWidth(text)/resolution), (int)(parent.textAscent())/resolution);
	}
	
	protected void drawToBuffer() {
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.noStroke();
		pGraphicsBuffer.textFont(font);
		pGraphicsBuffer.fill(textColor);
		pGraphicsBuffer.text(text, 0, (float)(dimensions.height - parent.textAscent() * .1));
		pGraphicsBuffer.endDraw();
	}
	
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
