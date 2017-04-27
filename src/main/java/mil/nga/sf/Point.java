package mil.nga.sf;

/**
 * A geometry consisting of a single position.
 * 
 * @author osbornb
 */
public class Point extends Geometry {

	private Position position;

	/**
	 * Get the position
	 * 
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position
	 * 
	 * @param position
	 *            position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param position
	 *            its position
	 */
	public Point(Position pos) {
		super(GeometryType.POINT, !(pos.getZ() == null), !(pos.getM() == null));
		position = pos;
	}

	@Override
	public boolean isEmpty() {
		return (position.getX() == null) || (position.getY() == null);
	}
}
