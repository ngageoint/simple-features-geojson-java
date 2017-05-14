package mil.nga.sf.wkb;

import java.nio.ByteOrder;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Curve;
import mil.nga.sf.SimpleCurvePolygon;
import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.GeometryType;
import mil.nga.sf.LinearRing;
import mil.nga.sf.SimpleLineString;
import mil.nga.sf.SimpleLinearRing;
import mil.nga.sf.SimpleMultiLineString;
import mil.nga.sf.MultiPoint;
import mil.nga.sf.SimpleMultiPolygon;
import mil.nga.sf.Point;
import mil.nga.sf.Polygon;
import mil.nga.sf.SimplePoint;
import mil.nga.sf.SimplePolygon;
import mil.nga.sf.PolyhedralSurface;
import mil.nga.sf.Position;
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
	 * Read a Position
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return position
	 */
	public static Position readPosition(ByteReader reader, boolean hasZ, boolean hasM) {

		Double x = reader.readDouble();
		Double y = reader.readDouble();

		Double z = hasZ ? reader.readDouble() : null;
		Double m = hasM ? reader.readDouble() : null;

		return new Position(x, y, z, m);
	}

	/**
	 * Read a Point
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return point
	 */
	public static SimplePoint readPoint(ByteReader reader, boolean hasZ, boolean hasM) {
		return new SimplePoint(readPosition(reader, hasZ, hasM));
	}

	/**
	 * Read a Line String
	 * 
	 * @param reader
	 * @param hasZ
	 * @param hasM
	 * @return line string
	 */
	public static SimpleLineString readLineString(ByteReader reader, boolean hasZ,
			boolean hasM) {

		SimpleLineString lineString = new SimpleLineString(hasZ, hasM);

		int numPoints = reader.readInt();

		for (int i = 0; i < numPoints; i++) {
			lineString.addPosition(readPosition(reader, hasZ, hasM));
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
	public static SimplePolygon readPolygon(ByteReader reader, boolean hasZ,
			boolean hasM) {

		SimplePolygon polygon = new SimplePolygon(hasZ, hasM);

		int numRings = reader.readInt();

		for (int i = 0; i < numRings; i++) {
			polygon.addRing(new SimpleLinearRing(readLineString(reader, hasZ, hasM)));

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
			Point simplePoint = readGeometry(reader, SimplePoint.class);
			multiPoint.addPosition(simplePoint.getPosition());
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
	public static SimpleMultiLineString readMultiLineString(ByteReader reader,
			boolean hasZ, boolean hasM) {

		SimpleMultiLineString multiLineString = new SimpleMultiLineString(hasZ, hasM);

		int numLineStrings = reader.readInt();

		for (int i = 0; i < numLineStrings; i++) {
			multiLineString.addLineString(readGeometry(reader, SimpleLineString.class));
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
	public static SimpleMultiPolygon readMultiPolygon(ByteReader reader,
			boolean hasZ, boolean hasM) {

		SimpleMultiPolygon multiPolygon = new SimpleMultiPolygon(hasZ, hasM);

		int numPolygons = reader.readInt();

		for (int i = 0; i < numPolygons; i++) {
			Polygon polygon = readGeometry(reader, SimplePolygon.class);
			multiPolygon.addPolygon(polygon);

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
	public static SimpleGeometryCollection<Geometry> readGeometryCollection(
			ByteReader reader, boolean hasZ, boolean hasM) {

		SimpleGeometryCollection<Geometry> geometryCollection = new SimpleGeometryCollection<Geometry>(
				hasZ, hasM);

		int numGeometries = reader.readInt();

		for (int i = 0; i < numGeometries; i++) {
			Geometry simpleGeometry = readGeometry(reader, Geometry.class);
			geometryCollection.addGeometry(simpleGeometry);

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
			Position position = readPosition(reader, hasZ, hasM);
			circularString.addPosition(position);
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
			SimpleLineString lineString = readGeometry(reader, SimpleLineString.class);
			compoundCurve.addLineString(lineString);

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
	public static SimpleCurvePolygon readCurvePolygon(ByteReader reader,
			boolean hasZ, boolean hasM) {

		SimpleCurvePolygon curvePolygon = new SimpleCurvePolygon(hasZ, hasM);

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
			SimplePolygon polygon = readGeometry(reader, SimplePolygon.class);
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
			SimplePolygon polygon = readGeometry(reader, SimplePolygon.class);
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
			triangle.addRing(new SimpleLinearRing(readLineString(reader, hasZ, hasM)));

		}

		return triangle;
	}

}
