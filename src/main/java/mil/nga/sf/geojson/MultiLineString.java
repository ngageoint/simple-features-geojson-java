package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import mil.nga.sf.util.PointUtils;

public class MultiLineString  extends GeoJsonObject implements Geometry, Coordinates<List<Position>> {

	/**
	 * 
	 */
	private mil.nga.sf.SimpleMultiLineString multiLineString;

	public MultiLineString() {
	}

	public MultiLineString(List<List<Position>> positions) {
		setCoordinates(positions);
	}
	
	public MultiLineString(mil.nga.sf.SimpleMultiLineString input) {
		multiLineString = input;
	}

	/**
	 * Returns coordinates as a GeoJSON Position list
	 * @return the coordinates
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<List<Position>> getCoordinates() {
		List<List<Position>> result = new ArrayList<List<Position>>();
		for(mil.nga.sf.SimpleLineString lineString : multiLineString.getCurves()){
			List<Position> positions = new ArrayList<Position>();
			for(mil.nga.sf.Position pos : lineString.getPoints()){
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
		List<mil.nga.sf.Curve> lineStrings = new ArrayList<mil.nga.sf.Curve>();
		for (List<Position> lineStringPositions : input) {
			List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
			for (Position position: lineStringPositions){
				positions.add(position);
			}
			mil.nga.sf.SimpleLinearRing ring = new mil.nga.sf.SimpleLinearRing(PointUtils.hasZ(positions), PointUtils.hasM(positions));
			ring.getPoints(positions);
			lineStrings.add(ring);
		}
		if (multiLineString == null){
			multiLineString = new mil.nga.sf.SimpleMultiLineString(lineStrings);
		} else {
			multiLineString.setCurves(lineStrings);
		}
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return multiLineString;
	}

	@Override
	public String getType() {
		return "MultiLineString";
	}
}
