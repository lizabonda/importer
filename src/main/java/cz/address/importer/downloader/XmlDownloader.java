package cz.address.importer.downloader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class XmlDownloader {

    private static final Path ZIP_FILE = Path.of("downloads", "kopidlno.xml.zip");
    private static final Path XML_FILE = Path.of("downloads", "kopidlno.xml");
    private static final String DOWNLOAD_URL = "https://www.smartform.cz/download/kopidlno.xml.zip";

    public void download() throws IOException {
        Files.createDirectories(Path.of("downloads"));
        URL url = URI.create(DOWNLOAD_URL).toURL();
        URLConnection connection = url.openConnection();

        try (InputStream inputStream = connection.getInputStream()) {
            Files.copy(inputStream, ZIP_FILE, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void extractXml() throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(ZIP_FILE))) {

            ZipEntry entry = zipInputStream.getNextEntry();

            if (entry == null || !entry.getName().endsWith(".xml")) {
                throw new IOException("XML file not found in ZIP archive");
            }

            Files.copy(zipInputStream, XML_FILE,StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
