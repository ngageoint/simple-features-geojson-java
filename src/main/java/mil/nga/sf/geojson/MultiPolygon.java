package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.GeometryUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MultiPolygon extends GeoJsonObject implements Geometry, Coordinates<List<List<Position>>> {

	private static final long serialVersionUID = -2739352075951612353L;
	
	/**
	 * 
	 */
	private mil.nga.sf.MultiPolygon multiPolygon;

	public MultiPolygon() {
	}

	public MultiPolygon(List<List<List<Position>>> positions) {
		setCoordinates(positions);
	}
	
	public MultiPolygon(mil.nga.sf.MultiPolygon input) {
		multiPolygon = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<List<Position>>> getCoordinates() {
		List<List<List<Position>>> result = new ArrayList<List<List<Position>>>();
		for(mil.nga.sf.Polygon polygon : multiPolygon.getGeometries()){
			List<List<Position>> polygonPositions = new ArrayList<List<Position>>();
			for(mil.nga.sf.LineString ring : polygon.getRings()){
				List<Position> positions = new ArrayList<Position>();
				for(mil.nga.sf.Point pos : ring.getPoints()){
					positions.add(new Position(pos));
				}
				polygonPositions.add(positions);
			}
			result.add(polygonPositions);
		}
		return result;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * @param input the list
	 */
	private void setCoordinates(List<List<List<Position>>> input) {
		List<mil.nga.sf.Polygon> polygons = new ArrayList<mil.nga.sf.Polygon>();
		for (List<List<Position>> polygonPositions : input){
			List<mil.nga.sf.LineString> rings = new ArrayList<>();
			for (List<Position> ringPositions : polygonPositions) {
				List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
				for (Position position: ringPositions){
					points.add(position.toSimplePoint());
				}
				mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(GeometryUtils.hasZ(points), GeometryUtils.hasM(points));
				ring.setPoints(points);
				rings.add(ring);
			}
			mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
			polygons.add(polygon);
		}
		if (multiPolygon == null){
			multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
		} else {
			multiPolygon.setGeometries(polygons);
		}
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiPolygon;
	}

	@Override
	public String getType() {
		return "MultiPolygon";
	}
}
