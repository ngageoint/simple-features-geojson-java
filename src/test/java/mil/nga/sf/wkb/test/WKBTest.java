package mil.nga.sf.wkb.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteOrder;

import junit.framework.TestCase;
import mil.nga.sf.Polygon;
import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.LineString;
import mil.nga.sf.MultiLineString;
import mil.nga.sf.MultiPoint;
import mil.nga.sf.MultiPolygon;
import mil.nga.sf.Point;
import mil.nga.sf.util.ByteReader;
import mil.nga.sf.wkb.GeometryReader;

import org.junit.Test;

/**
 * Test Well Known Binary Geometries
 * 
 * @author osbornb
 */
public class WKBTest {

	/**
	 * Number of random geometries to create for each test
	 */
	private static final int GEOMETRIES_PER_TEST = 10;

	/**
	 * Constructor
	 */
	public WKBTest() {

	}

	@Test
	public void testPoint() throws IOException, URISyntaxException {

		java.net.URL url = ClassLoader.getSystemResource("point.wkb");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		byte[] bytes = java.nio.file.Files.readAllBytes(resPath);
		Geometry simpleGeometry = GeometryReader.readGeometry(new ByteReader(bytes));
		TestCase.assertNotNull(simpleGeometry);
		TestCase.assertTrue(simpleGeometry instanceof Point);
		Point simplePoint = (Point)simpleGeometry;
		TestCase.assertEquals(simplePoint.getX(), 1.0d);
		TestCase.assertEquals(simplePoint.getY(), 2.0d);
	}

