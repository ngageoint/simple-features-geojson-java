package mil.nga.sf.geojson.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

import org.junit.Test;

public class PointTest {

	@Test
	public void itShouldSerializeASFPoint() throws Exception {
		mil.nga.sf.Point simplePoint = new mil.nga.sf.Point(100d, 10d);
		TestUtils.compareAsNodes(simplePoint,
				"{\"type\":\"Point\",\"coordinates\":[100.0,10.0]}");
	}

	@Test
	public void itShouldDeserializeAPoint() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}",
				GeoJsonObject.class);
		TestCase.assertNotNull(value);
		TestCase.assertTrue(value instanceof Point);
		Point point = (Point) value;
		TestUtils.assertPoint(100d, 5d, null, point);
	}

	@Test
	public void itShouldDeserializeAPointWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123]}",
				GeoJsonObject.class);
		Point point = (Point) value;
		TestUtils.assertPoint(100d, 5d, 123d, point);
	}

	@Test
	public void itShouldSerializeAPointWithAltitude() throws Exception {
		mil.nga.sf.Point simplePoint = new mil.nga.sf.Point(100d, 10d, 256d);
		TestUtils.compareAsNodes(simplePoint,
				"{\"type\":\"Point\",\"coordinates\":[100.0,10.0,256.0]}");
	}

	@Test
	public void itShouldDeserializeAPointWithAdditionalAttributes()
			throws IOException {
		GeoJsonObject value = TestUtils
				.getMapper()
				.readValue(
						"{\"type\":\"Point\",\"coordinates\":[100.0,5.0,123,456,789.2]}",
						GeoJsonObject.class);
		Point point = (Point) value;
		TestUtils.assertPoint(100d, 5d, 123d,
				new ArrayList<>(Arrays.asList(456d, 789.2)), point);
	}

	@Test
	public void itShouldSerializeAPointWithAdditionalAttributes()
			throws IOException {
		Position position = new Position(100d, 0d, 256d, 345d, 678d);
		Point point = new Point(position);
		TestUtils
				.compareAsNodes(point,
						"{\"type\":\"Point\",\"coordinates\":[100.0,0.0,256.0,345.0,678.0]}");
	}

	@Test
	public void toMap() {
		TestUtils.toMap(getTestGeometry());
	}

	@Test
	public void toStringValue() {
		TestUtils.toStringValue(getTestGeometry());
	}

	private mil.nga.sf.Geometry getTestGeometry() {

		mil.nga.sf.Point point = new mil.nga.sf.Point(100d, 10d);

		return point;
	}

}
