package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PointUtils;

public class MultiLineString extends GeometryCollection<LineString> {

	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiLineString(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTILINESTRING, hasZ, hasM);
	}

	/**
	 * Constructor, which uses contents of provided lineStrings
	 * to determine hasZ and hasM
	 * 
	 * @param lineStrings 
	 *        lineStrings
	 */
	public MultiLineString(List<LineString> lineStrings) {
		super(GeometryType.MULTILINESTRING, PointUtils.hasZ(lineStrings), PointUtils.hasM(lineStrings));
		setGeometries(lineStrings);
	}
}
