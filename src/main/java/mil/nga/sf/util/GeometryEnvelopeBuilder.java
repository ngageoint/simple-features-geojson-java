package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Geometry;
import mil.nga.sf.GeometryCollection;
import mil.nga.sf.GeometryEnvelope;
import mil.nga.sf.GeometryType;
import mil.nga.sf.LineString;
import mil.nga.sf.MultiLineString;
import mil.nga.sf.MultiPoint;
import mil.nga.sf.MultiPolygon;
import mil.nga.sf.Point;
import mil.nga.sf.Polygon;
import mil.nga.sf.PolyhedralSurface;
import mil.nga.sf.TIN;
import mil.nga.sf.Triangle;

/**
 * Builds an envelope from a Geometry
 * 
 * @author osbornb
 */
public class GeometryEnvelopeBuilder {

	/**
	 * Build Geometry Envelope
	 * 
	 * @param geometry
	 * @return geometry envelope
	 */
	public static GeometryEnvelope buildEnvelope(Geometry geometry) {

		GeometryEnvelope envelope = new GeometryEnvelope();

		envelope.setMinX(Double.MAX_VALUE);
		envelope.setMaxX(-Double.MAX_VALUE);
		envelope.setMinY(Double.MAX_VALUE);
		envelope.setMaxY(-Double.MAX_VALUE);

		buildEnvelope(geometry, envelope);

		return envelope;
	}

	/**
	 * Build Geometry Envelope
	 * 
	 * @param geometry
	 * @param envelope
	 */
	public static void buildEnvelope(Geometry geometry,
			GeometryEnvelope envelope) {

		GeometryType geometryType = geometry.getGeometryType();
		switch (geometryType) {
		case POINT:
			addPointMessage(envelope, (Point) geometry);
			break;
		case LINESTRING:
			addLineStringMessage(envelope, (LineString) geometry);
			break;
		case POLYGON:
			addPolygonMessage(envelope, (Polygon) geometry);
			break;
		case MULTIPOINT:
			addMultiPointMessage(envelope, (MultiPoint) geometry);
			break;
		case MULTILINESTRING:
			addMultiLineStringMessage(envelope, (MultiLineString) geometry);
			break;
		case MULTIPOLYGON:
			addMultiPolygonMessage(envelope, (MultiPolygon) geometry);
			break;
		case CIRCULARSTRING:
			addLineStringMessage(envelope, (CircularString) geometry);
			break;
		case COMPOUNDCURVE:
			addCompoundCurveMessage(envelope, (CompoundCurve) geometry);
			break;
		case POLYHEDRALSURFACE:
			addPolyhedralSurfaceMessage(envelope, (PolyhedralSurface) geometry);
			break;
		case TIN:
			addPolyhedralSurfaceMessage(envelope, (TIN) geometry);
			break;
		case TRIANGLE:
			addPolygonMessage(envelope, (Triangle) geometry);
			break;
		case GEOMETRYCOLLECTION:
			updateHasZandM(envelope, geometry);
			@SuppressWarnings("unchecked")
			GeometryCollection<Geometry> geomCollection = (GeometryCollection<Geometry>) geometry;
			List<Geometry> geometries = geomCollection.getGeometries();
			for (Geometry subGeometry : geometries) {
				buildEnvelope(subGeometry, envelope);
			}
			break;
		default:
		}

	}

	/**
	 * Update teh has z and m values
	 * 
	 * @param envelope
	 * @param geometry
	 */
	private static void updateHasZandM(GeometryEnvelope envelope,
			Geometry geometry) {
		if (!envelope.hasZ() && geometry.hasZ()) {
			envelope.setHasZ(true);
		}
		if (!envelope.hasM() && geometry.hasM()) {
			envelope.setHasM(true);
		}
	}

