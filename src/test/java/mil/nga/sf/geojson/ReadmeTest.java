package mil.nga.sf.geojson;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * README example tests
 * 
 * @author osbornb
 */
public class ReadmeTest {

	/**
	 * Geometry
	 */
	private static final mil.nga.sf.Geometry GEOMETRY = new mil.nga.sf.Point(
			1.0, 1.0);

	/**
	 * {@link #GEOMETRY} content
	 */
	private static final String CONTENT = "{\"type\":\"Point\",\"coordinates\":[1.0,1.0]}";

	/**
	 * Test read
	 * 
	 * @throws IOException
	 *             upon error
	 */
	@Test
	public void testRead() throws IOException {

		Geometry geometry = testRead(CONTENT);

		TestCase.assertEquals(GEOMETRY, geometry.getGeometry());

	}

	/**
	 * Test read
	 * 
	 * @param content
	 *            content
	 * @return geometry
	 * @throws IOException
	 *             upon error
	 */
	private Geometry testRead(String content) throws IOException {

		// String content = ...

		Geometry geometry = FeatureConverter.toGeometry(content);
		mil.nga.sf.Geometry simpleGeometry = geometry.getGeometry();

		/* Read as a generic GeoJSON object, Feature, or Feature Collection */
		// GeoJsonObject geoJsonObject =
		// FeatureConverter.toGeoJsonObject(content);
		// Feature feature = FeatureConverter.toFeature(content);
		// FeatureCollection featureCollection =
		// FeatureConverter.toFeatureCollection(content);

		return geometry;
	}

	/**
	 * Test write
	 * 
	 * @throws IOException
	 *             upon error
	 */
	@Test
	public void testWrite() throws IOException {

		String content = testWrite(GEOMETRY);

		TestCase.assertEquals(CONTENT, content);

	}

	/**
	 * Test write
	 * 
	 * @param geometry
	 *            geometry
	 * @return content
	 * @throws IOException
	 *             upon error
	 */
	private String testWrite(mil.nga.sf.Geometry geometry) throws IOException {

		// Geometry geometry = ...

		String content = FeatureConverter.toStringValue(geometry);

		Feature feature = FeatureConverter.toFeature(geometry);
		String featureContent = FeatureConverter.toStringValue(feature);

		FeatureCollection featureCollection = FeatureConverter
				.toFeatureCollection(geometry);
		String featureCollectionContent = FeatureConverter
				.toStringValue(featureCollection);

		Map<String, Object> contentMap = FeatureConverter.toMap(geometry);

		return content;
	}

}
