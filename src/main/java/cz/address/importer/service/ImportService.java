package cz.address.importer.service;

import cz.address.importer.dto.MunicipalityData;
import cz.address.importer.dto.MunicipalityPartData;
import cz.address.importer.dto.ParsedData;
import cz.address.importer.entity.Municipality;
import cz.address.importer.entity.MunicipalityPart;
import cz.address.importer.parser.XmlParser;
import cz.address.importer.repository.MunicipalityPartRepository;
import cz.address.importer.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {
    private final XmlParser xmlParser;
    private final MunicipalityPartRepository municipalityPartRepository;
    private final MunicipalityRepository municipalityRepository;

    public ImportService(XmlParser xmlParser, MunicipalityRepository municipalityRepository, MunicipalityPartRepository municipalityPartRepository) {

        this.xmlParser = xmlParser;
        this.municipalityPartRepository = municipalityPartRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Transactional
    public void save() throws Exception {
        ParsedData parsedData = xmlParser.parse();

        MunicipalityData municipalityData = parsedData.municipalityData();
        Municipality municipality = new Municipality();
        municipality.setCode(municipalityData.code());
        municipality.setName(municipalityData.name());

        municipalityRepository.save(municipality);

        List<MunicipalityPart> municipalityParts = new ArrayList<>();

        for (MunicipalityPartData municipalityPartData : parsedData.municipalityPartsData()) {
            MunicipalityPart municipalityPart = new MunicipalityPart();

            municipalityPart.setCode(municipalityPartData.code());
            municipalityPart.setName(municipalityPartData.name());
            municipalityPart.setMunicipality(municipality);

            municipalityParts.add(municipalityPart);
        }
        municipalityPartRepository.saveAll(municipalityParts);

    }
}
