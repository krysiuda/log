package net.siuda.clazzes;

import com.google.common.net.HostAndPort;

import java.lang.reflect.Method;
import java.net.URL;

public class Main {

    private URL[] urls;

    public static void main(String ... args) throws Exception {
        URL[] urls = new URL[args.length];
        for(int c = 0; c < args.length; c++) {
            urls[c] = new URL(args[c]);
        }
        new Main(urls).run();
    }

    private Main(URL[] urls) {
        this.urls = urls;
    }

    private void run() throws Exception {
        ClassLoader cl = new SeparatingClassLoader(urls, this.getClass().getClassLoader());
        Class clazz = cl.loadClass("net.siuda.clazzes.separated.Separated");
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("run");
        method.invoke(object);

        System.out.println("CONTINUE PARENT RUN:");
        HostAndPort hostAndPort = HostAndPort.fromHost("localhost");
        System.out.println("result:" + hostAndPort.getHost());
    }

}
