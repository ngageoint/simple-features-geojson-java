package mil.nga.wkb.test;

import java.io.IOException;
import java.nio.ByteOrder;

import junit.framework.TestCase;
import mil.nga.ogcsf.CircularString;
import mil.nga.ogcsf.CompoundCurve;
import mil.nga.ogcsf.CurvePolygon;
import mil.nga.ogcsf.Geometry;
import mil.nga.ogcsf.GeometryCollection;
import mil.nga.ogcsf.GeometryEnvelope;
import mil.nga.ogcsf.GeometryType;
import mil.nga.ogcsf.LineString;
import mil.nga.ogcsf.MultiLineString;
import mil.nga.ogcsf.MultiPoint;
import mil.nga.ogcsf.MultiPolygon;
import mil.nga.ogcsf.Point;
import mil.nga.ogcsf.Polygon;
import mil.nga.ogcsf.PolyhedralSurface;
import mil.nga.ogcsf.TIN;
import mil.nga.ogcsf.Triangle;
import mil.nga.wkb.io.ByteReader;
import mil.nga.wkb.io.ByteWriter;
import mil.nga.wkb.io.WkbGeometryReader;
import mil.nga.wkb.io.WkbGeometryWriter;
import mil.nga.wkb.util.WkbUtils;

/**
 * WKB test utils
 * 
 * @author osbornb
 */
public class WKBTestUtils {

