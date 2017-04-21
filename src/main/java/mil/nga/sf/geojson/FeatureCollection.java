package mil.nga.sf.geojson;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

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
}