	/**
	 * Add Point
	 * 
	 * @param envelope
	 * @param point
	 */
	private static void addPointMessage(GeometryEnvelope envelope, Point point) {

		updateHasZandM(envelope, point);

		double x = point.getX();
		double y = point.getY();
		if (x < envelope.getMinX()) {
			envelope.setMinX(x);
		}
		if (x > envelope.getMaxX()) {
			envelope.setMaxX(x);
		}
		if (y < envelope.getMinY()) {
			envelope.setMinY(y);
		}
		if (y > envelope.getMaxY()) {
			envelope.setMaxY(y);
		}
		if (point.hasZ()) {
			Double z = point.getZ();
			if (z != null) {
				if (envelope.getMinZ() == null || z < envelope.getMinZ()) {
					envelope.setMinZ(z);
				}
				if (envelope.getMaxZ() == null || z > envelope.getMaxZ()) {
					envelope.setMaxZ(z);
				}
			}
		}
		if (point.hasM()) {
			Double m = point.getM();
			if (m != null) {
				if (envelope.getMinM() == null || m < envelope.getMinM()) {
					envelope.setMinM(m);
				}
				if (envelope.getMaxM() == null || m > envelope.getMaxM()) {
					envelope.setMaxM(m);
				}
			}
		}
	}

	/**
	 * Add MultiPoint
	 * 
	 * @param envelope
	 * @param multiPoint
	 */
	private static void addMultiPointMessage(GeometryEnvelope envelope,
			MultiPoint multiPoint) {

		updateHasZandM(envelope, multiPoint);

		List<Point> points = multiPoint.getPoints();
		for (Point point : points) {
			addPointMessage(envelope, point);
		}
	}

	/**
	 * Add LineString
	 * 
	 * @param envelope
	 * @param lineString
	 */
	private static void addLineStringMessage(GeometryEnvelope envelope,
			LineString lineString) {

		updateHasZandM(envelope, lineString);

		for (Point point : lineString.getPoints()) {
			addPointMessage(envelope, point);
		}
	}

	/**
	 * Add MultiLineString
	 * 
	 * @param envelope
	 * @param multiLineString
	 */
	private static void addMultiLineStringMessage(GeometryEnvelope envelope,
			MultiLineString multiLineString) {

		updateHasZandM(envelope, multiLineString);

		List<LineString> lineStrings = multiLineString.getLineStrings();
		for (LineString lineString : lineStrings) {
			addLineStringMessage(envelope, lineString);
		}
	}

	/**
	 * Add Polygon
	 * 
	 * @param envelope
	 * @param polygon
	 */
	private static void addPolygonMessage(GeometryEnvelope envelope,
			Polygon polygon) {

		updateHasZandM(envelope, polygon);

		List<LineString> rings = polygon.getRings();
		for (LineString ring : rings) {
			addLineStringMessage(envelope, ring);
		}
	}

	/**
	 * Add MultiPolygon
	 * 
	 * @param envelope
	 * @param multiPolygon
	 */
	private static void addMultiPolygonMessage(GeometryEnvelope envelope,
			MultiPolygon multiPolygon) {

		updateHasZandM(envelope, multiPolygon);

		List<Polygon> polygons = multiPolygon.getPolygons();
		for (Polygon polygon : polygons) {
			addPolygonMessage(envelope, polygon);
		}
	}

	/**
	 * Add CompoundCurve
	 * 
	 * @param envelope
	 * @param compoundCurve
	 */
	private static void addCompoundCurveMessage(GeometryEnvelope envelope,
			CompoundCurve compoundCurve) {

		updateHasZandM(envelope, compoundCurve);

		List<LineString> lineStrings = compoundCurve.getLineStrings();
		for (LineString lineString : lineStrings) {
			addLineStringMessage(envelope, lineString);
		}
	}

	/**
	 * Add PolyhedralSurface
	 * 
	 * @param envelope
	 * @param polyhedralSurface
	 */
	private static void addPolyhedralSurfaceMessage(GeometryEnvelope envelope,
			PolyhedralSurface polyhedralSurface) {

		updateHasZandM(envelope, polyhedralSurface);

		List<Polygon> polygons = polyhedralSurface.getPolygons();
		for (Polygon polygon : polygons) {
			addPolygonMessage(envelope, polygon);
		}
	}

}
