package com.example.chat;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: RobotService.proto")
public final class RobotServiceGrpc {

  private RobotServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.chat.RobotService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getAskForPermissionToVisitMechanicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "askForPermissionToVisitMechanic",
      requestType = com.example.chat.RobotServiceOuterClass.RobotRequest.class,
      responseType = com.example.chat.RobotServiceOuterClass.RobotResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getAskForPermissionToVisitMechanicMethod() {
    io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse> getAskForPermissionToVisitMechanicMethod;
    if ((getAskForPermissionToVisitMechanicMethod = RobotServiceGrpc.getAskForPermissionToVisitMechanicMethod) == null) {
      synchronized (RobotServiceGrpc.class) {
        if ((getAskForPermissionToVisitMechanicMethod = RobotServiceGrpc.getAskForPermissionToVisitMechanicMethod) == null) {
          RobotServiceGrpc.getAskForPermissionToVisitMechanicMethod = getAskForPermissionToVisitMechanicMethod =
              io.grpc.MethodDescriptor.<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "askForPermissionToVisitMechanic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RobotServiceMethodDescriptorSupplier("askForPermissionToVisitMechanic"))
              .build();
        }
      }
    }
    return getAskForPermissionToVisitMechanicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.Robot,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getRegisterRobotInNetworkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerRobotInNetwork",
      requestType = com.example.chat.RobotServiceOuterClass.Robot.class,
      responseType = com.example.chat.RobotServiceOuterClass.RobotResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.Robot,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getRegisterRobotInNetworkMethod() {
    io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.Robot, com.example.chat.RobotServiceOuterClass.RobotResponse> getRegisterRobotInNetworkMethod;
    if ((getRegisterRobotInNetworkMethod = RobotServiceGrpc.getRegisterRobotInNetworkMethod) == null) {
      synchronized (RobotServiceGrpc.class) {
        if ((getRegisterRobotInNetworkMethod = RobotServiceGrpc.getRegisterRobotInNetworkMethod) == null) {
          RobotServiceGrpc.getRegisterRobotInNetworkMethod = getRegisterRobotInNetworkMethod =
              io.grpc.MethodDescriptor.<com.example.chat.RobotServiceOuterClass.Robot, com.example.chat.RobotServiceOuterClass.RobotResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registerRobotInNetwork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.Robot.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RobotServiceMethodDescriptorSupplier("registerRobotInNetwork"))
              .build();
        }
      }
    }
    return getRegisterRobotInNetworkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getFinishMechanicVisitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "finishMechanicVisit",
      requestType = com.example.chat.RobotServiceOuterClass.RobotRequest.class,
      responseType = com.example.chat.RobotServiceOuterClass.RobotResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getFinishMechanicVisitMethod() {
    io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse> getFinishMechanicVisitMethod;
    if ((getFinishMechanicVisitMethod = RobotServiceGrpc.getFinishMechanicVisitMethod) == null) {
      synchronized (RobotServiceGrpc.class) {
        if ((getFinishMechanicVisitMethod = RobotServiceGrpc.getFinishMechanicVisitMethod) == null) {
          RobotServiceGrpc.getFinishMechanicVisitMethod = getFinishMechanicVisitMethod =
              io.grpc.MethodDescriptor.<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "finishMechanicVisit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RobotServiceMethodDescriptorSupplier("finishMechanicVisit"))
              .build();
        }
      }
    }
    return getFinishMechanicVisitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getDeleteRobotFromNetworkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteRobotFromNetwork",
      requestType = com.example.chat.RobotServiceOuterClass.RobotRequest.class,
      responseType = com.example.chat.RobotServiceOuterClass.RobotResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest,
      com.example.chat.RobotServiceOuterClass.RobotResponse> getDeleteRobotFromNetworkMethod() {
    io.grpc.MethodDescriptor<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse> getDeleteRobotFromNetworkMethod;
    if ((getDeleteRobotFromNetworkMethod = RobotServiceGrpc.getDeleteRobotFromNetworkMethod) == null) {
      synchronized (RobotServiceGrpc.class) {
        if ((getDeleteRobotFromNetworkMethod = RobotServiceGrpc.getDeleteRobotFromNetworkMethod) == null) {
          RobotServiceGrpc.getDeleteRobotFromNetworkMethod = getDeleteRobotFromNetworkMethod =
              io.grpc.MethodDescriptor.<com.example.chat.RobotServiceOuterClass.RobotRequest, com.example.chat.RobotServiceOuterClass.RobotResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteRobotFromNetwork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.chat.RobotServiceOuterClass.RobotResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RobotServiceMethodDescriptorSupplier("deleteRobotFromNetwork"))
              .build();
        }
      }
    }
    return getDeleteRobotFromNetworkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RobotServiceStub newStub(io.grpc.Channel channel) {
    return new RobotServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RobotServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RobotServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RobotServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RobotServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RobotServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void askForPermissionToVisitMechanic(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAskForPermissionToVisitMechanicMethod(), responseObserver);
    }

    /**
     */
    public void registerRobotInNetwork(com.example.chat.RobotServiceOuterClass.Robot request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterRobotInNetworkMethod(), responseObserver);
    }

    /**
     */
    public void finishMechanicVisit(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFinishMechanicVisitMethod(), responseObserver);
    }

