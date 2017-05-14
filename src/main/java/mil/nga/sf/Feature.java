package mil.nga.sf;

import java.util.Map;

public interface Feature {

	Geometry getGeometry();

	void setGeometry(Geometry geometry);

	Map<String, Object> getProperties();

	void setProperties(Map<String, Object> properties);

}