	@Test
	public void testRTPoint() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a point
			Point simplePoint = WKBTestUtils.createPoint(WKBTestUtils.coinFlip(),
					WKBTestUtils.coinFlip());
			geometryTester(simplePoint);
		}

	}

	@Test
	public void testLineString() throws IOException, URISyntaxException {

		java.net.URL url = ClassLoader.getSystemResource("linestring.wkb");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		byte[] bytes = java.nio.file.Files.readAllBytes(resPath);
		Geometry geometry = GeometryReader.readGeometry(new ByteReader(bytes));
		TestCase.assertNotNull(geometry);
		TestCase.assertTrue(geometry instanceof LineString);
		LineString ls = (LineString)geometry;
		TestCase.assertEquals(ls.getPoints().size(), 2);
		Point point = ls.getPoints().get(0);
		TestCase.assertEquals(point.getX(), 1.0d);
		TestCase.assertEquals(point.getY(), 2.0d);
		Point point2 = ls.getPoints().get(1);
		TestCase.assertEquals(point2.getX(), 3.0d);
		TestCase.assertEquals(point2.getY(), 4.0d);
	}

	@Test
	public void testRTLineString() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a line string
			LineString lineString = WKBTestUtils.createLineString(
					WKBTestUtils.coinFlip(), WKBTestUtils.coinFlip());
			geometryTester(lineString);
		}

	}

	@Test
	public void testRTPolygon() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a polygon
			Polygon polygon = WKBTestUtils.createPolygon(
					WKBTestUtils.coinFlip(), WKBTestUtils.coinFlip());
			geometryTester(polygon);
		}

	}

	@Test
	public void testRTMultiPoint() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a multi point
			MultiPoint multiPoint = WKBTestUtils.createMultiPoint(
					WKBTestUtils.coinFlip(), WKBTestUtils.coinFlip());
			geometryTester(multiPoint);
		}

	}

	@Test
	public void testMultiPoint() throws IOException, URISyntaxException {

		java.net.URL url = ClassLoader.getSystemResource("multipoint.wkb");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		byte[] bytes = java.nio.file.Files.readAllBytes(resPath);
		Geometry simpleGeometry = GeometryReader.readGeometry(new ByteReader(bytes));
		TestCase.assertNotNull(simpleGeometry);
		TestCase.assertTrue(simpleGeometry instanceof MultiPoint);
		MultiPoint mp = (MultiPoint)simpleGeometry;
		TestCase.assertEquals(mp.getGeometries().size(), 2);
		Point point = mp.getGeometries().get(0);
		TestCase.assertEquals(point.getX(), 0.0d);
		TestCase.assertEquals(point.getY(), 1.0d);
		Point point2 = mp.getGeometries().get(1);
		TestCase.assertEquals(point2.getX(), 2.0d);
		TestCase.assertEquals(point2.getY(), 3.0d);
	}

	@Test
	public void testRTMultiLineString() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a multi line string
			MultiLineString multiLineString = WKBTestUtils
					.createMultiLineString(WKBTestUtils.coinFlip(),
							WKBTestUtils.coinFlip());
			geometryTester(multiLineString);
		}

	}

	@Test
	public void testRTMultiPolygon() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a multi polygon
			MultiPolygon multiPolygon = WKBTestUtils.createMultiPolygon(
					WKBTestUtils.coinFlip(), WKBTestUtils.coinFlip());
			geometryTester(multiPolygon);
		}

	}

	@Test
	public void testGeometryCollection1() throws IOException, URISyntaxException {

		java.net.URL url = ClassLoader.getSystemResource("gc1.wkb");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		byte[] bytes = java.nio.file.Files.readAllBytes(resPath);
		Geometry simpleGeometry = GeometryReader.readGeometry(new ByteReader(bytes));
		TestCase.assertNotNull(simpleGeometry);
		TestCase.assertTrue(simpleGeometry instanceof SimpleGeometryCollection);
		SimpleGeometryCollection gc = (SimpleGeometryCollection)simpleGeometry;
		TestCase.assertEquals(gc.numGeometries(), 6);
		Geometry g0 = gc.getGeometries().get(0);
		TestCase.assertTrue(g0 instanceof Point);
		Geometry g1 = gc.getGeometries().get(1);
		TestCase.assertTrue(g1 instanceof LineString);
		Geometry g2 = gc.getGeometries().get(2);
		TestCase.assertTrue(g2 instanceof Polygon);
		Geometry g3 = gc.getGeometries().get(3);
		TestCase.assertTrue(g3 instanceof MultiPoint);
		Geometry g4 = gc.getGeometries().get(4);
		TestCase.assertTrue(g4 instanceof MultiLineString);
		Geometry g5 = gc.getGeometries().get(5);
		TestCase.assertTrue(g5 instanceof MultiPolygon);
	}

	@Test
	public void testGeometryCollection2() throws IOException, URISyntaxException {

		java.net.URL url = ClassLoader.getSystemResource("gc2.wkb");
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		byte[] bytes = java.nio.file.Files.readAllBytes(resPath);
		Geometry simpleGeometry = GeometryReader.readGeometry(new ByteReader(bytes));
		TestCase.assertNotNull(simpleGeometry);
		TestCase.assertTrue(simpleGeometry instanceof MultiPoint);
		MultiPoint mp = (MultiPoint)simpleGeometry;
		TestCase.assertEquals(mp.getGeometries().size(), 2);
		Point point = mp.getGeometries().get(0);
		TestCase.assertEquals(point.getX(), 0.0d);
		TestCase.assertEquals(point.getY(), 1.0d);
		Point point2 = mp.getGeometries().get(1);
		TestCase.assertEquals(point2.getX(), 2.0d);
		TestCase.assertEquals(point2.getY(), 3.0d);
	}

	@Test
	public void testRTGeometryCollection() throws IOException {

		for (int i = 0; i < GEOMETRIES_PER_TEST; i++) {
			// Create and test a geometry collection
			SimpleGeometryCollection geometryCollection = WKBTestUtils
					.createGeometryCollection(WKBTestUtils.coinFlip(),
							WKBTestUtils.coinFlip());
			geometryTester(geometryCollection);
		}

	}

	/**
	 * Test the geometry writing to and reading from bytes
	 * 
	 * @param geometry
	 * @throws IOException
	 */
	private void geometryTester(Geometry geometry) throws IOException {

		// Write the geometries to bytes
		byte[] bytes1 = WKBTestUtils.writeBytes(geometry, ByteOrder.BIG_ENDIAN);
		byte[] bytes2 = WKBTestUtils.writeBytes(geometry,
				ByteOrder.LITTLE_ENDIAN);

		TestCase.assertFalse(WKBTestUtils.equalByteArrays(bytes1, bytes2));

		// Test that the bytes are read using their written byte order, not
		// the specified
		Geometry geometry1opposite = WKBTestUtils.readGeometry(bytes1,
				ByteOrder.LITTLE_ENDIAN);
		Geometry geometry2opposite = WKBTestUtils.readGeometry(bytes2,
				ByteOrder.BIG_ENDIAN);
		WKBTestUtils.compareByteArrays(WKBTestUtils.writeBytes(geometry),
				WKBTestUtils.writeBytes(geometry1opposite));
		WKBTestUtils.compareByteArrays(WKBTestUtils.writeBytes(geometry),
				WKBTestUtils.writeBytes(geometry2opposite));

		Geometry geometry1 = WKBTestUtils.readGeometry(bytes1,
				ByteOrder.BIG_ENDIAN);
		Geometry geometry2 = WKBTestUtils.readGeometry(bytes2,
				ByteOrder.LITTLE_ENDIAN);

		WKBTestUtils.compareGeometries(geometry, geometry1);
		WKBTestUtils.compareGeometries(geometry, geometry2);
		WKBTestUtils.compareGeometries(geometry1, geometry2);
	}

}
