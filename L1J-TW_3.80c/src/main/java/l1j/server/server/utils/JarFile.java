package l1j.server.server.utils;

import l1j.server.server.model.map.TextMapReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarFile {
    private static Logger _log = Logger.getLogger(JarFile.class.getName());
    private String pathname;

    public static boolean isInJar() {
        String path = "";
        try {
            path = JarFile.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return path.contains(".jar");
    }

    public JarFile(String pathname) {
        if (pathname == null) {
            throw new NullPointerException();
        }
        this.pathname = pathname;
    }

    public List<String> list() {
        String path = pathname;
        if (path.indexOf('/') == 0 && path.length() > 1) {
            path = path.substring(1);
        }
        InputStream in = null;
        try {
            in = JarFile.class.getProtectionDomain().getCodeSource().getLocation().openStream();
        } catch (IOException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        List<String> filenames = new ArrayList<>();
        try {
            ZipInputStream zip = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zip.getNextEntry()) != null) {
                if (ze.getName().contains(path) && ze.getName().contains(".")) {
                    String filename = ze.getName().split("/")[1];
                    filenames.add(filename);
                }
            }
        } catch (IOException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return filenames;
    }

}
