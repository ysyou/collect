package com.clamos.io.collect.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.*;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class SparkService {
    @Value("${link.spark.master}")
    private String sparkMasteer;

    @Value("${link.kafka.bootStrapAddress}")
    private String bootStrapAddress;


    public void sparkConnection() throws Exception {
        System.setProperty("hadoop.home.dir", "C:\\Hadoop");
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("clamos").setMaster(sparkMasteer);

        JavaStreamingContext streamingContext = new JavaStreamingContext(
                sparkConf, Durations.seconds(5));

        HashMap<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", bootStrapAddress);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "clamos");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Arrays.asList("test");

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        streamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topics, kafkaParams));

//        JavaPairDStream<String, String> stringStringJavaPairDStream = messages.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
        stream.mapToPair((PairFunction<ConsumerRecord<String, String>, String, String>) record -> new Tuple2<String ,String>(record.key(),record.value()));
        log.info("프린트1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("프린트1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        stream.map(record -> record.value()).print();
        streamingContext.start();
        streamingContext.awaitTermination();



        /*------------------------------------------------------- 위에는 sparkDStreaming */
//        System.setProperty("hadoop.home.dir", "C:\\Hadoop");
//        SparkSession sparkSession = SparkSession.builder()
//                .master(sparkMasteer)
//                .appName("clamos")
//                .config("spark.jars", "file:///C://project//spark-sql-kafka-0-10_2.12-3.3.0.jar,file:///C://project//kafka-clients-3.3.0.jar")
//                .config("spark.executor.extraClassPath", "file:///C://project//spark-sql-kafka-0-10_2.12-3.3.0.jar,file:///C://project//kafka-clients-3.1.2.jar")
//                .config("spark.executor.extraLibrary", "file:///C://project//spark-sql-kafka-0-10_2.12-3.3.0.jar,file:///C://project//kafka-clients-3.1.2.jar")
//                .config("spark.driver.extraClassPath", "file:///C://project//spark-sql-kafka-0-10_2.12-3.3.0.jar,file:///C://project//kafka-clients-3.1.2.jar")
//                .config("spark.driver.extraLibraryPath", "file:///C://project//spark-sql-kafka-0-10_2.12-3.3.0.jar,file:///C://project//kafka-clients-3.1.2.jar")
//                .config("spark.jars.packages", "org.apache.spark:spark-sql-kafka-0-10_2.12:3.3.0")
//                .config("spark.jars.repositories", "https://mvnrepository.com/artifact/org.apache.spark/spark-sql-kafka-0-10_2.12/3.3.0")
//                .getOrCreate();
//
//
//        Dataset<Row> load = sparkSession.readStream().
//                format("kafka")
//                .option("kafka.bootstrap.servers", bootStrapAddress)
//                .option("topic", "test")
//                .load();
//        load.printSchema();
//
//        StreamingQuery query = load.writeStream()
//                .outputMode("complete")
//                .format("console")
//                .option("truncate", false)
//                .start();
//        query.awaitTermination();
    }
}
