package net.siuda.clazzes;

import java.net.URL;
import java.net.URLClassLoader;

public class SeparatingClassLoader extends URLClassLoader {

    protected ClassLoader parent;

    public SeparatingClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.parent = parent;
    }

    public URL getResource(String name) {
        URL url;
        url = findResource(name);
        if(url == null) {
            super.getResource(name);
        }
        return url;
    }

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if(c == null && canResolve(name)) {
            c = findClass(name);
        }
        if(c == null) {
            c = super.loadClass(name, resolve);
        } else {
            if(resolve) {
                resolveClass(c);
            }
        }
        return c;
    }

    protected boolean canResolve(final String name)
    {
        String path = name.replace('.', '/').concat(".class");
        return findResource(path) != null;
    }

}
