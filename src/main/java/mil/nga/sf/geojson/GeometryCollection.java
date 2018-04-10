package mil.nga.sf.geojson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Geometry Collection
 * 
 * @author osbornb
 */
public class GeometryCollection extends GeoJsonObject implements Geometry {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 5522918226735882967L;

	/**
	 * Simple geometry collection
	 */
	private mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection;

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
		setGeometries(geometries);
	}

	/**
	 * Constructor
	 * 
	 * @param geometryCollection
	 *            simple geometry collection
	 */
	public GeometryCollection(
			mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> geometryCollection) {
		this.geometryCollection = geometryCollection;
	}

	/**
	 * Returns geometries as a GeoJSON Geometry list
	 * 
	 * @return the geometries
	 */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	public List<GeoJsonObject> getGeometries() {
		List<GeoJsonObject> geometries = new ArrayList<>();
		for (mil.nga.sf.Geometry simpleGeometry : geometryCollection
				.getGeometries()) {
			geometries.add(GeoJsonObjectFactory
					.toGeoJsonObject(simpleGeometry));
		}
		return geometries;
	}

	/**
	 * Sets the coordinates from a GeoJSON Position list
	 * 
	 * @param positions
	 *            list of positions
	 */
	private void setGeometries(List<Geometry> geometries) {
		mil.nga.sf.GeometryCollection<mil.nga.sf.Geometry> simpleGeometryCollection = new mil.nga.sf.GeometryCollection<>();
		for (Geometry geometry : geometries) {
			simpleGeometryCollection.addGeometry(geometry.getGeometry());
		}
		geometryCollection = simpleGeometryCollection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public mil.nga.sf.Geometry getGeometry() {
		return geometryCollection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return "GeometryCollection";
	}

}
