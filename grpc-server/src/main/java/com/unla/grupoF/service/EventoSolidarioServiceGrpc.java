package com.unla.grupoF.service;
import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.0)",
    comments = "Source: eventoSolidario.proto")
public final class EventoSolidarioServiceGrpc {

  private EventoSolidarioServiceGrpc() {}

  public static final String SERVICE_NAME = "EventoSolidarioService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO,
      EventoSolidarioOuterClass.EventoSolidario> getCreateEventoSolidarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateEventoSolidario",
      requestType = EventoSolidarioOuterClass.EventoSolidarioDTO.class,
      responseType = EventoSolidarioOuterClass.EventoSolidario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO,
      EventoSolidarioOuterClass.EventoSolidario> getCreateEventoSolidarioMethod() {
    io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO, EventoSolidarioOuterClass.EventoSolidario> getCreateEventoSolidarioMethod;
    if ((getCreateEventoSolidarioMethod = EventoSolidarioServiceGrpc.getCreateEventoSolidarioMethod) == null) {
      synchronized (EventoSolidarioServiceGrpc.class) {
        if ((getCreateEventoSolidarioMethod = EventoSolidarioServiceGrpc.getCreateEventoSolidarioMethod) == null) {
          EventoSolidarioServiceGrpc.getCreateEventoSolidarioMethod = getCreateEventoSolidarioMethod =
              io.grpc.MethodDescriptor.<EventoSolidarioOuterClass.EventoSolidarioDTO, EventoSolidarioOuterClass.EventoSolidario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateEventoSolidario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidarioDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidario.getDefaultInstance()))
              .setSchemaDescriptor(new EventoSolidarioServiceMethodDescriptorSupplier("CreateEventoSolidario"))
              .build();
        }
      }
    }
    return getCreateEventoSolidarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest,
      EventoSolidarioOuterClass.EventoSolidario> getGetEventoSolidarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEventoSolidario",
      requestType = EventoSolidarioOuterClass.EventoIdRequest.class,
      responseType = EventoSolidarioOuterClass.EventoSolidario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest,
      EventoSolidarioOuterClass.EventoSolidario> getGetEventoSolidarioMethod() {
    io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest, EventoSolidarioOuterClass.EventoSolidario> getGetEventoSolidarioMethod;
    if ((getGetEventoSolidarioMethod = EventoSolidarioServiceGrpc.getGetEventoSolidarioMethod) == null) {
      synchronized (EventoSolidarioServiceGrpc.class) {
        if ((getGetEventoSolidarioMethod = EventoSolidarioServiceGrpc.getGetEventoSolidarioMethod) == null) {
          EventoSolidarioServiceGrpc.getGetEventoSolidarioMethod = getGetEventoSolidarioMethod =
              io.grpc.MethodDescriptor.<EventoSolidarioOuterClass.EventoIdRequest, EventoSolidarioOuterClass.EventoSolidario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetEventoSolidario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidario.getDefaultInstance()))
              .setSchemaDescriptor(new EventoSolidarioServiceMethodDescriptorSupplier("GetEventoSolidario"))
              .build();
        }
      }
    }
    return getGetEventoSolidarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO,
      EventoSolidarioOuterClass.EventoSolidario> getUpdateEventoSolidarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateEventoSolidario",
      requestType = EventoSolidarioOuterClass.EventoSolidarioDTO.class,
      responseType = EventoSolidarioOuterClass.EventoSolidario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO,
      EventoSolidarioOuterClass.EventoSolidario> getUpdateEventoSolidarioMethod() {
    io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoSolidarioDTO, EventoSolidarioOuterClass.EventoSolidario> getUpdateEventoSolidarioMethod;
    if ((getUpdateEventoSolidarioMethod = EventoSolidarioServiceGrpc.getUpdateEventoSolidarioMethod) == null) {
      synchronized (EventoSolidarioServiceGrpc.class) {
        if ((getUpdateEventoSolidarioMethod = EventoSolidarioServiceGrpc.getUpdateEventoSolidarioMethod) == null) {
          EventoSolidarioServiceGrpc.getUpdateEventoSolidarioMethod = getUpdateEventoSolidarioMethod =
              io.grpc.MethodDescriptor.<EventoSolidarioOuterClass.EventoSolidarioDTO, EventoSolidarioOuterClass.EventoSolidario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateEventoSolidario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidarioDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidario.getDefaultInstance()))
              .setSchemaDescriptor(new EventoSolidarioServiceMethodDescriptorSupplier("UpdateEventoSolidario"))
              .build();
        }
      }
    }
    return getUpdateEventoSolidarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest,
      EventoSolidarioOuterClass.EventoSolidario> getDeleteEventoSolidarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteEventoSolidario",
      requestType = EventoSolidarioOuterClass.EventoIdRequest.class,
      responseType = EventoSolidarioOuterClass.EventoSolidario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest,
      EventoSolidarioOuterClass.EventoSolidario> getDeleteEventoSolidarioMethod() {
    io.grpc.MethodDescriptor<EventoSolidarioOuterClass.EventoIdRequest, EventoSolidarioOuterClass.EventoSolidario> getDeleteEventoSolidarioMethod;
    if ((getDeleteEventoSolidarioMethod = EventoSolidarioServiceGrpc.getDeleteEventoSolidarioMethod) == null) {
      synchronized (EventoSolidarioServiceGrpc.class) {
        if ((getDeleteEventoSolidarioMethod = EventoSolidarioServiceGrpc.getDeleteEventoSolidarioMethod) == null) {
          EventoSolidarioServiceGrpc.getDeleteEventoSolidarioMethod = getDeleteEventoSolidarioMethod =
              io.grpc.MethodDescriptor.<EventoSolidarioOuterClass.EventoIdRequest, EventoSolidarioOuterClass.EventoSolidario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteEventoSolidario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidario.getDefaultInstance()))
              .setSchemaDescriptor(new EventoSolidarioServiceMethodDescriptorSupplier("DeleteEventoSolidario"))
              .build();
        }
      }
    }
    return getDeleteEventoSolidarioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsuarioOuterClass.Empty,
      EventoSolidarioOuterClass.EventoSolidarioListResponse> getListEventoSolidariosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEventoSolidarios",
      requestType = UsuarioOuterClass.Empty.class,
      responseType = EventoSolidarioOuterClass.EventoSolidarioListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsuarioOuterClass.Empty,
      EventoSolidarioOuterClass.EventoSolidarioListResponse> getListEventoSolidariosMethod() {
    io.grpc.MethodDescriptor<UsuarioOuterClass.Empty, EventoSolidarioOuterClass.EventoSolidarioListResponse> getListEventoSolidariosMethod;
    if ((getListEventoSolidariosMethod = EventoSolidarioServiceGrpc.getListEventoSolidariosMethod) == null) {
      synchronized (EventoSolidarioServiceGrpc.class) {
        if ((getListEventoSolidariosMethod = EventoSolidarioServiceGrpc.getListEventoSolidariosMethod) == null) {
          EventoSolidarioServiceGrpc.getListEventoSolidariosMethod = getListEventoSolidariosMethod =
              io.grpc.MethodDescriptor.<UsuarioOuterClass.Empty, EventoSolidarioOuterClass.EventoSolidarioListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEventoSolidarios"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsuarioOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EventoSolidarioOuterClass.EventoSolidarioListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventoSolidarioServiceMethodDescriptorSupplier("ListEventoSolidarios"))
              .build();
        }
      }
    }
    return getListEventoSolidariosMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventoSolidarioServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceStub>() {
        @java.lang.Override
        public EventoSolidarioServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventoSolidarioServiceStub(channel, callOptions);
        }
      };
    return EventoSolidarioServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventoSolidarioServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceBlockingStub>() {
        @java.lang.Override
        public EventoSolidarioServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventoSolidarioServiceBlockingStub(channel, callOptions);
        }
      };
    return EventoSolidarioServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventoSolidarioServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventoSolidarioServiceFutureStub>() {
        @java.lang.Override
        public EventoSolidarioServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventoSolidarioServiceFutureStub(channel, callOptions);
        }
      };
    return EventoSolidarioServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EventoSolidarioServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateEventoSolidarioMethod(), responseObserver);
    }

    /**
     */
    public void getEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEventoSolidarioMethod(), responseObserver);
    }

    /**
     */
    public void updateEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateEventoSolidarioMethod(), responseObserver);
    }

    /**
     */
    public void deleteEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteEventoSolidarioMethod(), responseObserver);
    }

    /**
     */
    public void listEventoSolidarios(UsuarioOuterClass.Empty request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidarioListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEventoSolidariosMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateEventoSolidarioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                EventoSolidarioOuterClass.EventoSolidarioDTO,
                EventoSolidarioOuterClass.EventoSolidario>(
                  this, METHODID_CREATE_EVENTO_SOLIDARIO)))
          .addMethod(
            getGetEventoSolidarioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                EventoSolidarioOuterClass.EventoIdRequest,
                EventoSolidarioOuterClass.EventoSolidario>(
                  this, METHODID_GET_EVENTO_SOLIDARIO)))
          .addMethod(
            getUpdateEventoSolidarioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                EventoSolidarioOuterClass.EventoSolidarioDTO,
                EventoSolidarioOuterClass.EventoSolidario>(
                  this, METHODID_UPDATE_EVENTO_SOLIDARIO)))
          .addMethod(
            getDeleteEventoSolidarioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                EventoSolidarioOuterClass.EventoIdRequest,
                EventoSolidarioOuterClass.EventoSolidario>(
                  this, METHODID_DELETE_EVENTO_SOLIDARIO)))
          .addMethod(
            getListEventoSolidariosMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                UsuarioOuterClass.Empty,
                EventoSolidarioOuterClass.EventoSolidarioListResponse>(
                  this, METHODID_LIST_EVENTO_SOLIDARIOS)))
          .build();
    }
  }

  /**
   */
  public static final class EventoSolidarioServiceStub extends io.grpc.stub.AbstractAsyncStub<EventoSolidarioServiceStub> {
    private EventoSolidarioServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventoSolidarioServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventoSolidarioServiceStub(channel, callOptions);
    }

    /**
     */
    public void createEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateEventoSolidarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEventoSolidarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateEventoSolidarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteEventoSolidarioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listEventoSolidarios(UsuarioOuterClass.Empty request,
        io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidarioListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEventoSolidariosMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EventoSolidarioServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EventoSolidarioServiceBlockingStub> {
    private EventoSolidarioServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventoSolidarioServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventoSolidarioServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public EventoSolidarioOuterClass.EventoSolidario createEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateEventoSolidarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public EventoSolidarioOuterClass.EventoSolidario getEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEventoSolidarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public EventoSolidarioOuterClass.EventoSolidario updateEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateEventoSolidarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public EventoSolidarioOuterClass.EventoSolidario deleteEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteEventoSolidarioMethod(), getCallOptions(), request);
    }

    /**
     */
    public EventoSolidarioOuterClass.EventoSolidarioListResponse listEventoSolidarios(UsuarioOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEventoSolidariosMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EventoSolidarioServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EventoSolidarioServiceFutureStub> {
    private EventoSolidarioServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventoSolidarioServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventoSolidarioServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EventoSolidarioOuterClass.EventoSolidario> createEventoSolidario(
        EventoSolidarioOuterClass.EventoSolidarioDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateEventoSolidarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EventoSolidarioOuterClass.EventoSolidario> getEventoSolidario(
        EventoSolidarioOuterClass.EventoIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEventoSolidarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EventoSolidarioOuterClass.EventoSolidario> updateEventoSolidario(
        EventoSolidarioOuterClass.EventoSolidarioDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateEventoSolidarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EventoSolidarioOuterClass.EventoSolidario> deleteEventoSolidario(
        EventoSolidarioOuterClass.EventoIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteEventoSolidarioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EventoSolidarioOuterClass.EventoSolidarioListResponse> listEventoSolidarios(
        UsuarioOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEventoSolidariosMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_EVENTO_SOLIDARIO = 0;
  private static final int METHODID_GET_EVENTO_SOLIDARIO = 1;
  private static final int METHODID_UPDATE_EVENTO_SOLIDARIO = 2;
  private static final int METHODID_DELETE_EVENTO_SOLIDARIO = 3;
  private static final int METHODID_LIST_EVENTO_SOLIDARIOS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventoSolidarioServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EventoSolidarioServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_EVENTO_SOLIDARIO:
          serviceImpl.createEventoSolidario((EventoSolidarioOuterClass.EventoSolidarioDTO) request,
              (io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario>) responseObserver);
          break;
        case METHODID_GET_EVENTO_SOLIDARIO:
          serviceImpl.getEventoSolidario((EventoSolidarioOuterClass.EventoIdRequest) request,
              (io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario>) responseObserver);
          break;
        case METHODID_UPDATE_EVENTO_SOLIDARIO:
          serviceImpl.updateEventoSolidario((EventoSolidarioOuterClass.EventoSolidarioDTO) request,
              (io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario>) responseObserver);
          break;
        case METHODID_DELETE_EVENTO_SOLIDARIO:
          serviceImpl.deleteEventoSolidario((EventoSolidarioOuterClass.EventoIdRequest) request,
              (io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidario>) responseObserver);
          break;
        case METHODID_LIST_EVENTO_SOLIDARIOS:
          serviceImpl.listEventoSolidarios((UsuarioOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<EventoSolidarioOuterClass.EventoSolidarioListResponse>) responseObserver);
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

  private static abstract class EventoSolidarioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventoSolidarioServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return EventoSolidarioOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventoSolidarioService");
    }
  }

  private static final class EventoSolidarioServiceFileDescriptorSupplier
      extends EventoSolidarioServiceBaseDescriptorSupplier {
    EventoSolidarioServiceFileDescriptorSupplier() {}
  }

  private static final class EventoSolidarioServiceMethodDescriptorSupplier
      extends EventoSolidarioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventoSolidarioServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EventoSolidarioServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventoSolidarioServiceFileDescriptorSupplier())
              .addMethod(getCreateEventoSolidarioMethod())
              .addMethod(getGetEventoSolidarioMethod())
              .addMethod(getUpdateEventoSolidarioMethod())
              .addMethod(getDeleteEventoSolidarioMethod())
              .addMethod(getListEventoSolidariosMethod())
              .build();
        }
      }
    }
    return result;
  }
}
