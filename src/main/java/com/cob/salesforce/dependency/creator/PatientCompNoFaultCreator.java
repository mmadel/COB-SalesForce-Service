package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.InsuranceWorkerInsuranceWorkerCompNoFaultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientCompNoFaultCreator implements IPatientDependencyCreator {
    @Autowired
    @Qualifier("PatientCompNoFaultMapper")
    PatientDependencyMapper mapper;
    @Autowired
    InsuranceWorkerInsuranceWorkerCompNoFaultRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        InsuranceWorkerCompNoFault toBeSaved = (InsuranceWorkerCompNoFault) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setInsuranceWorkerType(InsuranceWorkerType.Comp_NoFault);
    }
}