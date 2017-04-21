package mil.nga.sf.geojson.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import mil.nga.sf.geojson.Position;

public class CoordinatesSerializer extends JsonSerializer<Position>
{

    @Override
    public void serialize(Position value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        jgen.writeStartArray();
        jgen.writeNumber(value.getLongitude());
        jgen.writeNumber(value.getLatitude());
        if (value.hasAltitude())
        {
            jgen.writeNumber(value.getAltitude());

            for (double d : value.getAdditionalElements())
            {
                jgen.writeNumber(d);
            }
        }
        jgen.writeEndArray();
    }
}
