package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import mil.nga.sf.util.PointUtils;

public class Polygon extends GeoJsonObject implements Geometry, Coordinates<List<Position>> {

	/**
	 * 
	 */
	private mil.nga.sf.Polygon polygon;

	public Polygon() {
	}

	public Polygon(List<List<Position>> positions) {
		setCoordinates(positions);
	}
	
	public Polygon(mil.nga.sf.Polygon input) {
		polygon = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<Position>> getCoordinates() {
		List<List<Position>> result = new ArrayList<List<Position>>();
		for(mil.nga.sf.LinearRing ring : polygon.getRings()){
			List<Position> positions = new ArrayList<Position>();
			for(mil.nga.sf.Position pos : ring.getPoints()){
				positions.add(new Position(pos));
			}
			result.add(positions);
		}
		return result;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * @param input the list
	 */
	private void setCoordinates(List<List<Position>> input) {
		List<mil.nga.sf.LinearRing> rings = new ArrayList<mil.nga.sf.LinearRing>();
		for (List<Position> ringPositions : input) {
			List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
			for (Position position: ringPositions){
				points.add(new mil.nga.sf.Point(position));
			}
			mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(PointUtils.hasZ(points), PointUtils.hasM(points));
			ring.setPoints(points);
			rings.add(ring);
		}
		if (polygon == null){
			polygon = new mil.nga.sf.Polygon(rings);
		} else {
			polygon.setRings(rings);
		}
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return polygon;
	}

	@Override
	public String getType() {
		return "Polygon";
	}
}
