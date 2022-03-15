# Read me

Repro script for bug reported in https://github.com/aws/aws-sdk-java-v2/issues/3080

## Running instructions

```shell
cd demo
./gradlew :installdist && build/install/demo/bin/demo
```

## Observations

Observe stacktrace in console logs

```
[sdk-async-response-0-3] WARN software.amazon.awssdk.metrics.publishers.cloudwatch - Failed while publishing some or all AWS SDK client-side metrics to CloudWatch.
java.util.concurrent.CompletionException: software.amazon.awssdk.services.cloudwatch.model.InvalidParameterValueException: The collection MetricData.member.1.Values must not have a size greater than 150. (Service: CloudWatch, Status Code: 400, Request ID: 51f0a1fc-4ea9-42db-9fa7-7b2215174cef)
        at software.amazon.awssdk.utils.CompletableFutureUtils.errorAsCompletionException(CompletableFutureUtils.java:62)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncExecutionFailureExceptionReportingStage.lambda$execute$0(AsyncExecutionFailureExceptionReportingStage.java:51)
        at java.base/java.util.concurrent.CompletableFuture.uniHandle(CompletableFuture.java:934)
        at java.base/java.util.concurrent.CompletableFuture$UniHandle.tryFire(CompletableFuture.java:911)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2162)
        at software.amazon.awssdk.utils.CompletableFutureUtils.lambda$forwardExceptionTo$0(CompletableFutureUtils.java:76)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2162)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncRetryableStage$RetryingExecutor.maybeAttemptExecute(AsyncRetryableStage.java:103)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncRetryableStage$RetryingExecutor.maybeRetryExecute(AsyncRetryableStage.java:181)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.AsyncRetryableStage$RetryingExecutor.lambda$attemptExecute$1(AsyncRetryableStage.java:167)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.MakeAsyncHttpRequestStage.lambda$null$0(MakeAsyncHttpRequestStage.java:105)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at software.amazon.awssdk.core.internal.http.pipeline.stages.MakeAsyncHttpRequestStage.lambda$executeHttpRequest$3(MakeAsyncHttpRequestStage.java:163)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture$Completion.run(CompletableFuture.java:482)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: software.amazon.awssdk.services.cloudwatch.model.InvalidParameterValueException: The collection MetricData.member.1.Values must not have a size greater than 150. (Service: CloudWatch, Status Code: 400, Request ID: 51f0a1fc-4ea9-42db-9fa7-7b2215174cef)
        at software.amazon.awssdk.services.cloudwatch.model.InvalidParameterValueException$BuilderImpl.build(InvalidParameterValueException.java:118)
        at software.amazon.awssdk.services.cloudwatch.model.InvalidParameterValueException$BuilderImpl.build(InvalidParameterValueException.java:78)
        at software.amazon.awssdk.protocols.query.internal.unmarshall.AwsXmlErrorUnmarshaller.unmarshall(AwsXmlErrorUnmarshaller.java:99)
        at software.amazon.awssdk.protocols.query.unmarshall.AwsXmlErrorProtocolUnmarshaller.handle(AwsXmlErrorProtocolUnmarshaller.java:102)
        at software.amazon.awssdk.protocols.query.unmarshall.AwsXmlErrorProtocolUnmarshaller.handle(AwsXmlErrorProtocolUnmarshaller.java:82)
        at software.amazon.awssdk.core.http.MetricCollectingHttpResponseHandler.lambda$handle$0(MetricCollectingHttpResponseHandler.java:52)
        at software.amazon.awssdk.core.internal.util.MetricUtils.measureDurationUnsafe(MetricUtils.java:64)
        at software.amazon.awssdk.core.http.MetricCollectingHttpResponseHandler.handle(MetricCollectingHttpResponseHandler.java:52)
        at software.amazon.awssdk.core.internal.http.async.AsyncResponseHandler.lambda$prepare$0(AsyncResponseHandler.java:89)
        at java.base/java.util.concurrent.CompletableFuture$UniCompose.tryFire(CompletableFuture.java:1150)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at software.amazon.awssdk.core.internal.http.async.AsyncResponseHandler$BaosSubscriber.onComplete(AsyncResponseHandler.java:132)
        at software.amazon.awssdk.http.nio.netty.internal.ResponseHandler$DataCountingPublisher$1.onComplete(ResponseHandler.java:513)
        at software.amazon.awssdk.http.nio.netty.internal.ResponseHandler.runAndLogError(ResponseHandler.java:249)
        at software.amazon.awssdk.http.nio.netty.internal.ResponseHandler.access$600(ResponseHandler.java:74)
        at software.amazon.awssdk.http.nio.netty.internal.ResponseHandler$PublisherAdapter$1.onComplete(ResponseHandler.java:370)
        at software.amazon.awssdk.http.nio.netty.internal.nrs.HandlerPublisher.publishMessage(HandlerPublisher.java:402)
        at software.amazon.awssdk.http.nio.netty.internal.nrs.HandlerPublisher.flushBuffer(HandlerPublisher.java:338)
        at software.amazon.awssdk.http.nio.netty.internal.nrs.HandlerPublisher.receivedDemand(HandlerPublisher.java:291)
        at software.amazon.awssdk.http.nio.netty.internal.nrs.HandlerPublisher.access$200(HandlerPublisher.java:61)
        at software.amazon.awssdk.http.nio.netty.internal.nrs.HandlerPublisher$ChannelSubscription$1.run(HandlerPublisher.java:495)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:164)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:469)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:500)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:986)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        ... 1 more
```