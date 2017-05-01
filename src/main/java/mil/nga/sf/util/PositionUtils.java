package mil.nga.sf.util;

import java.util.List;

import mil.nga.sf.Position;

public class PositionUtils {
	
	public static <T> boolean hasZ(List<T> input){
		boolean result = false;
		for(T tobject : input){
			if (tobject instanceof Position){
				if (((Position)tobject).getZ() != null) {
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
			if (tobject instanceof Position){
				if (((Position)tobject).getM() != null) {
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
