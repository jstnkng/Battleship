package battleship;

import static org.junit.Assert.*;

import java.awt.Point;

import javax.swing.JButton;

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
	}
	
	@Test
	public void testSetAircraftCarrierOnGrid() {
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getAircraftCarrier().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getAircraftCarrier());
		testFrame.snapShipToY(testFrame.getAircraftCarrier());
		assertEquals(1, testFrame.getAircraftCarrier().getColumn());
		assertEquals(1, testFrame.getAircraftCarrier().getRow());
	}
	
	@Test
	public void testSetBattleShipOnGrid() {
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getBattleShip().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getBattleShip());
		testFrame.snapShipToY(testFrame.getBattleShip());
		assertEquals(1, testFrame.getBattleShip().getColumn());
		assertEquals(1, testFrame.getBattleShip().getRow());
	}
	
	@Test
	public void testSetCruiserOnGrid() {
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getCruiser().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getCruiser());
		testFrame.snapShipToY(testFrame.getCruiser());
		assertEquals(1, testFrame.getCruiser().getColumn());
		assertEquals(1, testFrame.getCruiser().getRow());
	}
	
	@Test
	public void testSetSubmarineOnGrid() {
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getSubmarine().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getSubmarine());
		testFrame.snapShipToY(testFrame.getSubmarine());
		assertEquals(1, testFrame.getSubmarine().getColumn());
		assertEquals(1, testFrame.getSubmarine().getRow());
	}
	
	@Test
	public void testSetPatrolBoatOnGrid() {
		ShipSetupFrame testFrame = new ShipSetupFrame(GameMode.OnePlayerMode, 1);
		testFrame.getPatrolBoat().setLocation(600, 130);
		testFrame.snapShipToX(testFrame.getPatrolBoat());
		testFrame.snapShipToY(testFrame.getPatrolBoat());
		assertEquals(1, testFrame.getPatrolBoat().getColumn());
		assertEquals(1, testFrame.getPatrolBoat().getRow());
	}
	
	@Test
	public void JustinsTest() {
			//This is my test
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
	public void TestPlayerShot() {
		GameBoard testBoard = new GameBoard(GameMode.OnePlayerMode);
		JButton btn = new JButton();
		btn.setName("0");
		testBoard.playerShot(btn);
		assertEquals("shot", btn.getName());
		//testBoard.playerShot(btn);
		//assertNotNull();
		
		//AircraftCarrier hits
		for (int i = 0; i < 5; i++) {
			btn.setName("5");
			testBoard.playerShot(btn);
			assertEquals("shot5", btn.getName());
		}
		assertEquals(5, testBoard.getAircraftCarrierHits());
		
		//Battleship hits
		for (int i = 0; i < 4; i++) {
			btn.setName("4");
			testBoard.playerShot(btn);
			assertEquals("shot4", btn.getName());
		}
		assertEquals(4, testBoard.getBattleShipHits());
		
		//Cruiser hits
		for (int i = 0; i < 3; i++) {
			btn.setName("3");
			testBoard.playerShot(btn);
			assertEquals("shot3", btn.getName());
		}
		assertEquals(3, testBoard.getCruiserHits());
		
		//Submarine
		for (int i = 0; i < 3; i++) {
			btn.setName("2");
			testBoard.playerShot(btn);
			assertEquals("shot2", btn.getName());
		}
		assertEquals(3, testBoard.getSubmarineHits());
		
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
