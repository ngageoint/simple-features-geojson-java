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

	public Point(Coordinates coordinates) {
		setCoordinates(coordinates);
	}

	public Point(double longitude, double latitude) {
		point.setX(longitude);
		point.setY(latitude);
	}

	public Point(double longitude, double latitude, double altitude) {
		point.setX(longitude);
		point.setY(latitude);
		point.setZ(altitude);
	}

	public Point(double longitude, double latitude, double altitude, double... additionalElements) {
		setCoordinates(new Coordinates(longitude, latitude, altitude, additionalElements));
	}

	public Coordinates getCoordinates() {
		Coordinates result = new Coordinates();
		result.setLongitude(point.getX());
		result.setLatitude(point.getY());
		final double z = point.getZ();
		if (!Double.isNaN(z)) {
			result.setAltitude(z);
		}
		final double m = point.getM();
		if (!Double.isNaN(m)){
			result.setAdditionalElements(new double[]{m});			
		}
		
		return result;
	}

	public void setCoordinates(Coordinates coordinates) {
		point.setX(coordinates.getLongitude());
		point.setY(coordinates.getLatitude());
		point.setZ(coordinates.getAltitude());
		additionalElements = coordinates.getAdditionalElements();
		point.setM(additionalElements.length > 0 ? additionalElements[0] : 0);
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
