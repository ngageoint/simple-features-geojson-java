package mil.nga.sf.geojson.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

public class TestUtils {

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   Point point) {
		assertPoint(expectedLongitude, expectedLatitude, expectedAltitude, new ArrayList<Double>(), point);
	}

	public static void assertPosition(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   List<Double> expectedAdditionalElements, Position position) {
		TestCase.assertEquals(expectedLongitude, position.getX(), 0.00001);
		TestCase.assertEquals(expectedLatitude, position.getY(), 0.00001);
		if(expectedAltitude == null) {
			TestCase.assertNull(position.getZ());
		} else {
			TestCase.assertEquals(expectedAltitude, position.getZ(), 0.00001);
			TestCase.assertTrue(expectedAdditionalElements.equals(position.getAdditionalElements()));
		}
	}

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   List<Double> expectedAdditionalElements, Point point) {
		assertPosition(expectedLongitude, expectedLatitude, expectedAltitude, expectedAdditionalElements, point.getCoordinates());
	}

}
