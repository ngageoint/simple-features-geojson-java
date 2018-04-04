package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.util.SFException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoJsonObjectFactory {

	public final static ObjectMapper mapper = new ObjectMapper();

	public static Geometry toGeometry(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = null;
		if (simpleGeometry instanceof mil.nga.sf.Point) {
			geometry = new Point((mil.nga.sf.Point) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.LineString) {
			geometry = new LineString((mil.nga.sf.LineString) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiPoint) {
			geometry = new MultiPoint((mil.nga.sf.MultiPoint) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.Polygon) {
			geometry = new Polygon((mil.nga.sf.Polygon) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiLineString) {
			geometry = new MultiLineString(
					(mil.nga.sf.MultiLineString) simpleGeometry);
		} else if (simpleGeometry instanceof mil.nga.sf.MultiPolygon) {
			geometry = new MultiPolygon(
					(mil.nga.sf.MultiPolygon) simpleGeometry);
		}
		return geometry;
	}

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

	public static Geometry toGeometry(Object value) {
		JsonNode tree = mapper.valueToTree(value);
		Geometry geometry = toGeometry(tree);
		return geometry;
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

	public static Map<String, Object> toMap(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = GeoJsonObjectFactory.toGeometry(simpleGeometry);
		Map<String, Object> map = toMap(geometry);
		return map;
	}

	public static Map<String, Object> toMap(Geometry geometry) {
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = mapper.convertValue(geometry, typeRef);
		return map;
	}

	public static String toStringValue(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = GeoJsonObjectFactory.toGeometry(simpleGeometry);
		String stringValue = toStringValue(geometry);
		return stringValue;
	}

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
