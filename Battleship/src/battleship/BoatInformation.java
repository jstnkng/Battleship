package battleship;

public class BoatInformation {

	int locx;
	int locy;
	String imagePath;
	int width;
	int height;
	
	public BoatInformation(int x, int y, int w, int h, String path) {
		locx = x;
		locy = y;
		width = w;
		height = h;
		imagePath = path;
	}
	
	public void setPath(String newPath) {
		imagePath = newPath;
	}
}
