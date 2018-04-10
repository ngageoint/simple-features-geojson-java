package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.GeometryType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Feature
 * 
 * @author yutzlejp
 */
@JsonIgnoreProperties({ "feature", "geometryType" })
public class Feature extends GeoJsonObject {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -2507073025031506871L;

	/**
	 * Simple feature
	 */
	private SimpleFeature feature = new SimpleFeature();

	/**
	 * Feature id
	 */
	private String id;

	/**
	 * Get the geometry
	 * 
	 * @return geometry
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Geometry getGeometry() {
		return GeometryFactory.toGeometry(feature.getGeometry());
	}

	/**
	 * Set the geometry
	 * 
	 * @param geometry
	 *            geometry object
	 */
	public void setGeometry(Geometry geometry) {
		if (geometry instanceof Point) {
			Point point = (Point) geometry;
			this.feature.setGeometry(point.getGeometry());
		} else{
			this.feature.setGeometry((geometry == null) ? null : geometry
					.getGeometry());
		}
	}

	/**
	 * Get the properties
	 * 
	 * @return properties map
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Map<String, Object> getProperties() {
		return feature.getProperties();
	}

	/**
	 * Set the properties
	 * 
	 * @param properties
	 *            properties map
	 */
	public void setProperties(Map<String, Object> properties) {
		feature.setProperties(properties);
	}

	/**
	 * Get the feature id
	 * 
	 * @return feature id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the feature id
	 * 
	 * @param id
	 *            feature id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the simple feature
	 * 
	 * @return simple feature
	 */
	public SimpleFeature getFeature() {
		return feature;
	}

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 */
	public GeometryType getGeometryType() {
		GeometryType geometryType = null;
		Geometry geometry = getGeometry();
		if (geometry != null) {
			mil.nga.sf.Geometry simpleGeometry = geometry.getGeometry();
			if (simpleGeometry != null) {
				geometryType = simpleGeometry.getGeometryType();
			}
		}
		return geometryType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "Feature";
	}

}
