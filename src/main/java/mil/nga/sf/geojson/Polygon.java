package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mil.nga.sf.LinearRing;

/**
 * Polygon
 * 
 * @author yutzlejp
 */
public class Polygon extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of line string rings
	 */
	private List<LineString> rings = null;

	/**
	 * Create a polygon from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return polygon
	 * @since 3.0.0
	 */
	public static Polygon fromCoordinates(List<List<Position>> coordinates) {
		Polygon polygon = new Polygon();
		polygon.setCoordinates(coordinates);
		return polygon;
	}

	/**
	 * Constructor
	 */
	public Polygon() {

	}

	/**
	 * Constructor
	 * 
	 * @param rings
	 *            ring line string list
	 * @since 3.0.0
	 */
	public Polygon(List<LineString> rings) {
		this.rings = rings;
	}

	/**
	 * Constructor
	 * 
	 * @param polygon
	 *            simple polygon
	 */
	public Polygon(mil.nga.sf.Polygon polygon) {
		setPolygon(polygon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.POLYGON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getPolygon();
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<List<Position>> getCoordinates() {
		List<List<Position>> coordinates = new ArrayList<>();
		for (LineString ring : rings) {
			coordinates.add(ring.getCoordinates());
		}
		return coordinates;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param coordinates
	 *            coordinates
	 * @since 3.0.0
	 */
	public void setCoordinates(List<List<Position>> coordinates) {
		rings = new ArrayList<>();
		for (List<Position> positions : coordinates) {
			rings.add(LineString.fromCoordinates(positions));
		}
	}

	/**
	 * Get the rings
	 * 
	 * @return list of ring line strings
	 * @since 3.0.0
	 */
	@JsonIgnore
	public List<LineString> getRings() {
		return rings;
	}

	/**
	 * Set the rings
	 * 
	 * @param rings
	 *            list of ring line strings
	 * @since 3.0.0
	 */
	public void setRings(List<LineString> rings) {
		this.rings = rings;
	}

	/**
	 * Get the simple features polygon
	 * 
	 * @return polygon
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.Polygon getPolygon() {
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon();
		for (LineString ring : rings) {
			polygon.addRing(new LinearRing(ring.getLineString().getPoints()));
		}
		return polygon;
	}

	/**
	 * Set the simple features polygon
	 * 
	 * @param polygon
	 *            polygon
	 * @since 3.0.0
	 */
	public void setPolygon(mil.nga.sf.Polygon polygon) {
		rings = new ArrayList<>();
		for (mil.nga.sf.LineString ring : polygon.getRings()) {
			rings.add(new LineString(ring));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rings == null) ? 0 : rings.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polygon other = (Polygon) obj;
		if (rings == null) {
			if (other.rings != null)
				return false;
		} else if (!rings.equals(other.rings))
			return false;
		return true;
	}

}
