package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Curve;
import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.GeometryType;
import mil.nga.sf.LineString;
import mil.nga.sf.LinearRing;
import mil.nga.sf.SimpleLineString;
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

/**
 * String representation of a Geometry
 * 
 * @author osbornb
 */
public class GeometryPrinter {

	/**
	 * Get Geometry Information as a String
	 * 
	 * @param simpleGeometry
	 *            geometry
	 * @return geometry String
	 */
	public static String getGeometryString(Geometry simpleGeometry) {

		StringBuilder message = new StringBuilder();

		GeometryType geometryType = simpleGeometry.getGeometryType();
		switch (geometryType) {
		case POINT:
			addPositionMessage(message, ((Point) simpleGeometry).getPosition());
			break;
		case LINESTRING:
			addLineStringMessage(message, (Curve) simpleGeometry);
			break;
		case POLYGON:
			addPolygonMessage(message, (SimplePolygon) simpleGeometry);
			break;
		case MULTIPOINT:
			addMultiPointMessage(message, (MultiPoint) simpleGeometry);
			break;
		case MULTILINESTRING:
			addMultiLineStringMessage(message, (SimpleMultiLineString) simpleGeometry);
			break;
		case MULTIPOLYGON:
			addMultiPolygonMessage(message, (SimpleMultiPolygon) simpleGeometry);
			break;
		case CIRCULARSTRING:
			addLineStringMessage(message, (CircularString) simpleGeometry);
			break;
		case COMPOUNDCURVE:
			addCompoundCurveMessage(message, (CompoundCurve) simpleGeometry);
			break;
		case POLYHEDRALSURFACE:
			addPolyhedralSurfaceMessage(message, (PolyhedralSurface) simpleGeometry);
			break;
		case TIN:
			addPolyhedralSurfaceMessage(message, (TIN) simpleGeometry);
			break;
		case TRIANGLE:
			addPolygonMessage(message, (Triangle) simpleGeometry);
			break;
		case GEOMETRYCOLLECTION:
			@SuppressWarnings("unchecked")
			SimpleGeometryCollection<Geometry> geomCollection = (SimpleGeometryCollection<Geometry>) simpleGeometry;
			message.append("Geometries: " + geomCollection.numGeometries());
			List<Geometry> simpleGeometries = geomCollection.getGeometries();
			for (int i = 0; i < simpleGeometries.size(); i++) {
				Geometry subGeometry = simpleGeometries.get(i);
				message.append("\n\n");
				message.append(Geometry.class.getSimpleName() + " " + (i + 1));
				message.append("\n");
				message.append(subGeometry.getGeometryType().getName());
				message.append("\n");
				message.append(getGeometryString(subGeometry));
			}
			break;
		default:
		}

		return message.toString();
	}

	/**
	 * Add Point message
	 * 
	 * @param message
	 * @param position
	 */
	private static void addPositionMessage(StringBuilder message, Position position) {
		message.append("Latitude: ").append(position.getY());
		message.append("\nLongitude: ").append(position.getX());
	}

	/**
	 * Add MultiPoint message
	 * 
	 * @param message
	 * @param multiPoint
	 */
	private static void addMultiPointMessage(StringBuilder message,
			MultiPoint multiPoint) {
		message.append(SimplePoint.class.getSimpleName() + "s: "
				+ multiPoint.numPositions());
		List<Position> positions = multiPoint.getPositions();
		for (int i = 0; i < positions.size(); i++) {
			Position position = positions.get(i);
			message.append("\n\n");
			message.append(SimplePoint.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addPositionMessage(message, position);
		}
	}

	/**
	 * Add LineString message
	 * 
	 * @param message
	 * @param lineString
	 */
	private static void addLineStringMessage(StringBuilder message,
			Curve lineString) {
		message.append(SimplePoint.class.getSimpleName() + "s: "
				+ lineString.numPositions());
		for (Position position : lineString.getPositions()) {
			message.append("\n\n");
			addPositionMessage(message, position);
		}
	}

	/**
	 * Add MultiLineString message
	 * 
	 * @param message
	 * @param multiLineString
	 */
	private static void addMultiLineStringMessage(StringBuilder message,
			SimpleMultiLineString multiLineString) {
		message.append(SimpleLineString.class.getSimpleName() + "s: "
				+ multiLineString.numLineStrings());
		List<LineString> lineStrings = multiLineString.getLineStrings();
		for (int i = 0; i < lineStrings.size(); i++) {
			Curve lineString = lineStrings.get(i);
			message.append("\n\n");
			message.append(SimpleLineString.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addLineStringMessage(message, lineString);
		}
	}

	/**
	 * Add Polygon message
	 * 
	 * @param message
	 * @param polygon
	 */
	private static void addPolygonMessage(StringBuilder message, Polygon polygon) {
		message.append("Rings: " + polygon.numRings());
		List<LinearRing> rings = polygon.getRings();
		for (int i = 0; i < rings.size(); i++) {
			LinearRing ring = rings.get(i);
			message.append("\n\n");
			if (i > 0) {
				message.append("Hole " + i);
				message.append("\n");
			}
			addLineStringMessage(message, ring);
		}
	}

	/**
	 * Add MultiPolygon message
	 * 
	 * @param message
	 * @param multiPolygon
	 */
	private static void addMultiPolygonMessage(StringBuilder message,
			SimpleMultiPolygon multiPolygon) {
		message.append(SimplePolygon.class.getSimpleName() + "s: "
				+ multiPolygon.numPolygons());
		List<Polygon> polygons = multiPolygon.getPolygons();
		for (int i = 0; i < polygons.size(); i++) {
			Polygon polygon = polygons.get(i);
			message.append("\n\n");
			message.append(Polygon.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addPolygonMessage(message, polygon);
		}
	}

	/**
	 * Add CompoundCurve message
	 * 
	 * @param message
	 * @param compoundCurve
	 */
	private static void addCompoundCurveMessage(StringBuilder message,
			CompoundCurve compoundCurve) {
		message.append(SimpleLineString.class.getSimpleName() + "s: "
				+ compoundCurve.numLineStrings());
		List<SimpleLineString> lineStrings = compoundCurve.getLineStrings();
		for (int i = 0; i < lineStrings.size(); i++) {
			LineString lineString = lineStrings.get(i);
			message.append("\n\n");
			message.append(LineString.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addLineStringMessage(message, lineString);
		}
	}

	/**
	 * Add PolyhedralSurface message
	 * 
	 * @param message
	 * @param polyhedralSurface
	 */
	private static void addPolyhedralSurfaceMessage(StringBuilder message,
			PolyhedralSurface polyhedralSurface) {
		message.append(SimplePolygon.class.getSimpleName() + "s: "
				+ polyhedralSurface.numPolygons());
		List<Polygon> polygons = polyhedralSurface.getPolygons();
		for (int i = 0; i < polygons.size(); i++) {
			Polygon polygon = polygons.get(i);
			message.append("\n\n");
			message.append(SimplePolygon.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addPolygonMessage(message, polygon);
		}
	}

}
