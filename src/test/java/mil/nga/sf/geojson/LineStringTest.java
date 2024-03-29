package mil.nga.sf.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LineStringTest {

	@Test
	public void itShouldSerializeSFLineString() throws Exception {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		TestUtils.compareAsNodes(lineString,
				"{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0],[101.0,1.0]]}");
	}

	@Test
	public void itShouldSerializeSFLineStringWithAltitude() throws Exception {
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d, 15d));
		points.add(new mil.nga.sf.Point(101d, 1d, 11d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		TestUtils.compareAsNodes(lineString,
				"{\"type\":\"LineString\",\"coordinates\":[[100.0,10.0,15.0],[101.0,1.0,11.0]]}");
	}

	@Test
	public void itShouldDeserializeLineString() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"LineString\",\"coordinates\":[[100.0, 0.0],[101.0, 1.0]]}",
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof LineString);
		LineString lineString = (LineString) value;
		List<Position> positions = lineString.getCoordinates();
		assertEquals(2, positions.size());
		TestUtils.assertPosition(100d, 0d, null, null, positions.get(0));
		TestUtils.assertPosition(101d, 1.0d, null, null, positions.get(1));
	}

	@Test
	public void itShouldDeserializeLineStringWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				"{\"type\":\"LineString\",\"coordinates\":[[100.0, 10.0, -20.0],[101.0, 1.0, -10.0]]}",
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof LineString);
		LineString lineString = (LineString) value;
		List<Position> positions = lineString.getCoordinates();
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
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);

		return lineString;
	}

}
