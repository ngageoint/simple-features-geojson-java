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
	private mil.nga.sf.Point sfPoint;

	private Position position;
	/**
	 * Default Constructor, here to support deserialization.
	 */
	public Point() {
		this(new mil.nga.sf.Point());
	}

	public Point(Position position) {
		setCoordinates(position);
		this.position = position;
	}

	public Point(mil.nga.sf.Point input) {
		sfPoint = input;
		position = new Position(sfPoint);
	}

	/**
	 * Returns coordinates as a GeoJSON Position object
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Position getCoordinates() {
		return position;
	}

	/**
	 * Sets the new position (supporting dserialization)
	 * @param position
	 */
	public void setCoordinates(Position position) {
		// When we set new coordinates, 
		// we need to completely replace the underlying geometry
		sfPoint = new mil.nga.sf.Point(position.getX(), position.getY(), position.getZ(), position.getM());
		this.position = position;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public mil.nga.sf.Geometry getGeometry() {
		return sfPoint;
	}

	@Override
	public String getType() {
		return "Point";
	}
}
