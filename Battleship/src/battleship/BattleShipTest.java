package battleship;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.junit.Test;

/**
 * Runs JUnit tests on classes.
 */
public class BattleShipTest {
	/**
	 * Test creating an aircraft carrier.
	 */
	@Test
	public void testCheckAircraftCarrier() {
		Ship testAircraftCarrier = new Ship(ShipType.AircraftCarrier);
		assertEquals(325, testAircraftCarrier.getWidthH());
		assertEquals(50, testAircraftCarrier.getHeightH());
		assertEquals(280, testAircraftCarrier.getHeightV());
		assertEquals(50, testAircraftCarrier.getWidthV());
		assertEquals(ShipType.AircraftCarrier, 
				testAircraftCarrier.getTypeOfShip());
		assertEquals(5, testAircraftCarrier.getShipSize());
		assertEquals(5, testAircraftCarrier.getValue());
		testAircraftCarrier.setHorizontal(true);
		assertEquals(true, testAircraftCarrier.isHorizontal());
		testAircraftCarrier.rotate();
		assertEquals(false, testAircraftCarrier.isHorizontal());
		testAircraftCarrier.rotate();
		assertEquals(true, testAircraftCarrier.isHorizontal());
	}
	/**
	 * Test creating a battleship.
	 */
	@Test
	public void testCreateBattleShip() {
		Ship testBattleShip = new Ship(ShipType.Battleship);
		assertEquals(258, testBattleShip.getWidthH());
		assertEquals(50, testBattleShip.getHeightH());
		assertEquals(222, testBattleShip.getHeightV());
		assertEquals(50, testBattleShip.getWidthV());
		assertEquals(ShipType.Battleship, 
				testBattleShip.getTypeOfShip());
		assertEquals(4, testBattleShip.getShipSize());
		assertEquals(4, testBattleShip.getValue());
		testBattleShip.setHorizontal(true);
		assertEquals(true, testBattleShip.isHorizontal());
		testBattleShip.rotate();
		assertEquals(false, testBattleShip.isHorizontal());
		testBattleShip.rotate();
		assertEquals(true, testBattleShip.isHorizontal());
	}
	/**
	 * Test creating a cruiser.
	 */
	@Test
	public void testCreateCruiser() {
		Ship testCruiser = new Ship(ShipType.Cruiser);
		assertEquals(191, testCruiser.getWidthH());
		assertEquals(50, testCruiser.getHeightH());
		assertEquals(164, testCruiser.getHeightV());
		assertEquals(50, testCruiser.getWidthV());
		assertEquals(ShipType.Cruiser, testCruiser.getTypeOfShip());
		assertEquals(3, testCruiser.getShipSize());
		assertEquals(3, testCruiser.getValue());
		testCruiser.setHorizontal(true);
		assertEquals(true, testCruiser.isHorizontal());
		testCruiser.rotate();
		assertEquals(false, testCruiser.isHorizontal());
		testCruiser.rotate();
		assertEquals(true, testCruiser.isHorizontal());
		
	}
	/**
	 * Test creating a submarine.
	 */
	@Test
	public void testCreateSubmarine() {
		Ship testSubmarine = new Ship(ShipType.Submarine);
		assertEquals(191, testSubmarine.getWidthH());
		assertEquals(50, testSubmarine.getHeightH());
		assertEquals(164, testSubmarine.getHeightV());
		assertEquals(50, testSubmarine.getWidthV());
		assertEquals(ShipType.Submarine, testSubmarine.getTypeOfShip());
		assertEquals(3, testSubmarine.getShipSize());
		assertEquals(2, testSubmarine.getValue());
		testSubmarine.setHorizontal(true);
		assertEquals(true, testSubmarine.isHorizontal());
		testSubmarine.rotate();
		assertEquals(false, testSubmarine.isHorizontal());
		testSubmarine.rotate();
		assertEquals(true, testSubmarine.isHorizontal());		
	}
	/**
	 * Test creating a patrol boat.
	 */
	@Test
	public void testCreatePatrolBoat() {
		Ship testPatrolBoat = new Ship(ShipType.PatrolBoat);
		assertEquals(124, testPatrolBoat.getWidthH());
		assertEquals(50, testPatrolBoat.getHeightH());
		assertEquals(106, testPatrolBoat.getHeightV());
		assertEquals(50, testPatrolBoat.getWidthV());
		assertEquals(ShipType.PatrolBoat, 
				testPatrolBoat.getTypeOfShip());
		assertEquals(2, testPatrolBoat.getShipSize());
		assertEquals(1, testPatrolBoat.getValue());
		testPatrolBoat.setHorizontal(true);
		assertEquals(true, testPatrolBoat.isHorizontal());
		testPatrolBoat.setHorizontal(false);
		assertEquals(false, testPatrolBoat.isHorizontal());
		testPatrolBoat.rotate();
		assertEquals(true, testPatrolBoat.isHorizontal());
	}

	
//	public void testCreateShipWithInvalidImages() {
//	Ship testAircraftCarrier = new Ship(ShipType.AircraftCarrier);
//	testAircraftCarrier.setImages("invalidString1", "invalidString2");
//		
//	}

