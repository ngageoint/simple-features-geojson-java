package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MultiPolygon extends Geometry<List<List<Position>>> {

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
		for(mil.nga.sf.Polygon polygon : multiPolygon.getPolygons()){
			List<List<Position>> polygonPositions = new ArrayList<List<Position>>();
			for(mil.nga.sf.LinearRing ring : polygon.getRings()){
				List<Position> positions = new ArrayList<Position>();
				for(mil.nga.sf.Position pos : ring.getPositions()){
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
			List<mil.nga.sf.LinearRing> rings = new ArrayList<mil.nga.sf.LinearRing>();
			for (List<Position> ringPositions : polygonPositions) {
				List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
				for (Position position: ringPositions){
					positions.add(position);
				}
				mil.nga.sf.LinearRing ring = new mil.nga.sf.LinearRing(Position.hasZ(positions), Position.hasM(positions));
				ring.setPositions(positions);
				rings.add(ring);
			}
			mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
			polygons.add(polygon);
		}
		if (multiPolygon == null){
			multiPolygon = new mil.nga.sf.MultiPolygon(polygons);
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
}
