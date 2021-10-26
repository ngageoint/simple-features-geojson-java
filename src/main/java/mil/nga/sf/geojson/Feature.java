package mil.nga.sf.geojson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Feature
 * 
 * @author yutzlejp
 */
@JsonPropertyOrder({ "type", "bbox", "id", "geometry", "properties" })
public class Feature extends GeoJsonObject {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Feature id
	 */
	private String id;

	/**
	 * Geometry
	 */
	private Geometry geometry = null;

	/**
	 * Properties map
	 */
	private Map<String, Object> properties = new HashMap<>();

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
		this.geometry = geometry;
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
	 * Get the geometry
	 * 
	 * @return geometry
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Set the geometry
	 * 
	 * @param geometry
	 *            geometry object
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * Get the properties
	 * 
	 * @return properties map
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Set the properties
	 * 
	 * @param properties
	 *            properties map
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * Get the simple feature geometry
	 * 
	 * @return simple feature geometry
	 */
	@JsonIgnore
	public mil.nga.sf.Geometry getSimpleGeometry() {
		return geometry != null ? geometry.getGeometry() : null;
	}

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 * @since 3.0.0
	 */
	@JsonIgnore
	public GeometryType getGeometryType() {
		return geometry != null ? geometry.getGeometryType() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "Feature";
	}

}
