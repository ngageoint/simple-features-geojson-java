package mil.nga.sf.geojson.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.Point;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

public class FeatureTest {

	private Feature testObject = new Feature();
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldHaveProperties() throws Exception {
		assertNotNull(testObject.getProperties());
	}

	@Test
	public void itShouldSerializeFeature() throws Exception {
		// http://geojson.org/geojson-spec.html#feature-objects
		// A feature object must have a member with the name "properties".
		// The value of the properties member is an object (any JSON object or a JSON null value).
		assertEquals("{\"type\":\"Feature\",\"geometry\":null,\"properties\":{}}",
				mapper.writeValueAsString(testObject));
	}
	
	@Test
	public void itShouldDeserializeFeature() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]},\"properties\":{}}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertNotNull(feature.getGeometry());
		Object geometryO = feature.getGeometry();
		assertTrue(geometryO instanceof Point);
		Point point = (Point)geometryO;
		PointTest.assertPoint(100d, 5d, null, point);
	}
	
	@Test
	public void itShouldDeserializeFeatureWithProperty() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"Feature\",\"geometry\":null,\"properties\":{\"foo\":\"bar\"}}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Feature);
		Feature feature = (Feature) value;
		assertEquals(null, feature.getGeometry());
		Map<String, Object> map = feature.getProperties();
		assertTrue(map.containsKey("foo"));
		assertEquals("bar", map.get("foo"));
	}
}