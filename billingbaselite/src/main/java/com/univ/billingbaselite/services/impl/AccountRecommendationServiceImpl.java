package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.entities.AccountRecommendation;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.RecommendationTemplate;
import com.univ.billingbaselite.services.AccountRecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRecommendationServiceImpl implements AccountRecommendationService {


    @Override
    public AccountRecommendation createAccountRecommendations(BaseProduct product, List<RecommendationTemplate> templates) {
        return null;
    }
}
