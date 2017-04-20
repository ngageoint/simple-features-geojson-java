package mil.nga.sf.wkb;

import mil.nga.sf.Geometry;

/**
 * The root of the geometry type hierarchy
 * 
 * @author osbornb
 */
public class Utils {


	/**
	 * Get the Well-Known Binary code
	 * 
	 * @return Well-Known Binary code
	 */
	static public int getWkbCode(Geometry geometry) {
		int code = geometry.getGeometryType().getCode();
		if (geometry.hasZ()) {
			code += 1000;
		}
		if (geometry.hasM()) {
			code += 2000;
		}
		return code;
	}

}
