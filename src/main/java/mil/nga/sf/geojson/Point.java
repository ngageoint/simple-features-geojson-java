package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"geometry"})
public class Point extends GeoJsonObject implements Geometry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5973262853989536760L;
	/**
	 * 
	 */
	private mil.nga.sf.SimplePoint simplePoint;
	
	/**
	 * Default Constructor, here to support deserialization.
	 */
	public Point() {
		simplePoint = new mil.nga.sf.SimplePoint();
	}

	public Point(Position position) {
		setCoordinates(position);
	}

	public Point(mil.nga.sf.SimplePoint input) {
		simplePoint = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position object
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Position getCoordinates() {
		mil.nga.sf.Position position = simplePoint.getPosition();
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
		simplePoint = new mil.nga.sf.SimplePoint(position);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public mil.nga.sf.Geometry getGeometry() {
		return simplePoint;
	}

	@Override
	public String getType() {
		return "Point";
	}
}
