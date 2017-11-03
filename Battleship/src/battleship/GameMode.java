package battleship;

//Types of game modes
/**
 * Creates enum for different game modes.
 *
 */
public enum GameMode {
	/**
	 * Mode for person against cpu.
	 *
	 */
	OnePlayerMode,
	/**
	 * Mode for person against person
	 * while passing the device back and forth.
	 *
	 */
	TwoPlayerPassAndPlay,
	/**
	 * Mode for person against person online.
	 *
	 */
	MultiplayerMode
}
