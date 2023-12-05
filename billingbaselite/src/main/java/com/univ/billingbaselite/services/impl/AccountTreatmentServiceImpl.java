package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.entities.AccountTreatment;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.TreatmentTemplate;
import com.univ.billingbaselite.services.AccountTreatmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTreatmentServiceImpl implements AccountTreatmentService {
    @Override
    public AccountTreatment createAccountTreatments(BaseProduct product, List<TreatmentTemplate> templates) {
        return null;
    }
}
