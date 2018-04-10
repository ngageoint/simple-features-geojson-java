package mil.nga.sf.geojson;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * GeoJSON Object
 * 
 * @author yutzlejp
 */
@JsonTypeInfo(property = "type", use = Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({ @Type(Feature.class), @Type(Polygon.class),
		@Type(MultiPolygon.class), @Type(FeatureCollection.class),
		@Type(Point.class), @Type(MultiPoint.class),
		@Type(MultiLineString.class), @Type(LineString.class),
		@Type(GeometryCollection.class) })
@JsonInclude(Include.NON_NULL)
public abstract class GeoJsonObject implements Serializable {

	/**
	 * Serialization Version number
	 */
	private static final long serialVersionUID = 497455167530431479L;

	/**
	 * Coordinate Reference System
	 */
	private Crs crs;

	/**
	 * Bounding box
	 */
	private double[] bbox;

	/**
	 * Get the Coordinate Reference System
	 * 
	 * @return Coordinate Reference System
	 */
	public Crs getCrs() {
		return crs;
	}

	/**
	 * Set the Coordinate Reference System
	 * 
	 * @param crs
	 *            Coordinate Reference System
	 */
	public void setCrs(Crs crs) {
		this.crs = crs;
	}

	/**
	 * Get the bounding box
	 * 
	 * @return bounding box
	 */
	public double[] getBbox() {
		return bbox;
	}

	/**
	 * Set the bounding box
	 * 
	 * @param bbox
	 *            bounding box
	 */
	public void setBbox(double[] bbox) {
		this.bbox = bbox;
	}

	/**
	 * Get the GeoJSON object type
	 * 
	 * @return GeoJSON object type
	 */
	public abstract String getType();

}
