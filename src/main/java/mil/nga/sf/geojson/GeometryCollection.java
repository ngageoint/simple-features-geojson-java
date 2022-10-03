package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Geometry Collection
 * 
 * @author osbornb
 */
public class GeometryCollection extends Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * List of geometries
	 */
	private List<Geometry> geometries = null;

	/**
	 * Constructor
	 */
	public GeometryCollection() {

	}

	/**
	 * Constructor
	 * 
	 * @param geometries
	 *            list of geometries
	 */
	public GeometryCollection(List<Geometry> geometries) {
		this.geometries = geometries;
	}

	/**
	 * Constructor
	 * 
	 * @param geometryCollection
	 *            simple geometry collection
	 */
	public GeometryCollection(
			mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection) {
		setGeometryCollection(geometryCollection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeometryType getGeometryType() {
		return GeometryType.GEOMETRYCOLLECTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return getGeometryCollection();
	}

	/**
	 * Returns geometries as a GeoJSON Geometry list
	 * 
	 * @return the geometries
	 */
	public List<Geometry> getGeometries() {
		return geometries;
	}

	/**
	 * Sets the geometries from a GeoJSON Geometry list
	 * 
	 * @param geometries
	 *            list of geometries
	 * @since 3.0.0
	 */
	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

	/**
	 * Get the simple features geometry collection
	 * 
	 * @return geometry collection
	 * @since 3.0.0
	 */
	@JsonIgnore
	public mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> getGeometryCollection() {
		mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> simpleGeometryCollection = new mil.nga.sf.GeometryCollection<>();
		for (Geometry geometry : geometries) {
			simpleGeometryCollection.addGeometry(geometry.getGeometry());
		}
		return simpleGeometryCollection;
	}

	/**
	 * Set the simple features geometry collection
	 * 
	 * @param geometryCollection
	 *            geometry collection
	 * @since 3.0.0
	 */
	public void setGeometryCollection(
			mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection) {
		geometries = new ArrayList<>();
		for (mil.nga.sf.Geometry simpleGeometry : geometryCollection
				.getGeometries()) {
			geometries.add(FeatureConverter.toGeometry(simpleGeometry));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((geometries == null) ? 0 : geometries.hashCode());
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
		GeometryCollection other = (GeometryCollection) obj;
		if (geometries == null) {
			if (other.geometries != null)
				return false;
		} else if (!geometries.equals(other.geometries))
			return false;
		return true;
	}

}