	/**
	 * Test dragging and dropping aircraft carrier on grid.
	 */
	@Test
	public void testSetAircraftCarrierOnGrid() {
		
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getAircraftCarrier().setLocation(700, 400);
		testFrame.snapShipToX(testFrame.getAircraftCarrier());
		testFrame.snapShipToY(testFrame.getAircraftCarrier());
		assertEquals(3, testFrame.getAircraftCarrier().getColumn());
		assertEquals(6, testFrame.getAircraftCarrier().getRow());
	}
	/**
	 * Test dragging and dropping battleship on grid.
	 */
	@Test
	public void testSetBattleShipOnGrid() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getBattleShip().setLocation(784, 130);
		testFrame.snapShipToX(testFrame.getBattleShip());
		testFrame.snapShipToY(testFrame.getBattleShip());
		assertEquals(4, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());
	}
	/**
	 * Test dragging and dropping cruiser on grid.
	 */
	@Test
	public void testSetCruiserOnGrid() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getCruiser().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getCruiser());
		testFrame.snapShipToY(testFrame.getCruiser());
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());
	}
	/**
	 * Test dragging and dropping submarine on grid.
	 */
	@Test
	public void testSetSubmarineOnGrid() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getSubmarine().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getSubmarine());
		testFrame.snapShipToY(testFrame.getSubmarine());
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());
	}
	/**
	 * Test dragging and dropping patrol boat on grid.
	 */
	@Test
	public void testSetPatrolBoatOnGrid() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getPatrolBoat().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getPatrolBoat());
		testFrame.snapShipToY(testFrame.getPatrolBoat());
		assertEquals(1, testFrame.getPatrolBoat().getColumn());
		assertEquals(1, testFrame.getPatrolBoat().getRow());
	}
	/**
	 * Test dragging and dropping a ship to the left of the grid.
	 */
	@Test
	public void testSetShipOffGridToTheLeft() {
		
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getAircraftCarrier().setLocation(300, 400);
		testFrame.snapShipToX(testFrame.getAircraftCarrier());
		testFrame.snapShipToY(testFrame.getAircraftCarrier());
		assertEquals(0, testFrame.getAircraftCarrier().getX());
		assertEquals(1, testFrame.getAircraftCarrier().getY());
	}
	/**
	 * Test dragging and dropping a ship to the right of the grid.
	 */
	@Test
	public void testSetShipOffGridToTheRight() {
		
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getPatrolBoat().setLocation(400, 1200);
		testFrame.snapShipToX(testFrame.getPatrolBoat());
		testFrame.snapShipToY(testFrame.getPatrolBoat());
		assertEquals(0, testFrame.getPatrolBoat().getX());
		assertEquals(432, testFrame.getPatrolBoat().getY());
	}
	/**
	 * Test dragging and dropping a ship to the top of the grid.
	 */
	@Test
	public void testSetShipOffGridToTheTop() {
		
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getSubmarine().setLocation(-20, 1200);
		testFrame.snapShipToX(testFrame.getSubmarine());
		testFrame.snapShipToY(testFrame.getSubmarine());
		assertEquals(0, testFrame.getSubmarine().getX());
		assertEquals(330, testFrame.getSubmarine().getY());
	}
	/**
	 * Test dragging and dropping a ship to the bottom of the grid.
	 */
	@Test
	public void testSetShipOffGridToTheBottom() {
		
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getCruiser().setLocation(-20, 1200);
		testFrame.snapShipToX(testFrame.getCruiser());
		testFrame.snapShipToY(testFrame.getCruiser());
		assertEquals(0, testFrame.getCruiser().getX());
		assertEquals(220, testFrame.getCruiser().getY());
	}
	/**
	 * Test placing an aircraft carrier on to the grid horizontally.
	 */
	@Test
	public void testMouseDragAircraftCarrierHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getAircraftCarrier().getX() + 5,
				testFrame.getAircraftCarrier().getY() + 5,
					0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(
				testFrame, 506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(
				testFrame, 502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame.getAircraftCarrier().getRow());
	}
	/**
	 * Test placing an aircraft carrier on to the grid vertically.
	 */
	@Test
	public void testMouseDragAircraftCarrierVertical() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getAircraftCarrier().rotate();
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getAircraftCarrier().getX() + 5,
				testFrame.getAircraftCarrier().getY() + 5,
					0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(
				testFrame, 506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(
				testFrame, 502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame.getAircraftCarrier().getRow());
	}
	/**
	 * Test placing ships on to the grid horizontally and vertically.
	 */
	@Test
	public void testPlaceShipsThroughOutGrid() {
		ShipSetupFrame testFrame1 = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame1, 
				testFrame1.getAircraftCarrier(), 850, 130);	
		assertEquals(5, testFrame1.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame1.getAircraftCarrier().getRow());
		dragShipVerticalTo(testFrame1, 
				testFrame1.getCruiser(), 940, 130);	
		assertEquals(6, testFrame1.getCruiser().getColumn());
		assertEquals(1, testFrame1.getCruiser().getRow());
		dragShipVerticalTo(testFrame1, 
				testFrame1.getBattleShip(), 500, 130);
		assertEquals(0, testFrame1.getBattleShip().getColumn());
		assertEquals(1, testFrame1.getBattleShip().getRow());
		dragShipVerticalTo(testFrame1, 
				testFrame1.getPatrolBoat(), 1100, 550);
		assertEquals(9, testFrame1.getPatrolBoat().getColumn());
		assertEquals(8, testFrame1.getPatrolBoat().getRow());
		dragShipVerticalTo(testFrame1, 
				testFrame1.getSubmarine(), 1050, 100);
		assertEquals(8, testFrame1.getSubmarine().getColumn());
		assertEquals(0, testFrame1.getSubmarine().getRow());
		ShipSetupFrame testFrame2 = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame2, 
				testFrame2.getPatrolBoat(), 1000, 500);	
		assertEquals(7, testFrame2.getPatrolBoat().getColumn());
		assertEquals(7, testFrame2.getPatrolBoat().getRow());
		dragShipHorizontalTo(testFrame2, 
				testFrame2.getSubmarine(), 850, 600);	
		assertEquals(5, testFrame2.getSubmarine().getColumn());
		assertEquals(9, testFrame2.getSubmarine().getRow());
	}
	
	/**
	 * Method to simulate clicking, dragging, 
	 * and releasing a ship vertically.
	 * @param frame ShipSetupFrame for ships
	 * @param shipToDrag ship that is being dragged
	 * @param locX x-coordinate to drag to
	 * @param locY y-coordinate to drag to
	 */
	public void dragShipVerticalTo(final ShipSetupFrame frame, 
			final Ship shipToDrag, final int locX, final int locY) {
		shipToDrag.rotate();
		MouseEvent testMousePressEvent = new MouseEvent(frame,
				501, 0, 0,
				shipToDrag.getX() + 20,
				shipToDrag.getY() + 20, 0, true);
		frame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(
				frame, 506, 1, 0, locX, locY, 0, true);
		frame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(
				frame, 502, 1, 0, locX, locY, 0, true);
		frame.mouseReleased(testMouseReleaseEvent);		
	}
	/**
	 * Method to simulate clicking, dragging, 
	 * and releasing a ship horizontally.
	 * @param frame ShipSetupFrame for ships
	 * @param shipToDrag ship that is being dragged
	 * @param locX x-coordinate to be dragged to
	 * @param locY y-coordinate to be dragged to
	 */
	public void dragShipHorizontalTo(final ShipSetupFrame frame,
			final Ship shipToDrag, final int locX, final int locY) {
		MouseEvent testMousePressEvent = new MouseEvent(frame,
				501, 0, 0,
				shipToDrag.getX() + 20,
				shipToDrag.getY() + 20, 0, true);
		frame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(
				frame, 506, 1, 0, locX, locY, 0, true);
		frame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(
				frame, 502, 1, 0, locX, locY, 0, true);
		frame.mouseReleased(testMouseReleaseEvent);		
	}
	/**
	 * Test dragging and placing a battleship on the grid horizontally.
	 */
	@Test
	public void testMouseDragBattleshipHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getBattleShip().getX() + 5,
				testFrame.getBattleShip().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
			new MouseEvent(testFrame, 506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
			new MouseEvent(testFrame, 502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());		
	}
	/**
	 * Test dragging and placing a battleship on the grid vertically.
	 */
	@Test
	public void testMouseDragBattleshipVertical() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getBattleShip().rotate();
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getBattleShip().getX() + 5,
				testFrame.getBattleShip().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame, 
						506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 
						502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());		
	}
	/**
	 * Test dragging and placing a cruiser on the grid horizontally.
	 */
	@Test
	public void testMouseDragCruiserHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getCruiser().getX() + 5,
				testFrame.getCruiser().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame, 506,
					1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 502,
					1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());		
	}
	/**
	 * Test dragging and placing a cruiser on the grid vertically.
	 */
	@Test
	public void testMouseDragCruiserVertical() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getCruiser().rotate();
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getCruiser().getX() + 5,
				testFrame.getCruiser().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame, 
						506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 
						502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());		
	}
	/**
	 * Test dragging and placing a submarine on the grid horizontally.
	 */
	@Test
	public void testMouseDragSubmarineHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getSubmarine().getX() + 5,
				testFrame.getSubmarine().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame, 
						506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 
						502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());		
	}
	/**
	 * Test dragging and placing a submarine on the grid vertically.
	 */
	@Test
	public void testMouseDragSubmarineVertical() {
		ShipSetupFrame testFrame =
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getSubmarine().rotate();
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getSubmarine().getX() + 5,
				testFrame.getSubmarine().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame,
						506, 1, 0, 600, 130, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame,
						502, 1, 0, 600, 130, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());		
	}
	/**
	 * Test dragging and placing a patrol boat on the grid horizontally.
	 */
	@Test
	public void testMouseDragPatrolBoatHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getPatrolBoat().getX() + 20,
				testFrame.getPatrolBoat().getY() + 20, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame,
						506, 1, 0, 640, 200, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 
						502, 1, 0, 640, 200, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(2, testFrame.getPatrolBoat().getColumn());
		assertEquals(2, testFrame.getPatrolBoat().getRow());		
	}
	/**
	 * Test dragging and placing a patrol boat on the grid vertically.
	 */
	@Test
	public void testMouseDragPatrolBoatVertical() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.getPatrolBoat().rotate();
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getPatrolBoat().getX() + 20,
				testFrame.getPatrolBoat().getY() + 20, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = 
				new MouseEvent(testFrame, 
						506, 1, 0, 640, 200, 0, true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = 
				new MouseEvent(testFrame, 
						502, 1, 0, 640, 200, 0, true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(2, testFrame.getPatrolBoat().getColumn());
		assertEquals(2, testFrame.getPatrolBoat().getRow());		
	}
	/**
	 * Test clicking on an aircraft carrier to rotate it.
	 */
	@Test
	public void testMouseClickAircraftCarrier() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getAircraftCarrier().getX() + 5,
				testFrame.getAircraftCarrier().getY() + 5,
					0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseClickEvent = new MouseEvent(testFrame,
				500, 0, 0,
				testFrame.getAircraftCarrier().getX() + 5,
				testFrame.getAircraftCarrier().getY() + 5,
					0, true);
		testFrame.mouseClicked(testMouseClickEvent);	
		assertEquals(false, 
				testFrame.getAircraftCarrier().isHorizontal());	
	}
	/**
	 * Test clicking on a battleship to rotate it.
	 */
	@Test
	public void testMouseClickBattleShip() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getBattleShip().getX() + 5,
				testFrame.getBattleShip().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseClickEvent = new MouseEvent(testFrame,
				500, 0, 0,
				testFrame.getBattleShip().getX() + 5,
				testFrame.getBattleShip().getY() + 5, 0, true);
		testFrame.mouseClicked(testMouseClickEvent);	
		assertEquals(false, testFrame.getBattleShip().isHorizontal());	
	}
	/**
	 * Test clicking on a submarine to rotate it.
	 */
	@Test
	public void testMouseClickSubmarine() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getSubmarine().getX() + 5,
				testFrame.getSubmarine().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseClickEvent = new MouseEvent(testFrame,
				500, 0, 0,
				testFrame.getSubmarine().getX() + 5,
				testFrame.getSubmarine().getY() + 5, 0, true);
		testFrame.mouseClicked(testMouseClickEvent);	
		assertEquals(false, testFrame.getSubmarine().isHorizontal());	
	}
	/**
	 * Test clicking on a cruiser to rotate it.
	 */
	@Test
	public void testMouseClickCruiser() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getCruiser().getX() + 5,
				testFrame.getCruiser().getY() + 5, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseClickEvent = new MouseEvent(testFrame,
				500, 0, 0,
				testFrame.getCruiser().getX() + 5,
				testFrame.getCruiser().getY() + 5, 0, true);
		testFrame.mouseClicked(testMouseClickEvent);	
		assertEquals(false, testFrame.getCruiser().isHorizontal());	
	}
	/**
	 * Test clicking on a patrol boat to rotate it.
	 */
	@Test
	public void testMouseClickPatrolBoat() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getPatrolBoat().getX() + 20,
				testFrame.getPatrolBoat().getY() + 20, 0, true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseClickEvent = new MouseEvent(testFrame,
				500, 0, 0,
				testFrame.getPatrolBoat().getX() + 20,
				testFrame.getPatrolBoat().getY() + 20, 0, true);
		testFrame.mouseClicked(testMouseClickEvent);	
		assertEquals(false, testFrame.getPatrolBoat().isHorizontal());	
	}
	/**
	 * Test the CPU placing each ship horizontally.
	 */
	@Test
	public void setCpuShipsHorizontal() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.setCpuValues(1);
		int count = 0;
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (testFrame.getPlayer2Values()[x][y] != 0) {
					count++;
				}
			}
		}
		assertEquals(17, count);
		
	}
	/**
	 * Test CPU placing each ship vertically.
	 */
	@Test
	public void setCpuShipsVertical() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.setCpuValues(2);
		int count = 0;
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (testFrame.getPlayer2Values()[x][y] != 0) {
					count++;
				}
			}
		}
		assertEquals(17, count);	
	}
	/**
	 * Test placing aircraft carrier over battleship.
	 */
	@Test
	public void testShipOverLapAircraftOverBattleShip() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getAircraftCarrier(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getBattleShip(), 900, 130);
		assertEquals(0, testFrame.getBattleShip().getX());
	}
	/**
	 * Test placing aircraft carrier over cruiser.
	 */
	@Test
	public void testShipOverLapAircraftOverCruiser() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getAircraftCarrier(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getCruiser(), 900, 130);
		assertEquals(0, testFrame.getCruiser().getX());
	}
	/**
	 * Test placing aircraft carrier over submarine.
	 */
	@Test
	public void testShipOverLapAircraftOverSubmarine() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getAircraftCarrier(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getSubmarine(), 900, 130);
		assertEquals(0, testFrame.getSubmarine().getX());
	}
	/**
	 * Test placing aircraft carrier over patrol boat.
	 */
	@Test
	public void testShipOverLapAircraftOverPatrolBoat() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getAircraftCarrier(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getPatrolBoat(), 900, 130);
		assertEquals(0, testFrame.getPatrolBoat().getX());
	}
	/**
	 * Test placing battleship over aircraft carrier.
	 */
	@Test
	public void testShipOverLapBattleShipOverAircraftCarrier() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getBattleShip(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getAircraftCarrier(), 900, 130);
		assertEquals(0, testFrame.getAircraftCarrier().getX());
	}
	/**
	 * Test placing battleship over cruiser.
	 */
	@Test
	public void testShipOverLapBattleShipOverCruiser() {
		ShipSetupFrame testFrame =
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame, 
				testFrame.getBattleShip(), 850, 130);
		dragShipHorizontalTo(testFrame, 
				testFrame.getCruiser(), 900, 130);
		assertEquals(0, testFrame.getCruiser().getX());
	}
	/**
	 * Test placing battleship over submarine.
	 */
	@Test
	public void testShipOverLapBattleShipOverSubmarine() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getBattleShip(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getSubmarine(), 900, 130);
		assertEquals(0, testFrame.getSubmarine().getX());
	}
	/**
	 * Test placing battleship over patrol boat.
	 */
	@Test
	public void testShipOverLapBattleShipOverPatrolBoat() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getBattleShip(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getPatrolBoat(), 900, 130);
		assertEquals(0, testFrame.getPatrolBoat().getX());
	}
	/**
	 * Test placing cruiser over aircraft carrier.
	 */
	@Test
	public void testShipOverLapCruiserOverAircraftCarrier() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getCruiser(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getAircraftCarrier(), 900, 130);
		assertEquals(0, testFrame.getAircraftCarrier().getX());
	}
	/**
	 * Test placing cruiser over battleship.
	 */
	@Test
	public void testShipOverLapCruiserOverBattleShip() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getCruiser(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getBattleShip(), 900, 130);
		assertEquals(0, testFrame.getBattleShip().getX());
	}
	/**
	 * Test placing cruiser over submarine.
	 */
	@Test
	public void testShipOverLapCruiserOverSubmarine() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getCruiser(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getSubmarine(), 900, 130);
		assertEquals(0, testFrame.getSubmarine().getX());
	}
	/**
	 * Test placing cruiser over patrol boat.
	 */
	@Test
	public void testShipOverLapCruiserOverPatrolBoat() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getCruiser(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getPatrolBoat(), 860, 130);
		assertEquals(0, testFrame.getPatrolBoat().getX());
	}
	/**
	 * Test placing submarine over aircraft carrier.
	 */
	@Test
	public void testShipOverLapSubmarineOverAircraftCarrier() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getSubmarine(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getAircraftCarrier(), 900, 130);
		assertEquals(0, testFrame.getAircraftCarrier().getX());
	}
	/**
	 * Test placing submarine over battleship.
	 */
	@Test
	public void testShipOverLapSubmarineOverBattleShip() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getSubmarine(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getBattleShip(), 900, 130);
		assertEquals(0, testFrame.getBattleShip().getX());
	}
	/**
	 * Test placing submarine over cruiser.
	 */
	@Test
	public void testShipOverLapSubmarineOverCruiser() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getSubmarine(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getCruiser(), 900, 130);
		assertEquals(0, testFrame.getCruiser().getX());
	}
	/**
	 * Test placing submarine over patrol boat.
	 */
	@Test
	public void testShipOverLapSubmarineOverPatrolBoat() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getSubmarine(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getPatrolBoat(), 900, 130);
		assertEquals(0, testFrame.getPatrolBoat().getX());
	}
	/**
	 * Test placing patrol boat over aircraft carrier.
	 */
	@Test
	public void testShipOverLapPatrolBoatOverAircraftCarrier() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getPatrolBoat(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getAircraftCarrier(), 900, 130);
		assertEquals(0, testFrame.getAircraftCarrier().getX());
	}
	/**
	 * Test placing patrol boat over battleship.
	 */
	@Test
	public void testShipOverLapPatrolBoatOverBattleShip() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getPatrolBoat(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getBattleShip(), 900, 130);
		assertEquals(0, testFrame.getBattleShip().getX());
	}
	/**
	 * Test placing patrol boat over cruiser.
	 */
	@Test
	public void testShipOverLapPatrolBoatOverCruiser() {
		ShipSetupFrame testFrame =
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getPatrolBoat(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getCruiser(), 900, 130);
		assertEquals(0, testFrame.getCruiser().getX());
	}
	/**
	 * Test placing patrol boat over submarine.
	 */
	@Test
	public void testShipOverLapPatrolBoatOverSubmarine() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		dragShipVerticalTo(testFrame,
				testFrame.getPatrolBoat(), 850, 130);
		dragShipHorizontalTo(testFrame,
				testFrame.getSubmarine(), 900, 130);
		assertEquals(0, testFrame.getSubmarine().getX());
	}
	/**
	 * Test running submit method after clicking on submit button.
	 */
	@Test
	public void testSubmitButtonClick() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.submit();
		assertEquals(true, testFrame.getInvalidShipPlacement());
	}
	/**
	 * Test CPU placing its ships randomly.
	 */
	@Test
	public void setCpuShipsRandom() {
		ShipSetupFrame testFrame = 
				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
		testFrame.setCpuValues(0);
		int count = 0;
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (testFrame.getPlayer2Values()[x][y] != 0) {
					count++;
				}
			}
		}
		assertEquals(17, count);	
	}
	/**
	 * Test HomePageGui and buttons.
	 */
	@Test
	public void testHomePageGui() {
		HomePageGui hpTest = new HomePageGui();
		hpTest.setTitle("Play BattleShip!");
		hpTest.setSize(1200, 700);
		hpTest.setLocationRelativeTo(null);
		hpTest.setResizable(false);
		hpTest.setVisible(true);
		
		//test single player button
		assertNotNull("Verify that ShipSetupFrame is not null",
				hpTest.startOnePlayer());
		
		//test pass & play button
//		assertNotNull("Verify that ShipSetupFrame is not null",
//				hpTest.startPassAndPlay());
		
	}
	
	/**
	 * Test Cpu firing on player ships.
	 */
