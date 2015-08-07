package mil.nga.wkb.geom;

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
	 * @param hasM
	 */
	public GeometryEnvelope(boolean hasZ, boolean hasM) {
		this.hasZ = hasZ;
		this.hasM = hasM;
	}

	/**
	 * True if has Z coordinates
	 * 
	 * @return
	 */
	public boolean hasZ() {
		return hasZ;
	}

	/**
	 * True if has M measurements
	 * 
	 * @return
	 */
	public boolean hasM() {
		return hasM;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public boolean isHasZ() {
		return hasZ;
	}

	public void setHasZ(boolean hasZ) {
		this.hasZ = hasZ;
	}

	public Double getMinZ() {
		return minZ;
	}

	public void setMinZ(Double minZ) {
		this.minZ = minZ;
	}

	public Double getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(Double maxZ) {
		this.maxZ = maxZ;
	}

	public boolean isHasM() {
		return hasM;
	}

	public void setHasM(boolean hasM) {
		this.hasM = hasM;
	}

	public Double getMinM() {
		return minM;
	}

	public void setMinM(Double minM) {
		this.minM = minM;
	}

	public Double getMaxM() {
		return maxM;
	}

	public void setMaxM(Double maxM) {
		this.maxM = maxM;
	}

}
