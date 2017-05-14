package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LineString extends GeoJsonObject implements Geometry, Coordinates<Position> {

	/**
	 * 
	 */
	private mil.nga.sf.LineString lineString;

	public LineString() {
	}

	public LineString(List<Position> positions) {
		setCoordinates(positions);
	}

	public LineString(mil.nga.sf.LineString input) {
		lineString = input;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return lineString;
	}

	@Override
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<Position> getCoordinates() {
		List<Position> positions = new ArrayList<Position>();
		for(mil.nga.sf.Point point : lineString.getPoints()){
			positions.add(new Position(point));
		}
		return positions;
	}

	public void setCoordinates(List<Position> input) {
		List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
		for (Position pos : input) {
			points.add(new mil.nga.sf.Point(pos));
		}
		if (lineString == null) {
			lineString = new mil.nga.sf.LineString(points);
		} else {
			lineString.setPoints(points);
		}
	}

	@Override
	public String getType() {
		return "LineString";
	}
}
