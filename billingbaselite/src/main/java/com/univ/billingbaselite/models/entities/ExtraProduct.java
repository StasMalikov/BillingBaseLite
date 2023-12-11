package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.ExtraProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EXTRA_PRODUCT")
public class ExtraProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private ExtraProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_ID")
    private Account account;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private BaseProduct.ProductStatus status;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private List<WriteOff> writeOffs;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "VERSION")
    private Integer version;

    public enum ProductStatus {
        ACTIVE,
        ARCHIVED,
        PLANNED
    }

    public enum ExtraProductCategory {
        DAILY_WEATHER_FORECAST,
        TRAFFIC_NOTIFICATION,
        NUMBERS_CHECKER,
        ANTI_SPAM_SERVICE
    }

    public ExtraProduct(ExtraProductDTO extraProductDTO) {
        this.startDate = extraProductDTO.getStartDate();
        this.endDate = extraProductDTO.getEndDate();
        this.category = extraProductDTO.getCategory();
        this.name = (extraProductDTO.getName());
        this.description = (extraProductDTO.getDescription());
        this.cost = (extraProductDTO.getCost());
        this.status  = (extraProductDTO.getStatus());
        this.version = (extraProductDTO.getVersion());
    }


}
