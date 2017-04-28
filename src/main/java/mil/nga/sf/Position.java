package mil.nga.sf;

import java.util.List;

/**
 * A single location in space. Each position has an X and Y coordinate. 
 * A point MAY optionally also have a Z and/or an M value.
 * 
 * @author jyutzler
 */
public class Position {

	/**
	 * X coordinate
	 */
	final private double[] coordinates;

	/**
	 * has Z (because in some cases it can have M but no Z)
	 */
	final private boolean hasZ;
	
	/**
	 * Constructor
	 * 
	 */
	public Position() {
		this(null, null, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Position(Double x, Double y) {
		this(x, y, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 */
	public Position(Double x, Double y, Double z) {
		this(x, y, z, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @param m
	 *            m coordinate
	 */
	public Position(Double x, Double y, Double z, Double m) {
		if ((x == null) || (y == null)) {
			coordinates = new double[0];
			hasZ = false;
		} else {
			if (m != null) {
				coordinates = new double[4];
				hasZ = (z != null);
			} else if (z != null) {
				coordinates = new double[3];
				hasZ = true;
			} else {
				coordinates = new double[2];
				hasZ = false;
			}
		}
		
		if (coordinates.length >= 1) {
			coordinates[0] = x;
		}
		if (coordinates.length >= 2) {
			coordinates[1] = y;
		}
		if (coordinates.length >= 3) {
			// In the rare event of M but no Z...
			if (hasZ) {
				coordinates[2] = z;
			}
		}
		if (coordinates.length >= 4) {
			coordinates[3] = m;
		}
	}

	/**
	 * Get x
	 * 
	 * @return x
	 */
	public Double getX() {
		return (coordinates.length > 0) ? coordinates[0] : null;
	}

	/**
	 * Get y
	 * 
	 * @return y
	 */
	public Double getY() {
		return (coordinates.length > 1) ? coordinates[1] : null;
	}

	/**
	 * Get z
	 * 
	 * @return z
	 */
	public Double getZ() {
		return (hasZ && (coordinates.length > 2)) ? coordinates[2] : null;
	}

	/**
	 * Get m
	 * 
	 * @return m
	 */
	public Double getM() {
		return (coordinates.length > 3) ? coordinates[3] : null;
	}
	
	public boolean equals(Position right){
		if (this == right) {
			return true;
		}
		return compare(this.getM(), right.getM()) &&
				compare(this.getX(), right.getX()) && 
				compare(this.getY(), right.getY()) &&
				compare(this.getZ(), right.getZ());
	}
	
	static private boolean compare(Double left, Double right) {
		if ((left == null) || (right == null)) {
			return (left == null) && (right == null);
		}
		return left.compareTo(right) == 0;
	}
	
	public static boolean hasZ(List<Position> positions){
		boolean result = false;
		for(Position position : positions){
			if (position.getZ() != null) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static boolean hasM(List<Position> positions){
		boolean result = false;
		for(Position position : positions){
			if (position.getM() != null) {
				result = true;
				break;
			}
		}
		return result;
	}
}
