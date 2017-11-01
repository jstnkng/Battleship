package battleship;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * controls computer for different difficulties.
 * 
 * @author Sam Carson
 * @author Justin King
 *
 */
public class Cpu {
	
	/**
	 * current difficulty.
	 */
	private Difficulty currentDiff;
	
	/**
	 * arraylist of points that cpu has shot.
	 */
	private ArrayList<Point> cpuShots = new ArrayList<Point>();
	/**
	 * Boolean if cpu is still trying to sink certain ship.
	 */
	private boolean inPursuit = false;
	/**
	 * location of last cpu hit.
	 */
	private Point lastHitLoc = null;
	/**
	 * location of first cpu hit on ship.
	 */
	private Point firstHitLoc = null;
	/**
	 * boolean if last cpu shot was a hit.
	 */
	private boolean wasHit = false;
	/**
	 * boolean if found ship is vertical.
	 */
	private boolean isV = false;
	/**
	 * boolean if found ship is horizontal.
	 */
	private boolean isH = false;
	/**
	 * arrayList of first hits.
	 */
	private ArrayList<Point> firstHits = new ArrayList<Point>();
	
	
	/**
	 * Constructor for Cpu class.
	 * @param d difficulty being played
	 */
		public Cpu(final Difficulty d) {
			
			currentDiff = d;
		}
		
		/**
		 * Fires the shot for the cpu based on difficulty.
		 * @param first first shot location
		 * @param last last shit location
		 * @param hit if last shot was hit
		 * @param pursuit if in pursuit
		 * @return Point point to shoot
		 */
		public Point fire(final Point first, final Point last, 
				final boolean hit, final boolean pursuit) {
			Point p;
			
			firstHitLoc = first;
			lastHitLoc = last;
			wasHit = hit;
			inPursuit = pursuit;
			
			if (!firstHits.contains(firstHitLoc)) {
				firstHits.add(firstHitLoc);
			}
			
			if (currentDiff == Difficulty.Easy) {
				p = easy();
			} else if (currentDiff == Difficulty.Normal) {
				p = normal(firstHitLoc, lastHitLoc, 
						wasHit, inPursuit);
			} else {
				p = null;
			}
			
			System.out.println("cpu shot at: " + p);
			
			return p;
			
		}
		
		/**
		 * computer for easy difficulty.
		 * @return Point point to shoot
		 */
		private Point easy() {
			Point p = null;
			
			while (!isValid(p)) {
				int minimum = 0;
				int maximum = 9;
				Random rn = new Random();
				int range = maximum - minimum + 1;
				int x =  rn.nextInt(range) + minimum;
				int y = rn.nextInt(range) + minimum;

				p = new Point(x, y);
			}
			
			return p;
		}
		
		/**
		 * chooses the shot for the cpu for normal difficulty.
		 * @param firstHitLoc first shot location
		 * @param lastHitLoc last shit location
		 * @param wasHit if last shot was hit
		 * @param inPursuit if in pursuit
		 * @return Point point to shoot
		 */
		private Point normal(final Point firstHitLoc, 
				final Point lastHitLoc, final boolean wasHit, 
				final boolean inPursuit) {
			
			Point p = null;
			
			int x = 0;
			int y = 0;
			
			System.out.println("inPursuit: " + inPursuit);
			
			while (!isValid(p)) {
				
				if (!inPursuit) {
					//Choose random spot on board
					int minimum = 0;
					int maximum = 9;
					Random rn = new Random();
					int range = maximum - minimum + 1;
					x =  rn.nextInt(range) + minimum;
					y = rn.nextInt(range) + minimum;
					p = new Point(x, y);
					
				} else if (inPursuit 
					&& lastHitLoc.equals(firstHitLoc)) {
					//Choose random spot in relation
					//to first hit
					p = chooseFromFirst();
					
				} else if (inPursuit && wasHit 
					&& !lastHitLoc.equals(firstHitLoc)) {
					//Choose shot continuing in 
					//working direction
					p = continueDirection();
					
				} else if (inPursuit
					&& (!wasHit && (isV || isH))) {
					//Jump to the other side of the
					//first hit
					p = jumpToOtherSide();
					
				} 
				
			}
			
			cpuShots.add(p);
			return p;
		}
//		
//		private Point Hard() {
//			Point p;
//			
//			
//			
//			return p;
//		}
		/**
		 * chooses a shot based on the first hit.
		 * @return Point point to shoot
		 */
		private Point chooseFromFirst() {
			Random rn = new Random();
			int range = 4;
			int num = rn.nextInt(range);
			int x = 0, y = 0;
			Point p;
			
			if (num == 0) {
				//try right
				System.out.println("try right");
				x = lastHitLoc.x + 1;
				y = lastHitLoc.y;
			} else if (num == 1) {
				//try left
				System.out.println("try left");
				x = lastHitLoc.x - 1;
				y = lastHitLoc.y;
			} else if (num == 2) {
				//try up
				System.out.println("try up");
				x = lastHitLoc.x;
				y = lastHitLoc.y - 1;
			} else if (num == 3) {
				//try down
				System.out.println("try down");
				x = lastHitLoc.x;
				y = lastHitLoc.y + 1;	
			}
			
			p = new Point(x, y);
			return p;
		}
		
