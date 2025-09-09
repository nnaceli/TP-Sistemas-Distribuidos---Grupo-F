package com.unla.grupoF.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class UsuarioServiceGrpc {

  private UsuarioServiceGrpc() {}

  public static final String SERVICE_NAME = "UsuarioService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.UsuarioDTO> getCreateUsuarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUsuario",
      requestType = UsuarioOuterClass.UsuarioDTO.class,
      responseType = UsuarioOuterClass.UsuarioDTO.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.UsuarioDTO> getCreateUsuarioMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.UsuarioDTO> getCreateUsuarioMethod;
    if ((getCreateUsuarioMethod = UsuarioServiceGrpc.getCreateUsuarioMethod) == null) {
      synchronized (UsuarioServiceGrpc.class) {
        if ((getCreateUsuarioMethod = UsuarioServiceGrpc.getCreateUsuarioMethod) == null) {
          UsuarioServiceGrpc.getCreateUsuarioMethod = getCreateUsuarioMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.UsuarioDTO>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUsuario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioDTO.getDefaultInstance()))
              .setSchemaDescriptor(new UsuarioServiceMethodDescriptorSupplier("CreateUsuario"))
              .build();
        }
      }
    }
    return getCreateUsuarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.Username,
      UsuarioOuterClass.Usuario> getGetUsuarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUsuario",
      requestType = UsuarioOuterClass.Username.class,
      responseType = UsuarioOuterClass.Usuario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.Username,
      UsuarioOuterClass.Usuario> getGetUsuarioMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.Username, UsuarioOuterClass.Usuario> getGetUsuarioMethod;
    if ((getGetUsuarioMethod = UsuarioServiceGrpc.getGetUsuarioMethod) == null) {
      synchronized (UsuarioServiceGrpc.class) {
        if ((getGetUsuarioMethod = UsuarioServiceGrpc.getGetUsuarioMethod) == null) {
          UsuarioServiceGrpc.getGetUsuarioMethod = getGetUsuarioMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.Username, UsuarioOuterClass.Usuario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUsuario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.Usuario.getDefaultInstance()))
              .setSchemaDescriptor(new UsuarioServiceMethodDescriptorSupplier("GetUsuario"))
              .build();
        }
      }
    }
    return getGetUsuarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.UsuarioDTO> getUpdateUsuarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUsuario",
      requestType = UsuarioOuterClass.UsuarioDTO.class,
      responseType = UsuarioOuterClass.UsuarioDTO.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.UsuarioDTO> getUpdateUsuarioMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.UsuarioDTO> getUpdateUsuarioMethod;
    if ((getUpdateUsuarioMethod = UsuarioServiceGrpc.getUpdateUsuarioMethod) == null) {
      synchronized (UsuarioServiceGrpc.class) {
        if ((getUpdateUsuarioMethod = UsuarioServiceGrpc.getUpdateUsuarioMethod) == null) {
          UsuarioServiceGrpc.getUpdateUsuarioMethod = getUpdateUsuarioMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.UsuarioDTO>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateUsuario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioDTO.getDefaultInstance()))
              .setSchemaDescriptor(new UsuarioServiceMethodDescriptorSupplier("UpdateUsuario"))
              .build();
        }
      }
    }
    return getUpdateUsuarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.Username> getDeleteUsuarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUsuario",
      requestType = UsuarioOuterClass.UsuarioDTO.class,
      responseType = UsuarioOuterClass.Username.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO,
      UsuarioOuterClass.Username> getDeleteUsuarioMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.Username> getDeleteUsuarioMethod;
    if ((getDeleteUsuarioMethod = UsuarioServiceGrpc.getDeleteUsuarioMethod) == null) {
      synchronized (UsuarioServiceGrpc.class) {
        if ((getDeleteUsuarioMethod = UsuarioServiceGrpc.getDeleteUsuarioMethod) == null) {
          UsuarioServiceGrpc.getDeleteUsuarioMethod = getDeleteUsuarioMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.UsuarioDTO, UsuarioOuterClass.Username>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUsuario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.Username.getDefaultInstance()))
              .setSchemaDescriptor(new UsuarioServiceMethodDescriptorSupplier("DeleteUsuario"))
              .build();
        }
      }
    }
    return getDeleteUsuarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.Empty,
      UsuarioOuterClass.UsuarioListResponse> getListUsuariosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListUsuarios",
      requestType = UsuarioOuterClass.Empty.class,
      responseType = UsuarioOuterClass.UsuarioListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.Empty,
      UsuarioOuterClass.UsuarioListResponse> getListUsuariosMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.Empty, UsuarioOuterClass.UsuarioListResponse> getListUsuariosMethod;
    if ((getListUsuariosMethod = UsuarioServiceGrpc.getListUsuariosMethod) == null) {
      synchronized (UsuarioServiceGrpc.class) {
        if ((getListUsuariosMethod = UsuarioServiceGrpc.getListUsuariosMethod) == null) {
          UsuarioServiceGrpc.getListUsuariosMethod = getListUsuariosMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.Empty, UsuarioOuterClass.UsuarioListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListUsuarios"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.UsuarioListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UsuarioServiceMethodDescriptorSupplier("ListUsuarios"))
              .build();
        }
      }
    }
    return getListUsuariosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UsuarioServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceStub>() {
        @Override
        public UsuarioServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsuarioServiceStub(channel, callOptions);
        }
      };
    return UsuarioServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static UsuarioServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceBlockingV2Stub>() {
        @Override
        public UsuarioServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsuarioServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return UsuarioServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UsuarioServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceBlockingStub>() {
        @Override
        public UsuarioServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsuarioServiceBlockingStub(channel, callOptions);
        }
      };
    return UsuarioServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UsuarioServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsuarioServiceFutureStub>() {
        @Override
        public UsuarioServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsuarioServiceFutureStub(channel, callOptions);
        }
      };
    return UsuarioServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createUsuario(UsuarioOuterClass.UsuarioDTO request,
                               io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUsuarioMethod(), responseObserver);
    }

    /**
     */
    default void getUsuario(UsuarioOuterClass.Username request,
                            io.grpc.stub.StreamObserver<UsuarioOuterClass.Usuario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUsuarioMethod(), responseObserver);
    }

    /**
     */
    default void updateUsuario(UsuarioOuterClass.UsuarioDTO request,
                               io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateUsuarioMethod(), responseObserver);
    }

    /**
     */
    default void deleteUsuario(UsuarioOuterClass.UsuarioDTO request,
                               io.grpc.stub.StreamObserver<UsuarioOuterClass.Username> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUsuarioMethod(), responseObserver);
    }

    /**
     */
    default void listUsuarios(UsuarioOuterClass.Empty request,
                              io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListUsuariosMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UsuarioService.
   */
  public static abstract class UsuarioServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return UsuarioServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UsuarioService.
   */
  public static final class UsuarioServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UsuarioServiceStub> {
    private UsuarioServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsuarioServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsuarioServiceStub(channel, callOptions);
    }

    /**
     */
    public void createUsuario(UsuarioOuterClass.UsuarioDTO request,
                              io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUsuarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUsuario(UsuarioOuterClass.Username request,
                           io.grpc.stub.StreamObserver<UsuarioOuterClass.Usuario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUsuarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUsuario(UsuarioOuterClass.UsuarioDTO request,
                              io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateUsuarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUsuario(UsuarioOuterClass.UsuarioDTO request,
                              io.grpc.stub.StreamObserver<UsuarioOuterClass.Username> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUsuarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listUsuarios(UsuarioOuterClass.Empty request,
                             io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListUsuariosMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UsuarioService.
   */
  public static final class UsuarioServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<UsuarioServiceBlockingV2Stub> {
    private UsuarioServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsuarioServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsuarioServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioDTO createUsuario(UsuarioOuterClass.UsuarioDTO request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.Usuario getUsuario(UsuarioOuterClass.Username request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioDTO updateUsuario(UsuarioOuterClass.UsuarioDTO request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.Username deleteUsuario(UsuarioOuterClass.UsuarioDTO request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioListResponse listUsuarios(UsuarioOuterClass.Empty request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListUsuariosMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service UsuarioService.
   */
  public static final class UsuarioServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UsuarioServiceBlockingStub> {
    private UsuarioServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsuarioServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsuarioServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioDTO createUsuario(UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.Usuario getUsuario(UsuarioOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioDTO updateUsuario(UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.Username deleteUsuario(UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUsuarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public UsuarioOuterClass.UsuarioListResponse listUsuarios(UsuarioOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListUsuariosMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UsuarioService.
   */
  public static final class UsuarioServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UsuarioServiceFutureStub> {
    private UsuarioServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsuarioServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsuarioServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UsuarioOuterClass.UsuarioDTO> createUsuario(
        UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUsuarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UsuarioOuterClass.Usuario> getUsuario(
        UsuarioOuterClass.Username request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUsuarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UsuarioOuterClass.UsuarioDTO> updateUsuario(
        UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateUsuarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UsuarioOuterClass.Username> deleteUsuario(
        UsuarioOuterClass.UsuarioDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUsuarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UsuarioOuterClass.UsuarioListResponse> listUsuarios(
        UsuarioOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListUsuariosMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USUARIO = 0;
  private static final int METHODID_GET_USUARIO = 1;
  private static final int METHODID_UPDATE_USUARIO = 2;
  private static final int METHODID_DELETE_USUARIO = 3;
  private static final int METHODID_LIST_USUARIOS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USUARIO:
          serviceImpl.createUsuario((UsuarioOuterClass.UsuarioDTO) request,
              (io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO>) responseObserver);
          break;
        case METHODID_GET_USUARIO:
          serviceImpl.getUsuario((UsuarioOuterClass.Username) request,
              (io.grpc.stub.StreamObserver<UsuarioOuterClass.Usuario>) responseObserver);
          break;
        case METHODID_UPDATE_USUARIO:
          serviceImpl.updateUsuario((UsuarioOuterClass.UsuarioDTO) request,
              (io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioDTO>) responseObserver);
          break;
        case METHODID_DELETE_USUARIO:
          serviceImpl.deleteUsuario((UsuarioOuterClass.UsuarioDTO) request,
              (io.grpc.stub.StreamObserver<UsuarioOuterClass.Username>) responseObserver);
          break;
        case METHODID_LIST_USUARIOS:
          serviceImpl.listUsuarios((UsuarioOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<UsuarioOuterClass.UsuarioListResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateUsuarioMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              UsuarioOuterClass.UsuarioDTO,
              UsuarioOuterClass.UsuarioDTO>(
                service, METHODID_CREATE_USUARIO)))
        .addMethod(
          getGetUsuarioMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              UsuarioOuterClass.Username,
              UsuarioOuterClass.Usuario>(
                service, METHODID_GET_USUARIO)))
        .addMethod(
          getUpdateUsuarioMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              UsuarioOuterClass.UsuarioDTO,
              UsuarioOuterClass.UsuarioDTO>(
                service, METHODID_UPDATE_USUARIO)))
        .addMethod(
          getDeleteUsuarioMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              UsuarioOuterClass.UsuarioDTO,
              UsuarioOuterClass.Username>(
                service, METHODID_DELETE_USUARIO)))
        .addMethod(
          getListUsuariosMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              UsuarioOuterClass.Empty,
              UsuarioOuterClass.UsuarioListResponse>(
                service, METHODID_LIST_USUARIOS)))
        .build();
  }

  private static abstract class UsuarioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UsuarioServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return UsuarioOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UsuarioService");
    }
  }

  private static final class UsuarioServiceFileDescriptorSupplier
      extends UsuarioServiceBaseDescriptorSupplier {
    UsuarioServiceFileDescriptorSupplier() {}
  }

  private static final class UsuarioServiceMethodDescriptorSupplier
      extends UsuarioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UsuarioServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UsuarioServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UsuarioServiceFileDescriptorSupplier())
              .addMethod(getCreateUsuarioMethod())
              .addMethod(getGetUsuarioMethod())
              .addMethod(getUpdateUsuarioMethod())
              .addMethod(getDeleteUsuarioMethod())
              .addMethod(getListUsuariosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
