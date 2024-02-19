import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public Class Main {

    static final String BROKERS = 'kafka:9092';

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<Weather> source = KafkaSource.<Weather>builder()
                                        .setBootstrapServers(BROKERS)
                                        .setProperty('partition.discovery.interval.ms', '1000')
                                        .setTopics('weather')
                                        .setGroupId('groupId_9191')
                                        .setStartingOffsets(OffsetsInitializer.earliest())
                                        .setValueOnlyDeserializer(new WeatherDeserializationSchema())
                                        .build();

        DataStreamSource<Weather> kafka = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");

        System.out.println('Kafka source created');
    }
}
