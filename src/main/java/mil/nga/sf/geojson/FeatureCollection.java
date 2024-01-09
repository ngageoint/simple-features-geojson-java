package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import mil.nga.sf.GeometryCollection;

/**
 * Feature Collection
 * 
 * @author yutzlejp
 */
@JsonPropertyOrder({ "type", "bbox", "features" })
public class FeatureCollection extends GeoJsonObject
		implements Iterable<Feature> {

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
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getSimpleGeometry() {
		mil.nga.sf.Geometry geometry = null;
		if (features != null && !features.isEmpty()) {
			GeometryCollection<mil.nga.sf.Geometry> geomCollection = new GeometryCollection<>();
			for (Feature feature : features) {
				mil.nga.sf.Geometry geom = feature.getSimpleGeometry();
				if (geom != null) {
					geomCollection.addGeometry(geom);
				}
			}
			if (!geomCollection.isEmpty()) {
				geometry = geomCollection;
			}
		}
		return geometry;
	}

	/**
	 * Get the geometry type
	 * 
	 * @return geometry type
	 * @since 3.0.0
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
		for (Feature feature : getFeatures()) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((features == null) ? 0 : features.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeatureCollection other = (FeatureCollection) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		return true;
	}

}
