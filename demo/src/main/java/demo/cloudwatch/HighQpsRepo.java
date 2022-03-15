package demo.cloudwatch;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.metrics.CoreMetric;
import software.amazon.awssdk.metrics.MetricCollector;
import software.amazon.awssdk.metrics.publishers.cloudwatch.CloudWatchMetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;

/**
 * Run!
 * ./gradlew :installdist && build/install/demo/bin/demo
 */
public class HighQpsRepo {

  private static final Logger logger = LoggerFactory.getLogger(HighQpsRepo.class);

  public static void main(String[] args) throws InterruptedException {
    logger.info("Connecting to AWS");

    CloudWatchAsyncClient cloudWatchAsyncClient =
        CloudWatchAsyncClient.builder().region(Region.of("us-east-2")).build();

    CloudWatchMetricPublisher metricPublisher = CloudWatchMetricPublisher.builder()
        .namespace("Test")
        .cloudWatchClient(cloudWatchAsyncClient)
        .detailedMetrics(CoreMetric.API_CALL_DURATION)
        .dimensions(CoreMetric.OPERATION_NAME)
        .uploadFrequency(Duration.ofSeconds(10))
        .build();

    logger.info("Creating lots of latency metrics");

    for (int i = 0; i < 1000; i++) {
      MetricCollector methodCollector = MetricCollector.create("RPC");
      methodCollector.reportMetric(CoreMetric.API_CALL_DURATION, Duration.ofMillis(i));
      methodCollector.reportMetric(CoreMetric.OPERATION_NAME, "YourRPC");
      metricPublisher.publish(methodCollector.collect());
      Thread.sleep(5);
    }

    logger.info("Done. Waiting for exception...");
    Thread.sleep(15 * 1000);
    logger.info("Shutdown");
  }
}
