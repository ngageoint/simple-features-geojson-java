package mil.nga.sf;

/**
 * Geometry envelope
 * 
 * @author osbornb
 */
public class GeometryEnvelope {

	/**
	 * Min X
	 */
	private double minX;

	/**
	 * Max X
	 */
	private double maxX;

	/**
	 * Min Y
	 */
	private double minY;

	/**
	 * Max Y
	 */
	private double maxY;

	/**
	 * True if has z coordinates
	 */
	private boolean hasZ = false;

	/**
	 * Min Z
	 */
	private Double minZ;

	/**
	 * Max Z
	 */
	private Double maxZ;

	/**
	 * True if has M measurements
	 */
	private boolean hasM = false;

	/**
	 * Min M
	 */
	private Double minM;

	/**
	 * Max M
	 */
	private Double maxM;

	/**
	 * Constructor
	 */
	public GeometryEnvelope() {

	}

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public GeometryEnvelope(boolean hasZ, boolean hasM) {
		this.hasZ = hasZ;
		this.hasM = hasM;
	}

	/**
	 * True if has Z coordinates
	 * 
	 * @return has z
	 */
	public boolean hasZ() {
		return hasZ;
	}

	/**
	 * True if has M measurements
	 * 
	 * @return has m
	 */
	public boolean hasM() {
		return hasM;
	}

	/**
	 * Get min x
	 * 
	 * @return min x
	 */
	public double getMinX() {
		return minX;
	}

	/**
	 * Set min x
	 * 
	 * @param minX
	 *            min x
	 */
	public void setMinX(double minX) {
		this.minX = minX;
	}

	/**
	 * Get max x
	 * 
	 * @return max x
	 */
	public double getMaxX() {
		return maxX;
	}

	/**
	 * Set max x
	 * 
	 * @param maxX
	 *            max x
	 */
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	/**
	 * Get min y
	 * 
	 * @return min y
	 */
	public double getMinY() {
		return minY;
	}

	/**
	 * Set min y
	 * 
	 * @param minY
	 *            min y
	 */
	public void setMinY(double minY) {
		this.minY = minY;
	}

	/**
	 * Get max y
	 * 
	 * @return max y
	 */
	public double getMaxY() {
		return maxY;
	}

	/**
	 * Set max y
	 * 
	 * @param maxY
	 *            max y
	 */
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	/**
	 * Has z coordinates
	 * 
	 * @return true if has z coordinates
	 */
	public boolean isHasZ() {
		return hasZ;
	}

	/**
	 * Set has z coordinates
	 * 
	 * @param hasZ
	 *            has z
	 */
	public void setHasZ(boolean hasZ) {
		this.hasZ = hasZ;
	}

	/**
	 * Get min z
	 * 
	 * @return min z
	 */
	public Double getMinZ() {
		return minZ;
	}

	/**
	 * Set min z
	 * 
	 * @param minZ
	 *            min z
	 */
	public void setMinZ(Double minZ) {
		this.minZ = minZ;
	}

	/**
	 * Get max z
	 * 
	 * @return max z
	 */
	public Double getMaxZ() {
		return maxZ;
	}

	/**
	 * Set max z
	 * 
	 * @param maxZ
	 *            max z
	 */
	public void setMaxZ(Double maxZ) {
		this.maxZ = maxZ;
	}

	/**
	 * Has m coordinates
	 * 
	 * @return true if has m coordinates
	 */
	public boolean isHasM() {
		return hasM;
	}

	/**
	 * Set has m coordinates
	 * 
	 * @param hasM
	 *            has m
	 */
	public void setHasM(boolean hasM) {
		this.hasM = hasM;
	}

	/**
	 * Get min m
	 * 
	 * @return min m
	 */
	public Double getMinM() {
		return minM;
	}

	/**
	 * Set min m
	 * 
	 * @param minM
	 *            min m
	 */
	public void setMinM(Double minM) {
		this.minM = minM;
	}

	/**
	 * Get max m
	 * 
	 * @return max m
	 */
	public Double getMaxM() {
		return maxM;
	}

	/**
	 * Set max m
	 * 
	 * @param maxM
	 *            max m
	 */
	public void setMaxM(Double maxM) {
		this.maxM = maxM;
	}

}
