package mil.nga.sf;

/**
 * A geometry consisting of a single position.
 * 
 * @author osbornb
 */
public class SimplePoint extends AbstractGeometry implements Point {

	private Position position;

	/* (non-Javadoc)
	 * @see mil.nga.sf.Point#getPosition()
	 */
	@Override
	public Position getPosition() {
		return position;
	}

	/* (non-Javadoc)
	 * @see mil.nga.sf.Point#setPosition(mil.nga.sf.Position)
	 */
	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param pos
	 *            its Position
	 */
	public SimplePoint(Position pos) {
		super(GeometryType.POINT, !(pos.getZ() == null), !(pos.getM() == null));
		position = pos;
	}
	
	/**
	 * Default Constructor. It is best not to use this unless another process 
	 * intends to immediately call setPosition().
	 */
	public SimplePoint() {
		super(GeometryType.POINT, false, false);
		this.position = new Position();
	}

	@Override
	public boolean isEmpty() {
		return (position.getX() == null) || (position.getY() == null);
	}
}
