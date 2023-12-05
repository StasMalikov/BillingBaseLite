package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.entities.AccountRecommendation;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.RecommendationTemplate;

import java.util.List;

public interface AccountRecommendationService {

    AccountRecommendation createAccountRecommendations(BaseProduct product, List<RecommendationTemplate> templates);
}
