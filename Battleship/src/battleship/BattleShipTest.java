package battleship;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import org.junit.Test;

public class BattleShipTest {

	@Test
	public void testCheckAircraftCarrier() {
		Ship testAircraftCarrier = new Ship(ShipType.AircraftCarrier);
		assertEquals(325, testAircraftCarrier.getWidthH());
		assertEquals(50, testAircraftCarrier.getHeightH());
		assertEquals(280, testAircraftCarrier.getHeightV());
		assertEquals(50, testAircraftCarrier.getWidthV());
		assertEquals(ShipType.AircraftCarrier, testAircraftCarrier.getTypeOfShip());
		assertEquals(5, testAircraftCarrier.getShipSize());
		assertEquals(5, testAircraftCarrier.getValue());
		testAircraftCarrier.setHorizontal(true);
		assertEquals(true, testAircraftCarrier.isHorizontal());
		testAircraftCarrier.rotate();
		assertEquals(false, testAircraftCarrier.isHorizontal());
		testAircraftCarrier.rotate();
		assertEquals(true, testAircraftCarrier.isHorizontal());
	}
	
	@Test
	public void testCreateBattleShip() {
		Ship testBattleShip = new Ship(ShipType.Battleship);
		assertEquals(258, testBattleShip.getWidthH());
		assertEquals(50, testBattleShip.getHeightH());
		assertEquals(222, testBattleShip.getHeightV());
		assertEquals(50, testBattleShip.getWidthV());
		assertEquals(ShipType.Battleship, testBattleShip.getTypeOfShip());
		assertEquals(4, testBattleShip.getShipSize());
		assertEquals(4, testBattleShip.getValue());
		testBattleShip.setHorizontal(true);
		assertEquals(true, testBattleShip.isHorizontal());
		testBattleShip.rotate();
		assertEquals(false, testBattleShip.isHorizontal());
		testBattleShip.rotate();
		assertEquals(true, testBattleShip.isHorizontal());
	}
	
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
	
