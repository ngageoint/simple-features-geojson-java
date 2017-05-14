package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.Point;

public class PointUtils {
	
	public static <T> boolean hasZ(List<T> input){
		boolean result = false;
		for(T tobject : input){
			if (tobject instanceof Point){
				if (((Point)tobject).getZ() != null) {
					result = true;
					break;
				}
			} else if (tobject instanceof List<?>) {
				if (hasZ((List<?>)tobject)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public static <T> boolean hasM(List<T> input){
		boolean result = false;
		for(T tobject : input){
			if (tobject instanceof Point){
				if (((Point)tobject).getM() != null) {
					result = true;
					break;
				}
			} else if (tobject instanceof List<?>) {
				if (hasM((List<?>)tobject)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

}
