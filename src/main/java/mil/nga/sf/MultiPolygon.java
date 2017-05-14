package mil.nga.sf;

import java.util.List;

import mil.nga.sf.util.PointUtils;

public class MultiPolygon extends GeometryCollection<Polygon> {
	
	/**
	 * Constructor
	 * 
	 * @param hasZ
	 *            has z
	 * @param hasM
	 *            has m
	 */
	public MultiPolygon(boolean hasZ, boolean hasM) {
		super(GeometryType.MULTIPOLYGON, hasZ, hasM);
	}

	/**
	 * Constructor
	 * 
	 * @param polygons
	 *            A list of polygons, which will be used to determine hasZ and hasM
	 */
	public MultiPolygon(List<Polygon> polygons) {
		super(GeometryType.MULTIPOLYGON, PointUtils.hasZ(polygons), PointUtils.hasM(polygons));
		setGeometries(polygons);
	}
}
