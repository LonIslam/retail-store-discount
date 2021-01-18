package com.example.retailstorediscounts.entity;

import com.example.retailstorediscounts.model.ItemModel;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author islam-gamal
 */

@Data
@EqualsAndHashCode
@Entity
@Table(name = "bills")
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })
public class Bill extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private double totalAmount;
    private double totalAmountWoGroceries;
    private double totalPayableAmount;
    private double percentageDiscount;
    private double priceDiscount;

    @Type(type = "json")
    @Column(name = "items_list", columnDefinition = "json")
    private List<ItemModel> itemList = new ArrayList<>();

}
