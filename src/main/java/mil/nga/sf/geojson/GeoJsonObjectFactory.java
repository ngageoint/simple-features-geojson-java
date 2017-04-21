package mil.nga.sf.geojson;

public class GeoJsonObjectFactory {

	public static GeoJsonObject createObject(Object input) {
		GeoJsonObject result = null;
		if (input instanceof mil.nga.sf.Point) {
			result = new Point((mil.nga.sf.Point)input);
		}
		return result;
	}
}
