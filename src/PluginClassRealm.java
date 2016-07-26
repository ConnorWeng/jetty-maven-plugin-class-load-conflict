import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by ConnorWeng on 2016/7/26.
 */
public class PluginClassRealm extends URLClassLoader {
    public PluginClassRealm(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("Parent loading :" + name);
        return super.loadClass(name, resolve);
    }
}
