package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MultiPoint extends Geometry<Position> {

	/**
	 * 
	 */
	private mil.nga.sf.MultiPoint multiPoint;

	public MultiPoint() {
	}

	public MultiPoint(List<Position> positions) {
		setCoordinates(positions);
	}
	
	public MultiPoint(mil.nga.sf.MultiPoint input) {
		multiPoint = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<Position>();
		for(mil.nga.sf.Position pos : multiPoint.getPositions()){
			positions.add(new Position(pos));
		}
		return positions;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * @param input the list
	 */
	private void setCoordinates(List<Position> input) {
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		for (Position pos : input) {
			positions.add(pos);
		}
		if (multiPoint == null) {
			multiPoint = new mil.nga.sf.MultiPoint(positions);
		} else {
			multiPoint.setPositions(positions);
		}
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiPoint;
	}
}
