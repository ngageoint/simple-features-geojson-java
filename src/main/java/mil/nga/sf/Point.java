package mil.nga.sf;

public interface Point extends Geometry {

	/**
	 * Get the position
	 * 
	 * @return position
	 */
	Position getPosition();

	/**
	 * Set the position
	 * 
	 * @param position
	 *            position
	 */
	void setPosition(Position position);

}