//	@Test
//	public void testCpuFire() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		//Test two shots on aircraft carrier
//		Point point = new Point(1, 1);
//		testBoard.cpuFire(point);
//		assertEquals(1, testBoard.getCpuAircraftCarrierHits());
//		point = new Point(1, 2);
//		testBoard.cpuFire(point);
//		assertEquals(2, testBoard.getCpuAircraftCarrierHits());
//		
//		//Test shot on battleship
//		point = new Point(2, 1);
//		testBoard.cpuFire(point);
//		assertEquals(1, testBoard.getCpuBattleShipHits());
//		
//		//Test shot on cruiser
//		point = new Point(3, 1);
//		testBoard.cpuFire(point);
//		assertEquals(1, testBoard.getCpuCruiserHits());
//		
//		//Test shot on submarine
//		point = new Point(4, 1);
//		testBoard.cpuFire(point);
//		assertEquals(1, testBoard.getCpuSubmarineHits());
//		
//		//Test shot on patrol boat
//		point = new Point(5, 1);
//		testBoard.cpuFire(point);
//		assertEquals(1, testBoard.getCpuPatrolBoatHits());
//	}
//	
	/**
	 * Test for cpu winning.
	 */
//	@Test
//	public void testCpuWin() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		//Test win JOptionPane for cpu
//		testBoard.setCpuAircraftCarrierHits(5);
//		testBoard.setCpuBattleShipHits(4);
//		testBoard.setCpuCruiserHits(3);
//		testBoard.setCpuSubmarineHits(3);
//		testBoard.setCpuPatrolBoatHits(2);
//		assertEquals(5, testBoard.getCpuAircraftCarrierHits());
//		assertEquals(4, testBoard.getCpuBattleShipHits());
//		assertEquals(3, testBoard.getCpuCruiserHits());
//		assertEquals(3, testBoard.getCpuSubmarineHits());
//		assertEquals(2, testBoard.getCpuPatrolBoatHits());
//		Point point = new Point(0, 0);
//		testBoard.cpuFire(point);
//		JOptionPane win = new JOptionPane();
//		assertNotNull("Verify that JOptionPane is not null", win);
//		
//	}
	/**
	 * Test for the player winning.
	 */
	@Test
	public void testPlayerWin() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
		
		testBoard.setAircraftCarrierHits(5);
		testBoard.setBattleShipHits(4);
		testBoard.setCruiserHits(3);
		testBoard.setSubmarineHits(3);
		testBoard.setPatrolBoatHits(2);
		assertEquals(5, testBoard.getAircraftCarrierHits());
		assertEquals(4, testBoard.getBattleShipHits());
		assertEquals(3, testBoard.getCruiserHits());
		assertEquals(3, testBoard.getSubmarineHits());
		assertEquals(2, testBoard.getPatrolBoatHits());
		
		JButton btn = new JButton();
		btn.setName("0");
		testBoard.playerShot(btn);
		
		//checks win JOptionPane
		JOptionPane win = new JOptionPane();
		assertNotNull("Verify that JOptionPane is not null", win);
	}
	
	/**
	 * Test for player shooting at same spot twice.
	 */
	@Test
	public void testPlayerShotAlreadyShot() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
		JButton btn = new JButton();
		btn.setName("0");
		testBoard.playerShot(btn);
		assertEquals("shot", btn.getName());
		
		//checks code for already shot location
		JOptionPane alreadyShot = new JOptionPane();
		testBoard.playerShot(btn);
		assertNotNull("Verify that JOptionPane is not null",
				alreadyShot);
	}
	
	/**
	 * Test for player shot on aircraftcarrier.
	 */
	//@Test
