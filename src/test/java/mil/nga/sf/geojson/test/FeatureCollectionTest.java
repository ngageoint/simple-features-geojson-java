package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import mil.nga.sf.Geometry;
import mil.nga.sf.GeometryCollection;
import mil.nga.sf.LinearRing;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.GeometryFactory;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FeatureCollectionTest {

	private static String GEOMETRYCOLLECTION = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"GeometryCollection\",\"geometries\":[{\"type\":\"Point\",\"coordinates\":[61.34765625,48.63290858589535]},{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}]}}]}";
	
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
	public void itShouldDeserializeALargerFeatureCollectionWithAltitude() throws Exception {
		
		java.net.URL url = ClassLoader.getSystemResource("fc-points-altitude.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
		GeoJsonObject value = mapper.readValue(json, GeoJsonObject.class);
		value.toString();
    }
	
	@Test
	public void itShouldDeserializeALargerFeatureCollectionGeometryCollection() throws Exception {
		
		java.net.URL url = ClassLoader.getSystemResource("gc.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
		GeoJsonObject value = mapper.readValue(json, GeoJsonObject.class);
		value.toString();
    }
	
	@Test
	public void itShouldSerializeALargerFeatureCollectionGeometryCollection() throws Exception {
		
		java.net.URL url = ClassLoader.getSystemResource("gc.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
		FeatureCollection value = mapper.readValue(json, FeatureCollection.class);
		value.toString();
    }
	
	@Test
	public void itShouldSerializeASFGeometryCollection() throws Exception {
		GeometryCollection<Geometry> geometryCollection = new GeometryCollection<>();
		geometryCollection.addGeometry(new mil.nga.sf.Point(61.34765625,48.63290858589535));
		LinearRing lineString = new LinearRing();
		lineString.addPoint(new mil.nga.sf.Point(100.0,10.0));
		lineString.addPoint(new mil.nga.sf.Point(101.0,1.0));
		geometryCollection.addGeometry(lineString);
		Feature feature = new Feature(GeometryFactory.toGeometry(geometryCollection));
		FeatureCollection featureCollection = new FeatureCollection(feature);
		TestUtils.compareAsNodesGeoJsonObject(featureCollection, GEOMETRYCOLLECTION);
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