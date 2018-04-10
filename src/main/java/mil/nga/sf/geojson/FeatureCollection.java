package mil.nga.sf.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import mil.nga.sf.GeometryType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Feature Collection
 * 
 * @author yutzlejp
 */
@JsonIgnoreProperties({ "propertiesMap" })
public class FeatureCollection extends GeoJsonObject implements
		Iterable<Feature> {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = -7676012376081292349L;

	/**
	 * Collection of features
	 */
	private Collection<Feature> features = new HashSet<Feature>();

	/**
	 * Get the features
	 * 
	 * @return collection of features
	 */
	public Collection<Feature> getFeatures() {
		return features;
	}

	/**
	 * Set the features
	 * 
	 * @param features
	 *            collection of features
	 */
	public void setFeatures(Collection<Feature> features) {
		this.features = features;
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
	public Map<String, String> getPropertiesMap() {
		Map<String, String> result = new HashMap<String, String>();
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
