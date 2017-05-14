package mil.nga.sf;

import java.util.List;

public interface MultiPolygon extends GeometryCollection<Polygon> {

	/**
	 * Get the polygons
	 * 
	 * @return polygons
	 */
	public List<Polygon> getPolygons();

	/**
	 * Set the polygons
	 * 
	 * @param polygons
	 *            polygons
	 */
	public void setPolygons(List<Polygon> polygons);

	/**
	 * Add a polygon
	 * 
	 * @param polygon
	 *            polygon
	 */
	public void addPolygon(Polygon polygon);

	/**
	 * Get the number of polygons
	 * 
	 * @return number of polygons
	 */
	public int numPolygons();
}
