package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.PatientDTO;

public interface IPatientDependencyCreator {

    void create(PatientDTO dto, Patient entity);
}