	/**
	 * Compare two geometry envelopes and verify they are equal
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareEnvelopes(GeometryEnvelope expected,
			GeometryEnvelope actual) {

		if (expected == null) {
			TestCase.assertNull(actual);
		} else {
			TestCase.assertNotNull(actual);

			TestCase.assertEquals(expected.getMinX(), actual.getMinX());
			TestCase.assertEquals(expected.getMaxX(), actual.getMaxX());
			TestCase.assertEquals(expected.getMinY(), actual.getMinY());
			TestCase.assertEquals(expected.getMaxY(), actual.getMaxY());
			TestCase.assertEquals(expected.hasZ(), actual.hasZ());
			TestCase.assertEquals(expected.getMinZ(), actual.getMinZ());
			TestCase.assertEquals(expected.getMaxZ(), actual.getMaxZ());
			TestCase.assertEquals(expected.hasM(), actual.hasM());
			TestCase.assertEquals(expected.getMinM(), actual.getMinM());
			TestCase.assertEquals(expected.getMaxM(), actual.getMaxM());
		}

	}

	/**
	 * Compare two geometries and verify they are equal
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareGeometries(Geometry expected, Geometry actual) {
		if (expected == null) {
			TestCase.assertNull(actual);
		} else {
			TestCase.assertNotNull(actual);

			GeometryType geometryType = expected.getGeometryType();
			switch (geometryType) {

			case GEOMETRY:
				TestCase.fail("Unexpected Geometry Type of "
						+ geometryType.name() + " which is abstract");
			case POINT:
				comparePoint((Point) actual, (Point) expected);
				break;
			case LINESTRING:
				compareLineString((LineString) expected, (LineString) actual);
				break;
			case POLYGON:
				comparePolygon((Polygon) expected, (Polygon) actual);
				break;
			case MULTIPOINT:
				compareMultiPoint((MultiPoint) expected, (MultiPoint) actual);
				break;
			case MULTILINESTRING:
				compareMultiLineString((MultiLineString) expected,
						(MultiLineString) actual);
				break;
			case MULTIPOLYGON:
				compareMultiPolygon((MultiPolygon) expected,
						(MultiPolygon) actual);
				break;
			case GEOMETRYCOLLECTION:
				compareGeometryCollection((GeometryCollection<?>) expected,
						(GeometryCollection<?>) actual);
				break;
			case CIRCULARSTRING:
				compareCircularString((CircularString) expected,
						(CircularString) actual);
				break;
			case COMPOUNDCURVE:
				compareCompoundCurve((CompoundCurve) expected,
						(CompoundCurve) actual);
				break;
			case CURVEPOLYGON:
				compareCurvePolygon((CurvePolygon<?>) expected,
						(CurvePolygon<?>) actual);
				break;
			case MULTICURVE:
				TestCase.fail("Unexpected Geometry Type of "
						+ geometryType.name() + " which is abstract");
			case MULTISURFACE:
				TestCase.fail("Unexpected Geometry Type of "
						+ geometryType.name() + " which is abstract");
			case CURVE:
				TestCase.fail("Unexpected Geometry Type of "
						+ geometryType.name() + " which is abstract");
			case SURFACE:
				TestCase.fail("Unexpected Geometry Type of "
						+ geometryType.name() + " which is abstract");
			case POLYHEDRALSURFACE:
				comparePolyhedralSurface((PolyhedralSurface) expected,
						(PolyhedralSurface) actual);
				break;
			case TIN:
				compareTIN((TIN) expected, (TIN) actual);
				break;
			case TRIANGLE:
				compareTriangle((Triangle) expected, (Triangle) actual);
				break;
			default:
				throw new RuntimeException("Geometry Type not supported: "
						+ geometryType);
			}
		}
	}

	/**
	 * Compare to the base attributes of two geometries
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareBaseGeometryAttributes(Geometry expected,
			Geometry actual) {
		TestCase.assertEquals(expected.getGeometryType(),
				actual.getGeometryType());
		TestCase.assertEquals(expected.hasZ(), actual.hasZ());
		TestCase.assertEquals(expected.hasM(), actual.hasM());
		TestCase.assertEquals(WkbUtils.getWkbCode(expected), WkbUtils.getWkbCode(actual));
	}

	/**
	 * Compare the two points for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void comparePoint(Point expected, Point actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.getX(), actual.getX());
		TestCase.assertEquals(expected.getY(), actual.getY());
		TestCase.assertEquals(expected.getZ(), actual.getZ());
		TestCase.assertEquals(expected.getM(), actual.getM());
	}

	/**
	 * Compare the two line strings for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareLineString(LineString expected, LineString actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPoints(), actual.numPoints());
		for (int i = 0; i < expected.numPoints(); i++) {
			comparePoint(expected.getPoints().get(i), actual.getPoints().get(i));
		}
	}

	/**
	 * Compare the two polygons for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void comparePolygon(Polygon expected, Polygon actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numRings(), actual.numRings());
		for (int i = 0; i < expected.numRings(); i++) {
			compareLineString(expected.getRings().get(i), actual.getRings()
					.get(i));
		}
	}

	/**
	 * Compare the two multi points for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareMultiPoint(MultiPoint expected, MultiPoint actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPoints(), actual.numPoints());
		for (int i = 0; i < expected.numPoints(); i++) {
			comparePoint(expected.getPoints().get(i), actual.getPoints().get(i));
		}
	}

	/**
	 * Compare the two multi line strings for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareMultiLineString(MultiLineString expected,
			MultiLineString actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numLineStrings(),
				actual.numLineStrings());
		for (int i = 0; i < expected.numLineStrings(); i++) {
			compareLineString(expected.getLineStrings().get(i), actual
					.getLineStrings().get(i));
		}
	}

	/**
	 * Compare the two multi polygons for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareMultiPolygon(MultiPolygon expected,
			MultiPolygon actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPolygons(), actual.numPolygons());
		for (int i = 0; i < expected.numPolygons(); i++) {
			comparePolygon(expected.getPolygons().get(i), actual.getPolygons()
					.get(i));
		}
	}

	/**
	 * Compare the two geometry collections for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareGeometryCollection(
			GeometryCollection<?> expected, GeometryCollection<?> actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numGeometries(), actual.numGeometries());
		for (int i = 0; i < expected.numGeometries(); i++) {
			compareGeometries(expected.getGeometries().get(i), actual
					.getGeometries().get(i));
		}
	}

	/**
	 * Compare the two circular strings for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareCircularString(CircularString expected,
			CircularString actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPoints(), actual.numPoints());
		for (int i = 0; i < expected.numPoints(); i++) {
			comparePoint(expected.getPoints().get(i), actual.getPoints().get(i));
		}
	}

	/**
	 * Compare the two compound curves for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareCompoundCurve(CompoundCurve expected,
			CompoundCurve actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numLineStrings(),
				actual.numLineStrings());
		for (int i = 0; i < expected.numLineStrings(); i++) {
			compareLineString(expected.getLineStrings().get(i), actual
					.getLineStrings().get(i));
		}
	}

	/**
	 * Compare the two curve polygons for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareCurvePolygon(CurvePolygon<?> expected,
			CurvePolygon<?> actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numRings(), actual.numRings());
		for (int i = 0; i < expected.numRings(); i++) {
			compareGeometries(expected.getRings().get(i), actual.getRings()
					.get(i));
		}
	}

	/**
	 * Compare the two polyhedral surfaces for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void comparePolyhedralSurface(PolyhedralSurface expected,
			PolyhedralSurface actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPolygons(), actual.numPolygons());
		for (int i = 0; i < expected.numPolygons(); i++) {
			compareGeometries(expected.getPolygons().get(i), actual
					.getPolygons().get(i));
		}
	}

	/**
	 * Compare the two TINs for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareTIN(TIN expected, TIN actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numPolygons(), actual.numPolygons());
		for (int i = 0; i < expected.numPolygons(); i++) {
			compareGeometries(expected.getPolygons().get(i), actual
					.getPolygons().get(i));
		}
	}

	/**
	 * Compare the two triangles for equality
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareTriangle(Triangle expected, Triangle actual) {

		compareBaseGeometryAttributes(expected, actual);
		TestCase.assertEquals(expected.numRings(), actual.numRings());
		for (int i = 0; i < expected.numRings(); i++) {
			compareLineString(expected.getRings().get(i), actual.getRings()
					.get(i));
		}
	}

	/**
	 * Write and compare the bytes of the geometries
	 * 
	 * @param expected
	 * @param actual
	 * @throws IOException
	 */
	public static void compareGeometryBytes(Geometry expected, Geometry actual)
			throws IOException {
		compareGeometryBytes(expected, actual, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Write and compare the bytes of the geometries using the byte order
	 * 
	 * @param expected
	 * @param actual
	 * @param byteOrder
	 * @throws IOException
	 */
	public static void compareGeometryBytes(Geometry expected, Geometry actual,
			ByteOrder byteOrder) throws IOException {

		byte[] expectedBytes = writeBytes(expected, byteOrder);
		byte[] actualBytes = writeBytes(actual, byteOrder);

		compareByteArrays(expectedBytes, actualBytes);
	}

	/**
	 * Read and compare the byte geometries
	 * 
	 * @param expected
	 * @param actual
	 * @throws IOException
	 */
	public static void compareByteGeometries(byte[] expected, byte[] actual)
			throws IOException {
		compareByteGeometries(expected, actual, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Read and compare the byte geometries using the byte order
	 * 
	 * @param expected
	 * @param actual
	 * @param byteOrder
	 * @throws IOException
	 */
	public static void compareByteGeometries(byte[] expected, byte[] actual,
			ByteOrder byteOrder) throws IOException {

		Geometry expectedGeometry = readGeometry(expected, byteOrder);
		Geometry actualGeometry = readGeometry(actual, byteOrder);

		compareGeometries(expectedGeometry, actualGeometry);
	}

	/**
	 * Write the geometry to bytes as big endian
	 * 
	 * @param geometry
	 * @return
	 * @throws IOException
	 */
	public static byte[] writeBytes(Geometry geometry) throws IOException {
		return writeBytes(geometry, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Write the geometry to bytes in the provided byte order
	 * 
	 * @param geometry
	 * @param byteOrder
	 * @return
	 * @throws IOException
	 */
	public static byte[] writeBytes(Geometry geometry, ByteOrder byteOrder)
			throws IOException {
		ByteWriter writer = new ByteWriter();
		writer.setByteOrder(byteOrder);
		WkbGeometryWriter.writeGeometry(writer, geometry);
		byte[] bytes = writer.getBytes();
		writer.close();
		return bytes;
	}

	/**
	 * Read a geometry from bytes as big endian
	 * 
	 * @param bytes
	 * @return
	 */
	public static Geometry readGeometry(byte[] bytes) {
		return readGeometry(bytes, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Read a geometry from bytes as the provided byte order
	 * 
	 * @param bytes
	 * @param byteOrder
	 * @return
	 */
	public static Geometry readGeometry(byte[] bytes, ByteOrder byteOrder) {
		ByteReader reader = new ByteReader(bytes);
		reader.setByteOrder(byteOrder);
		Geometry geometry = WkbGeometryReader.readGeometry(reader);
		return geometry;
	}

	/**
	 * Compare two byte arrays and verify they are equal
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void compareByteArrays(byte[] expected, byte[] actual) {

		TestCase.assertEquals(expected.length, actual.length);

		for (int i = 0; i < expected.length; i++) {
			TestCase.assertEquals("Byte: " + i, expected[i], actual[i]);
		}

	}

	/**
	 * Compare two byte arrays and verify they are equal
	 * 
	 * @param expected
	 * @param actual
	 * @return true if equal
	 */
	public static boolean equalByteArrays(byte[] expected, byte[] actual) {

		boolean equal = expected.length == actual.length;

		for (int i = 0; equal && i < expected.length; i++) {
			equal = expected[i] == actual[i];
		}

		return equal;
	}

	/**
	 * Create a random point
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static Point createPoint(boolean hasZ, boolean hasM) {

		double x = Math.random() * 180.0 * (Math.random() < .5 ? 1 : -1);
		double y = Math.random() * 90.0 * (Math.random() < .5 ? 1 : -1);

		Point point = new Point(hasZ, hasM, x, y);

		if (hasZ) {
			double z = Math.random() * 1000.0;
			point.setZ(z);
		}

		if (hasM) {
			double m = Math.random() * 1000.0;
			point.setM(m);
		}

		return point;
	}

	/**
	 * Create a random line string
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static LineString createLineString(boolean hasZ, boolean hasM) {
		return createLineString(hasZ, hasM, false);
	}

	/**
	 * Create a random line string
	 * 
	 * @param hasZ
	 * @param hasM
	 * @param ring
	 * @return
	 */
	public static LineString createLineString(boolean hasZ, boolean hasM,
			boolean ring) {

		LineString lineString = new LineString(hasZ, hasM);

		int num = 2 + ((int) (Math.random() * 9));

		for (int i = 0; i < num; i++) {
			lineString.addPoint(createPoint(hasZ, hasM));
		}

		if (ring) {
			lineString.addPoint(lineString.getPoints().get(0));
		}

		return lineString;
	}

	/**
	 * Create a random polygon
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static Polygon createPolygon(boolean hasZ, boolean hasM) {

		Polygon polygon = new Polygon(hasZ, hasM);

		int num = 1 + ((int) (Math.random() * 5));

		for (int i = 0; i < num; i++) {
			polygon.addRing(createLineString(hasZ, hasM, true));
		}

		return polygon;
	}

	/**
	 * Create a random multi point
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static MultiPoint createMultiPoint(boolean hasZ, boolean hasM) {

		MultiPoint multiPoint = new MultiPoint(hasZ, hasM);

		int num = 1 + ((int) (Math.random() * 5));

		for (int i = 0; i < num; i++) {
			multiPoint.addPoint(createPoint(hasZ, hasM));
		}

		return multiPoint;
	}

	/**
	 * Create a random multi line string
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static MultiLineString createMultiLineString(boolean hasZ,
			boolean hasM) {

		MultiLineString multiLineString = new MultiLineString(hasZ, hasM);

		int num = 1 + ((int) (Math.random() * 5));

		for (int i = 0; i < num; i++) {
			multiLineString.addLineString(createLineString(hasZ, hasM));
		}

		return multiLineString;
	}

	/**
	 * Create a random multi polygon
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static MultiPolygon createMultiPolygon(boolean hasZ, boolean hasM) {

		MultiPolygon multiPolygon = new MultiPolygon(hasZ, hasM);

		int num = 1 + ((int) (Math.random() * 5));

		for (int i = 0; i < num; i++) {
			multiPolygon.addPolygon(createPolygon(hasZ, hasM));
		}

		return multiPolygon;
	}

	/**
	 * Create a random geometry collection
	 * 
	 * @param hasZ
	 * @param hasM
	 * @return
	 */
	public static GeometryCollection<Geometry> createGeometryCollection(
			boolean hasZ, boolean hasM) {

		GeometryCollection<Geometry> geometryCollection = new GeometryCollection<Geometry>(
				hasZ, hasM);

		int num = 1 + ((int) (Math.random() * 5));

		for (int i = 0; i < num; i++) {

			Geometry geometry = null;
			int randomGeometry = (int) (Math.random() * 6);

			switch (randomGeometry) {
			case 0:
				geometry = createPoint(hasZ, hasM);
				break;
			case 1:
				geometry = createLineString(hasZ, hasM);
				break;
			case 2:
				geometry = createPolygon(hasZ, hasM);
				break;
			case 3:
				geometry = createMultiPoint(hasZ, hasM);
				break;
			case 4:
				geometry = createMultiLineString(hasZ, hasM);
				break;
			case 5:
				geometry = createMultiPolygon(hasZ, hasM);
				break;
			}

			geometryCollection.addGeometry(geometry);
		}

		return geometryCollection;
	}

	/**
	 * Randomly return true or false
	 * 
	 * @return
	 */
	public static boolean coinFlip() {
		return Math.random() < 0.5;
	}

}
