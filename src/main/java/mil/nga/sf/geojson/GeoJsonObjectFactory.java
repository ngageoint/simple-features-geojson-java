package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.util.SFException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoJsonObjectFactory {

	public final static ObjectMapper mapper = new ObjectMapper();

	public static Geometry createObject(Object input) {
		Geometry result = null;
		if (input instanceof Geometry) {
			result = (Geometry) input;
		} else if (input instanceof mil.nga.sf.Point) {
			result = new Point((mil.nga.sf.Point) input);
		} else if (input instanceof mil.nga.sf.LineString) {
			result = new LineString((mil.nga.sf.LineString) input);
		} else if (input instanceof mil.nga.sf.MultiPoint) {
			result = new MultiPoint((mil.nga.sf.MultiPoint) input);
		} else if (input instanceof mil.nga.sf.Polygon) {
			result = new Polygon((mil.nga.sf.Polygon) input);
		} else if (input instanceof mil.nga.sf.MultiLineString) {
			result = new MultiLineString((mil.nga.sf.MultiLineString) input);
		} else if (input instanceof mil.nga.sf.MultiPolygon) {
			result = new MultiPolygon((mil.nga.sf.MultiPolygon) input);
		}
		return result;
	}

	public static Map<String, Object> toMap(Object input) {
		Geometry geometry = GeoJsonObjectFactory.createObject(input);
		return toMap(geometry);
	}

	public static Map<String, Object> toMap(Geometry geometry) {
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = mapper.convertValue(geometry, typeRef);
		return map;
	}

	public static Geometry toGeometry(String content) {
		JsonNode tree;
		try {
			tree = mapper.readTree(content);
		} catch (Exception e) {
			throw new SFException("Failed to convert content to a node tree: "
					+ content, e);
		}
		return toGeometry(tree);
	}

	public static Geometry toGeometry(Object value) {
		JsonNode tree = mapper.valueToTree(value);
		return toGeometry(tree);
	}

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

	public static String toStringValue(Object input) {
		Geometry geometry = GeoJsonObjectFactory.createObject(input);
		return toStringValue(geometry);
	}

	public static String toStringValue(Geometry geometry) {
		String value = null;
		try {
			value = mapper.writeValueAsString(geometry);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to write geometry as a String: "
					+ geometry.getGeometry().getGeometryType(), e);
		}
		return value;
	}

}
