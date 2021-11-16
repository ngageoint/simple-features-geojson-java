package mil.nga.sf.geojson;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mil.nga.sf.LineString;
import mil.nga.sf.LinearRing;

public class PolygonTest {

	private static String POLYGON = "{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0],[101.0,1.0],[101.0,10.0],[100.0,10.0]]]}";
	private static String POLYGON_WITH_ALT = "{\"type\":\"Polygon\",\"coordinates\":[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0],[100.0,10.0,5.0]]]}";
	private static String POLYGON_WITH_RINGS = "{\"type\":\"Polygon\",\"coordinates\":[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0],[-100.0,-50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0],[-50.0,-25.0]]]}";

	@Test
	public void itShouldSerializeSFPolygon() throws Exception {
		List<LineString> rings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		points.add(new mil.nga.sf.Point(101d, 10d));
		LinearRing ring = new LinearRing(points);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON);
	}

	@Test
	public void itShouldSerializeSFPolygonWithAltitude() throws Exception {
		List<LineString> rings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d, 5d));
		points.add(new mil.nga.sf.Point(101d, 1d, 10d));
		points.add(new mil.nga.sf.Point(101d, 10d, 15d));
		LinearRing ring = new LinearRing(points);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON_WITH_ALT);
	}

	@Test
	public void itShouldSerializeSFPolygonWithRings() throws Exception {
		List<LineString> rings = new ArrayList<>();
		List<mil.nga.sf.Point> positions = new ArrayList<>();
		positions.add(new mil.nga.sf.Point(-100d, -50d));
		positions.add(new mil.nga.sf.Point(100d, -50d));
		positions.add(new mil.nga.sf.Point(1d, 50d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		positions = new ArrayList<>();
		positions.add(new mil.nga.sf.Point(-50d, -25d));
		positions.add(new mil.nga.sf.Point(50d, -25d));
		positions.add(new mil.nga.sf.Point(-1d, 25d));
		ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);
		TestUtils.compareAsNodes(polygon, POLYGON_WITH_RINGS);
	}

	@Test
	public void itShouldDeserializePolygon() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(POLYGON,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon) value;
		mil.nga.sf.Geometry geometry = gjPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.Polygon polygon = (mil.nga.sf.Polygon) geometry;
		List<LineString> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		LineString ring = rings.get(0);
		List<mil.nga.sf.Point> points = ring.getPoints();
		TestUtils.assertPosition(100d, 10d, null, null, points.get(0));
		TestUtils.assertPosition(101d, 1d, null, null, points.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, points.get(2));
		TestUtils.assertPosition(100d, 10d, null, null, points.get(3));
	}

	@Test
	public void itShouldDeserializePolygonWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(POLYGON_WITH_ALT,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon) value;
		mil.nga.sf.Geometry geometry = gjPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.Polygon polygon = (mil.nga.sf.Polygon) geometry;
		List<LineString> rings = polygon.getRings();
		assertTrue(rings.size() == 1);
		LineString ring = rings.get(0);
		List<mil.nga.sf.Point> points = ring.getPoints();
		TestUtils.assertPosition(100d, 10d, 5d, null, points.get(0));
		TestUtils.assertPosition(101d, 1d, 10d, null, points.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, points.get(2));
		TestUtils.assertPosition(100d, 10d, 5d, null, points.get(3));
	}

	@Test
	public void itShouldDeserializePolygonWithRings() throws Exception {
		GeoJsonObject value = TestUtils.getMapper()
				.readValue(POLYGON_WITH_RINGS, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Polygon);
		Polygon gjPolygon = (Polygon) value;
		mil.nga.sf.Geometry geometry = gjPolygon.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.Polygon);
		mil.nga.sf.Polygon polygon = (mil.nga.sf.Polygon) geometry;
		List<LineString> rings = polygon.getRings();
		assertTrue(rings.size() == 2);
		LineString ring = rings.get(0);
		List<mil.nga.sf.Point> points = ring.getPoints();
		TestUtils.assertPosition(-100d, -50d, null, null, points.get(0));
		TestUtils.assertPosition(100d, -50d, null, null, points.get(1));
		TestUtils.assertPosition(1d, 50d, null, null, points.get(2));
		TestUtils.assertPosition(-100d, -50d, null, null, points.get(3));
		ring = rings.get(1);
		points = ring.getPoints();
		TestUtils.assertPosition(-50d, -25d, null, null, points.get(0));
		TestUtils.assertPosition(50d, -25d, null, null, points.get(1));
		TestUtils.assertPosition(-1d, 25d, null, null, points.get(2));
		TestUtils.assertPosition(-50d, -25d, null, null, points.get(3));
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

		List<LineString> rings = new ArrayList<>();
		List<mil.nga.sf.Point> positions = new ArrayList<>();
		positions.add(new mil.nga.sf.Point(-100d, -50d));
		positions.add(new mil.nga.sf.Point(100d, -50d));
		positions.add(new mil.nga.sf.Point(1d, 50d));
		LinearRing ring = new LinearRing(positions);
		rings.add(ring);
		positions = new ArrayList<>();
		positions.add(new mil.nga.sf.Point(-50d, -25d));
		positions.add(new mil.nga.sf.Point(50d, -25d));
		positions.add(new mil.nga.sf.Point(-1d, 25d));
		ring = new LinearRing(positions);
		rings.add(ring);
		mil.nga.sf.Polygon polygon = new mil.nga.sf.Polygon(rings);

		return polygon;
	}

}
