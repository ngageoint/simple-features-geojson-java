package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Multi Polygon
 * 
 * @author yutzlejp
 */
public class MultiPolygon extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of polygons
	 */
	private List<Polygon> polygons = null;

	/**
	 * Create a multi polygon from coordinates
	 * 
	 * @param coordinates
	 *            coordinates
	 * @return multi polygon
	 * @since 3.0.0
	 */
	public static MultiPolygon fromCoordinates(
			List<List<List<Position>>> coordinates) {
		MultiPolygon multiPolygon = new MultiPolygon();
		multiPolygon.setCoordinates(coordinates);
		return multiPolygon;
	}

	/**
	 * Constructor
	 */
	public MultiPolygon() {

	}

	/**
	 * Constructor
	 * 
	 * @param polygons
	 *            polygon list
	 * @since 3.0.0
	 */
	public MultiPolygon(List<Polygon> polygons) {
		this.polygons = polygons;
	}

	/**
	 * Constructor
	 * 
	 * @param multiPolygon
	 *            simple multi polygon
	 */
	public MultiPolygon(mil.nga.sf.MultiPolygon multiPolygon) {
		setMultiPolygon(multiPolygon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.MULTIPOLYGON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getMultiPolygon();
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * 
	 * @return the coordinates
	 */
	public List<List<List<Position>>> getCoordinates() {
		List<List<List<Position>>> coordinates = new ArrayList<>();
		for (Polygon polygon : polygons) {
			coordinates.add(polygon.getCoordinates());
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
	public void setCoordinates(List<List<List<Position>>> coordinates) {
		polygons = new ArrayList<>();
		for (List<List<Position>> positions : coordinates) {
			polygons.add(Polygon.fromCoordinates(positions));
		}
	}

	/**
	 * Get the polygons
	 * 
	 * @return list of polygons
	 * @since 3.0.0
	 */
	@JsonIgnore
	public List<Polygon> getPolygons() {
		return polygons;
	}

	/**
	 * Set the polygons
	 * 
	 * @param polygons
	 *            list of polygons
	 * @since 3.0.0
	 */
	public void setPolygons(List<Polygon> polygons) {
		this.polygons = polygons;
	}

	/**
	 * Get the simple features multi polygon
	 * 
	 * @return multi polygon
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.MultiPolygon getMultiPolygon() {
		List<mil.nga.sf.Polygon> simplePolygons = new ArrayList<>();
		for (Polygon polygon : polygons) {
			simplePolygons.add(polygon.getPolygon());
		}
		return new mil.nga.sf.MultiPolygon(simplePolygons);
	}

	/**
	 * Set the simple features multi polygon
	 * 
	 * @param multiPolygon
	 *            multi polygon
	 * @since 3.0.0
	 */
	public void setMultiPolygon(mil.nga.sf.MultiPolygon multiPolygon) {
		polygons = new ArrayList<>();
		for (mil.nga.sf.Polygon polygon : multiPolygon.getPolygons()) {
			polygons.add(new Polygon(polygon));
		}
	}

}
