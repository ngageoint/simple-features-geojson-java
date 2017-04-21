package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonIgnoreProperties({"feature"})
public class Feature extends GeoJsonObject {
	
	private static final long serialVersionUID = -2507073025031506871L;

	private mil.nga.sf.Feature feature = new mil.nga.sf.Feature();

	private String id;

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Geometry getGeometry() {
		return (Geometry)GeoJsonObjectFactory.createObject(feature.getGeometry());
	}

	public void setGeometry(Geometry geometry) {
		this.feature.setGeometry((geometry == null) ? null : geometry.getGeometry());
	}

	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Map<String, Object> getProperties() {
		return feature.getProperties();
	}

	public void setProperties(Map<String, Object> properties) {
		feature.setProperties(properties);
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

	public mil.nga.sf.Feature getFeature() {
		return feature;
	}
}
