package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Curve;
import mil.nga.sf.Polygon;
import mil.nga.sf.Geometry;
import mil.nga.sf.GeometryCollection;
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
			addPointMessage(message, ((Point) simpleGeometry));
			break;
		case LINESTRING:
			addCurveMessage(message, (Curve) simpleGeometry);
			break;
		case POLYGON:
			addPolygonMessage(message, (Polygon) simpleGeometry);
			break;
		case MULTIPOINT:
			addMultiPointMessage(message, (MultiPoint) simpleGeometry);
			break;
		case MULTILINESTRING:
			addMultiLineStringMessage(message, (MultiLineString) simpleGeometry);
			break;
		case MULTIPOLYGON:
			addMultiPolygonMessage(message, (MultiPolygon) simpleGeometry);
			break;
		case CIRCULARSTRING:
			addCurveMessage(message, (CircularString) simpleGeometry);
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
			GeometryCollection<Geometry> geomCollection = (GeometryCollection<Geometry>) simpleGeometry;
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
	private static void addPointMessage(StringBuilder message, Point point) {
		message.append("Latitude: ").append(point.getY());
		message.append("\nLongitude: ").append(point.getX());
		Double alt = point.getZ();
		if (alt != null){
			message.append("\nAltitude: ").append(alt);
		}
	}

	/**
	 * Add MultiPoint message
	 * 
	 * @param message
	 * @param multiPoint
	 */
	private static void addMultiPointMessage(StringBuilder message,
			MultiPoint multiPoint) {
		message.append(Point.class.getSimpleName() + "s: "
				+ multiPoint.numGeometries());
		List<Point> points = multiPoint.getGeometries();
		for (int i = 0; i < points.size(); i++) {
			Point point = points.get(i);
			message.append("\n\n");
			message.append(Point.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addPointMessage(message, point);
		}
	}

	/**
	 * Add Curve message
	 * 
	 * @param message
	 * @param curve
	 */
	private static void addCurveMessage(StringBuilder message,
			Curve curve) {
		message.append(Point.class.getSimpleName() + "s: "
				+ curve.numPoints());
		for (Point point : curve.getPoints()) {
			message.append("\n\n");
			addPointMessage(message, point);
		}
	}

	/**
	 * Add MultiLineString message
	 * 
	 * @param message
	 * @param multiLineString
	 */
	private static void addMultiLineStringMessage(StringBuilder message,
			MultiLineString multiLineString) {
		message.append(LineString.class.getSimpleName() + "s: "
				+ multiLineString.numGeometries());
		List<LineString> lineStrings = multiLineString.getGeometries();
		for (int i = 0; i < lineStrings.size(); i++) {
			Curve lineString = lineStrings.get(i);
			message.append("\n\n");
			message.append(LineString.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addCurveMessage(message, lineString);
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
			addCurveMessage(message, ring);
		}
	}

	/**
	 * Add MultiPolygon message
	 * 
	 * @param message
	 * @param multiPolygon
	 */
	private static void addMultiPolygonMessage(StringBuilder message,
			MultiPolygon multiPolygon) {
		message.append(MultiPolygon.class.getSimpleName() + "s: "
				+ multiPolygon.numGeometries());
		List<Polygon> polygons = multiPolygon.getGeometries();
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
		message.append(LineString.class.getSimpleName() + "s: "
				+ compoundCurve.numCurves());
		List<Curve> curves = compoundCurve.getCurves();
		for (int i = 0; i < curves.size(); i++) {
			Curve curve = curves.get(i);
			message.append("\n\n");
			message.append(LineString.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addCurveMessage(message, curve);
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
		message.append(Polygon.class.getSimpleName() + "s: "
				+ polyhedralSurface.numPolygons());
		List<Polygon> polygons = polyhedralSurface.getPolygons();
		for (int i = 0; i < polygons.size(); i++) {
			Polygon polygon = polygons.get(i);
			message.append("\n\n");
			message.append(Polygon.class.getSimpleName() + " " + (i + 1));
			message.append("\n");
			addPolygonMessage(message, polygon);
		}
	}

}
