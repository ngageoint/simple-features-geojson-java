package mil.nga.sf.geojson;

public class GeoJsonObjectFactory {

	public static GeoJsonObject createObject(Object input) {
		GeoJsonObject result = null;
		if (input instanceof mil.nga.sf.Point) {
			result = new Point((mil.nga.sf.Point)input);
		}
		if (input instanceof mil.nga.sf.LineString) {
			result = new LineString((mil.nga.sf.LineString)input);
		}
		if (input instanceof mil.nga.sf.MultiPoint) {
			result = new MultiPoint((mil.nga.sf.MultiPoint)input);
		}
		return result;
	}
}
