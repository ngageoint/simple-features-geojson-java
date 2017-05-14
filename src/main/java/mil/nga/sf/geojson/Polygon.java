package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import mil.nga.sf.util.PositionUtils;

public class Polygon extends GeoJsonObject implements Geometry, Coordinates<List<Position>> {

	/**
	 * 
	 */
	private mil.nga.sf.SimplePolygon polygon;

	public Polygon() {
	}

	public Polygon(List<List<Position>> positions) {
		setCoordinates(positions);
	}
	
	public Polygon(mil.nga.sf.SimplePolygon input) {
		polygon = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<Position>> getCoordinates() {
		List<List<Position>> result = new ArrayList<List<Position>>();
		for(mil.nga.sf.SimpleLinearRing ring : polygon.getRings()){
			List<Position> positions = new ArrayList<Position>();
			for(mil.nga.sf.Position pos : ring.getPositions()){
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
		List<mil.nga.sf.SimpleLinearRing> rings = new ArrayList<mil.nga.sf.SimpleLinearRing>();
		for (List<Position> ringPositions : input) {
			List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
			for (Position position: ringPositions){
				positions.add(position);
			}
			mil.nga.sf.SimpleLinearRing ring = new mil.nga.sf.SimpleLinearRing(PositionUtils.hasZ(positions), PositionUtils.hasM(positions));
			ring.setPositions(positions);
			rings.add(ring);
		}
		if (polygon == null){
			polygon = new mil.nga.sf.SimplePolygon(rings);
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
