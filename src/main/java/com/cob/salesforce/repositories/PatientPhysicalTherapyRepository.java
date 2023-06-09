package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PhysicalTherapy;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientPhysicalTherapyRepository extends PagingAndSortingRepository<PhysicalTherapy,Long> {
    PhysicalTherapy findByPatient_Id(Long id);
    Long deleteByPatient(Patient patient);;
}
