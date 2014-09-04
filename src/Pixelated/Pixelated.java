package Pixelated;

import processing.core.*; 

class Pixelated {
	protected PApplet parent; 
	protected int[][] pixelatedArray;
	protected PGraphics pGraphicsBuffer; 
	protected Dimensions dimensions;
	protected int resolution;
	
	Pixelated(PApplet parent) {
		this.parent = parent;
		resolution = 1;
	}
	
	Pixelated(PApplet parent, int imageWidth, int imageHeight) {
		this(parent);
		dimensions = new Dimensions(imageWidth, imageHeight);
		pGraphicsBuffer = parent.createGraphics(imageWidth, imageHeight, parent.JAVA2D);
		drawToBuffer();
		convertToIntArray();
	}
	
	public PGraphics getPGraphic() {
		return pGraphicsBuffer;
	}

	public int[][] getIntArray() {
		return pixelatedArray;
	}
	
	public int getPixelWidth() {
		return dimensions.width * resolution;
	}
	
	public int getPixelHeight() {
		return dimensions.height * resolution;
	}
	
	public int getArrayWidth() {
		return dimensions.width;
	}
	
	public int getArrayHeight() {
		return dimensions.height;
	}
	
	public int getPixel(int x, int y) {
		if(x < 0 && x >= dimensions.width && y < 0 && y >= dimensions.height)
			throw new IndexOutOfBoundsException("Index " + x + " is out of bounds setPixel in Pixelated.");
		return pixelatedArray[x][y];
	}
	
	public void setResolution(int resolution) {
		if(this.resolution != resolution) {
			this.resolution = resolution;
			convertToIntArray();
			createPGraphics();
		}
	}
	
	public void setPixel(int x, int y, int pixelColor) {
		if(x < 0 && x >= dimensions.width && y < 0 && y >= dimensions.height)
			throw new IndexOutOfBoundsException("Index " + x + " is out of bounds setPixel in Pixelated.");
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.set(x, y, pixelColor);
		pGraphicsBuffer.endDraw();
	}
	
	public void setArrayIndex(int x, int y, int indexColor) {
		if(resolution == 1) 
			setPixel(x, y, indexColor);
		else 
			updateRegion(x, y, indexColor);
	}
	
	protected void updateRegion(int currentX, int currentY, int indexColor) {
		currentX = currentX * resolution;
		currentY = currentY * resolution;
		pGraphicsBuffer.beginDraw();
		for(int x = currentX; x < currentX + resolution; x++) 
			for(int y = currentY; y < currentY + resolution; y++)  
				pGraphicsBuffer.set(x, y, indexColor);
		pGraphicsBuffer.endDraw();
	}
	
	protected void drawToBuffer() {
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.fill(0, 0, 0);
		pGraphicsBuffer.endDraw();
	}

	protected void convertToIntArray() {
		setDimensions(resolution);
		pixelatedArray = new int[dimensions.width][dimensions.height];
		for(int x = 0; x < dimensions.width; x++)
			for(int y = 0; y < dimensions.height; y++)
				pixelatedArray[x][y] = averageColor(x, y);
	}

	protected int averageColor(int currentX, int currentY) {
		RGBAColor rgbColor = new RGBAColor(0, 0, 0, 0);
		int pixelsPer = resolution * resolution;
		currentX = currentX * resolution;
		currentY = currentY * resolution;
		for(int x = currentX; x < currentX + resolution; x++)
			for(int y = currentY; y < currentY + resolution; y++) 
				rgbColor.addTo(parent.red(pGraphicsBuffer.get(x, y)), 
							   parent.green(pGraphicsBuffer.get(x, y)),
						       parent.blue(pGraphicsBuffer.get(x, y)),
						       parent.alpha(pGraphicsBuffer.get(x, y)));
		return parent.color(rgbColor.red/pixelsPer, rgbColor.green/pixelsPer, rgbColor.blue/pixelsPer, rgbColor.alpha/pixelsPer);
	}
	
	protected void setDimensions(int resolution) {
		this.resolution = resolution;
		dimensions = new Dimensions((int)(pGraphicsBuffer.width/resolution), (int)(pGraphicsBuffer.height/resolution));
	}
	
	protected void createPGraphics() {
		pGraphicsBuffer = parent.createGraphics(dimensions.width * resolution,  dimensions.height * resolution + 1, parent.JAVA2D);
		pGraphicsBuffer.beginDraw();
		pGraphicsBuffer.noStroke();
		for(int i = 0; i < dimensions.width; i++) {
			for(int j = 0; j < dimensions.height; j++) {
				pGraphicsBuffer.fill(parent.red(pixelatedArray[i][j]), parent.green(pixelatedArray[i][j]), parent.blue(pixelatedArray[i][j]), parent.alpha(pixelatedArray[i][j])); 
				pGraphicsBuffer.rect(i * resolution, j * resolution, resolution, resolution); 
			}
		}
		pGraphicsBuffer.endDraw();
	}
}

