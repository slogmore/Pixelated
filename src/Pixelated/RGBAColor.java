package Pixelated;

public class RGBAColor {
	float red, green, blue, alpha;	
	
	RGBAColor(float red, float green, float blue, float alpha) {
		this.red = red; 
		this.green = green; 
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public void addTo(float red, float green, float blue, float alpha) {
		this.red += red;
		this.green += green;
		this.blue += blue;
		this.alpha += alpha;
	}
}
