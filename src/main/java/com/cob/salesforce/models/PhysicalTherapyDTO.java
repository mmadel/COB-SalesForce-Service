
package com.cob.salesforce.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PhysicalTherapyDTO {

    
    private String location;
    
    private Long numberOfVisit;
}
