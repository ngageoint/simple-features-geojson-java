package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;
import mil.nga.sf.Geometry;
import mil.nga.sf.GeometryCollection;
import mil.nga.sf.LineString;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.geojson.GeoJsonObject;

public class FeatureCollectionTest {

	private static String FEATURECOLLECTION = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"GeometryCollection\",\"geometries\":[{\"type\":\"Point\",\"coordinates\":[61.34765625,48.63290858589535]},{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}]}},{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[50.1,60.9]}}]}";

	@Test
	public void itShouldHaveProperties() throws Exception {
		assertNotNull(new FeatureCollection().getFeatures());
	}

	@Test
	public void itShouldDeserializeFeatureCollection() throws Exception {
		String stringValue = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}}]}";
		GeoJsonObject value = TestUtils.getMapper().readValue(stringValue,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof FeatureCollection);
		FeatureCollection fc = (FeatureCollection) value;
		assertNotNull(fc.getFeatures());
		Collection<Feature> features = fc.getFeatures();
		assertTrue(features.size() == 1);
		TestUtils.compareAsNodesGeoJsonObject(fc, stringValue);
	}

	@Test
	public void itShouldDeserializeFeatureCollection2() throws Exception {

		java.net.URL url = ClassLoader.getSystemResource("fc-points.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath),
				"UTF8");
		GeoJsonObject value = TestUtils.getMapper().readValue(json,
				GeoJsonObject.class);
		value.toString();
	}

	@Test
	public void itShouldDeserializeFeatureCollection3() throws Exception {

		java.net.URL url = ClassLoader
				.getSystemResource("fc-points-altitude.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath),
				"UTF8");
		GeoJsonObject value = TestUtils.getMapper().readValue(json,
				GeoJsonObject.class);
		value.toString();
	}

	@Test
	public void itShouldDeserializeFeatureCollection4() throws Exception {

		java.net.URL url = ClassLoader.getSystemResource("gc.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath),
				"UTF8");
		GeoJsonObject value = TestUtils.getMapper().readValue(json,
				GeoJsonObject.class);
		value.toString();
	}

	@Test
	public void itShouldDeserializeFeatureCollection5() throws Exception {

		java.net.URL url = ClassLoader.getSystemResource("gc-multiple.geojson");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		String json = new String(java.nio.file.Files.readAllBytes(resPath),
				"UTF8");
		GeoJsonObject value = TestUtils.getMapper().readValue(json,
				GeoJsonObject.class);
		value.toString();
	}

	@Test
	public void itShouldDeserializeFeatureCollection6() throws Exception {
		String stringValue = "{\"type\":\"FeatureCollection\",\"bbox\": [-10.0, -10.0, 10.0, 10.0],\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}}]}";
		GeoJsonObject value = TestUtils.getMapper().readValue(stringValue,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof FeatureCollection);
		FeatureCollection fc = (FeatureCollection) value;
		assertNotNull(fc.getFeatures());
		Collection<Feature> features = fc.getFeatures();
		assertTrue(features.size() == 1);
		TestUtils.compareAsNodesGeoJsonObject(fc, stringValue);
	}

	@Test
	public void itShouldSerializeFeatureCollection() throws Exception {
		FeatureCollection featureCollection = getTestFeatureCollection();
		TestUtils.compareAsNodesGeoJsonObject(featureCollection,
				FEATURECOLLECTION);
	}

	@Test
	public void toGeometry() {

		List<mil.nga.sf.Geometry> simpleGeometries = getTestSimpleGeometries();

		FeatureCollection featureCollection = FeatureConverter
				.toFeatureCollection(simpleGeometries);
		TestCase.assertNotNull(featureCollection);

		for (int i = 0; i < simpleGeometries.size(); i++) {
			TestCase.assertEquals(simpleGeometries.get(i),
					featureCollection.getFeature(i).getSimpleGeometry());
		}

	}

	@Test
	public void toMap() {

		FeatureCollection featureCollection = getTestFeatureCollection();

		Map<String, Object> map = FeatureConverter.toMap(featureCollection);
		TestCase.assertNotNull(map);
		TestCase.assertFalse(map.isEmpty());

		FeatureCollection featureCollectionFromMap = FeatureConverter
				.toFeatureCollection(map);
		TestCase.assertNotNull(featureCollectionFromMap);

		TestCase.assertEquals(featureCollection.numFeatures(),
				featureCollectionFromMap.numFeatures());
		TestCase.assertEquals(2, featureCollectionFromMap.numFeatures());
		for (int i = 0; i < featureCollection.numFeatures(); i++) {
			TestCase.assertEquals(
					featureCollection.getFeature(i).getSimpleGeometry(),
					featureCollectionFromMap.getFeature(i).getSimpleGeometry());
		}

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(map);
		TestCase.assertNotNull(geoJsonObjectFromString);
		TestCase.assertTrue(
				geoJsonObjectFromString instanceof FeatureCollection);

		FeatureCollection featureCollectionGeoJsonObject = (FeatureCollection) geoJsonObjectFromString;
		TestCase.assertEquals(featureCollection.numFeatures(),
				featureCollectionGeoJsonObject.numFeatures());
		TestCase.assertEquals(2, featureCollectionGeoJsonObject.numFeatures());
		for (int i = 0; i < featureCollection.numFeatures(); i++) {
			TestCase.assertEquals(
					featureCollection.getFeature(i).getSimpleGeometry(),
					featureCollectionGeoJsonObject.getFeature(i)
							.getSimpleGeometry());
		}
	}

	@Test
	public void toStringValue() {

		FeatureCollection featureCollection = getTestFeatureCollection();

		String stringValue = FeatureConverter.toStringValue(featureCollection);
		TestCase.assertNotNull(stringValue);
		TestCase.assertFalse(stringValue.isEmpty());

		FeatureCollection featureCollectionFromString = FeatureConverter
				.toFeatureCollection(stringValue);
		TestCase.assertNotNull(featureCollectionFromString);

		TestCase.assertEquals(featureCollection.numFeatures(),
				featureCollectionFromString.numFeatures());
		TestCase.assertEquals(2, featureCollectionFromString.numFeatures());
		for (int i = 0; i < featureCollection.numFeatures(); i++) {
			TestCase.assertEquals(
					featureCollection.getFeature(i).getSimpleGeometry(),
					featureCollectionFromString.getFeature(i)
							.getSimpleGeometry());
		}

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(stringValue);
		TestCase.assertNotNull(geoJsonObjectFromString);
		TestCase.assertTrue(
				geoJsonObjectFromString instanceof FeatureCollection);

		FeatureCollection featureCollectionGeoJsonObject = (FeatureCollection) geoJsonObjectFromString;
		TestCase.assertEquals(featureCollection.numFeatures(),
				featureCollectionGeoJsonObject.numFeatures());
		TestCase.assertEquals(2, featureCollectionGeoJsonObject.numFeatures());
		for (int i = 0; i < featureCollection.numFeatures(); i++) {
			TestCase.assertEquals(
					featureCollection.getFeature(i).getSimpleGeometry(),
					featureCollectionGeoJsonObject.getFeature(i)
							.getSimpleGeometry());
		}

	}

	private FeatureCollection getTestFeatureCollection() {

		Collection<mil.nga.sf.Geometry> simpleGeometries = getTestSimpleGeometries();

		FeatureCollection featureCollection = FeatureConverter
				.toFeatureCollection(simpleGeometries);

		return featureCollection;
	}

	private List<mil.nga.sf.Geometry> getTestSimpleGeometries() {

		List<mil.nga.sf.Geometry> simpleGeometries = new ArrayList<>();

		GeometryCollection<Geometry> geometryCollection = new GeometryCollection<>();
		geometryCollection.addGeometry(
				new mil.nga.sf.Point(61.34765625, 48.63290858589535));
		LineString lineString = new LineString();
		lineString.addPoint(new mil.nga.sf.Point(100.0, 10.0));
		lineString.addPoint(new mil.nga.sf.Point(101.0, 1.0));
		geometryCollection.addGeometry(lineString);

		simpleGeometries.add(geometryCollection);

		mil.nga.sf.Point point = new mil.nga.sf.Point(50.1, 60.9);
		simpleGeometries.add(point);

		return simpleGeometries;
	}

}