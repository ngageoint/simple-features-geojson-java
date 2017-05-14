package mil.nga.sf.projection;

import mil.nga.sf.CircularString;
import mil.nga.sf.CompoundCurve;
import mil.nga.sf.Geometry;
import mil.nga.sf.AbstractGeometry;
import mil.nga.sf.SimpleGeometryCollection;
import mil.nga.sf.GeometryType;
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
import mil.nga.sf.util.SFException;

import org.osgeo.proj4j.ProjCoordinate;

/**
 * Geometry Projection Transform
 * 
 * @author osbornb
 * @since 1.1.3
 */
public class GeometryProjectionTransform {

	/**
	 * Projection transform
	 */
	private final ProjectionTransform transform;

	/**
	 * Constructor
	 * 
	 * @param transform
	 */
	public GeometryProjectionTransform(ProjectionTransform transform) {
		this.transform = transform;
	}

	/**
	 * Transform the geometry
	 * 
	 * @param simpleGeometry
	 * @return projected geometry
	 */
	public AbstractGeometry transform(Geometry simpleGeometry) {

		AbstractGeometry to = null;

		GeometryType geometryType = simpleGeometry.getGeometryType();
		switch (geometryType) {
		case POINT:
			to = transform((Point) simpleGeometry);
			break;
		case LINESTRING:
			to = transform((SimpleLineString) simpleGeometry);
			break;
		case POLYGON:
			to = transform((SimplePolygon) simpleGeometry);
			break;
		case MULTIPOINT:
			to = transform((MultiPoint) simpleGeometry);
			break;
		case MULTILINESTRING:
			to = transform((SimpleMultiLineString) simpleGeometry);
			break;
		case MULTIPOLYGON:
			to = transform((SimpleMultiPolygon) simpleGeometry);
			break;
		case CIRCULARSTRING:
			to = transform((CircularString) simpleGeometry);
			break;
		case COMPOUNDCURVE:
			to = transform((CompoundCurve) simpleGeometry);
			break;
		case POLYHEDRALSURFACE:
			to = transform((PolyhedralSurface) simpleGeometry);
			break;
		case TIN:
			to = transform((TIN) simpleGeometry);
			break;
		case TRIANGLE:
			to = transform((Triangle) simpleGeometry);
			break;
		case GEOMETRYCOLLECTION:
			@SuppressWarnings("unchecked")
			SimpleGeometryCollection<AbstractGeometry> toCollection = transform((SimpleGeometryCollection<AbstractGeometry>) simpleGeometry);
			to = toCollection;
			break;
		default:
			throw new SFException("Unsupported Geometry Type: "
					+ geometryType);
		}

		return to;
	}

	/**
	 * Transform the projected position
	 * 
	 * @param from
	 * @return projected from
	 */
	public Position transform(Position from) {

		ProjCoordinate fromCoord;
		if (from.hasZ()) {
			fromCoord = new ProjCoordinate(from.getX(), from.getY(),
					from.getZ() != null ? from.getZ() : Double.NaN);
		} else {
			fromCoord = new ProjCoordinate(from.getX(), from.getY());
		}

		ProjCoordinate toCoord = transform.transform(fromCoord);
		
		Double emm = from.hasM() ? from.getM() : null;
		Double alt = from.hasZ() ? (Double.isNaN(toCoord.z) ? from.getZ() : toCoord.z) : null;

		Position to = new Position(toCoord.x, toCoord.y, emm, alt);

		return to;
	}

	/**
	 * Transform the projected point
	 * 
	 * @param simplePoint
	 * @return projected point
	 */
	public SimplePoint transform(Point simplePoint) {

		return new SimplePoint(transform(simplePoint.getPosition()));
	}

	/**
	 * Transform the projected line string
	 * 
	 * @param lineString
	 * @return projected line string
	 */
	public SimpleLineString transform(SimpleLineString lineString) {

		SimpleLineString to = new SimpleLineString(lineString.hasZ(), lineString.hasM());

		for (Position position : lineString.getPositions()) {
			Position toPosition = transform(position);
			to.addPosition(toPosition);
		}

		return to;
	}

	/**
	 * Transform the projected polygon
	 * 
	 * @param polygon
	 * @return projected polygon
	 */
	public SimplePolygon transform(SimplePolygon polygon) {

		SimplePolygon to = new SimplePolygon(polygon.hasZ(), polygon.hasM());

		for (SimpleLinearRing ring : polygon.getRings()) {
			SimpleLinearRing toRing = new SimpleLinearRing(transform(ring));
			to.addRing(toRing);
		}

		return to;
	}

