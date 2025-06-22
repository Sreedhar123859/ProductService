package org.example.productservice7oct.repositories;

public interface CustomQueries {
    public String GET_PRODUCT_WITH_SUB_CATEGORY_NAME = "Select * from Products p join Category c where p.category_id = c.id join Subcategory s where s.id = c.subcategory_id ";
}
