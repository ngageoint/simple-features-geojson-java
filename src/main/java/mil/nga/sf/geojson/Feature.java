package mil.nga.sf.geojson;

import java.util.Map;

import mil.nga.sf.GeometryType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Feature
 * 
 * @author yutzlejp
 */
@JsonPropertyOrder({ "type", "id", "geometry", "properties" })
public class Feature extends GeoJsonObject {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Simple feature
	 */
	@JsonIgnore
	private SimpleFeature feature = new SimpleFeature();

	/**
	 * Feature id
	 */
	private String id;

	/**
	 * Constructor
	 */
	public Feature() {
	}

	/**
	 * Constructor
	 * 
	 * @param geometry
	 *            geometry
	 */
	public Feature(Geometry geometry) {
		setGeometry(geometry);
	}

	/**
	 * Get the geometry
	 * 
	 * @return geometry
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Geometry getGeometry() {
		return GeometryConverter.toGeometry(feature.getGeometry());
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
		} else {
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
	 * Get the simple feature geometry
	 * 
	 * @return simple feature geometry
	 */
	@JsonIgnore
	public mil.nga.sf.Geometry getSimpleGeometry() {
		return feature.getGeometry();
	}

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 */
	@JsonIgnore
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
