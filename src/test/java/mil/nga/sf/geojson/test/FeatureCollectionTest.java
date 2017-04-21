package mil.nga.sf.geojson.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.GeoJsonObject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

public class FeatureCollectionTest {

	private FeatureCollection testObject = new FeatureCollection();
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void itShouldHaveProperties() throws Exception {
		assertNotNull(testObject.getFeatures());
	}

	@Test
	public void itShouldDeserializeALargerFeatureCollection() throws Exception {
		
		java.net.URL url = ClassLoader.getSystemResource("fc-points.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
		GeoJsonObject value = mapper.readValue(json, GeoJsonObject.class);
		value.toString();
    }
	@Test
	public void itShouldSerializeFeatureCollection() throws Exception {
		// A feature collection object must have a member with the name "features".
		assertEquals("{\"type\":\"FeatureCollection\",\"features\":[]}",
				mapper.writeValueAsString(testObject));
	}
	
	@Test
	public void itShouldDeserializeFeatureCollection() throws Exception {
		GeoJsonObject value = mapper
				.readValue("{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}}]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof FeatureCollection);
		FeatureCollection fc = (FeatureCollection) value;
		assertNotNull(fc.getFeatures());
		Collection<Feature> features = fc.getFeatures();
		assertTrue(features.size() == 1);
	}
}