	/**
	 * Transform the projected multi point
	 * 
	 * @param multiPoint
	 * @return projected multi point
	 */
	public MultiPoint transform(MultiPoint multiPoint) {

		MultiPoint to = new MultiPoint(multiPoint.hasZ(), multiPoint.hasM());

		for (Position position : multiPoint.getPositions()) {
			Position toPosition = transform(position);
			to.addPosition(toPosition);
		}

		return to;
	}

	/**
	 * Transform the projected multi line string
	 * 
	 * @param multiLineString
	 * @return projected multi line string
	 */
	public SimpleMultiLineString transform(SimpleMultiLineString multiLineString) {

		SimpleMultiLineString to = new SimpleMultiLineString(multiLineString.hasZ(),
				multiLineString.hasM());

		for (SimpleLineString lineString : multiLineString.getLineStrings()) {
			SimpleLineString toLineString = transform(lineString);
			to.addLineString(toLineString);
		}

		return to;
	}

	/**
	 * Transform the projected multi polygon
	 * 
	 * @param multiPolygon
	 * @return projected multi polygon
	 */
	public SimpleMultiPolygon transform(SimpleMultiPolygon multiPolygon) {

		SimpleMultiPolygon to = new SimpleMultiPolygon(multiPolygon.hasZ(),
				multiPolygon.hasM());

		for (SimplePolygon polygon : multiPolygon.getPolygons()) {
			Polygon toPolygon = transform(polygon);
			to.addPolygon(toPolygon);
		}

		return to;
	}

	/**
	 * Transform the projected circular string
	 * 
	 * @param circularString
	 * @return projected circular string
	 */
	public CircularString transform(CircularString circularString) {

		CircularString to = new CircularString(circularString.hasZ(),
				circularString.hasM());

		for (Position position : circularString.getPositions()) {
			Position toPosition = transform(position);
			to.addPosition(toPosition);
		}

		return to;
	}

	/**
	 * Transform the projected compound curve
	 * 
	 * @param compoundCurve
	 * @return projected compound curve
	 */
	public CompoundCurve transform(CompoundCurve compoundCurve) {

		CompoundCurve to = new CompoundCurve(compoundCurve.hasZ(),
				compoundCurve.hasM());

		for (SimpleLineString lineString : compoundCurve.getLineStrings()) {
			SimpleLineString toLineString = transform(lineString);
			to.addLineString(toLineString);
		}

		return to;
	}

	/**
	 * Transform the projected polyhedral surface
	 * 
	 * @param polyhedralSurface
	 * @return projected polyhedral surface
	 */
	public PolyhedralSurface transform(PolyhedralSurface polyhedralSurface) {

		PolyhedralSurface to = new PolyhedralSurface(polyhedralSurface.hasZ(),
				polyhedralSurface.hasM());

		for (SimplePolygon polygon : polyhedralSurface.getPolygons()) {
			SimplePolygon toPolygon = transform(polygon);
			to.addPolygon(toPolygon);
		}

		return to;
	}

	/**
	 * Transform the projected TIN
	 * 
	 * @param tin
	 * @return projected tin
	 */
	public TIN transform(TIN tin) {

		TIN to = new TIN(tin.hasZ(), tin.hasM());

		for (SimplePolygon polygon : tin.getPolygons()) {
			SimplePolygon toPolygon = transform(polygon);
			to.addPolygon(toPolygon);
		}

		return to;
	}

	/**
	 * Transform the projected triangle
	 * 
	 * @param triangle
	 * @return projected triangle
	 */
	public Triangle transform(Triangle triangle) {

		Triangle to = new Triangle(triangle.hasZ(), triangle.hasM());

		for (SimpleLineString ring : triangle.getRings()) {
			SimpleLinearRing toRing = new SimpleLinearRing(transform(ring));
			to.addRing(toRing);
		}

		return to;
	}

	/**
	 * Transform the projected geometry collection
	 * 
	 * @param geometryCollection
	 * @return projected geometry collection
	 */
	public SimpleGeometryCollection<AbstractGeometry> transform(
			SimpleGeometryCollection<AbstractGeometry> geometryCollection) {

		SimpleGeometryCollection<AbstractGeometry> to = new SimpleGeometryCollection<AbstractGeometry>(
				geometryCollection.hasZ(), geometryCollection.hasM());

		for (Geometry simpleGeometry : geometryCollection.getGeometries()) {
			AbstractGeometry toGeometry = transform(simpleGeometry);
			to.addGeometry(toGeometry);
		}

		return to;
	}

}
