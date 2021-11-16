package mil.nga.sf.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import junit.framework.TestCase;

public class FeatureTest {

	@Test
	public void itShouldSerializeFeature() throws Exception {
		Feature testObject = new Feature();

		// http://geojson.org/geojson-spec.html#feature-objects
		// A feature object must have a member with the name "properties".
		// The value of the properties member is an object (any JSON object or a
		// JSON null value).
		assertNotNull(testObject.getProperties());

		JsonNode nodeFromPojo = TestUtils.getMapper().valueToTree(testObject);
		JsonNode nodeFromString = TestUtils.getMapper()
				.readTree("{\"type\":\"Feature\",\"geometry\":null}");
		((ObjectNode) nodeFromString).set("properties",
				TestUtils.getMapper().createObjectNode());
		TestCase.assertEquals(nodeFromPojo, nodeFromString);
	}

	@Test
	public void itShouldSerializeFeature2() throws Exception {

		Feature feature = getTestFeature();

		feature.getProperties().put("foo", "bar");

		JsonNode nodeFromPojo = TestUtils.getMapper().valueToTree(feature);
		JsonNode nodeFromString = TestUtils.getMapper().readTree(
				"{\"type\":\"Feature\",\"properties\":{\"foo\":\"bar\"},\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}}");
		TestCase.assertEquals(nodeFromPojo, nodeFromString);
	}

	@Test
	public void itShouldDeserializeFeature() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]},\"properties\":{}}",
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertNotNull(feature.getSimpleGeometry());
		Object geometryO = feature.getSimpleGeometry();
		assertTrue(geometryO instanceof mil.nga.sf.Point);
		mil.nga.sf.Point simplePoint = (mil.nga.sf.Point) geometryO;
		TestUtils.assertPoint(100d, 5d, null,
				(Point) FeatureConverter.toGeometry(simplePoint));
	}

	@Test
	public void itShouldDeserializeFeatureWithProperty() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"Feature\",\"geometry\":null,\"properties\":{\"foo\":\"bar\"}}",
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertTrue(null == feature.getSimpleGeometry());
		Map<String, Object> map = feature.getProperties();
		assertTrue(map.containsKey("foo"));
		assertEquals("bar", map.get("foo"));
	}

	@Test
	public void toMap() {

		Feature feature = getTestFeature();

		Map<String, Object> map = FeatureConverter.toMap(feature);
		TestCase.assertNotNull(map);
		TestCase.assertFalse(map.isEmpty());

		Feature featureFromMap = FeatureConverter.toFeature(map);
		TestCase.assertNotNull(featureFromMap);

		TestCase.assertEquals(feature.getSimpleGeometry(),
				featureFromMap.getSimpleGeometry());

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(map);
		TestCase.assertNotNull(geoJsonObjectFromString);
		TestCase.assertTrue(geoJsonObjectFromString instanceof Feature);

		Feature featureGeoJsonObject = (Feature) geoJsonObjectFromString;
		TestCase.assertEquals(feature.getSimpleGeometry(),
				featureGeoJsonObject.getSimpleGeometry());
	}

	@Test
	public void toGeometry() {

		mil.nga.sf.Geometry simpleGeometry = TestUtils
				.getMultiPolygonWithRings();

		Feature feature = FeatureConverter.toFeature(simpleGeometry);
		TestCase.assertNotNull(feature);

		TestCase.assertEquals(simpleGeometry, feature.getSimpleGeometry());
	}

	@Test
	public void toStringValue() {

		Feature feature = getTestFeature();

		String stringValue = FeatureConverter.toStringValue(feature);
		TestCase.assertNotNull(stringValue);
		TestCase.assertFalse(stringValue.isEmpty());

		Feature featureFromString = FeatureConverter.toFeature(stringValue);
		TestCase.assertNotNull(featureFromString);

		TestCase.assertEquals(feature.getSimpleGeometry(),
				featureFromString.getSimpleGeometry());

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(stringValue);
		TestCase.assertNotNull(geoJsonObjectFromString);
		TestCase.assertTrue(geoJsonObjectFromString instanceof Feature);

		Feature featureGeoJsonObject = (Feature) geoJsonObjectFromString;
		TestCase.assertEquals(feature.getSimpleGeometry(),
				featureGeoJsonObject.getSimpleGeometry());
	}

	private Feature getTestFeature() {

		mil.nga.sf.Geometry geometry = TestUtils.getMultiPolygonWithRings();
		Feature feature = FeatureConverter.toFeature(geometry);

		return feature;
	}

}