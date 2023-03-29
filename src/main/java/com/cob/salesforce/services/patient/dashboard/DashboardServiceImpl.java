package com.cob.salesforce.services.patient.dashboard;

import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.models.dashboard.PatientSourceContainer;
import com.cob.salesforce.repositories.patient.PatientDoctorSourceRepository;
import com.cob.salesforce.repositories.patient.PatientEntitySourceRepository;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DashboardServiceImpl implements DashboardService {
    int totalNumberOfPatients = 0;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientDoctorSourceRepository patientDoctorSourceRepository;
    @Autowired
    PatientEntitySourceRepository patientEntitySourceRepository;

    @Override
    public DashboardDataContainer getData() {
        totalNumberOfPatients = Lists.newArrayList(patientRepository.findAll().iterator()).size();
        return DashboardDataContainer.builder()
                .genderContainer(getGenderData())
                .patientSourceContainer(getPatientSourceData())
                .build();
    }

    private GenderContainer getGenderData() {
        int numberOfMale = patientRepository.getPatientByGender(Gender.Male);
        int numberOfFemale = patientRepository.getPatientByGender(Gender.Female);
        return GenderContainer
                .builder()
                .malePercentage(calculatePercentage(numberOfMale))
                .femalePercentage(calculatePercentage(numberOfFemale))
                .build();

    }

    private PatientSourceContainer getPatientSourceData() {
        int numberOfDoctorSource = Lists.newArrayList(patientDoctorSourceRepository.findAll().iterator()).size();
        AtomicInteger numberOfZocdoc = new AtomicInteger();
        AtomicInteger numberOfTV = new AtomicInteger();
        AtomicInteger numberOfWebsite = new AtomicInteger();
        AtomicInteger numberOfSocialMedia = new AtomicInteger();

        List<PatientEntitySource> PatientEntitySource = Lists.newArrayList(patientEntitySourceRepository.findAll().iterator());
        PatientEntitySource.stream().forEach(patientEntitySource -> {
            if (patientEntitySource.getName().equals("Zocdoc"))
                numberOfZocdoc.getAndIncrement();
            if (patientEntitySource.getName().equals("TV"))
                numberOfTV.getAndIncrement();
            if (patientEntitySource.getName().equals("website"))
                numberOfWebsite.getAndIncrement();
            if (patientEntitySource.getName().equals("Social media"))
                numberOfSocialMedia.getAndIncrement();
        });
        return PatientSourceContainer.builder()
                .zocdocPercentage(calculatePercentage(numberOfZocdoc.get()))
                .tvPercentage(calculatePercentage(numberOfTV.get()))
                .websitePercentage(calculatePercentage(numberOfWebsite.get()))
                .socialMediaPercentage(calculatePercentage(numberOfSocialMedia.get()))
                .doctorPercentage(numberOfDoctorSource)
                .build();
    }

    private int calculatePercentage(int toBeCalculated) {
        return (toBeCalculated * 100) / totalNumberOfPatients;
    }
}
