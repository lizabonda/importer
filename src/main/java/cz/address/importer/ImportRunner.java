package cz.address.importer;

import cz.address.importer.downloader.XmlDownloader;
import cz.address.importer.service.ImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportRunner implements CommandLineRunner {
    private final XmlDownloader xmlDownloader;
    private final ImportService importService;

    public ImportRunner(XmlDownloader xmlDownloader, ImportService importService) {
        this.xmlDownloader = xmlDownloader;
        this.importService = importService;
    }

    @Override
    public void run(String... args) throws Exception {
        xmlDownloader.download();
        xmlDownloader.extractXml();
        importService.save();

    }
}
