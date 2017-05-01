package mil.nga.sf.geojson;

public class GeoJsonObjectFactory {

	public static GeoJsonObject createObject(Object input) {
		GeoJsonObject result = null;
		if (input instanceof mil.nga.sf.Point) {
			result = new Point((mil.nga.sf.Point)input);
		} else if (input instanceof mil.nga.sf.LineString) {
			result = new LineString((mil.nga.sf.LineString)input);
		} else if (input instanceof mil.nga.sf.MultiPoint) {
			result = new MultiPoint((mil.nga.sf.MultiPoint)input);
		} else if (input instanceof mil.nga.sf.Polygon) {
			result = new Polygon((mil.nga.sf.Polygon)input);
		} else if (input instanceof mil.nga.sf.MultiLineString) {
			result = new MultiLineString((mil.nga.sf.MultiLineString)input);
		}
		return result;
	}
}
