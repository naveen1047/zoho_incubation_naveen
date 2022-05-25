package com.naveen.jersey_db.orders;

import com.naveen.jersey_db.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {
    private int uId;
    private int tId;
    private Date date;
    private Time time;
    private List<Product> products;
    private double total;
}