    /**
     */
    public void deleteRobotFromNetwork(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteRobotFromNetworkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAskForPermissionToVisitMechanicMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.chat.RobotServiceOuterClass.RobotRequest,
                com.example.chat.RobotServiceOuterClass.RobotResponse>(
                  this, METHODID_ASK_FOR_PERMISSION_TO_VISIT_MECHANIC)))
          .addMethod(
            getRegisterRobotInNetworkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.chat.RobotServiceOuterClass.Robot,
                com.example.chat.RobotServiceOuterClass.RobotResponse>(
                  this, METHODID_REGISTER_ROBOT_IN_NETWORK)))
          .addMethod(
            getFinishMechanicVisitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.chat.RobotServiceOuterClass.RobotRequest,
                com.example.chat.RobotServiceOuterClass.RobotResponse>(
                  this, METHODID_FINISH_MECHANIC_VISIT)))
          .addMethod(
            getDeleteRobotFromNetworkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.chat.RobotServiceOuterClass.RobotRequest,
                com.example.chat.RobotServiceOuterClass.RobotResponse>(
                  this, METHODID_DELETE_ROBOT_FROM_NETWORK)))
          .build();
    }
  }

  /**
   */
  public static final class RobotServiceStub extends io.grpc.stub.AbstractStub<RobotServiceStub> {
    private RobotServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RobotServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RobotServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RobotServiceStub(channel, callOptions);
    }

    /**
     */
    public void askForPermissionToVisitMechanic(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAskForPermissionToVisitMechanicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerRobotInNetwork(com.example.chat.RobotServiceOuterClass.Robot request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterRobotInNetworkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void finishMechanicVisit(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFinishMechanicVisitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteRobotFromNetwork(com.example.chat.RobotServiceOuterClass.RobotRequest request,
        io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteRobotFromNetworkMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RobotServiceBlockingStub extends io.grpc.stub.AbstractStub<RobotServiceBlockingStub> {
    private RobotServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RobotServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RobotServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RobotServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.chat.RobotServiceOuterClass.RobotResponse askForPermissionToVisitMechanic(com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return blockingUnaryCall(
          getChannel(), getAskForPermissionToVisitMechanicMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.chat.RobotServiceOuterClass.RobotResponse registerRobotInNetwork(com.example.chat.RobotServiceOuterClass.Robot request) {
      return blockingUnaryCall(
          getChannel(), getRegisterRobotInNetworkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.chat.RobotServiceOuterClass.RobotResponse finishMechanicVisit(com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return blockingUnaryCall(
          getChannel(), getFinishMechanicVisitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.chat.RobotServiceOuterClass.RobotResponse deleteRobotFromNetwork(com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteRobotFromNetworkMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RobotServiceFutureStub extends io.grpc.stub.AbstractStub<RobotServiceFutureStub> {
    private RobotServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RobotServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RobotServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RobotServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.chat.RobotServiceOuterClass.RobotResponse> askForPermissionToVisitMechanic(
        com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAskForPermissionToVisitMechanicMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.chat.RobotServiceOuterClass.RobotResponse> registerRobotInNetwork(
        com.example.chat.RobotServiceOuterClass.Robot request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterRobotInNetworkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.chat.RobotServiceOuterClass.RobotResponse> finishMechanicVisit(
        com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFinishMechanicVisitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.chat.RobotServiceOuterClass.RobotResponse> deleteRobotFromNetwork(
        com.example.chat.RobotServiceOuterClass.RobotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteRobotFromNetworkMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ASK_FOR_PERMISSION_TO_VISIT_MECHANIC = 0;
  private static final int METHODID_REGISTER_ROBOT_IN_NETWORK = 1;
  private static final int METHODID_FINISH_MECHANIC_VISIT = 2;
  private static final int METHODID_DELETE_ROBOT_FROM_NETWORK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RobotServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RobotServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ASK_FOR_PERMISSION_TO_VISIT_MECHANIC:
          serviceImpl.askForPermissionToVisitMechanic((com.example.chat.RobotServiceOuterClass.RobotRequest) request,
              (io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse>) responseObserver);
          break;
        case METHODID_REGISTER_ROBOT_IN_NETWORK:
          serviceImpl.registerRobotInNetwork((com.example.chat.RobotServiceOuterClass.Robot) request,
              (io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse>) responseObserver);
          break;
        case METHODID_FINISH_MECHANIC_VISIT:
          serviceImpl.finishMechanicVisit((com.example.chat.RobotServiceOuterClass.RobotRequest) request,
              (io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse>) responseObserver);
          break;
        case METHODID_DELETE_ROBOT_FROM_NETWORK:
          serviceImpl.deleteRobotFromNetwork((com.example.chat.RobotServiceOuterClass.RobotRequest) request,
              (io.grpc.stub.StreamObserver<com.example.chat.RobotServiceOuterClass.RobotResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RobotServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RobotServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.chat.RobotServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RobotService");
    }
  }

  private static final class RobotServiceFileDescriptorSupplier
      extends RobotServiceBaseDescriptorSupplier {
    RobotServiceFileDescriptorSupplier() {}
  }

  private static final class RobotServiceMethodDescriptorSupplier
      extends RobotServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RobotServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RobotServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RobotServiceFileDescriptorSupplier())
              .addMethod(getAskForPermissionToVisitMechanicMethod())
              .addMethod(getRegisterRobotInNetworkMethod())
              .addMethod(getFinishMechanicVisitMethod())
              .addMethod(getDeleteRobotFromNetworkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
