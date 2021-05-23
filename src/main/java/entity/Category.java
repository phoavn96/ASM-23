package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;
import service.CategoryService;

@Entity(tableName = "categories")
public class Category {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = "int")
    private int id;

    @Column(columnName = "nameCategory", columnType = "VARCHAR(250)")
    private String nameCategory;

    private CategoryService categoryService = new CategoryService();


    public String getNameFromId(int idd) {
        String name = categoryService.findById(idd).getNameCategory();
        return name;
    }

    public Category() {
    }

    public Category(int id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