//	public void testPlayerShotsACC() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		JButton btn = new JButton();
//		
//		//AircraftCarrier hits
//		for (int i = 0; i < 5; i++) {
//			btn.setName("5");
//			testBoard.playerShot(btn);
//			assertEquals("shot5", btn.getName());
//		}
//		assertEquals(5, testBoard.getAircraftCarrierHits());
//	}
	
	/**
	 * Test for player shot on battleship.
	 */
//	@Test
//	public void testPlayerShotsBS() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		JButton btn = new JButton();
//		
//		//BattleShip hits
//		for (int i = 0; i < 4; i++) {
//			btn.setName("4");
//			testBoard.playerShot(btn);
//			assertEquals("shot4", btn.getName());
//		}
//		assertEquals(4, testBoard.getBattleShipHits());
//	}
	
	/**
	 * Test for player shot on cruiser.
	 */
	//@Test
//	public void testPlayerShotsC() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		JButton btn = new JButton();
//		
//		//Cruiser hits
//		for (int i = 0; i < 3; i++) {
//			btn.setName("3");
//			testBoard.playerShot(btn);
//			assertEquals("shot3", btn.getName());
//		}
//		assertEquals(3, testBoard.getCruiserHits());
//	}
	
	/**
	 * Test for player shot on submarine.
	 */
