package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import mil.nga.sf.util.PointUtils;

public class MultiPolygon extends GeoJsonObject implements Geometry, Coordinates<List<List<Position>>> {

	/**
	 * 
	 */
	private mil.nga.sf.SimpleMultiPolygon multiPolygon;

	public MultiPolygon() {
	}

	public MultiPolygon(List<List<List<Position>>> positions) {
		setCoordinates(positions);
	}
	
	public MultiPolygon(mil.nga.sf.SimpleMultiPolygon input) {
		multiPolygon = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<List<Position>>> getCoordinates() {
		List<List<List<Position>>> result = new ArrayList<List<List<Position>>>();
		for(mil.nga.sf.SimplePolygon polygon : multiPolygon.getPolygons()){
			List<List<Position>> polygonPositions = new ArrayList<List<Position>>();
			for(mil.nga.sf.SimpleLinearRing ring : polygon.getRings()){
				List<Position> positions = new ArrayList<Position>();
				for(mil.nga.sf.Position pos : ring.getPoints()){
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
		List<mil.nga.sf.SimplePolygon> polygons = new ArrayList<mil.nga.sf.SimplePolygon>();
		for (List<List<Position>> polygonPositions : input){
			List<mil.nga.sf.SimpleLinearRing> rings = new ArrayList<mil.nga.sf.SimpleLinearRing>();
			for (List<Position> ringPositions : polygonPositions) {
				List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
				for (Position position: ringPositions){
					positions.add(position);
				}
				mil.nga.sf.SimpleLinearRing ring = new mil.nga.sf.SimpleLinearRing(PointUtils.hasZ(positions), PointUtils.hasM(positions));
				ring.getPoints(positions);
				rings.add(ring);
			}
			mil.nga.sf.SimplePolygon polygon = new mil.nga.sf.SimplePolygon(rings);
			polygons.add(polygon);
		}
		if (multiPolygon == null){
			multiPolygon = new mil.nga.sf.SimpleMultiPolygon(polygons);
		} else {
			multiPolygon.setPolygons(polygons);
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
