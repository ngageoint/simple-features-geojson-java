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
		mil.nga.sf.Position pos = point.getPosition();
		return new Position(pos.getX(), pos.getY(), pos.getZ(), additionalElements);
	}

	private void setCoordinates(Position position) {
		additionalElements = position.getAdditionalElements();
		mil.nga.sf.Position pos = new mil.nga.sf.Position(position.getX(), 
				position.getY(), 
				position.getZ(), 
				(additionalElements.length > 0) ? additionalElements[0] : null);
		point = new mil.nga.sf.Point(pos);
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
