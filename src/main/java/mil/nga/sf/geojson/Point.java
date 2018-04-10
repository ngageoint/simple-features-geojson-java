package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Point
 * 
 * @author yutzlejp
 */
@JsonIgnoreProperties({ "geometry" })
public class Point extends GeoJsonObject implements Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -5973262853989536760L;

	/**
	 * Simple point
	 */
	private mil.nga.sf.Point point;

	/**
	 * Position
	 */
	private Position position;

	/**
	 * Constructor
	 */
	public Point() {
		this(new mil.nga.sf.Point());
	}

	/**
	 * Constructor
	 * 
	 * @param position
	 *            position
	 */
	public Point(Position position) {
		setCoordinates(position);
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param point
	 *            simple point
	 */
	public Point(mil.nga.sf.Point point) {
		this.point = point;
		position = new Position(point);
	}

	/**
	 * Returns coordinates as a GeoJSON Position object
	 * 
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Position getCoordinates() {
		return position;
	}

	/**
	 * Sets the new position (supporting deserialization)
	 * 
	 * @param position
	 *            point position
	 */
	public void setCoordinates(Position position) {
		// When we set new coordinates,
		// we need to completely replace the underlying geometry
		point = new mil.nga.sf.Point(position.getX(), position.getY(),
				position.getZ(), position.getM());
		this.position = position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public mil.nga.sf.Geometry getGeometry() {
		return point;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "Point";
	}

}
