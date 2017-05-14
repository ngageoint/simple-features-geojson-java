package mil.nga.sf;

import java.util.List;

/**
 * The base type for all 2-dimensional geometry types. A 2-dimensional geometry
 * is a geometry that has an area.
 * 
 * @author yutzlerj
 */
public interface Surface extends Orientable {

	/**
	 * Get the polygons
	 * 
	 * @return polygons
	 */
	public List<CurvePolygon> getPolygons();

	/**
	 * Set the polygons
	 * 
	 * @param polygons
	 *            polygons
	 */
	public void setPolygons(List<CurvePolygon> polygons);

	/**
	 * Add a polygon
	 * 
	 * @param polygon
	 *            polygon
	 */
	public void addPolygon(CurvePolygon polygon);

	/**
	 * Get the number of polygons
	 * 
	 * @return number of polygons
	 */
	public int numPolygons();
}
