package com.keepthinker.example.netty.udp;

import java.net.InetSocketAddress;

/**
 * Created by keepthinker on 2018/9/24.
 */
public class UdpPayload {
    private InetSocketAddress addr;
    private Long data;

    public InetSocketAddress getAddr() {
        return addr;
    }

    public void setAddr(InetSocketAddress addr) {
        this.addr = addr;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UdpPayload{");
        sb.append("addr=").append(addr);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
