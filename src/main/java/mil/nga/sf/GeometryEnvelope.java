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
	 * Constructor
	 * 
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 */
	public GeometryEnvelope(Double minX, Double minY, Double maxX, Double maxY) {
		this(minX, minY, null, null, maxX, maxY, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param minX
	 * @param minY
	 * @param minZ
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 */
	public GeometryEnvelope(Double minX, Double minY, Double minZ, Double maxX, Double maxY, Double maxZ) {
		this(minX, minY, minZ, null, maxX, maxY, maxZ, null);
	}

	/**
	 * Constructor
	 * 
	 * @param minX
	 * @param minY
	 * @param minZ
	 * @param minM
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 * @param maxM
	 */
	public GeometryEnvelope(Double minX, Double minY, Double minZ, Double minM, Double maxX, Double maxY, Double maxZ, Double maxM) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.hasZ = false;
		this.hasM = false;
	}

	/**
	 * Copy Constructor
	 * @param boundingBox Envelope to copy
	 */
	public GeometryEnvelope(GeometryEnvelope boundingBox) {
		this.minX = boundingBox.minX;
		this.maxX = boundingBox.maxX;
		this.minY = boundingBox.minY;
		this.maxY = boundingBox.maxY;
		this.minZ = boundingBox.minZ;
		this.maxZ = boundingBox.maxZ;
		this.minM = boundingBox.minM;
		this.maxM = boundingBox.maxM;
		this.hasM = boundingBox.hasM;
		this.hasZ = boundingBox.hasZ;
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
	 * Set min x
	 * 
	 * @param minX
	 *            min x
	 */
	public void setMinLongitude(double minX) {
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
	 * Set max x
	 * 
	 * @param maxX
	 *            max x
	 */
	public void setMaxLongitude(double maxX) {
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
	 * Set min y
	 * 
	 * @param minY
	 *            min y
	 */
	public void setMinLatitude(double minY) {
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
	 * Set max y
	 * 
	 * @param maxY
	 *            max y
	 */
	public void setMaxLatitude(double maxY) {
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
	
	/**
	 * 
	 * @return min X
	 */
	public double getMinLongitude() {
		return getMinX();
	}

	/**
	 * 
	 * @return max X
	 */
	public double getMaxLongitude() {
		return getMaxX();
	}

	/**
	 * 
	 * @return min Y
	 */
	public double getMinLatitude() {
		return getMinY();
	}
	
	/**
	 * 
	 * @return max Y
	 */
	public double getMaxLatitude() {
		return getMaxY();
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj) {
			return true;
		}
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GeometryEnvelope))
			return false;

		GeometryEnvelope other = (GeometryEnvelope) obj;
		if (Double.doubleToLongBits(maxY) != Double
				.doubleToLongBits(other.maxY))
			return false;
		if (Double.doubleToLongBits(minY) != Double
				.doubleToLongBits(other.minY))
			return false;
		if (Double.doubleToLongBits(maxX) != Double
				.doubleToLongBits(other.maxX))
			return false;
		if (Double.doubleToLongBits(minX) != Double
				.doubleToLongBits(other.minX))
			return false;
		if (hasZ != other.hasZ) {
			return false;
		}
		if (hasZ) {
			if (Double.doubleToLongBits(minZ) != Double
					.doubleToLongBits(other.minZ))
				return false;
			if (Double.doubleToLongBits(maxZ) != Double
					.doubleToLongBits(other.maxZ))
				return false;
		}
		if (hasM != other.hasM) {
			return false;
		}
		if (hasM) {
			if (Double.doubleToLongBits(minM) != Double
					.doubleToLongBits(other.minM))
				return false;
			if (Double.doubleToLongBits(maxM) != Double
					.doubleToLongBits(other.maxM))
				return false;
		}
		return true;		
	}
	
	
	/**
	 * Get the overlapping bounding box between the two bounding boxes
	 *
	 * @param boundingBox
	 *            bounding box 1
	 * @param boundingBox2
	 *            bounding box 2
	 * @return bounding box
	 */
	public static GeometryEnvelope overlap(GeometryEnvelope boundingBox,
			GeometryEnvelope boundingBox2) {

		double minLongitude = Math.max(boundingBox.getMinLongitude(),
				boundingBox2.getMinLongitude());
		double maxLongitude = Math.min(boundingBox.getMaxLongitude(),
				boundingBox2.getMaxLongitude());
		double minLatitude = Math.max(boundingBox.getMinLatitude(),
				boundingBox2.getMinLatitude());
		double maxLatitude = Math.min(boundingBox.getMaxLatitude(),
				boundingBox2.getMaxLatitude());

		GeometryEnvelope overlap = null;

		if (minLongitude < maxLongitude && minLatitude < maxLatitude) {
			overlap = new GeometryEnvelope(minLongitude, minLatitude, maxLongitude, 
					maxLatitude);
		}

		return overlap;
	}

	/**
	 * Get the union bounding box combining the two bounding boxes
	 *
	 * @param boundingBox
	 *            bounding box 1
	 * @param boundingBox2
	 *            bounding box 2
	 * @return bounding box
	 */
	public static GeometryEnvelope union(GeometryEnvelope boundingBox,
			GeometryEnvelope boundingBox2) {

		double minLongitude = Math.min(boundingBox.getMinLongitude(),
				boundingBox2.getMinLongitude());
		double maxLongitude = Math.max(boundingBox.getMaxLongitude(),
				boundingBox2.getMaxLongitude());
		double minLatitude = Math.min(boundingBox.getMinLatitude(),
				boundingBox2.getMinLatitude());
		double maxLatitude = Math.max(boundingBox.getMaxLatitude(),
				boundingBox2.getMaxLatitude());

		GeometryEnvelope union = null;

		if (minLongitude < maxLongitude && minLatitude < maxLatitude) {
			union = new GeometryEnvelope(minLongitude, minLatitude, maxLongitude,
					maxLatitude);
		}

		return union;
	}

}
