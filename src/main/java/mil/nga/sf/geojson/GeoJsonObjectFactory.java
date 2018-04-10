package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.util.SFException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * GeoJSON Object
 * 
 * @author yutzlejp
 * @author osbornb
 */
public class GeoJsonObjectFactory {

	/**
	 * Object mapper
	 */
	public final static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Convert a simple geometry to a GeoJSON geometry
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return geometry
	 */
	public static Geometry toGeometry(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = null;
		GeoJsonObject geoJsonObject = toGeoJsonObject(simpleGeometry);
		if(geoJsonObject != null){
			geometry = (Geometry) geoJsonObject;
		}
		return geometry;
	}
	
	/**
	 * Convert a simple geometry to a GeoJSON object
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return GeoJSON object
	 */
	public static GeoJsonObject toGeoJsonObject(mil.nga.sf.Geometry simpleGeometry) {
		GeoJsonObject geoJsonObject = null;
		if (simpleGeometry instanceof mil.nga.sf.Point) {
			geoJsonObject = new Point((mil.nga.sf.Point) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.LineString) {
			geoJsonObject = new LineString((mil.nga.sf.LineString) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiPoint) {
			geoJsonObject = new MultiPoint((mil.nga.sf.MultiPoint) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.Polygon) {
			geoJsonObject = new Polygon((mil.nga.sf.Polygon) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiLineString) {
			geoJsonObject = new MultiLineString(
					(mil.nga.sf.MultiLineString) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiPolygon) {
			geoJsonObject = new MultiPolygon(
					(mil.nga.sf.MultiPolygon) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.GeometryCollection<?>) {
			@SuppressWarnings("unchecked")
			mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> simpleGeometryCollection = (mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry>) simpleGeometry;
			geoJsonObject = new GeometryCollection(simpleGeometryCollection);
		}
		return geoJsonObject;
	}

	/**
	 * Convert the string content to a geometry
	 * 
	 * @param content
	 *            string content
	 * @return geometry
	 */
	public static Geometry toGeometry(String content) {
		JsonNode tree;
		try {
			tree = mapper.readTree(content);
		} catch (Exception e) {
			throw new SFException("Failed to convert content to a node tree: "
					+ content, e);
		}
		Geometry geometry = toGeometry(tree);
		return geometry;
	}

	/**
	 * Convert the object value to a geometry
	 * 
	 * @param value
	 *            object value
	 * @return geometry
	 */
	public static Geometry toGeometry(Object value) {
		JsonNode tree = mapper.valueToTree(value);
		Geometry geometry = toGeometry(tree);
		return geometry;
	}

	/**
	 * Convert the JSON tree to a Geometry
	 * 
	 * @param tree
	 *            tree node
	 * @return geometry
	 */
	public static Geometry toGeometry(JsonNode tree) {
		GeoJsonObject geoJson;
		try {
			geoJson = mapper.treeToValue(tree, GeoJsonObject.class);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to convert node tree to geometry: "
					+ tree, e);
		}
		Geometry geometry = (Geometry) geoJson;
		return geometry;
	}

	/**
	 * Convert the simple geometry to an object map
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return object map
	 */
	public static Map<String, Object> toMap(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = GeoJsonObjectFactory.toGeometry(simpleGeometry);
		Map<String, Object> map = toMap(geometry);
		return map;
	}

	/**
	 * Convert the geometry to an object map
	 * 
	 * @param geometry
	 *            geometry
	 * @return object map
	 */
	public static Map<String, Object> toMap(Geometry geometry) {
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = mapper.convertValue(geometry, typeRef);
		return map;
	}

	/**
	 * Convert the simple geometry to a string value
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return string value
	 */
	public static String toStringValue(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = GeoJsonObjectFactory.toGeometry(simpleGeometry);
		String stringValue = toStringValue(geometry);
		return stringValue;
	}

	/**
	 * Convert the geometry to a string value
	 * 
	 * @param geometry
	 *            geometry
	 * @return string value
	 */
	public static String toStringValue(Geometry geometry) {
		String stringValue = null;
		try {
			stringValue = mapper.writeValueAsString(geometry);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to write geometry as a String: "
					+ geometry.getGeometry().getGeometryType(), e);
		}
		return stringValue;
	}

}
