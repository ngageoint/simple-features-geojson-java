package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mil.nga.sf.util.SFException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Feature Converter
 * 
 * @author osbornb
 */
public class FeatureConverter {

	/**
	 * Object mapper
	 */
	public final static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Convert a simple geometry to a feature
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return feature
	 */
	public static Feature toFeature(mil.nga.sf.Geometry simpleGeometry) {
		Geometry geometry = GeometryConverter.toGeometry(simpleGeometry);
		Feature feature = new Feature(geometry);
		return feature;
	}

	/**
	 * Convert a simple geometry to a feature collection
	 * 
	 * @param simpleGeometry
	 *            simple geometry
	 * @return feature collection
	 */
	public static FeatureCollection toFeatureCollection(
			mil.nga.sf.Geometry simpleGeometry) {
		Feature feature = toFeature(simpleGeometry);
		FeatureCollection featureCollection = new FeatureCollection(feature);
		return featureCollection;
	}

	/**
	 * Convert simple geometries to a feature collection
	 * 
	 * @param simpleGeometries
	 *            simple geometries
	 * @return feature collection
	 */
	public static FeatureCollection toFeatureCollection(
			Collection<mil.nga.sf.Geometry> simpleGeometries) {
		List<Feature> features = new ArrayList<>();
		for (mil.nga.sf.Geometry simpleGeometry : simpleGeometries) {
			Feature feature = toFeature(simpleGeometry);
			features.add(feature);
		}
		FeatureCollection featureCollection = new FeatureCollection(features);
		return featureCollection;
	}

	/**
	 * Convert the string content to a feature
	 * 
	 * @param content
	 *            string content
	 * @return feature
	 */
	public static Feature toFeature(String content) {
		JsonNode tree;
		try {
			tree = mapper.readTree(content);
		} catch (Exception e) {
			throw new SFException("Failed to convert content to a node tree: "
					+ content, e);
		}
		Feature feature = toFeature(tree);
		return feature;
	}

	/**
	 * Convert the object value to a feature
	 * 
	 * @param value
	 *            object value
	 * @return feature
	 */
	public static Feature toFeature(Object value) {
		JsonNode tree = mapper.valueToTree(value);
		Feature feature = toFeature(tree);
		return feature;
	}

	/**
	 * Convert the JSON tree to a feature
	 * 
	 * @param tree
	 *            tree node
	 * @return feature
	 */
	public static Feature toFeature(JsonNode tree) {
		Feature feature;
		try {
			feature = mapper.treeToValue(tree, Feature.class);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to convert node tree to feature: "
					+ tree, e);
		}
		return feature;
	}

	/**
	 * Convert the string content to a feature collection
	 * 
	 * @param content
	 *            string content
	 * @return feature collection
	 */
	public static FeatureCollection toFeatureCollection(String content) {
		JsonNode tree;
		try {
			tree = mapper.readTree(content);
		} catch (Exception e) {
			throw new SFException("Failed to convert content to a node tree: "
					+ content, e);
		}
		FeatureCollection featureCollection = toFeatureCollection(tree);
		return featureCollection;
	}

	/**
	 * Convert the object value to a feature collection
	 * 
	 * @param value
	 *            object value
	 * @return feature collection
	 */
	public static FeatureCollection toFeatureCollection(Object value) {
		JsonNode tree = mapper.valueToTree(value);
		FeatureCollection featureCollection = toFeatureCollection(tree);
		return featureCollection;
	}

	/**
	 * Convert the JSON tree to a feature collection
	 * 
	 * @param tree
	 *            tree node
	 * @return feature collection
	 */
	public static FeatureCollection toFeatureCollection(JsonNode tree) {
		FeatureCollection featureCollection;
		try {
			featureCollection = mapper.treeToValue(tree,
					FeatureCollection.class);
		} catch (JsonProcessingException e) {
			throw new SFException(
					"Failed to convert node tree to feature collection: "
							+ tree, e);
		}
		return featureCollection;
	}

	/**
	 * Convert the feature to an object map
	 * 
	 * @param feature
	 *            feature
	 * @return object map
	 */
	public static Map<String, Object> toMap(Feature feature) {
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = mapper.convertValue(feature, typeRef);
		return map;
	}

	/**
	 * Convert the feature collection to an object map
	 * 
	 * @param featureCollection
	 *            feature collection
	 * @return object map
	 */
	public static Map<String, Object> toMap(FeatureCollection featureCollection) {
		TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = mapper.convertValue(featureCollection,
				typeRef);
		return map;
	}

	/**
	 * Convert the feature to a string value
	 * 
	 * @param feature
	 *            feature
	 * @return string value
	 */
	public static String toStringValue(Feature feature) {
		String stringValue = null;
		try {
			stringValue = mapper.writeValueAsString(feature);
		} catch (JsonProcessingException e) {
			throw new SFException("Failed to write feature as a String: "
					+ feature.getGeometryType(), e);
		}
		return stringValue;
	}

	/**
	 * Convert the feature collection to a string value
	 * 
	 * @param featureCollection
	 *            feature collection
	 * @return string value
	 */
	public static String toStringValue(FeatureCollection featureCollection) {
		String stringValue = null;
		try {
			stringValue = mapper.writeValueAsString(featureCollection);
		} catch (JsonProcessingException e) {
			throw new SFException(
					"Failed to write feature collection as a String: "
							+ featureCollection.getGeometryType(), e);
		}
		return stringValue;
	}

}
