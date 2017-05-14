package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Curve;
import mil.nga.sf.Geometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.GeometryEnvelope;
import mil.nga.sf.GeometryType;
import mil.nga.sf.LineString;
import mil.nga.sf.LinearRing;
import mil.nga.sf.SimpleLineString;
import mil.nga.sf.SimpleMultiLineString;
import mil.nga.sf.MultiPoint;
import mil.nga.sf.SimpleMultiPolygon;
import mil.nga.sf.Polygon;
import mil.nga.sf.SimplePoint;
import mil.nga.sf.SimplePolygon;
import mil.nga.sf.PolyhedralSurface;
import mil.nga.sf.Position;
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
	 * @param simpleGeometry
	 * @return geometry envelope
	 */
	public static GeometryEnvelope buildEnvelope(Geometry simpleGeometry) {

		GeometryEnvelope envelope = new GeometryEnvelope();

		envelope.setMinX(Double.MAX_VALUE);
		envelope.setMaxX(-Double.MAX_VALUE);
		envelope.setMinY(Double.MAX_VALUE);
		envelope.setMaxY(-Double.MAX_VALUE);

		buildEnvelope(simpleGeometry, envelope);

		return envelope;
	}

	/**
	 * Build Geometry Envelope
	 * 
	 * @param simpleGeometry
	 * @param envelope
	 */
	public static void buildEnvelope(Geometry simpleGeometry,
			GeometryEnvelope envelope) {

		GeometryType geometryType = simpleGeometry.getGeometryType();
		switch (geometryType) {
		case POINT:
			addPointMessage(envelope, (SimplePoint) simpleGeometry);
			break;
		case LINESTRING:
			addLineStringMessage(envelope, (Curve) simpleGeometry);
			break;
		case POLYGON:
			addPolygonMessage(envelope, (SimplePolygon) simpleGeometry);
			break;
		case MULTIPOINT:
			addMultiPointMessage(envelope, (MultiPoint) simpleGeometry);
			break;
		case MULTILINESTRING:
			addMultiLineStringMessage(envelope, (SimpleMultiLineString) simpleGeometry);
			break;
		case MULTIPOLYGON:
			addMultiPolygonMessage(envelope, (SimpleMultiPolygon) simpleGeometry);
			break;
		case CIRCULARSTRING:
			addLineStringMessage(envelope, (CircularString) simpleGeometry);
			break;
		case COMPOUNDCURVE:
			addCompoundCurveMessage(envelope, (CompoundCurve) simpleGeometry);
			break;
		case POLYHEDRALSURFACE:
			addPolyhedralSurfaceMessage(envelope, (PolyhedralSurface) simpleGeometry);
			break;
		case TIN:
			addPolyhedralSurfaceMessage(envelope, (TIN) simpleGeometry);
			break;
		case TRIANGLE:
			addPolygonMessage(envelope, (Triangle) simpleGeometry);
			break;
		case GEOMETRYCOLLECTION:
			updateHasZandM(envelope, simpleGeometry);
			@SuppressWarnings("unchecked")
			SimpleGeometryCollection<Geometry> geomCollection = (SimpleGeometryCollection<Geometry>) simpleGeometry;
			List<Geometry> simpleGeometries = geomCollection.getGeometries();
			for (Geometry subGeometry : simpleGeometries) {
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
	 * @param simpleGeometry
	 */
	private static void updateHasZandM(GeometryEnvelope envelope,
			Geometry simpleGeometry) {
		if (!envelope.hasZ() && simpleGeometry.hasZ()) {
			envelope.setHasZ(true);
		}
		if (!envelope.hasM() && simpleGeometry.hasM()) {
			envelope.setHasM(true);
		}
	}

	/**
	 * Add Point
	 * 
	 * @param envelope
	 * @param position
	 */
	private static void addPointMessage(GeometryEnvelope envelope, SimplePoint simplePoint) {

		updateHasZandM(envelope, simplePoint);
		addPositionMessage (envelope, simplePoint.getPosition());
	}

	/**
	 * Add Position
	 * 
	 * @param envelope
	 * @param position
	 */
	private static void addPositionMessage(GeometryEnvelope envelope, Position position) {

		double x = position.getX();
		double y = position.getY();
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
		Double z = position.getZ();
		if (z != null) {
			if (envelope.getMinZ() == null || z < envelope.getMinZ()) {
				envelope.setMinZ(z);
			}
			if (envelope.getMaxZ() == null || z > envelope.getMaxZ()) {
				envelope.setMaxZ(z);
			}
		}
		Double m = position.getM();
		if (m != null) {
			if (envelope.getMinM() == null || m < envelope.getMinM()) {
				envelope.setMinM(m);
			}
			if (envelope.getMaxM() == null || m > envelope.getMaxM()) {
				envelope.setMaxM(m);
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

		List<Position> positions = multiPoint.getPositions();
		for (Position position : positions) {
			addPositionMessage(envelope, position);
		}
	}

	/**
	 * Add LineString
	 * 
	 * @param envelope
	 * @param lineString
	 */
	private static void addLineStringMessage(GeometryEnvelope envelope,
			Curve lineString) {

		updateHasZandM(envelope, lineString);

		for (Position position : lineString.getPositions()) {
			addPositionMessage(envelope, position);
		}
	}

	/**
	 * Add MultiLineString
	 * 
	 * @param envelope
	 * @param multiLineString
	 */
	private static void addMultiLineStringMessage(GeometryEnvelope envelope,
			SimpleMultiLineString multiLineString) {

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

		List<LinearRing> rings = polygon.getRings();
		for (LinearRing ring : rings) {
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
			SimpleMultiPolygon multiPolygon) {

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

		List<SimpleLineString> lineStrings = compoundCurve.getLineStrings();
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
