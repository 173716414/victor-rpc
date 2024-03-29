package com.victor.vicrpc.server.tcp;

import com.victor.vicrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.server
 *Project：victor-rpc
 *name：VertxTcpServer
 *Date：2024/3/29  10:58
 *Filename：VertxTcpServer
 */
public class VertxTcpServer implements HttpServer {

    private byte[] hanleRequest(byte[] requestData) {
        return "hello, client!".getBytes();
    }
    @Override
    public void doStart(int port) {

        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        // 创建TCP服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(socket -> {
            // 处理连接
            socket.handler(buffer -> {
                // 处理接收到的字节数组
                byte[] requestData = buffer.getBytes();
                // 定义处理逻辑
                byte[] responseData = hanleRequest(requestData);
                // 发送响应
                socket.write(Buffer.buffer(responseData));

                System.out.println("Received request from client: " +
                        buffer);
            });
        });

        // 启动TCP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
            } else {
                System.err.println("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
