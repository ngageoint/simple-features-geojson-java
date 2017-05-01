package mil.nga.sf.geojson.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

public class TestUtils {
	
	private static double EPSILON = 0.00001d;

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

	public static void assertPoint(Double expectedLongitude, Double expectedLatitude, Double expectedAltitude,
									   List<Double> expectedAdditionalElements, Point point) {
		assertPosition(expectedLongitude, expectedLatitude, expectedAltitude, expectedAdditionalElements, point.getCoordinates());
	}
}
