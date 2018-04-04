package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"geometry"})
public interface Geometry {

	public mil.nga.sf.Geometry getGeometry();
}
