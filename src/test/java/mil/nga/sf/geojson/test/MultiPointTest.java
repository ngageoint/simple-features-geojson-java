package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.MultiPoint;
import mil.nga.sf.geojson.Position;

public class MultiPointTest {

	private static String MULTIPOINT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}";
	private static String MULTIPOINT_WITH_ALT = "{\"type\":\"MultiPoint\",\"coordinates\":[[100.0,10.0,-20.0],[101.0,1.0,-10.0]]}";


	@Test
	public void itShouldSerializeASFMultiPoint() throws Exception {
		List<mil.nga.sf.Point> points = new ArrayList<mil.nga.sf.Point>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(points);
		TestUtils.compareAsNodes(multiPoint, MULTIPOINT);
	}

	@Test
	public void itShouldSerializeASFMultiPointWithAltitude() throws Exception {
		List<mil.nga.sf.Point> positions = new ArrayList<mil.nga.sf.Point>();
		positions.add(new mil.nga.sf.Point(100d, 10d, -20d));
		positions.add(new mil.nga.sf.Point(101d, 1d, -10d));
		mil.nga.sf.MultiPoint multiPoint = new mil.nga.sf.MultiPoint(positions);
		TestUtils.compareAsNodes(multiPoint, MULTIPOINT_WITH_ALT);
	}

	@Test
	public void itShouldDeserializeAMultiPoint() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOINT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint)value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
	}

	@Test
	public void itShouldDeserializeAMultiPointWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTIPOINT_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiPoint);
		MultiPoint multiPoint = (MultiPoint)value;
		List<Position> positions = multiPoint.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 10d, -20d, null, positions.get(0));
		TestUtils.assertPosition(101d, 1d, -10d, null, positions.get(1));
	}
}
