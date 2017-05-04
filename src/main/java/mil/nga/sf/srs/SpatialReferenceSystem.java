package mil.nga.sf.srs;

/**
 * Interface for a Spatial Reference System object. The coordinate reference 
 * system definitions it contains are used in projection operations.
 * 
 * @author jyutzler
 */
public interface SpatialReferenceSystem {


	/**
	 * Human readable name of this SRS
	 */


	/**
	 * Case-insensitive name of the defining organization e.g. EPSG or epsg
	 */

	/**
	 * Numeric ID of the Spatial Reference System assigned by the organization
	 */

	/**
	 * Well-known Text [32] Representation of the Spatial Reference System
	 */

	/**
	 * Human readable description of this SRS
	 */

	/**
	 * Well-known Text [34] Representation of the Spatial Reference System
	 */

	/**
	 * 
	 * @return Unique identifier for each Spatial Reference System
	 */
	public long getId();

	/**
	 * 
	 * @param id
	 */
	public void setId(long id);

	public String getSrsName();

	public void setSrsName(String srsName);

	public long getSrsId();

	public void setSrsId(long srsId);

	public String getOrganization();
	
	public void setOrganization(String organization);

	public long getOrganizationCoordsysId();

	public void setOrganizationCoordsysId(long organizationCoordsysId);

	public String getDefinition();

	public void setDefinition(String definition);

	public String getDescription();

	public void setDescription(String description);

	public String getDefinition_12_063();

	public void setDefinition_12_063(String definition_12_063);
}
