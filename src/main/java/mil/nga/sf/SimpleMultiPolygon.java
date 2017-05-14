package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PositionUtils;

/**
 * A restricted form of MultiSurface where each Surface in the collection must
 * be of type Polygon.
 * 
 * @author osbornb
 */
public class SimpleMultiPolygon extends AbstractGeometryCollection<Polygon> implements MultiPolygon  {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public SimpleMultiPolygon(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTIPOLYGON, hasZ, hasM);
	}

	public SimpleMultiPolygon(List<Polygon> polygons) {
		super(GeometryType.MULTIPOLYGON, PositionUtils.hasZ(polygons), PositionUtils.hasM(polygons));
		setPolygons(polygons);
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
