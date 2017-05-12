package mil.nga.sf.geojson.test;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.MultiPolygon;
import mil.nga.sf.geojson.Point;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

public class FeatureTest {

	@Test
	public void itShouldSerializeFeature() throws Exception {
		Feature testObject = new Feature();

		// http://geojson.org/geojson-spec.html#feature-objects
		// A feature object must have a member with the name "properties".
		// The value of the properties member is an object (any JSON object or a JSON null value).
		assertNotNull(testObject.getProperties());

		JsonNode nodeFromPojo = TestUtils.getMapper().valueToTree(testObject);
		JsonNode nodeFromString = TestUtils.getMapper().readTree("{\"type\":\"Feature\",\"properties\":{},\"geometry\":null}");
		TestCase.assertEquals(nodeFromPojo, nodeFromString);
	}

	@Test
	public void itShouldSerializeAMoreInterestingFeature() throws Exception {
		Feature testObject = new Feature();
		
		testObject.getProperties().put("foo", "bar");
		testObject.setGeometry((MultiPolygon)GeoJsonObjectFactory.createObject(TestUtils.getMultiPolygonWithRings()));
		
		JsonNode nodeFromPojo = TestUtils.getMapper().valueToTree(testObject);
		JsonNode nodeFromString = TestUtils.getMapper().readTree("{\"type\":\"Feature\",\"properties\":{\"foo\":\"bar\"},\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]]}}");
		TestCase.assertEquals(nodeFromPojo, nodeFromString);
	}
	
	@Test
	public void itShouldDeserializeFeature() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]},\"properties\":{}}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertNotNull(feature.getFeature().getGeometry());
		Object geometryO = feature.getFeature().getGeometry();
		assertTrue(geometryO instanceof mil.nga.sf.Point);
		mil.nga.sf.Point point = (mil.nga.sf.Point)geometryO;
		TestUtils.assertPoint(100d, 5d, null, (Point)GeoJsonObjectFactory.createObject(point));
	}
	
	@Test
	public void itShouldDeserializeFeatureWithProperty() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue("{\"type\":\"Feature\",\"geometry\":null,\"properties\":{\"foo\":\"bar\"}}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertTrue(null == feature.getFeature().getGeometry());
		Map<String, Object> map = feature.getProperties();
		assertTrue(map.containsKey("foo"));
		assertEquals("bar", map.get("foo"));
	}
}