	@Test
	public void testCreatePatrolBoat() {
		Ship testPatrolBoat = new Ship(ShipType.PatrolBoat);
		assertEquals(124, testPatrolBoat.getWidthH());
		assertEquals(50, testPatrolBoat.getHeightH());
		assertEquals(106, testPatrolBoat.getHeightV());
		assertEquals(50, testPatrolBoat.getWidthV());
		assertEquals(ShipType.PatrolBoat, testPatrolBoat.getTypeOfShip());
		assertEquals(2, testPatrolBoat.getShipSize());
		assertEquals(1, testPatrolBoat.getValue());
		testPatrolBoat.setHorizontal(true);
		assertEquals(true, testPatrolBoat.isHorizontal());
		testPatrolBoat.setHorizontal(false);
		assertEquals(false, testPatrolBoat.isHorizontal());
		testPatrolBoat.rotate();
		assertEquals(true, testPatrolBoat.isHorizontal());
	}
	
	ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);

	
	@Test
	public void testSetAircraftCarrierOnGrid() {
		testFrame.getAircraftCarrier().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getAircraftCarrier());
		testFrame.snapShipToY(testFrame.getAircraftCarrier());
		assertEquals(1, testFrame.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame.getAircraftCarrier().getRow());
	}
	
	@Test
	public void testSetBattleShipOnGrid() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getBattleShip().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getBattleShip());
		testFrame.snapShipToY(testFrame.getBattleShip());
		assertEquals(1, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());
	}
	
	@Test
	public void testSetCruiserOnGrid() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getCruiser().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getCruiser());
		testFrame.snapShipToY(testFrame.getCruiser());
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());
	}
	
	@Test
	public void testSetSubmarineOnGrid() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getSubmarine().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getSubmarine());
		testFrame.snapShipToY(testFrame.getSubmarine());
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());
	}
	
	@Test
	public void testSetPatrolBoatOnGrid() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getPatrolBoat().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getPatrolBoat());
		testFrame.snapShipToY(testFrame.getPatrolBoat());
		assertEquals(1, testFrame.getPatrolBoat().getColumn());
		assertEquals(1, testFrame.getPatrolBoat().getRow());
	}
	
	@Test
	public void testMouseDragAircraftCarrier() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				506, 0, 0,
				testFrame.getAircraftCarrier().getX() + 5,
				testFrame.getAircraftCarrier().getY() + 5 ,0,true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame.getAircraftCarrier().getRow());		
	}
	
	@Test
	public void testMouseDragBattleship() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				506, 0, 0,
				testFrame.getBattleShip().getX() + 5,
				testFrame.getBattleShip().getY() + 5 ,0,true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());		
	}
	
	@Test
	public void testMouseDragCruiser() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				506, 0, 0,
				testFrame.getCruiser().getX() + 5,
				testFrame.getCruiser().getY() + 5 ,0,true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());		
	}
	
	@Test
	public void testMouseDragSubmarine() {
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getSubmarine().getX() + 5,
				testFrame.getSubmarine().getY() + 5 ,0,true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(testFrame, 506, 1, 0,600,130,0,true);
		testFrame.mouseDragged(testMouseDragEvent);
		MouseEvent testMouseReleaseEvent = new MouseEvent(testFrame, 502, 1, 0,600,130,0,true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());		
	}
	
	@Test
	public void testMouseDragPatrolBoat() {
		System.out.println("Patrol boat drag:");
		System.out.println("Before press - Patrol boat x : " + testFrame.getPatrolBoat().getX());
		System.out.println("               Patrol boat y : " + testFrame.getPatrolBoat().getY());
		System.out.println("Before press - Submarine x : " + testFrame.getSubmarine().getX());
		System.out.println("               Submarine y : " + testFrame.getSubmarine().getY());
//		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		MouseEvent testMousePressEvent = new MouseEvent(testFrame,
				501, 0, 0,
				testFrame.getPatrolBoat().getX() + 5,
				testFrame.getPatrolBoat().getY() + 5,0,true);
		testFrame.mousePressed(testMousePressEvent);
		MouseEvent testMouseDragEvent = new MouseEvent(testFrame, 506, 1, 0,640,200,0,true);
		System.out.println("After press - pbCanDrag = " + testFrame.getPbCanDrag());
		System.out.println("              acCanDrag = " + testFrame.getAcCanDrag());
		System.out.println("              bsCanDrag = " + testFrame.getBsCanDrag());
		System.out.println("              CCanDrag = " + testFrame.getcCanDrag());
		System.out.println("              sCanDrag = " + testFrame.getSCanDrag());
		testFrame.mouseDragged(testMouseDragEvent);
		System.out.println("After drag - Patrol boat x : " + testFrame.getPatrolBoat().getX());
		System.out.println("             Patrol boat y : " + testFrame.getPatrolBoat().getY());
		System.out.println("After drag - Submarine x : " + testFrame.getSubmarine().getX());
		System.out.println("              Submarine y : " + testFrame.getSubmarine().getY());
		MouseEvent testMouseReleaseEvent = new MouseEvent(testFrame, 502, 1, 0,640,200,0,true);
		testFrame.mouseReleased(testMouseReleaseEvent);
		System.out.println("After release - Patrol boat x : " + testFrame.getPatrolBoat().getX());
		System.out.println("             Patrol boat y : " + testFrame.getPatrolBoat().getY());
		System.out.println("Before release - Submarine x : " + testFrame.getSubmarine().getX());
		System.out.println("              Submarine y : " + testFrame.getSubmarine().getY());
		System.out.println("");
		System.out.println(testFrame.getPatrolBoat().getColumn());
//		testFrame.getPatrolBoat().setLocation(600,130);
//		testFrame.snapShipToX(testFrame.getPatrolBoat());
//		testFrame.snapShipToY(testFrame.getPatrolBoat());
		assertEquals(1, testFrame.getPatrolBoat().getColumn());
		System.out.println("hi");
		assertEquals(1, testFrame.getPatrolBoat().getRow());		
	}
	
	
	@Test
	public void samsTest() {
		//this is my test
	}
	

}
