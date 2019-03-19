package me.itzsomebody.simpleloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Defines a {@link ClassLoader} which is used to find and define all classes which are "protected" via encryption.
 *
 * @author ItzSomebody
 */
public class CustomClassLoader extends ClassLoader {
    public CustomClassLoader() {
        // This line allows access to Spigot's PluginClassLoader.
        super(Loader.class.getClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // In this example, we're pulling the class out of the JAR and loading it. In your "sUpEr SeCuRe" system
        // you would probably fetch the class from your server or something similar.
        InputStream in = CustomClassLoader.class.getResourceAsStream('/' + name.replace('.', '/') + ".class");

        // Oh noes, no class!
        if (in == null)
            // We need this so we can resolve non-protected classes like the Spigot API.
            return super.findClass(name);
        else {
            // Gotta define that class.
            byte[] b = decrypt(toByteArray(in));
            return this.defineClass(name, b, 0, b.length, Loader.class.getProtectionDomain());
        }
    }

    private static byte[] decrypt(byte[] b) {
        // Do your own fancy decryption here, I'm not going to do the work for you.
        return b;
    }

    /**
     * Converts the provided {@link InputStream} into a byte array.
     *
     * @param in {@link InputStream} to create a byte array from.
     * @return a byte array formed from the provided {@link InputStream}.
     */
    private static byte[] toByteArray(InputStream in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (in.available() > 0) {
                int data = in.read(buffer);
                out.write(buffer, 0, data);
            }

            in.close();
            out.close();
            return out.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new IllegalStateException(ioe);
        }
    }
}
