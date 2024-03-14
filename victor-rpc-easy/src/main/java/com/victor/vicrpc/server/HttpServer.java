package com.victor.vicrpc.server;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.server
 *Project：victor-rpc
 *name：HttpServer
 *Date：2024/3/14  20:55
 *Filename：HttpServer
 */
public interface HttpServer {
    /**
     * Http 服务器接口
     * @param port 端口
     */
    void doStart(int port);
}
