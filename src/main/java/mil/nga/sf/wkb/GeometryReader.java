package mil.nga.sf.wkb;

import java.nio.ByteOrder;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Curve;
import mil.nga.sf.Polygon;
import mil.nga.sf.CurvePolygon;
import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.GeometryType;
import mil.nga.sf.LineString;
import mil.nga.sf.LinearRing;
import mil.nga.sf.MultiLineString;
import mil.nga.sf.MultiPoint;
import mil.nga.sf.MultiPolygon;
import mil.nga.sf.Point;
import mil.nga.sf.PolyhedralSurface;
import mil.nga.sf.TIN;
import mil.nga.sf.Triangle;
import mil.nga.sf.util.ByteReader;
import mil.nga.sf.util.SFException;

/**
 * Well Known Binary reader
 * 
 * @author osbornb
 */
public class GeometryReader {

	/**
	 * Read a geometry from the byte reader
	 * 
	 * @param reader
	 * @return geometry
	 */
	public static Geometry readGeometry(ByteReader reader) {
		Geometry simpleGeometry = readGeometry(reader, null);
		return simpleGeometry;
	}

	/**
	 * Read a geometry from the byte reader
	 * 
	 * @param reader
	 * @param expectedType
	 * @return geometry
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Geometry> T readGeometry(ByteReader reader,
			Class<T> expectedType) {

		// Read the single byte order byte
		byte byteOrderValue = reader.readByte();
		ByteOrder byteOrder = byteOrderValue == 0 ? ByteOrder.BIG_ENDIAN
				: ByteOrder.LITTLE_ENDIAN;
		ByteOrder originalByteOrder = reader.getByteOrder();
		reader.setByteOrder(byteOrder);

		// Read the geometry type integer
		int geometryTypeWkbCode = reader.readInt();

		// Look at the last 2 digits to find the geometry type code (1 - 14)
		int geometryTypeCode = geometryTypeWkbCode % 1000;

		// Look at the first digit to find the options (z when 1 or 3, m when 2
		// or 3)
		int geometryTypeMode = geometryTypeWkbCode / 1000;

		// Determine if the geometry has a z (3d) or m (linear referencing
		// system) value
		boolean hasZ = false;
		boolean hasM = false;
		switch (geometryTypeMode) {
		case 0:
			break;

		case 1:
			hasZ = true;
			break;

		case 2:
			hasM = true;
			break;

		case 3:
			hasZ = true;
			hasM = true;
			break;
		}

		GeometryType geometryType = GeometryType.fromCode(geometryTypeCode);

		Geometry simpleGeometry = null;

		switch (geometryType) {

		case GEOMETRY:
			throw new SFException("Unexpected Geometry Type of "
					+ geometryType.name() + " which is abstract");
		case POINT:
			simpleGeometry = readPoint(reader, hasZ, hasM);
			break;
		case LINESTRING:
			simpleGeometry = readLineString(reader, hasZ, hasM);
			break;
		case POLYGON:
			simpleGeometry = readPolygon(reader, hasZ, hasM);
			break;
		case MULTIPOINT:
			simpleGeometry = readMultiPoint(reader, hasZ, hasM);
			break;
		case MULTILINESTRING:
			simpleGeometry = readMultiLineString(reader, hasZ, hasM);
			break;
		case MULTIPOLYGON:
			simpleGeometry = readMultiPolygon(reader, hasZ, hasM);
			break;
		case GEOMETRYCOLLECTION:
			simpleGeometry = readGeometryCollection(reader, hasZ, hasM);
			break;
		case CIRCULARSTRING:
			simpleGeometry = readCircularString(reader, hasZ, hasM);
			break;
		case COMPOUNDCURVE:
			simpleGeometry = readCompoundCurve(reader, hasZ, hasM);
			break;
		case CURVEPOLYGON:
			simpleGeometry = readCurvePolygon(reader, hasZ, hasM);
			break;
		case MULTICURVE:
			throw new SFException("Unexpected Geometry Type of "
					+ geometryType.name() + " which is abstract");
		case MULTISURFACE:
			throw new SFException("Unexpected Geometry Type of "
					+ geometryType.name() + " which is abstract");
		case CURVE:
			throw new SFException("Unexpected Geometry Type of "
					+ geometryType.name() + " which is abstract");
		case SURFACE:
			throw new SFException("Unexpected Geometry Type of "
					+ geometryType.name() + " which is abstract");
		case POLYHEDRALSURFACE:
			simpleGeometry = readPolyhedralSurface(reader, hasZ, hasM);
			break;
		case TIN:
			simpleGeometry = readTIN(reader, hasZ, hasM);
			break;
		case TRIANGLE:
			simpleGeometry = readTriangle(reader, hasZ, hasM);
			break;
		default:
			throw new SFException("Geometry Type not supported: "
					+ geometryType);
		}

		// If there is an expected type, verify the geometry if of that type
		if (expectedType != null && simpleGeometry != null
				&& !expectedType.isAssignableFrom(simpleGeometry.getClass())) {
			throw new SFException("Unexpected Geometry Type. Expected: "
					+ expectedType.getSimpleName() + ", Actual: "
					+ simpleGeometry.getClass().getSimpleName());
		}

		// Restore the byte order
		reader.setByteOrder(originalByteOrder);

		return (T) simpleGeometry;
	}

	/**
	 * Read a Point
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return Point
	 */
	public static Point readPoint(ByteReader reader, boolean hasZ, boolean hasM) {

		Double x = reader.readDouble();
		Double y = reader.readDouble();

		Double z = hasZ ? reader.readDouble() : null;
		Double m = hasM ? reader.readDouble() : null;

		return new Point(x, y, z, m);
	}

