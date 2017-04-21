package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public class Feature extends GeoJsonObject {
	
	private static final long serialVersionUID = -2507073025031506871L;

	private mil.nga.sf.Feature feature = new mil.nga.sf.Feature();

	@JsonInclude(JsonInclude.Include.ALWAYS)
	private GeoJsonObject geometry;
	private String id;

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Map<String, Object> getProperties() {
		return feature.getProperties();
	}

	public void setProperties(Map<String, Object> properties) {
		feature.setProperties(properties);
	}

	public GeoJsonObject getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoJsonObject geometry) {
		this.geometry = geometry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}
}
