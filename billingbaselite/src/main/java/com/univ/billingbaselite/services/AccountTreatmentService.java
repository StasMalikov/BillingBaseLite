package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.entities.AccountRecommendation;
import com.univ.billingbaselite.models.entities.AccountTreatment;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.RecommendationTemplate;
import com.univ.billingbaselite.models.entities.TreatmentTemplate;

import java.util.List;

public interface AccountTreatmentService {
    AccountTreatment createAccountTreatments(BaseProduct product, List<TreatmentTemplate> templates);
}
