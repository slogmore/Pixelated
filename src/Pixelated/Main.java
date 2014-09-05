package Pixelated;

import processing.core.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends PApplet { 

	/* (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
	public void setup() {
		size(700,800);
		background(255);
		
		//Uncomment one at a time to see examples
		//pixelatedRawTest();
		//pixelatedImageTest();
		//pixelatedTextTest();
	}	
	
	/**
	 * Pixelated raw test.
	 */
	public void pixelatedRawTest() {
		//Set up the graphic - unpixelated
		Pixelated pixelated= new Pixelated(this, 255, 255);
		for(int x = 0; x < pixelated.getPixelWidth(); x++) 
			for(int y = 0; y < pixelated.getPixelHeight(); y++)
				pixelated.setPixel(x, y, color(x, y, 255-x));
		image(pixelated.getPGraphic(), 0, 0);
		
		//Evenly divisible resolution set
		pixelated.setResolution(15);
		image(pixelated.getPGraphic(), 255, 0);
		
		//Resolution set where cropping (resizing occurs) - see docs
		pixelated.setResolution(40);
		image(pixelated.getPGraphic(), 0, 255);
	}
			
	/**
	 * Pixelated image test.
	 */
	public void pixelatedImageTest() {
		//Unpixelated
		PixelatedImage pixelatedImage = new PixelatedImage(this, loadImage("test.bmp"));
		image(pixelatedImage.getPGraphic(), 0, 0); 
		
		//Pixelated
		pixelatedImage.setResolution(10);
		image(pixelatedImage.getPGraphic(), pixelatedImage.getPixelWidth(), 0);

		//Changing a section of the image to a specific color
		pixelatedImage.setArrayIndex(pixelatedImage.getArrayWidth() / 2, pixelatedImage.getArrayHeight() / 2, color(0, 255, 0));
		image(pixelatedImage.getPGraphic(), 0, pixelatedImage.getPixelHeight());
	}
	
	/**
	 * Pixelated text test.
	 */
	public void pixelatedTextTest() {
		//Unpixelated
		PFont testFont = createFont("Times New Roman", 100);
		PixelatedText pixelatedText = new PixelatedText(this, testFont, color(200, 150, 0), "Here");
		image(pixelatedText.getPGraphic(), 0, 0);
		
		//Progressively change resolution
		pixelatedText = new PixelatedText(this, testFont, color(200, 150, 0), "Here");				
		pixelatedText.setResolution(3);
		image(pixelatedText.getPGraphic(), 200, 0);
		
		pixelatedText = new PixelatedText(this, testFont, color(200, 150, 0), "Here");				
		pixelatedText.setResolution(8);
		image(pixelatedText.getPGraphic(), 0, 100);
		
		//Pixel tolerance example - see docs for detail
		pixelatedText = new PixelatedText(this, testFont, color(200, 150, 0), "Here");				
		pixelatedText.setPixelationTolerance(0.5f);
		pixelatedText.setResolution(6);
		image(pixelatedText.getPGraphic(), 200, 100);
	}
		
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Main" });
	}  
}