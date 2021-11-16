package mil.nga.sf.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.MultiPoint;
import mil.nga.sf.geojson.Position;

import org.junit.Test;

public class MultiPointTest {

	private static String MULTIPOINT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}";
	private static String MULTIPOINT_WITH_ALT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0,-20.0],[101.0,1.0,-10.0]]}";

	@Test
	public void itShouldSerializeSFMultiPoint() throws Exception {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(points);
		TestUtils.compareAsNodes(multiPoint, MULTIPOINT);
	}

	@Test
	public void itShouldSerializeSFMultiPointWithAltitude() throws Exception {
		List<mil.nga.sf.Point> positions = new ArrayList<>();
		positions.add(new mil.nga.sf.Point(100d, 10d, -20d));
		positions.add(new mil.nga.sf.Point(101d, 1d, -10d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(positions);
		TestUtils.compareAsNodes(multiPoint, MULTIPOINT_WITH_ALT);
	}

	@Test
	public void itShouldDeserializeMultiPoint() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOINT,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint) value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
	}

	@Test
	public void itShouldDeserializeMultiPointWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				MULTIPOINT_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint) value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, -20d, null, positions.get(0));
		TestUtils.assertPosition(101d, 1d, -10d, null, positions.get(1));
	}

	@Test
	public void toGeometry() {
		TestUtils.toGeometry(getTestGeometry());
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

		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(points);

		return multiPoint;
	}

}
