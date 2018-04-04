package mil.nga.sf.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mil.nga.sf.GeometryType;

@JsonIgnoreProperties({"propertiesMap"})
public class FeatureCollection extends GeoJsonObject implements Iterable<Feature> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7676012376081292349L;
	
	private Collection<Feature> features = new HashSet<Feature>();

	public Collection<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Collection<Feature> features) {
		this.features = features;
	}

	@Override
	public Iterator<Feature> iterator() {
		return features.iterator();
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}
	
	
	public GeometryType getGeometryType() {
		GeometryType result = null;
		
		for(Feature feature : features){
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
    public Map<String, String> getPropertiesMap(){
    	Map<String, String> result = new HashMap<String, String>();
    	for (final Feature feature : getFeatures()){
    		Map<String, Object> properties = feature.getProperties();
    		for (final String property : properties.keySet()){
    			if (!result.containsKey(property)) {
    				result.put(property, properties.get(property).getClass().getSimpleName());
    			}
    		}
    	}
    	return result;
    }

	@Override
	public String getType() {
		return "FeatureCollection";
	}
	
}
