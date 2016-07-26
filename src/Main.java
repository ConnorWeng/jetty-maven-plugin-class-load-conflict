import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by ConnorWeng on 2016/7/22.
 */
public class Main {

    public void testLoadClass() throws Exception {
        // SystemClassLoader
        ClassLoader appClassLoader = Launcher.getLauncher().getClassLoader();

        // PluginClassRealm
        PluginClassRealm parentClassLoader = new PluginClassRealm(new URL[] {}, appClassLoader);
        Class cls1 = parentClassLoader.loadClass("org.eclipse.jetty.servlet.DefaultServlet");
        cls1.getMethod("printLoadedByWhom").invoke(cls1.newInstance());

        // WebAppClassLoader
        URL webAppClassPath = new URL("file:/" + Main.class.getClassLoader().getResource("").getPath());
        WebAppClassLoader webAppClassLoader = new WebAppClassLoader(new URL[] { webAppClassPath }, parentClassLoader);
        Class cls2 = webAppClassLoader.loadClass("serv.DownloadTestServlet");
        cls2.getMethod("printLoadedByWhom").invoke(cls2.newInstance());

        System.out.println(cls1.equals(cls2));
    }

    public static void main(String[] args) {
        try {
            Main m = new Main();
            m.testLoadClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
