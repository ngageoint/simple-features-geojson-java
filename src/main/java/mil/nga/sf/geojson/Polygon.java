package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.util.GeometryUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Polygon extends GeoJsonObject implements Geometry, Coordinates<List<Position>> {

	private static final long serialVersionUID = 2866577421910418944L;
	
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
		for(mil.nga.sf.LineString ring : polygon.getRings()){
			List<Position> positions = new ArrayList<Position>();
			for(mil.nga.sf.Point pos : ring.getPoints()){
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
		List<mil.nga.sf.LineString> rings = new ArrayList<>();
		for (List<Position> ringPositions : input) {
			List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
			for (Position position: ringPositions){
				points.add(position.toSimplePoint());
			}
			mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(GeometryUtils.hasZ(points), GeometryUtils.hasM(points));
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
