package mil.nga.sf.geojson.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mil.nga.sf.Geometry;
import mil.nga.sf.geojson.GeoJsonObject;
import mil.nga.sf.geojson.MultiLineString;
import mil.nga.sf.geojson.Position;

public class MultiLineStringTest {

	private static String MULTILINESTRING = "{\"type\":\"MultiLineString\",\"coordinates\":[[[100.0,10.0],[101.0,1.0],[101.0,10.0]]]}";
	private static String MULTILINESTRING_WITH_ALT = "{\"type\":\"MultiLineString\",\"coordinates\":[[[100.0,10.0,5.0],[101.0,1.0,10.0],[101.0,10.0,15.0]]]}";
	private static String MULTILINESTRING_WITH_MULTIPLE = "{\"type\":\"MultiLineString\",\"coordinates\":[[[-100.0,-50.0],[100.0,-50.0],[1.0,50.0]],[[-50.0,-25.0],[50.0,-25.0],[-1.0,25.0]]]}";

	@Test
	public void itShouldSerializeASFMLS() throws Exception {
		List<mil.nga.sf.SimpleLineString> lineStrings = new ArrayList<mil.nga.sf.SimpleLineString>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d));
		positions.add(new Position(101d, 1d));
		positions.add(new Position(101d, 10d));
		mil.nga.sf.SimpleLineString lineString = new mil.nga.sf.SimpleLineString(positions);
		lineStrings.add(lineString);
		mil.nga.sf.SimpleMultiLineString mls = new mil.nga.sf.SimpleMultiLineString(lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING);
	}

	@Test
	public void itShouldSerializeASFMLSWithAltitude() throws Exception {
		List<mil.nga.sf.SimpleLineString> lineStrings = new ArrayList<mil.nga.sf.SimpleLineString>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(100d, 10d,  5d));
		positions.add(new Position(101d,  1d, 10d));
		positions.add(new Position(101d, 10d, 15d));
		mil.nga.sf.SimpleLineString lineString = new mil.nga.sf.SimpleLineString(positions);
		lineStrings.add(lineString);
		mil.nga.sf.SimpleMultiLineString mls = new mil.nga.sf.SimpleMultiLineString(lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING_WITH_ALT);
	}

	@Test
	public void itShouldSerializeASFMLSWithMultiple() throws Exception {
		List<mil.nga.sf.SimpleLineString> lineStrings = new ArrayList<mil.nga.sf.SimpleLineString>();
		List<mil.nga.sf.Position> positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-100d, -50d));
		positions.add(new Position( 100d, -50d));
		positions.add(new Position(   1d,  50d));
		mil.nga.sf.SimpleLineString lineString = new mil.nga.sf.SimpleLineString(positions);
		lineStrings.add(lineString);
		positions = new ArrayList<mil.nga.sf.Position>();
		positions.add(new Position(-50d, -25d));
		positions.add(new Position( 50d, -25d));
		positions.add(new Position( -1d,  25d));
		lineString = new mil.nga.sf.SimpleLineString(positions);
		lineStrings.add(lineString);
		mil.nga.sf.SimpleMultiLineString mls = new mil.nga.sf.SimpleMultiLineString(lineStrings);
		TestUtils.compareAsNodes(mls, MULTILINESTRING_WITH_MULTIPLE);
	}

	@Test
	public void itShouldDeserializeAMLS() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTILINESTRING, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString)value;
		Geometry simpleGeometry = gjMLS.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiLineString);
		mil.nga.sf.SimpleMultiLineString mls = (mil.nga.sf.SimpleMultiLineString)simpleGeometry;
		List<mil.nga.sf.SimpleLineString> lineStrings = mls.getCurves();
		assertTrue(lineStrings.size() == 1);
		mil.nga.sf.SimpleLineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Position> positions = lineString.getPoints();
		TestUtils.assertPosition(100d, 10d, null, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, null, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, null, null, positions.get(2));
	}

	@Test
	public void itShouldDeserializeALineStringWithAltitude() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTILINESTRING_WITH_ALT, GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString)value;
		Geometry simpleGeometry = gjMLS.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiLineString);
		mil.nga.sf.SimpleMultiLineString mls = (mil.nga.sf.SimpleMultiLineString)simpleGeometry;
		List<mil.nga.sf.SimpleLineString> lineStrings = mls.getCurves();
		assertTrue(lineStrings.size() == 1);
		mil.nga.sf.SimpleLineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Position> positions = lineString.getPoints();
		TestUtils.assertPosition(100d, 10d,  5d, null, positions.get(0));
		TestUtils.assertPosition(101d,  1d, 10d, null, positions.get(1));
		TestUtils.assertPosition(101d, 10d, 15d, null, positions.get(2));
	}

	@Test
	public void itShouldDeserializeAMLSWithRings() throws Exception {
		GeoJsonObject value = TestUtils.getMapper().readValue(MULTILINESTRING_WITH_MULTIPLE, GeoJsonObject.class);
		assertTrue(value instanceof MultiLineString);
		MultiLineString gjMLS = (MultiLineString)value;
		Geometry simpleGeometry = gjMLS.getGeometry();
		assertTrue(simpleGeometry instanceof mil.nga.sf.SimpleMultiLineString);
		mil.nga.sf.SimpleMultiLineString mls = (mil.nga.sf.SimpleMultiLineString)simpleGeometry;
		List<mil.nga.sf.SimpleLineString> lineStrings = mls.getCurves();
		assertTrue(lineStrings.size() == 2);
		mil.nga.sf.SimpleLineString lineString = lineStrings.get(0);
		List<mil.nga.sf.Position> positions = lineString.getPoints();
		TestUtils.assertPosition(-100d, -50d, null, null, positions.get(0));
		TestUtils.assertPosition( 100d, -50d, null, null, positions.get(1));
		TestUtils.assertPosition(   1d,  50d, null, null, positions.get(2));
		lineString = lineStrings.get(1);
		positions = lineString.getPoints();
		TestUtils.assertPosition(-50d, -25d, null, null, positions.get(0));
		TestUtils.assertPosition( 50d, -25d, null, null, positions.get(1));
		TestUtils.assertPosition( -1d,  25d, null, null, positions.get(2));
	}
}
