package com.victor.vicrpc.server.tcp;

import com.victor.vicrpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

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
                    // // 处理连接
                    // socket.handler(buffer -> {
                    // String testMessage = "Hello, server!Hello, server!Hello, server!";
                    // int messageLength = testMessage.getBytes().length;
                    //
                    // RecordParser parser = RecordParser.newFixed(messageLength);
                    // parser.setOutput(new Handler<Buffer>() {
                    //     @Override
                    //     public void handle(Buffer buffer) {
                    //         String str = new String(buffer.getBytes());
                    //         System.out.println(str);
                    //         if (testMessage.equals(str)) {
                    //             System.out.println("good");
                    //         }
                    //     }
                    // });
                    // socket.handler(parser);
                    // if (buffer.getBytes().length < messageLength) {
                    //     System.out.println("半包, length = " + buffer.getBytes().length);
                    //     return;
                    // } else if (buffer.getBytes().length > messageLength) {
                    //     System.out.println("粘包, length = " + buffer.getBytes().length);
                    //     return;
                    // }
                    // String str = new String(buffer.getBytes(0, messageLength));
                    // System.out.println(str);
                    // if (testMessage.equals(str)) {
                    //     System.out.println("good");
                    // }
                    // // 处理接收到的字节数组
                    // byte[] requestData = buffer.getBytes();
                    // // 定义处理逻辑
                    // byte[] responseData = hanleRequest(requestData);
                    // // 发送响应
                    // socket.write(Buffer.buffer(responseData));
                    //
                    // System.out.println("Received request from client: " +
                    //         buffer);
                    //     });
                    // });

                    // 启动TCP服务器并监听指定端口
                    // 构造 parser
                    RecordParser parser = RecordParser.newFixed(8);
                    parser.setOutput(new Handler<Buffer>() {
                        // 初始化
                        int size = -1;
                        // 一次完整读取
                        Buffer resultBuffer = Buffer.buffer();

                        @Override
                        public void handle(Buffer buffer) {
                            if (size == -1) {
                                // 读取消息长度
                                size = buffer.getInt(4);
                                parser.fixedSizeMode(size);
                                resultBuffer.appendBuffer(buffer);
                            } else {
                                resultBuffer.appendBuffer(buffer);
                                System.out.println(resultBuffer.toString());

                                parser.fixedSizeMode(8);
                                size = -1;
                                resultBuffer = Buffer.buffer();
                            }
                        }
                    });

                    socket.handler(parser);
                });
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
