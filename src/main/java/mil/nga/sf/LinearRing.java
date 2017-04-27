package mil.nga.sf;

import java.util.List;

/**
 * The point of LinearRing is to enforce the rule
 * that the begin position must equal the end position
 * 
 * @author JeffYutzler
 *
 */
public class LinearRing extends LineString {

	/**
	 * Main constructor
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	public LinearRing(boolean hasZ, boolean hasM) {
		super(hasZ, hasM);
	}

	/**
	 * Main constructor
	 * 
	 * @param hasZ
	 * @param hasM
	 */
	public LinearRing(Curve ls) {
		super(ls.hasZ(), ls.hasM());
		setPositions(ls.getPositions());
	}

	@Override
	public void setPositions(List<Position> positions){
		super.setPositions(positions);
		if (!positions.isEmpty()){
			if (!positions.get(0).equals(positions.get(positions.size() - 1))){
				addPosition(positions.get(0));
			}
		}
	}
	
	@Override
	public void addPosition(Position position) {
		List<Position> positions = getPositions();
		positions.add(positions.size(), position);
	}
}
