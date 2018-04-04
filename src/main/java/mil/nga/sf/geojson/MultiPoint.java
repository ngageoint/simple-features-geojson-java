package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MultiPoint extends GeoJsonObject implements Geometry, Coordinates<Position> {

	private static final long serialVersionUID = -3432834802398357952L;
	
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
		for(mil.nga.sf.Point point : multiPoint.getGeometries()){
			positions.add(new Position(point));
		}
		return positions;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * @param input the list
	 */
	private void setCoordinates(List<Position> input) {
		List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
		for (Position pos : input) {
			points.add(pos.toSimplePoint());
		}
		if (multiPoint == null) {
			multiPoint = new mil.nga.sf.MultiPoint(points);
		} else {
			multiPoint.setGeometries(points);
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

	@Override
	public String getType() {
		return "MultiPoint";
	}
}
