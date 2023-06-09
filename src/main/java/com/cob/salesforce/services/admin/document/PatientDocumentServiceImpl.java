package com.cob.salesforce.services.admin.document;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.enums.admin.documents.PatientDocumentType;
import com.cob.salesforce.repositories.PatientPhotoImageRepository;
import com.cob.salesforce.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PatientDocumentServiceImpl implements PatientDocumentService {


    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientPhotoImageRepository patientPhotoImageRepository;

    @Override
    public List<PatientPhotoImage> findIdDocumentByType(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        return patientPhotoImageRepository.findByNameContainingAndPatient(PatientDocumentType.ID.label, patient);
    }

    @Override
    public List<PatientPhotoImage> findInsuranceDocumentByType(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        return patientPhotoImageRepository.findByNameContainingAndPatient(PatientDocumentType.INSURANCE.label, patient);
    }
}
