package mil.nga.sf.geojson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class TwoDGeometry extends Geometry<Position> {

//	/**
//	 * Returns coordinates as a GeoJSON Position object
//	 * @return the coordinates
//	 */
//	@JsonInclude(JsonInclude.Include.ALWAYS)
//	public List<Position> getCoordinates() {
//		mil.nga.sf.Position pos = geometry.getPositions();
//		return new Position(pos.getX(), pos.getY(), pos.getZ(), additionalElements);
//	}
//
//	private void setCoordinates(List<Position> position) {
//		additionalElements = position.getAdditionalElements();
//		mil.nga.sf.Position pos = new mil.nga.sf.Position(position.getX(), 
//				position.getY(), 
//				position.getZ(), 
//				(additionalElements.length > 0) ? additionalElements[0] : null);
//		point = new mil.nga.sf.Point(pos);
//	}

}
