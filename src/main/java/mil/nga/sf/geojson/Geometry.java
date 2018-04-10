package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Geometry interface
 * 
 * @author yutzlejp
 */
@JsonIgnoreProperties({ "geometry" })
public interface Geometry {

	/**
	 * Get the simple geometry
	 * 
	 * @return simple geometry
	 */
	public mil.nga.sf.Geometry getGeometry();

}
