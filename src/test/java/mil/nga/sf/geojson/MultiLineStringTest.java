package mil.nga.sf.geojson;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.Geometry;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.MultiLineString;

import org.junit.Test;

public class MultiLineStringTest {

	private static String MULTILINESTRING = "{\"type\":\"MultiLineString\",\"coordinates\":[[[100.0,10.0],[101.0,1.0],[101.0,10.0]]]}";
	private static String MULTILINESTRING_WITH_ALT = "{\"type\":\"MultiLineString\",\"coordinates\":[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0]]]}";
	private static String MULTILINESTRING_WITH_MULTIPLE = "{\"type\":\"MultiLineString\",\"coordinates\":[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0]]]}";

	@Test
	public void itShouldSerializeSFMultiLineString() throws Exception {
		List<mil.nga.sf.LineString> lineStrings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		points.add(new mil.nga.sf.Point(101d, 10d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		lineStrings.add(lineString);
		mil.nga.sf.MultiLineString mls = new mil.nga.sf.MultiLineString(
				lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING);
	}

	@Test
	public void itShouldSerializeSFMultiLineStringWithAltitude()
			throws Exception {
		List<mil.nga.sf.LineString> lineStrings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d, 5d));
		points.add(new mil.nga.sf.Point(101d, 1d, 10d));
		points.add(new mil.nga.sf.Point(101d, 10d, 15d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		lineStrings.add(lineString);
		mil.nga.sf.MultiLineString mls = new mil.nga.sf.MultiLineString(
				lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING_WITH_ALT);
	}

	@Test
	public void itShouldSerializeSFMultiLineStringWithMultiple()
			throws Exception {
		List<mil.nga.sf.LineString> lineStrings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(-100d, -50d));
		points.add(new mil.nga.sf.Point(100d, -50d));
		points.add(new mil.nga.sf.Point(1d, 50d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		lineStrings.add(lineString);
		points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(-50d, -25d));
		points.add(new mil.nga.sf.Point(50d, -25d));
		points.add(new mil.nga.sf.Point(-1d, 25d));
		lineString = new mil.nga.sf.LineString(points);
		lineStrings.add(lineString);
		mil.nga.sf.MultiLineString mls = new mil.nga.sf.MultiLineString(
				lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING_WITH_MULTIPLE);
	}

	@Test
	public void itShouldDeserializeMultiLineString() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTILINESTRING,
				GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString) value;
		Geometry simpleGeometry = gjMLS.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.MultiLineString);
		mil.nga.sf.MultiLineString mls = (mil.nga.sf.MultiLineString) simpleGeometry;
		List<mil.nga.sf.LineString> lineStrings = mls.getGeometries();
		assertTrue(lineStrings.size() == 1);
		mil.nga.sf.LineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Point> points = lineString.getPoints();
		TestUtils.assertPosition(100d, 10d, null, null, points.get(0));
		TestUtils.assertPosition(101d, 1d, null, null, points.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, points.get(2));
	}

	@Test
	public void itShouldDeserializeMultiLineStringWithAltitude()
			throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				MULTILINESTRING_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString) value;
		Geometry simpleGeometry = gjMLS.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.MultiLineString);
		mil.nga.sf.MultiLineString mls = (mil.nga.sf.MultiLineString) simpleGeometry;
		List<mil.nga.sf.LineString> lineStrings = mls.getGeometries();
		assertTrue(lineStrings.size() == 1);
		mil.nga.sf.LineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Point> points = lineString.getPoints();
		TestUtils.assertPosition(100d, 10d, 5d, null, points.get(0));
		TestUtils.assertPosition(101d, 1d, 10d, null, points.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, points.get(2));
	}

	@Test
	public void itShouldDeserializeMultiLineStringWithMultiple()
			throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(
				MULTILINESTRING_WITH_MULTIPLE, GeoJsonObject.class);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString) value;
		Geometry geometry = gjMLS.getGeometry();
		assertTrue(geometry instanceof mil.nga.sf.MultiLineString);
		mil.nga.sf.MultiLineString mls = (mil.nga.sf.MultiLineString) geometry;
		List<mil.nga.sf.LineString> lineStrings = mls.getGeometries();
		assertTrue(lineStrings.size() == 2);
		mil.nga.sf.LineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Point> points = lineString.getPoints();
		TestUtils.assertPosition(-100d, -50d, null, null, points.get(0));
		TestUtils.assertPosition(100d, -50d, null, null, points.get(1));
		TestUtils.assertPosition(1d, 50d, null, null, points.get(2));
		lineString = lineStrings.get(1);
		points = lineString.getPoints();
		TestUtils.assertPosition(-50d, -25d, null, null, points.get(0));
		TestUtils.assertPosition(50d, -25d, null, null, points.get(1));
		TestUtils.assertPosition(-1d, 25d, null, null, points.get(2));
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

		List<mil.nga.sf.LineString> lineStrings = new ArrayList<>();
		List<mil.nga.sf.Point> points = new ArrayList<>();
		points.add(new mil.nga.sf.Point(100d, 10d));
		points.add(new mil.nga.sf.Point(101d, 1d));
		points.add(new mil.nga.sf.Point(101d, 10d));
		mil.nga.sf.LineString lineString = new mil.nga.sf.LineString(points);
		lineStrings.add(lineString);
		mil.nga.sf.MultiLineString mls = new mil.nga.sf.MultiLineString(
				lineStrings);

		return mls;
	}

}
