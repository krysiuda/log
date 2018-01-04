package net.siuda.clazzes.separated;

import com.google.common.net.HostAndPort;

public class Separated {

    public void run() {
        System.out.println("RUN!");
        HostAndPort hostAndPort = HostAndPort.fromParts("localhost", 8080);
        System.out.println("result:" + hostAndPort.getHostText());
    }

}
