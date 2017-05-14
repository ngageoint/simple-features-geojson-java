package mil.nga.sf;

import java.util.List;

public interface Curve extends Orientable {

	/**
	 * Get the positions
	 * 
	 * @return positions
	 */
	List<Position> getPositions();

	/**
	 * Set the positions
	 * 
	 * @param positions
	 *            positions
	 */
	void setPositions(List<Position> positions);

	/**
	 * Add a position
	 * 
	 * @param pos
	 *            position
	 */
	void addPosition(Position pos);

	/**
	 * Get the number of positions
	 * 
	 * @return number of positions
	 */
	int numPositions();

	/**
	 * 
	 * @return true: is a (closed) ring
	 */
	boolean isRing();
}