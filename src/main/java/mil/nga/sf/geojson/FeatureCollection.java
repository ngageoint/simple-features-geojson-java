package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mil.nga.sf.GeometryType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Feature Collection
 * 
 * @author yutzlejp
 */
@JsonPropertyOrder({ "type", "features" })
public class FeatureCollection extends GeoJsonObject implements
		Iterable<Feature> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Collection of features
	 */
	private List<Feature> features = new ArrayList<>();

	/**
	 * Constructor
	 */
	public FeatureCollection() {
	}

	/**
	 * Constructor
	 * 
	 * @param feature
	 *            feature
	 */
	public FeatureCollection(Feature feature) {
		addFeature(feature);
	}

	/**
	 * Constructor
	 * 
	 * @param features
	 *            collection of features
	 */
	public FeatureCollection(Collection<Feature> features) {
		setFeatures(features);
	}

	/**
	 * Get the features
	 * 
	 * @return collection of features
	 */
	public List<Feature> getFeatures() {
		return features;
	}

	/**
	 * Set the features
	 * 
	 * @param features
	 *            collection of features
	 */
	public void setFeatures(Collection<Feature> features) {
		this.features = new ArrayList<>(features);
	}

	/**
	 * Add a feature
	 * 
	 * @param feature
	 *            feature
	 */
	public void addFeature(Feature feature) {
		features.add(feature);
	}

	/**
	 * Add the features
	 * 
	 * @param features
	 *            collection of features
	 */
	public void addFeatures(Collection<Feature> features) {
		this.features.addAll(features);
	}

	/**
	 * Get the number of features
	 * 
	 * @return feature count
	 */
	public int numFeatures() {
		return features.size();
	}

	/**
	 * Get the feature at the index
	 * 
	 * @param i
	 *            index
	 * @return feature
	 */
	public Feature getFeature(int i) {
		return features.get(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Feature> iterator() {
		return features.iterator();
	}

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 */
	@JsonIgnore
	public GeometryType getGeometryType() {
		GeometryType result = null;

		for (Feature feature : features) {
			GeometryType gt = feature.getGeometryType();
			if (result == null) {
				result = gt;
			} else if (gt != result) {
				result = GeometryType.GEOMETRY;
				break;
			}
		}

		return result;
	}

	/**
	 * Get the properties map
	 * 
	 * @return properties map
	 */
	@JsonIgnore
	public Map<String, String> getPropertiesMap() {
		Map<String, String> result = new HashMap<>();
		for (final Feature feature : getFeatures()) {
			Map<String, Object> properties = feature.getProperties();
			for (final String property : properties.keySet()) {
				if (!result.containsKey(property)) {
					result.put(property, properties.get(property).getClass()
							.getSimpleName());
				}
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "FeatureCollection";
	}

}
