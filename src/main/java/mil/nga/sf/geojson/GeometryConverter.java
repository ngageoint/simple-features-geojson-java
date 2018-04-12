package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.util.SFException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Geometry Converter
 * 
 * @author yutzlejp
 * @author osbornb
 */
public class GeometryConverter {

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
		if (simpleGeometry != null) {
			if (simpleGeometry instanceof mil.nga.sf.Point) {
				geometry = new Point((mil.nga.sf.Point) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.LineString) {
				geometry = new LineString(
						(mil.nga.sf.LineString) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.MultiPoint) {
				geometry = new MultiPoint(
						(mil.nga.sf.MultiPoint) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.Polygon) {
				geometry = new Polygon((mil.nga.sf.Polygon) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.MultiLineString) {
				geometry = new MultiLineString(
						(mil.nga.sf.MultiLineString) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.MultiPolygon) {
				geometry = new MultiPolygon(
						(mil.nga.sf.MultiPolygon) simpleGeometry);
			} else if (simpleGeometry instanceof mil.nga.sf.GeometryCollection<?>) {
				@SuppressWarnings("unchecked")
				mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> simpleGeometryCollection = (mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry>) simpleGeometry;
				geometry = new GeometryCollection(simpleGeometryCollection);
			} else {
				throw new SFException("Unsupported Geometry type: "
						+ simpleGeometry.getClass().getSimpleName());
			}
		}
		return geometry;
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
		Geometry geometry;
		try {
			geometry = mapper.treeToValue(tree, Geometry.class);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to convert node tree to geometry: "
					+ tree, e);
		}
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
		Geometry geometry = GeometryConverter.toGeometry(simpleGeometry);
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
		Geometry geometry = GeometryConverter.toGeometry(simpleGeometry);
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
