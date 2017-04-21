package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"geometry"})
public abstract class Geometry extends GeoJsonObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2761575868252640610L;

	abstract public mil.nga.sf.Geometry getGeometry();
}
