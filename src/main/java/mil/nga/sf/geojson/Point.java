package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Point extends Geometry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5973262853989536760L;
	/**
	 * 
	 */
	private mil.nga.sf.Point point;
	private Double[] additionalElements;

	public Point() {
	}

	public Point(Position position) {
		setCoordinates(position);
	}

	public Point(mil.nga.sf.Point input) {
		point = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position object
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Position getCoordinates() {
		return new Position(point.getX(), point.getY(), point.getZ(), additionalElements);
	}

	private void setCoordinates(Position position) {
		additionalElements = position.getAdditionalElements();
		point = new mil.nga.sf.Point(position.getLongitude(), 
				position.getLatitude(), 
				position.getAltitude(), 
				(additionalElements.length > 0) ? additionalElements[0] : null);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return point;
	}
}
