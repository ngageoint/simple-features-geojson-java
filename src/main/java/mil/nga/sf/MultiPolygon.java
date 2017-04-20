package mil.nga.sf;

import java.util.List;

/**
 * A restricted form of MultiSurface where each Surface in the collection must
 * be of type Polygon.
 * 
 * @author osbornb
 */
public class MultiPolygon extends MultiSurface<Polygon> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiPolygon(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTIPOLYGON, hasZ, hasM);
	}

	/**
	 * Get the polygons
	 * 
	 * @return polygons
	 */
	public List<Polygon> getPolygons() {
		return getGeometries();
	}

	/**
	 * Set the polygons
	 * 
	 * @param polygons
	 *            polygons
	 */
	public void setPolygons(List<Polygon> polygons) {
		setGeometries(polygons);
	}

	/**
	 * Add a polygon
	 * 
	 * @param polygon
	 *            polygon
	 */
	public void addPolygon(Polygon polygon) {
		addGeometry(polygon);
	}

	/**
	 * Get the number of polygons
	 * 
	 * @return number of polygons
	 */
	public int numPolygons() {
		return numGeometries();
	}

}