//	@Test
//	public void testPlayerShotsS() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		JButton btn = new JButton();
//		
//		//Submarine
//		for (int i = 0; i < 3; i++) {
//			btn.setName("2");
//			testBoard.playerShot(btn);
//			assertEquals("shot2", btn.getName());
//		}
//		assertEquals(3, testBoard.getSubmarineHits());	
//	}
//	
	/**
	 * Test for player shot on patrol boat.
	 */
//	@Test
//	public void testPlayerShotsPB() {
//		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode, Difficulty.Easy);
//		ShipSetupFrame testFrame = createSSF();
//		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
//		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
//		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
//		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
//		
//		JButton btn = new JButton();
//		
//		//PatrolBoat hits
//		for (int i = 0; i < 2; i++) {
//			btn.setName("1");
//			testBoard.playerShot(btn);
//			assertEquals("shot1", btn.getName());
//		}
//		assertEquals(2, testBoard.getPatrolBoatHits());
//	}
	
	
	/**
	 * test the server.
	 */
	@Test 
	public void testServer() {
		Server server = new Server(5445);
		server.start();
		
		MultiplayerMenu gui = new MultiplayerMenu();
		
		Client client = new Client("localhost", 5445, gui);
		client.start();
		
		String ip = client.getMyIP();
		
		ServerInfo si = new ServerInfo("name", "password", ip, 0);
		
		assertEquals("name", si.getName());
		assertEquals("password", si.getPassword());
		assertEquals(ip, si.getIP());
		assertEquals(0, si.getType());
		assertEquals(si, si.getServerInfo());
		
		
	}
	
