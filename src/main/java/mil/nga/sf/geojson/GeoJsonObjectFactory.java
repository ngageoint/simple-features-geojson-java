package mil.nga.sf.geojson;

public class GeoJsonObjectFactory {

	public static Geometry createObject(Object input) {
		Geometry result = null;
		if (input instanceof mil.nga.sf.SimplePoint) {
			result = new Point((mil.nga.sf.SimplePoint)input);
		} else if (input instanceof mil.nga.sf.LineString) {
			result = new LineString((mil.nga.sf.SimpleLineString)input);
		} else if (input instanceof mil.nga.sf.MultiPoint) {
			result = new MultiPoint((mil.nga.sf.MultiPoint)input);
		} else if (input instanceof mil.nga.sf.Polygon) {
			result = new Polygon((mil.nga.sf.SimplePolygon)input);
		} else if (input instanceof mil.nga.sf.SimpleMultiLineString) {
			result = new MultiLineString((mil.nga.sf.SimpleMultiLineString)input);
		} else if (input instanceof mil.nga.sf.SimpleMultiPolygon) {
			result = new MultiPolygon((mil.nga.sf.SimpleMultiPolygon)input);
		}
		return result;
	}
}
