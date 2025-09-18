package com.unla.grupoF.service;
import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.0)",
    comments = "Source: donacion.proto")
public final class DonacionServiceGrpc {

  private DonacionServiceGrpc() {}

  public static final String SERVICE_NAME = "DonacionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO,
      DonacionOuterClass.DonacionDTO> getCreateDonacionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDonacion",
      requestType = DonacionOuterClass.DonacionDTO.class,
      responseType = DonacionOuterClass.DonacionDTO.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO,
      DonacionOuterClass.DonacionDTO> getCreateDonacionMethod() {
    io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO, DonacionOuterClass.DonacionDTO> getCreateDonacionMethod;
    if ((getCreateDonacionMethod = DonacionServiceGrpc.getCreateDonacionMethod) == null) {
      synchronized (DonacionServiceGrpc.class) {
        if ((getCreateDonacionMethod = DonacionServiceGrpc.getCreateDonacionMethod) == null) {
          DonacionServiceGrpc.getCreateDonacionMethod = getCreateDonacionMethod =
              io.grpc.MethodDescriptor.<DonacionOuterClass.DonacionDTO, DonacionOuterClass.DonacionDTO>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDonacion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionDTO.getDefaultInstance()))
              .setSchemaDescriptor(new DonacionServiceMethodDescriptorSupplier("CreateDonacion"))
              .build();
        }
      }
    }
    return getCreateDonacionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest,
      DonacionOuterClass.Donacion> getGetDonacionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDonacion",
      requestType = DonacionOuterClass.DonacionIdRequest.class,
      responseType = DonacionOuterClass.Donacion.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest,
      DonacionOuterClass.Donacion> getGetDonacionMethod() {
    io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest, DonacionOuterClass.Donacion> getGetDonacionMethod;
    if ((getGetDonacionMethod = DonacionServiceGrpc.getGetDonacionMethod) == null) {
      synchronized (DonacionServiceGrpc.class) {
        if ((getGetDonacionMethod = DonacionServiceGrpc.getGetDonacionMethod) == null) {
          DonacionServiceGrpc.getGetDonacionMethod = getGetDonacionMethod =
              io.grpc.MethodDescriptor.<DonacionOuterClass.DonacionIdRequest, DonacionOuterClass.Donacion>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDonacion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.Donacion.getDefaultInstance()))
              .setSchemaDescriptor(new DonacionServiceMethodDescriptorSupplier("GetDonacion"))
              .build();
        }
      }
    }
    return getGetDonacionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO,
      DonacionOuterClass.DonacionDTO> getUpdateDonacionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateDonacion",
      requestType = DonacionOuterClass.DonacionDTO.class,
      responseType = DonacionOuterClass.DonacionDTO.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO,
      DonacionOuterClass.DonacionDTO> getUpdateDonacionMethod() {
    io.grpc.MethodDescriptor<DonacionOuterClass.DonacionDTO, DonacionOuterClass.DonacionDTO> getUpdateDonacionMethod;
    if ((getUpdateDonacionMethod = DonacionServiceGrpc.getUpdateDonacionMethod) == null) {
      synchronized (DonacionServiceGrpc.class) {
        if ((getUpdateDonacionMethod = DonacionServiceGrpc.getUpdateDonacionMethod) == null) {
          DonacionServiceGrpc.getUpdateDonacionMethod = getUpdateDonacionMethod =
              io.grpc.MethodDescriptor.<DonacionOuterClass.DonacionDTO, DonacionOuterClass.DonacionDTO>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateDonacion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionDTO.getDefaultInstance()))
              .setSchemaDescriptor(new DonacionServiceMethodDescriptorSupplier("UpdateDonacion"))
              .build();
        }
      }
    }
    return getUpdateDonacionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest,
      com.google.protobuf.Empty> getDeleteDonacionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteDonacion",
      requestType = DonacionOuterClass.DonacionIdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest,
      com.google.protobuf.Empty> getDeleteDonacionMethod() {
    io.grpc.MethodDescriptor<DonacionOuterClass.DonacionIdRequest, com.google.protobuf.Empty> getDeleteDonacionMethod;
    if ((getDeleteDonacionMethod = DonacionServiceGrpc.getDeleteDonacionMethod) == null) {
      synchronized (DonacionServiceGrpc.class) {
        if ((getDeleteDonacionMethod = DonacionServiceGrpc.getDeleteDonacionMethod) == null) {
          DonacionServiceGrpc.getDeleteDonacionMethod = getDeleteDonacionMethod =
              io.grpc.MethodDescriptor.<DonacionOuterClass.DonacionIdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteDonacion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DonacionServiceMethodDescriptorSupplier("DeleteDonacion"))
              .build();
        }
      }
    }
    return getDeleteDonacionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      DonacionOuterClass.DonacionListResponse> getListDonacionesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDonaciones",
      requestType = com.google.protobuf.Empty.class,
      responseType = DonacionOuterClass.DonacionListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      DonacionOuterClass.DonacionListResponse> getListDonacionesMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, DonacionOuterClass.DonacionListResponse> getListDonacionesMethod;
    if ((getListDonacionesMethod = DonacionServiceGrpc.getListDonacionesMethod) == null) {
      synchronized (DonacionServiceGrpc.class) {
        if ((getListDonacionesMethod = DonacionServiceGrpc.getListDonacionesMethod) == null) {
          DonacionServiceGrpc.getListDonacionesMethod = getListDonacionesMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, DonacionOuterClass.DonacionListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDonaciones"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DonacionOuterClass.DonacionListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DonacionServiceMethodDescriptorSupplier("ListDonaciones"))
              .build();
        }
      }
    }
    return getListDonacionesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DonacionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonacionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonacionServiceStub>() {
        @java.lang.Override
        public DonacionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonacionServiceStub(channel, callOptions);
        }
      };
    return DonacionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DonacionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonacionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonacionServiceBlockingStub>() {
        @java.lang.Override
        public DonacionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonacionServiceBlockingStub(channel, callOptions);
        }
      };
    return DonacionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DonacionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonacionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonacionServiceFutureStub>() {
        @java.lang.Override
        public DonacionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonacionServiceFutureStub(channel, callOptions);
        }
      };
    return DonacionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DonacionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createDonacion(DonacionOuterClass.DonacionDTO request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDonacionMethod(), responseObserver);
    }

    /**
     */
    public void getDonacion(DonacionOuterClass.DonacionIdRequest request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.Donacion> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDonacionMethod(), responseObserver);
    }

    /**
     */
    public void updateDonacion(DonacionOuterClass.DonacionDTO request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateDonacionMethod(), responseObserver);
    }

    /**
     */
    public void deleteDonacion(DonacionOuterClass.DonacionIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteDonacionMethod(), responseObserver);
    }

    /**
     */
    public void listDonaciones(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListDonacionesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateDonacionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DonacionOuterClass.DonacionDTO,
                DonacionOuterClass.DonacionDTO>(
                  this, METHODID_CREATE_DONACION)))
          .addMethod(
            getGetDonacionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DonacionOuterClass.DonacionIdRequest,
                DonacionOuterClass.Donacion>(
                  this, METHODID_GET_DONACION)))
          .addMethod(
            getUpdateDonacionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DonacionOuterClass.DonacionDTO,
                DonacionOuterClass.DonacionDTO>(
                  this, METHODID_UPDATE_DONACION)))
          .addMethod(
            getDeleteDonacionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DonacionOuterClass.DonacionIdRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_DELETE_DONACION)))
          .addMethod(
            getListDonacionesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                DonacionOuterClass.DonacionListResponse>(
                  this, METHODID_LIST_DONACIONES)))
          .build();
    }
  }

  /**
   */
  public static final class DonacionServiceStub extends io.grpc.stub.AbstractAsyncStub<DonacionServiceStub> {
    private DonacionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonacionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonacionServiceStub(channel, callOptions);
    }

    /**
     */
    public void createDonacion(DonacionOuterClass.DonacionDTO request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDonacionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDonacion(DonacionOuterClass.DonacionIdRequest request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.Donacion> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDonacionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateDonacion(DonacionOuterClass.DonacionDTO request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateDonacionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteDonacion(DonacionOuterClass.DonacionIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteDonacionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listDonaciones(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListDonacionesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DonacionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DonacionServiceBlockingStub> {
    private DonacionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonacionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonacionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public DonacionOuterClass.DonacionDTO createDonacion(DonacionOuterClass.DonacionDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDonacionMethod(), getCallOptions(), request);
    }

    /**
     */
    public DonacionOuterClass.Donacion getDonacion(DonacionOuterClass.DonacionIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDonacionMethod(), getCallOptions(), request);
    }

    /**
     */
    public DonacionOuterClass.DonacionDTO updateDonacion(DonacionOuterClass.DonacionDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateDonacionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteDonacion(DonacionOuterClass.DonacionIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteDonacionMethod(), getCallOptions(), request);
    }

    /**
     */
    public DonacionOuterClass.DonacionListResponse listDonaciones(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListDonacionesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DonacionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DonacionServiceFutureStub> {
    private DonacionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonacionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonacionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DonacionOuterClass.DonacionDTO> createDonacion(
        DonacionOuterClass.DonacionDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDonacionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DonacionOuterClass.Donacion> getDonacion(
        DonacionOuterClass.DonacionIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDonacionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DonacionOuterClass.DonacionDTO> updateDonacion(
        DonacionOuterClass.DonacionDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateDonacionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteDonacion(
        DonacionOuterClass.DonacionIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteDonacionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DonacionOuterClass.DonacionListResponse> listDonaciones(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListDonacionesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_DONACION = 0;
  private static final int METHODID_GET_DONACION = 1;
  private static final int METHODID_UPDATE_DONACION = 2;
  private static final int METHODID_DELETE_DONACION = 3;
  private static final int METHODID_LIST_DONACIONES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DonacionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DonacionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_DONACION:
          serviceImpl.createDonacion((DonacionOuterClass.DonacionDTO) request,
              (io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO>) responseObserver);
          break;
        case METHODID_GET_DONACION:
          serviceImpl.getDonacion((DonacionOuterClass.DonacionIdRequest) request,
              (io.grpc.stub.StreamObserver<DonacionOuterClass.Donacion>) responseObserver);
          break;
        case METHODID_UPDATE_DONACION:
          serviceImpl.updateDonacion((DonacionOuterClass.DonacionDTO) request,
              (io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionDTO>) responseObserver);
          break;
        case METHODID_DELETE_DONACION:
          serviceImpl.deleteDonacion((DonacionOuterClass.DonacionIdRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_LIST_DONACIONES:
          serviceImpl.listDonaciones((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<DonacionOuterClass.DonacionListResponse>) responseObserver);
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

  private static abstract class DonacionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DonacionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return DonacionOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DonacionService");
    }
  }

  private static final class DonacionServiceFileDescriptorSupplier
      extends DonacionServiceBaseDescriptorSupplier {
    DonacionServiceFileDescriptorSupplier() {}
  }

  private static final class DonacionServiceMethodDescriptorSupplier
      extends DonacionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DonacionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DonacionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DonacionServiceFileDescriptorSupplier())
              .addMethod(getCreateDonacionMethod())
              .addMethod(getGetDonacionMethod())
              .addMethod(getUpdateDonacionMethod())
              .addMethod(getDeleteDonacionMethod())
              .addMethod(getListDonacionesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
