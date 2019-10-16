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
		ctxt.reportWrongTokenException(this, JsonToken.START_ARRAY,
				"Unexpected token when binding data into Position");
		return null;
	}

	/**
	 * Deserialize array
	 * 
	 * @param jp
	 *            json parser
	 * @param ctxt
	 *            context
	 * @return position
	 * @throws IOException upon error
	 * @throws JsonProcessingException upon error
	 */
	protected Position deserializeArray(JsonParser jp,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {

		Double x = extractDouble(jp, ctxt, false);
		Double y = extractDouble(jp, ctxt, false);
		Double z = extractDouble(jp, ctxt, true);

		List<Double> additionalElements = new ArrayList<>();
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
			if (!optional) {
				ctxt.reportWrongTokenException(this,
						JsonToken.VALUE_NUMBER_FLOAT,
						"Unexpected end-of-input when binding data into Coordinates");
			}
			return null;
		} else {
			switch (token) {
			case END_ARRAY:
				if (!optional) {
					ctxt.reportWrongTokenException(this,
							JsonToken.VALUE_NUMBER_FLOAT,
							"Unexpected end-of-input when binding data into Coordinates");
				}
				return null;
			case VALUE_NUMBER_FLOAT:
				return jp.getDoubleValue();
			case VALUE_NUMBER_INT:
				return Double.valueOf(jp.getLongValue());
			case VALUE_STRING:
				return jp.getValueAsDouble();
			default:
				ctxt.reportWrongTokenException(
						this,
						JsonToken.VALUE_NUMBER_FLOAT,
						"Unexpected token (%s) when binding data into LngLatAlt",
						token.name());
				return null;
			}
		}
	}

}
