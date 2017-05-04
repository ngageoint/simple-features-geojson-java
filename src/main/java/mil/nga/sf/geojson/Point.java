package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import mil.nga.sf.GeometryType;

@JsonIgnoreProperties({"geometry"})
public class Point extends GeoJsonObject implements Geometry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5973262853989536760L;
	/**
	 * 
	 */
	private mil.nga.sf.Point point;
	
	/**
	 * Default Constructor, here to support deserialization.
	 */
	public Point() {
		point = new mil.nga.sf.Point();
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
		mil.nga.sf.Position position = point.getPosition();
		if (position instanceof Position) {
			return (Position)position;
		} 

		return new Position(position.getX(), position.getY(), position.getZ(), position.getM());
	}

	/**
	 * Sets the new position (supporting dserialization)
	 * @param position
	 */
	public void setCoordinates(Position position) {
		// When we set new coordinates, 
		// we need to completely replace the underlying geometry
		point = new mil.nga.sf.Point(position);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public mil.nga.sf.Geometry getGeometry() {
		return point;
	}
}
