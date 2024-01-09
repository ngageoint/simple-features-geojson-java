package mil.nga.sf.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mil.nga.sf.LineString;
import mil.nga.sf.LinearRing;

public class TestUtils {

	private static double EPSILON = 0.00001d;

	private static ObjectMapper mapper = FeatureConverter.mapper;

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static void assertPoint(Double expectedLongitude,
			Double expectedLatitude, Double expectedAltitude, Point point) {
		assertPoint(expectedLongitude, expectedLatitude, expectedAltitude,
				new ArrayList<Double>(), point);
	}

	public static void assertPosition(Double expectedLongitude,
			Double expectedLatitude, Double expectedAltitude,
			List<Double> expectedAdditionalElements, Position position) {
		assertEquals(expectedLongitude, position.getX(), EPSILON);
		assertEquals(expectedLatitude, position.getY(), EPSILON);
		if (expectedAltitude == null) {
			assertNull(position.getZ());
		} else {
			assertEquals(expectedAltitude, position.getZ(), EPSILON);
			if (expectedAdditionalElements == null) {
				final List<Double> ae = ((Position) position)
						.getAdditionalElements();
				assertTrue((ae == null) || ae.isEmpty());
			} else {
				assertTrue(expectedAdditionalElements
						.equals(((Position) position).getAdditionalElements()));
			}
		}
	}

	public static void assertPosition(Double expectedLongitude,
			Double expectedLatitude, Double expectedAltitude,
			List<Double> expectedAdditionalElements,
			mil.nga.sf.Point position) {
		assertEquals(expectedLongitude, position.getX(), EPSILON);
		assertEquals(expectedLatitude, position.getY(), EPSILON);
		if (expectedAltitude == null) {
			assertNull(position.getZ());
		} else {
			assertEquals(expectedAltitude, position.getZ(), EPSILON);
			if (expectedAdditionalElements == null) {
				assertNull(position.getM());
			} else {
				assertEquals(expectedAdditionalElements.size(), 1);
				assertEquals(expectedAdditionalElements.get(0), position.getM(),
						EPSILON);
			}
		}
	}

	public static mil.nga.sf.MultiPolygon getMultiPolygonWithRings() {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<>();
		List<LineString> rings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(-100d, -50d));
		points.add(new mil.nga.sf.Point(100d, -50d));
		points.add(new mil.nga.sf.Point(1d, 50d));
		LinearRing ring = new LinearRing(points);
		rings.add(ring);
		points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(-50d, -25d));
		points.add(new mil.nga.sf.Point(50d, -25d));
		points.add(new mil.nga.sf.Point(-1d, 25d));
		ring = new LinearRing(points);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		polygons.add(polygon);
		mil.nga.sf.MultiPolygon multiPolygon = new mil.nga.sf.MultiPolygon(
				polygons);
		return multiPolygon;
	}

	public static void assertPoint(Double expectedLongitude,
			Double expectedLatitude, Double expectedAltitude,
			List<Double> expectedAdditionalElements, Point point) {
		assertPosition(expectedLongitude, expectedLatitude, expectedAltitude,
				expectedAdditionalElements, point.getCoordinates());
	}

	public static void compareAsNodes(mil.nga.sf.Geometry simpleGeometry,
			String input) throws JsonProcessingException, IOException {
		Geometry geometry = FeatureConverter.toGeometry(simpleGeometry);
		compareAsNodes(geometry, input);
	}

	public static void compareAsNodes(Geometry geometry, String input)
			throws JsonProcessingException, IOException {
		JsonNode nodeFromPojo = mapper.valueToTree(geometry);
		JsonNode nodeFromString = mapper.readTree(input);
		assertEquals(nodeFromPojo, nodeFromString);
	}

	public static void compareAsNodesGeoJsonObject(GeoJsonObject object,
			String input) throws JsonProcessingException, IOException {
		JsonNode nodeFromPojo = mapper.valueToTree(object);
		JsonNode nodeFromString = mapper.readTree(input);
		assertEquals(nodeFromPojo, nodeFromString);
	}

	public static void toGeometry(mil.nga.sf.Geometry simpleGeometry) {

		Geometry geometry = FeatureConverter.toGeometry(simpleGeometry);
		assertNotNull(geometry);

		assertEquals(simpleGeometry, geometry.getGeometry());

	}

	public static void toMap(mil.nga.sf.Geometry simpleGeometry) {

		Geometry geometry = FeatureConverter.toGeometry(simpleGeometry);
		assertNotNull(geometry);

		Map<String, Object> map = FeatureConverter.toMap(simpleGeometry);
		assertNotNull(map);
		assertFalse(map.isEmpty());

		Geometry geometryFromMap = FeatureConverter.toGeometry(map);
		assertNotNull(geometryFromMap);

		assertEquals(geometry, geometryFromMap);
		assertEquals(geometry.getGeometry(), geometryFromMap.getGeometry());

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(map);
		assertNotNull(geoJsonObjectFromString);
		assertTrue(geoJsonObjectFromString instanceof Geometry);

		Geometry geometryGeoJsonObject = (Geometry) geoJsonObjectFromString;
		assertEquals(geometry, geometryGeoJsonObject);
		assertEquals(geometry.getGeometry(),
				geometryGeoJsonObject.getGeometry());
	}

	public static void toStringValue(mil.nga.sf.Geometry simpleGeometry) {

		Geometry geometry = FeatureConverter.toGeometry(simpleGeometry);
		assertNotNull(geometry);

		String stringValue = FeatureConverter.toStringValue(simpleGeometry);
		assertNotNull(stringValue);
		assertFalse(stringValue.isEmpty());
		String type = "\"type\":\"" + ((GeoJsonObject) geometry).getType()
				+ "\"";
		int index = stringValue.indexOf(type);
		assertTrue(index >= 0);
		String restOfString = stringValue.substring(index + type.length());
		int secondIndex = restOfString.indexOf(type);
		assertEquals(-1, secondIndex);

		Geometry geomteryFromString = FeatureConverter.toGeometry(stringValue);
		assertNotNull(geomteryFromString);

		assertEquals(geometry, geomteryFromString);
		assertEquals(geometry.getGeometry(), geomteryFromString.getGeometry());

		GeoJsonObject geoJsonObjectFromString = FeatureConverter
				.toGeoJsonObject(stringValue);
		assertNotNull(geoJsonObjectFromString);
		assertTrue(geoJsonObjectFromString instanceof Geometry);

		Geometry geometryGeoJsonObject = (Geometry) geoJsonObjectFromString;
		assertEquals(geometry, geometryGeoJsonObject);
		assertEquals(geometry.getGeometry(),
				geometryGeoJsonObject.getGeometry());

		assertEquals(simpleGeometry,
				geoJsonObjectFromString.getSimpleGeometry());
		assertEquals(simpleGeometry, geomteryFromString.getSimpleGeometry());
		assertEquals(simpleGeometry,
				FeatureConverter.toSimpleGeometry(stringValue));

	}

}
