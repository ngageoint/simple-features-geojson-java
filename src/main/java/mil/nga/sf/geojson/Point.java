package mil.nga.sf.geojson;

public class Point extends GeoJsonObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3297315529077828956L;
	private mil.nga.sf.Point point;
	private double[] additionalElements;

	public Point() {
	}

	public Point(Position position) {
		setCoordinates(position);
	}

	public Point(double longitude, double latitude) {
		point = new mil.nga.sf.Point(longitude, latitude);
	}

	public Point(double longitude, double latitude, double altitude) {
		point = new mil.nga.sf.Point(longitude, latitude, altitude);
	}

	public Point(double longitude, double latitude, double altitude, double... additionalElements) {
		this(new Position(longitude, latitude, altitude, additionalElements));
	}

	public Position getCoordinates() {
		return new Position(point.getX(), point.getY(), point.getZ(), additionalElements);
	}

	private void setCoordinates(Position position) {
		additionalElements = position.getAdditionalElements();
		point = new mil.nga.sf.Point(position.getLongitude(), 
				position.getLatitude(), 
				position.getAltitude(), 
				(additionalElements.length > 0) ? additionalElements[0] : Double.NaN);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Point oPoint = (Point)o;
		return !(point != null ? !point.equals(oPoint.point) : oPoint.point != null);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (point != null ? point.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Point{" + "coordinates=" + point.toString() + "} " + super.toString();
	}
}
