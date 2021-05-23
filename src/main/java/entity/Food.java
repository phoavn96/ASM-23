package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;

import java.sql.Date;
@Entity(tableName = "foods")
public class Food {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = "int")
    private int id;
    @Column(columnName = "nameFood", columnType = "VARCHAR(250)")
    private String nameFood;
    @Column(columnName = "categoryId", columnType = "int")
    private int categoryId;
    @Column(columnName = "description", columnType = "VARCHAR(250)")
    private String description;
    @Column(columnName = "thumbnail", columnType = "VARCHAR(250)")
    private String thumbnail;
    @Column(columnName = "price", columnType = "double")
    private double price;
    @Column(columnName = "sellStartAt", columnType = "DATE")
    private Date sellStartAt;
    @Column(columnName = "updateAt", columnType = "DATE")
    private Date updateAt;
    @Column(columnName = "status", columnType = "int")
    private int status;
    public String toStatus(int status) {
        switch (status) {
            case 0:
                return "Đã xóa";
            case 1:
                return "Đang Bán";
            case 2:
                return "Dừng bán";
            default:
                return "";
        }
    }

    public Food() {
    }

    public Food(int id, String nameFood, int categoryId, String description, String thumbnail, double price, Date sellStartAt, Date updateAt, int status) {
        this.id = id;
        this.nameFood = nameFood;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.sellStartAt = sellStartAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getSellStartAt() {
        return sellStartAt;
    }

    public void setSellStartAt(Date sellStartAt) {
        this.sellStartAt = sellStartAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public boolean isValid() {
        if (this.getNameFood().length() > 7 && this.getPrice() > 0) {
            return true;
        };
        return false;
    }
}
