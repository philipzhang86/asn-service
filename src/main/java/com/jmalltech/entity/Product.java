package com.jmalltech.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@TableName(value = "mwms_product", schema = "public")
@Getter
@Setter
@ToString
public class Product implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long clientId;

    private String sku;

    private String currency;

    private BigDecimal sellingPrice;

    private String shortDescription;

    private Long createdById;

    private Long updatedById;

    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(value = "updated_date", fill = FieldFill.INSERT_UPDATE)
    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    public void setSellingPriceFromDouble(Double price) {
        this.sellingPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

    @JsonSetter("sellingPrice")
    public void setSellingPrice(Object sellingPrice) {
        if (sellingPrice instanceof List<?> priceList) {
            if (!priceList.isEmpty() && priceList.get(1) instanceof Number priceNumber) {
                this.sellingPrice = BigDecimal.valueOf(priceNumber.doubleValue()).setScale(2, RoundingMode.HALF_UP);
            }
        } else if (sellingPrice instanceof BigDecimal decimalValue) {
            this.sellingPrice = decimalValue;
        } else if (sellingPrice instanceof String stringValue) {
            this.sellingPrice = new BigDecimal(stringValue).setScale(2, RoundingMode.HALF_UP);
        }
    }


}
