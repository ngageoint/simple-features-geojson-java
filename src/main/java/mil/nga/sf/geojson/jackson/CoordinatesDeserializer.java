package mil.nga.sf.geojson.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mil.nga.sf.geojson.Position;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Coordinates Deserializer
 * 
 * @author yutzlejp
 */
public class CoordinatesDeserializer extends JsonDeserializer<Position> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if (jp.isExpectedStartArrayToken()) {
			return deserializeArray(jp, ctxt);
		}
		throw ctxt.mappingException(Position.class);
	}

	/**
	 * Deserialize array
	 * 
	 * @param jp
	 *            json parser
	 * @param ctxt
	 *            context
	 * @return position
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	protected Position deserializeArray(JsonParser jp,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {

		Double x = extractDouble(jp, ctxt, false);
		Double y = extractDouble(jp, ctxt, false);
		Double z = extractDouble(jp, ctxt, true);

		List<Double> additionalElements = new ArrayList<Double>();
		while (jp.hasCurrentToken()
				&& jp.getCurrentToken() != JsonToken.END_ARRAY) {
			Double element = extractDouble(jp, ctxt, true);
			if (!(element == null)) {
				additionalElements.add(element);
			}
		}

		Double[] aeArray = new Double[additionalElements.size()];

		return new Position(x, y, z, additionalElements.toArray(aeArray));
	}

	/**
	 * Extract double
	 * 
	 * @param jp
	 *            json parser
	 * @param ctxt
	 *            context
	 * @param optional
	 *            true if optional
	 * @return double value
	 * @throws JsonParseException
	 * @throws IOException
	 */
	private Double extractDouble(JsonParser jp, DeserializationContext ctxt,
			boolean optional) throws JsonParseException, IOException {
		JsonToken token = jp.nextToken();
		if (token == null) {
			if (optional)
				return null;
			else
				throw ctxt
						.mappingException("Unexpected end-of-input when binding data into Coordinates");
		} else {
			switch (token) {
			case END_ARRAY:
				if (optional)
					return null;
				else
					throw ctxt
							.mappingException("Unexpected end-of-input when binding data into Coordinates");
			case VALUE_NUMBER_FLOAT:
				return jp.getDoubleValue();
			case VALUE_NUMBER_INT:
				return Double.valueOf(jp.getLongValue());
			case VALUE_STRING:
				return jp.getValueAsDouble();
			default:
				throw ctxt.mappingException("Unexpected token (" + token.name()
						+ ") when binding data into LngLatAlt");
			}
		}
	}

}
