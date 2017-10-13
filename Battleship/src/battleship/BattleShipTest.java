package battleship;

import static org.junit.Assert.*;

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

	}
	
	
	

}