		/**
		 * Chooses spot that continues in same direction.
		 * @return Point point to shoot
		 */
		private Point continueDirection() {
			
			int x = 0, y = 0;
			Point p;
			
			if (lastHitLoc.x < firstHitLoc.x) {
				//ship is horizontal. guess left
				System.out.println("go left");
				isH = true;
				isV = false;
				x = lastHitLoc.x - 1;
				y = lastHitLoc.y;
			} else if (lastHitLoc.x > firstHitLoc.x) {
				//ship is horizontal. guess right
				System.out.println("go right");
				isH = true;
				isV = false;
				x = lastHitLoc.x + 1;
				y = lastHitLoc.y;
			} else if (lastHitLoc.y < firstHitLoc.y) {
				//ship is vertical. guess up
				System.out.println("go up");
				isV = true;
				isH = false;
				x = lastHitLoc.x;
				y = lastHitLoc.y - 1;
			} else if (lastHitLoc.y > firstHitLoc.y) {
				//ship is vertical. guess down
				System.out.println("go down");
				isV = true;
				isH = false;
				x = lastHitLoc.x;
				y = lastHitLoc.y + 1;
			} 
			Point test = new Point(x, y);
			if (!isValid(test)) {
				test = jumpToOtherSide();
				x = test.x;
				y = test.y;
			}
			
			p = new Point(x, y);
			return p;
		}
		/**
		 * Checks to see if point is a valid shot.
		 * @param p point to check
		 * @return true or false
		 */
		private boolean isValid(final Point p) {
			return !(cpuShots.contains(p) || p == null 
					|| (p.x < 0 || p.x > 9 
						|| p.y < 0 || p.y > 9));
			
		}
		
		/**
		 * Starts guessing the opposite direction of 
		 * the first guess.
		 * @return Point point to shoot
		 */
		private Point jumpToOtherSide() {
			int x = 0;
			int y = 0;
			
			if (isH) {
				System.out.println(
					"Jump to opposite side of "
					+ "start horizontal");
				if (lastHitLoc.x < firstHitLoc.x) {
					//jump to right
					x = firstHitLoc.x + 1;
					y = firstHitLoc.y;
				} else {
					//jump to left
					x = firstHitLoc.x - 1;
					y = firstHitLoc.y;
				}
			} else if (isV) {
				System.out.println(
					"Jump to opposite side of "
					+ "start vertical");
				if (lastHitLoc.y < firstHitLoc.y) {
					//jump to bottom of first hit
					x = firstHitLoc.x;
					y = firstHitLoc.y + 1;
				} else {
					//jump to top of first hit
					x = firstHitLoc.x;
					y = firstHitLoc.y - 1;
				}
			}
			
			Point p = new Point(x, y);
			
			return p;
		}
		
}