	/**
	 * Read a Line String
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return line string
	 */
	public static LineString readLineString(ByteReader reader, boolean hasZ,
			boolean hasM) {

		LineString lineString = new LineString(hasZ, hasM);

		int numPoints = reader.readInt();

		for (int i = 0; i < numPoints; i++) {
			lineString.addPoint(readPoint(reader, hasZ, hasM));
		}

		return lineString;
	}

	/**
	 * Read a Polygon
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return polygon
	 */
	public static Polygon readPolygon(ByteReader reader, boolean hasZ,
			boolean hasM) {

		Polygon polygon = new Polygon(hasZ, hasM);

		int numRings = reader.readInt();

		for (int i = 0; i < numRings; i++) {
			polygon.addRing(new LinearRing(readLineString(reader, hasZ, hasM)));
		}

		return polygon;
	}

	/**
	 * Read a Multi Point
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return multi point
	 */
	public static MultiPoint readMultiPoint(ByteReader reader, boolean hasZ,
			boolean hasM) {

		MultiPoint multiPoint = new MultiPoint(hasZ, hasM);

		int numPoints = reader.readInt();

		for (int i = 0; i < numPoints; i++) {
			multiPoint.addGeometry(readGeometry(reader, Point.class));
		}

		return multiPoint;
	}

	/**
	 * Read a Multi Line String
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return multi line string
	 */
	public static MultiLineString readMultiLineString(ByteReader reader,
			boolean hasZ, boolean hasM) {

		MultiLineString multiLineString = new MultiLineString(hasZ, hasM);

		int numLineStrings = reader.readInt();

		for (int i = 0; i < numLineStrings; i++) {
			multiLineString.addGeometry(readGeometry(reader, LineString.class));
		}

		return multiLineString;
	}

	/**
	 * Read a Multi Polygon
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return multi polygon
	 */
	public static MultiPolygon readMultiPolygon(ByteReader reader,
			boolean hasZ, boolean hasM) {

		MultiPolygon multiPolygon = new MultiPolygon(hasZ, hasM);

		int numPolygons = reader.readInt();

		for (int i = 0; i < numPolygons; i++) {
			multiPolygon.addGeometry(readGeometry(reader, Polygon.class));
		}

		return multiPolygon;
	}

	/**
	 * Read a Geometry Collection
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return geometry collection
	 */
	public static SimpleGeometryCollection readGeometryCollection(
			ByteReader reader, boolean hasZ, boolean hasM) {

		SimpleGeometryCollection geometryCollection = new SimpleGeometryCollection(
				hasZ, hasM);

		int numGeometries = reader.readInt();

		for (int i = 0; i < numGeometries; i++) {
			geometryCollection.addGeometry(readGeometry(reader, Geometry.class));

		}

		return geometryCollection;
	}

	/**
	 * Read a Circular String
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return circular string
	 */
	public static CircularString readCircularString(ByteReader reader,
			boolean hasZ, boolean hasM) {

		CircularString circularString = new CircularString(hasZ, hasM);

		int numPoints = reader.readInt();

		for (int i = 0; i < numPoints; i++) {
			Point point = readPoint(reader, hasZ, hasM);
			circularString.addPoint(point);
		}

		return circularString;
	}

	/**
	 * Read a Compound Curve
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return compound curve
	 */
	public static CompoundCurve readCompoundCurve(ByteReader reader,
			boolean hasZ, boolean hasM) {

		CompoundCurve compoundCurve = new CompoundCurve(hasZ, hasM);

		int numLineStrings = reader.readInt();

		for (int i = 0; i < numLineStrings; i++) {
			LineString lineString = readGeometry(reader, LineString.class);
			compoundCurve.addCurve(lineString);

		}

		return compoundCurve;
	}

	/**
	 * Read a Curve Polygon
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return curve polygon
	 */
	public static CurvePolygon readCurvePolygon(ByteReader reader,
			boolean hasZ, boolean hasM) {

		CurvePolygon curvePolygon = new CurvePolygon(hasZ, hasM);

		int numRings = reader.readInt();

		for (int i = 0; i < numRings; i++) {
			LinearRing ring = readGeometry(reader, LinearRing.class);
			curvePolygon.addRing(ring);

		}

		return curvePolygon;
	}

	/**
	 * Read a Polyhedral Surface
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return polyhedral surface
	 */
	public static PolyhedralSurface readPolyhedralSurface(ByteReader reader,
			boolean hasZ, boolean hasM) {

		PolyhedralSurface polyhedralSurface = new PolyhedralSurface(hasZ, hasM);

		int numPolygons = reader.readInt();

		for (int i = 0; i < numPolygons; i++) {
			Polygon polygon = readGeometry(reader, Polygon.class);
			polyhedralSurface.addPolygon(polygon);

		}

		return polyhedralSurface;
	}

	/**
	 * Read a TIN
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return TIN
	 */
	public static TIN readTIN(ByteReader reader, boolean hasZ, boolean hasM) {

		TIN tin = new TIN(hasZ, hasM);

		int numPolygons = reader.readInt();

		for (int i = 0; i < numPolygons; i++) {
			Polygon polygon = readGeometry(reader, Polygon.class);
			tin.addPolygon(polygon);

		}

		return tin;
	}

	/**
	 * Read a Triangle
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return triangle
	 */
	public static Triangle readTriangle(ByteReader reader, boolean hasZ,
			boolean hasM) {

		Triangle triangle = new Triangle(hasZ, hasM);

		int numRings = reader.readInt();

		for (int i = 0; i < numRings; i++) {
			triangle.addRing(new LinearRing(readLineString(reader, hasZ, hasM)));

		}

		return triangle;
	}

}
