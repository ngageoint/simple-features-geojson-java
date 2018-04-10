package mil.nga.sf.geojson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Coordinates interface
 * 
 * @author yutzlejp
 */
@JsonIgnoreProperties({ "geometry" })
public interface Coordinates<T> {

	/**
	 * Get the coordinates
	 * 
	 * @return list of coordinates
	 */
	public abstract List<T> getCoordinates();

}
