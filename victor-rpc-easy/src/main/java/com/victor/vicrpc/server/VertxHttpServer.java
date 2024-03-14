package com.victor.vicrpc.server;

import io.vertx.core.Vertx;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.server
 *Project：victor-rpc
 *name：VertxHttpServer
 *Date：2024/3/14  20:57
 *Filename：VertxHttpServer
 */
public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
        //     处理http请求
            System.out.println("Received request: " + request + " " + request.uri());

            request.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x HTTP server!");
        });

        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}
