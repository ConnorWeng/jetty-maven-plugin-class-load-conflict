import sun.misc.Launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;

/**
 * Created by ConnorWeng on 2016/7/22.
 */
public class WebAppClassLoader extends URLClassLoader {

    public WebAppClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("self loading: " + name);
        Class<?> c = findLoadedClass(name);
        if (c != null) return c;
        if (isSystemClass(name)) {
            return super.loadClass(name, resolve);
        } else {
            Class<?> cls = this.findClass(name);
            if (resolve) {
                resolveClass(cls);
            }
            return cls;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String path = name.replaceAll("\\.", "/");
            File classFile = new File(this.getURLs()[0].toString().replace("file://", "") + path + ".class");
            FileInputStream fileInputStream = new FileInputStream(classFile);
            int length = (int) classFile.length();
            byte[] buf = new byte[length];
            int readLength = fileInputStream.read(buf);
            if (length != readLength) {
                throw new IOException("read uncompleted");
            }
            return defineClass(name, buf, 0, length);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isSystemClass(String name) {
        if (!name.contains("DownloadTestServlet") && !name.contains("HttpContent")) return true;
        return false;
    }
}
