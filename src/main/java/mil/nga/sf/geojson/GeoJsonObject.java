package mil.nga.sf.geojson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.io.Serializable;

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({ 
	@Type(Feature.class), 
//	@Type(Polygon.class), 
//	@Type(MultiPolygon.class), 
	@Type(FeatureCollection.class),
		@Type(Point.class)//, 
//		@Type(MultiPoint.class), 
//		@Type(MultiLineString.class), 
//		@Type(LineString.class),
//                @Type(GeometryCollection.class) 
	})
@JsonInclude(Include.NON_NULL)
public abstract class GeoJsonObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 497455167530431479L;
	/**
	 * 
	 */
	private Crs crs;
	private double[] bbox;

	public Crs getCrs() {
		return crs;
	}

	public void setCrs(Crs crs) {
		this.crs = crs;
	}

	public double[] getBbox() {
		return bbox;
	}

	public void setBbox(double[] bbox) {
		this.bbox = bbox;
	}


	public abstract <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor);
}
