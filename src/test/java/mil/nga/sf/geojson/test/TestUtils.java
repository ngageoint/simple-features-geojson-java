package mil.nga.sf.geojson.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import mil.nga.sf.SimpleLinearRing;
import mil.nga.sf.geojson.GeoJsonObjectFactory;
import mil.nga.sf.geojson.Geometry;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

public class TestUtils {
	
	private static double EPSILON = 0.00001d;
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   Point point) {
		assertPoint(expectedLongitude, expectedLatitude, expectedAltitude, new ArrayList<Double>(), point);
	}

	public static void assertPosition(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   List<Double> expectedAdditionalElements, mil.nga.sf.Position position) {
		TestCase.assertEquals(expectedLongitude, position.getX(), EPSILON);
		TestCase.assertEquals(expectedLatitude, position.getY(), EPSILON);
		if(expectedAltitude == null) {
			TestCase.assertNull(position.getZ());
		} else {
			TestCase.assertEquals(expectedAltitude, position.getZ(), EPSILON);
			if(expectedAdditionalElements == null){
				if (position instanceof Position){
					final List<Double> ae = ((Position)position).getAdditionalElements();
					TestCase.assertTrue((ae == null) || ae.isEmpty());
				} else {
					TestCase.assertNull(position.getM());
				}
			} else {
				if (position instanceof Position){
					TestCase.assertTrue(expectedAdditionalElements.equals(((Position)position).getAdditionalElements()));
				} else {
					TestCase.assertEquals(expectedAdditionalElements.size(), 1);
					TestCase.assertEquals(expectedAdditionalElements.get(0), position.getM(), EPSILON);
				}
			}
		}
	}

	public static mil.nga.sf.SimpleMultiPolygon getMultiPolygonWithRings() {
		List<mil.nga.sf.SimplePolygon> polygons = new ArrayList<mil.nga.sf.SimplePolygon>();
		List<SimpleLinearRing> rings = new ArrayList<SimpleLinearRing>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		SimpleLinearRing ring = new SimpleLinearRing(positions);
		rings.add(ring);
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		ring = new SimpleLinearRing(positions);
		rings.add(ring);
		mil.nga.sf.SimplePolygon polygon = new mil.nga.sf.SimplePolygon(rings);
		polygons.add(polygon);
		mil.nga.sf.SimpleMultiPolygon multiPolygon = new mil.nga.sf.SimpleMultiPolygon(polygons);
		return multiPolygon;
	}
	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   List<Double> expectedAdditionalElements, Point point) {
		assertPosition(expectedLongitude, expectedLatitude, expectedAltitude, expectedAdditionalElements, point.getCoordinates());
	}
	
	public static void compareAsNodes(Object obj, String input) throws JsonProcessingException, IOException{
		Geometry geometry = GeoJsonObjectFactory.createObject(obj);
		JsonNode nodeFromPojo = mapper.valueToTree(geometry);
		JsonNode nodeFromString = mapper.readTree(input);
		TestCase.assertEquals(nodeFromPojo, nodeFromString);
	}
}
