public class WeatherDeserializationSchema extends AbstractDeserializationSchema<Weather> {
    private static final long serialVersionUUID = 1L;

    private transient ObjectMapper objectMapper;

    @Override
    public void open(InitializationContext context) {
        objectMapper = JsonMapper.builder().build().registerModule(new JavaTimeModule());
    }

    @Override
    public Weather deserialize(byte[] message) throws IOException {
        return objectMapper.readValue(message, Weather.class);
    }



}