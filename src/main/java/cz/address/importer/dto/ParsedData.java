package cz.address.importer.dto;

import java.util.List;

public record ParsedData(
        MunicipalityData municipalityData,
        List<MunicipalityPartData> municipalityPartsData
) {
}
