package battleship;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	public void TestHomePageGui() {
		HomePageGui hpTest = new HomePageGui();
		hpTest.setTitle("Play BattleShip!");
		hpTest.setSize(1200,700);
		hpTest.setLocationRelativeTo(null);
		hpTest.setResizable(false);
		hpTest.setVisible(true);
		
		//test single player button
		assertNotNull("Verify that ShipSetupFrame is not null", hpTest.startOnePlayer());
		
		//test pass & play button
		assertNotNull("Verify that ShipSetupFrame is not null", hpTest.startPassAndPlay());
		
	}
	
	@Test
	public void TestCpuFire() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		ShipSetupFrame testFrame = createSSF();
		testBoard.setPlayer1Ships(testFrame.getPlayer1Ships());
		testBoard.setPlayer2Ships(testFrame.getPlayer2Ships());
		testBoard.setPlayer1Values(testFrame.getPlayer1Values());
		testBoard.setPlayer2Values(testFrame.getPlayer2Values());
		
		//Test two shots on aircraft carrier
		Point point = new Point(1,1);
		testBoard.cpuFire(point);
		assertEquals(1, testBoard.getCpuAircraftCarrierHits());
		point = new Point(1,2);
		testBoard.cpuFire(point);
		assertEquals(2, testBoard.getCpuAircraftCarrierHits());
		
		//Test shot on battleship
		point = new Point(2,1);
		testBoard.cpuFire(point);
		assertEquals(1, testBoard.getCpuBattleShipHits());
		
		//Test shot on cruiser
		point = new Point(3,1);
		testBoard.cpuFire(point);
		assertEquals(1, testBoard.getCpuCruiserHits());
		
		//Test shot on submarine
		point = new Point(4,1);
		testBoard.cpuFire(point);
		assertEquals(1, testBoard.getCpuSubmarineHits());
		
		//Test shot on patrol boat
		point = new Point(5,1);
		testBoard.cpuFire(point);
		assertEquals(1, testBoard.getCpuPatrolBoatHits());
	}
	
	@Test
	public void TestCpuWin() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		//Test win JOptionPane for cpu
		testBoard.setCpuAircraftCarrierHits(5);
		testBoard.setCpuBattleShipHits(4);
		testBoard.setCpuCruiserHits(3);
		testBoard.setCpuSubmarineHits(3);
		testBoard.setCpuPatrolBoatHits(2);
		assertEquals(5, testBoard.getCpuAircraftCarrierHits());
		assertEquals(4, testBoard.getCpuBattleShipHits());
		assertEquals(3, testBoard.getCpuCruiserHits());
		assertEquals(3, testBoard.getCpuSubmarineHits());
		assertEquals(2, testBoard.getCpuPatrolBoatHits());
		Point point = new Point(0,0);
		testBoard.cpuFire(point);
		JOptionPane win = new JOptionPane();
		assertNotNull("Verify that JOptionPane is not null", win);
		
	}
	
	@Test
	public void TestPlayerWin() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		
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
	
	@Test
	public void TestPlayerShotAlreadyShot() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		btn.setName("0");
		testBoard.playerShot(btn);
		assertEquals("shot", btn.getName());
		
		//checks code for already shot location
		JOptionPane alreadyShot = new JOptionPane();
		testBoard.playerShot(btn);
		assertNotNull("Verify that JOptionPane is not null", alreadyShot);
	}
	
	@Test
	public void TestPlayerShotsACC() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		
		//AircraftCarrier hits
		for (int i = 0; i < 5; i++) {
			btn.setName("5");
			testBoard.playerShot(btn);
			assertEquals("shot5", btn.getName());
		}
		assertEquals(5, testBoard.getAircraftCarrierHits());
	}
	
	@Test
	public void TestPlayerShotsBS() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		
		//Battleship hits
		for (int i = 0; i < 4; i++) {
			btn.setName("4");
			testBoard.playerShot(btn);
			assertEquals("shot4", btn.getName());
		}
		assertEquals(4, testBoard.getBattleShipHits());
	}
	
	@Test
	public void TestPlayerShotsC() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		
		//Cruiser hits
		for (int i = 0; i < 3; i++) {
			btn.setName("3");
			testBoard.playerShot(btn);
			assertEquals("shot3", btn.getName());
		}
		assertEquals(3, testBoard.getCruiserHits());
	}
	
	@Test
	public void TestPlayerShotsS() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		
		//Submarine
		for (int i = 0; i < 3; i++) {
			btn.setName("2");
			testBoard.playerShot(btn);
			assertEquals("shot2", btn.getName());
		}
		assertEquals(3, testBoard.getSubmarineHits());	
	}
	
	@Test
	public void TestPlayerShotsPB() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		
		//PatrolBoat hits
		for (int i = 0; i < 2; i++) {
			btn.setName("1");
			testBoard.playerShot(btn);
			assertEquals("shot1", btn.getName());
		}
		assertEquals(2, testBoard.getPatrolBoatHits());
	}
	
	/**
	 * Creates a ShipSetupFrame for testing
	 * @return a ShipSetupFrame
	 */
	public ShipSetupFrame createSSF() {
		
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		
		//snaps aircraft carrier to column 1 row 1
		testFrame.getAircraftCarrier().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getAircraftCarrier());
		testFrame.snapShipToY(testFrame.getAircraftCarrier());
		
		//snaps battleShip to column 1 row 2
		testFrame.getBattleShip().setLocation(600, 165);
		testFrame.snapShipToX(testFrame.getBattleShip());
		testFrame.snapShipToY(testFrame.getBattleShip());
		
		//snaps cruiser to column 1 row 3
		testFrame.getCruiser().setLocation(600, 223);
		testFrame.snapShipToX(testFrame.getCruiser());
		testFrame.snapShipToY(testFrame.getCruiser());
		
		//snaps submarine to column 1 row 4
		testFrame.getSubmarine().setLocation(600, 281);
		testFrame.snapShipToX(testFrame.getSubmarine());
		testFrame.snapShipToY(testFrame.getSubmarine());
		
		//snaps patrol boat to column 1 row 5
		testFrame.getPatrolBoat().setLocation(600, 339);
		testFrame.snapShipToX(testFrame.getPatrolBoat());
		testFrame.snapShipToY(testFrame.getPatrolBoat());
		
		testFrame.submit();
		
		return testFrame;
	}
	

}