//	/**
//	 * Creates a ShipSetupFrame for testing.
//	 * @return a ShipSetupFrame
//	 */
//	public ShipSetupFrame createSSF() {
//		
//		ShipSetupFrame testFrame = 
//				new ShipSetupFrame(GameMode.OnePlayerMode, 1, Difficulty.Easy);
//		
//		//snaps aircraft carrier to column 1 row 1
//		testFrame.getAircraftCarrier().setLocation(600, 130);
//		testFrame.snapShipToX(testFrame.getAircraftCarrier());
//		testFrame.snapShipToY(testFrame.getAircraftCarrier());
//		
//		//snaps battleShip to column 1 row 2
//		testFrame.getBattleShip().setLocation(600, 165);
//		testFrame.snapShipToX(testFrame.getBattleShip());
//		testFrame.snapShipToY(testFrame.getBattleShip());
//		
//		//snaps cruiser to column 1 row 3
//		testFrame.getCruiser().setLocation(600, 223);
//		testFrame.snapShipToX(testFrame.getCruiser());
//		testFrame.snapShipToY(testFrame.getCruiser());
//		
//		//snaps submarine to column 1 row 4
//		testFrame.getSubmarine().setLocation(600, 281);
//		testFrame.snapShipToX(testFrame.getSubmarine());
//		testFrame.snapShipToY(testFrame.getSubmarine());
//		
//		//snaps patrol boat to column 1 row 5
//		testFrame.getPatrolBoat().setLocation(600, 339);
//		testFrame.snapShipToX(testFrame.getPatrolBoat());
//		testFrame.snapShipToY(testFrame.getPatrolBoat());
//		
//		testFrame.submit();
//		
//		return testFrame;
//	}
	

}
