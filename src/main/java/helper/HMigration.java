package helper;

import entity.Category;
import entity.Food;
import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;
import org.reflections.Reflections;
import service.CategoryService;
import service.FoodService;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public class HMigration {

    public static void main(String[] args) throws SQLException {
        // quét trong package entity.
        Reflections reflections = new Reflections("entity");
        // tìm ra tất cả các class có annotation là @Entity.
        Set<Class<?>> allClasses =
                reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> c :
                allClasses) {
            registerClass(c);
            if(c.getSimpleName().equals("Food")){
                FoodService foodService = new FoodService();

                Food food1 = new Food();
                food1.setNameFood("Bún Real Kua");
                food1.setDescription("kuaaaa");
                food1.setCategoryId(1);
                food1.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food1.setPrice(30000);
                food1.setStatus(1);
                food1.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food1);

                Food food2 = new Food();
                food2.setNameFood("Bún Real Kua2");
                food2.setDescription("kuaaaa");
                food2.setCategoryId(1);
                food2.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food2.setPrice(30000);
                food2.setStatus(1);
                food2.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food2);

                Food food3 = new Food();
                food3.setNameFood("Bún Real Kua3");
                food3.setDescription("kuaaaa");
                food3.setCategoryId(1);
                food3.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food3.setPrice(30000);
                food3.setStatus(1);
                food3.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food3);

                Food food4 = new Food();
                food4.setNameFood("Bún Real Kua4");
                food4.setDescription("kuaaaa");
                food4.setCategoryId(1);
                food4.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food4.setPrice(30000);
                food4.setStatus(1);
                food4.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food4);

                Food food5 = new Food();
                food5.setNameFood("Bún Real Kua5");
                food5.setDescription("kuaaaa");
                food5.setCategoryId(1);
                food5.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food5.setPrice(30000);
                food5.setStatus(1);
                food5.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food5);

                Food food6 = new Food();
                food6.setNameFood("Bún Real Kua6");
                food6.setDescription("kuaaaa");
                food6.setCategoryId(1);
                food6.setThumbnail("https://res.cloudinary.com/hoadaica/image/upload/v1621746737/bun-rieu-cua_eaej9a.jpg");
                food6.setPrice(30000);
                food6.setStatus(1);
                food6.setSellStartAt(Date.valueOf("2021-05-23"));
                foodService.create(food6);


                System.out.printf("Add food thành công.");
            }

            if(c.getSimpleName().equals("Category")){
                CategoryService categoryService = new CategoryService();

                Category category1 = new Category();
                category1.setNameCategory("Món nướng");
                categoryService.create(category1);
                Category category2 = new Category();
                category2.setNameCategory("Món Luộc");
                categoryService.create(category2);
                Category category3 = new Category();
                category3.setNameCategory("Món Chay");
                categoryService.create(category3);
                Category category4 = new Category();
                category4.setNameCategory("Đồ uống");
                categoryService.create(category4);
                System.out.printf("Add category thành công.");
            }
        }
    }
    public static void registerClass(Class clazz) throws SQLException {

        try {
            // kiểm tra class có được đánh dấu là @Entity hay không?
            if (!clazz.isAnnotationPresent(Entity.class)) {
                return;
            }
            // Lấy ra giá trị của annotation entity.
            Entity entityInfor = (Entity) clazz.getAnnotation(Entity.class);
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(SQLConstant.CREATE_TABLE);
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(entityInfor.tableName());
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(SQLConstant.OPEN_PARENTHESES);
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // check xem trường có phải là column không.
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                // lấy thông tin column để check tên trường, kiểu giá trị của trường.
                Column columnInfor = (Column) field.getAnnotation(Column.class);
                strQuery.append(columnInfor.columnName());
                strQuery.append(SQLConstant.SPACE);
                strQuery.append(columnInfor.columnType());
                // check xem trường có phải là id không.
                if (field.isAnnotationPresent(Id.class)) {
                    // lấy thông tin id.
                    Id idInfor = (Id) field.getAnnotation(Id.class);
                    strQuery.append(SQLConstant.SPACE);
                    strQuery.append(SQLConstant.PRIMARY_KEY);
                    if (idInfor.autoIncrement()) {
                        strQuery.append(SQLConstant.SPACE);
                        strQuery.append(SQLConstant.AUTO_INCREMENT);
                    }
                }
                strQuery.append(SQLConstant.COMMON);
                strQuery.append(SQLConstant.SPACE);
            }
            strQuery.setLength(strQuery.length() - 2);
            strQuery.append(SQLConstant.CLOSE_PARENTHESES);
            ConnectionHelper.getConnection().createStatement().execute(strQuery.toString());
            System.out.printf("Tạo bảng %s thành công.\n", entityInfor.tableName());

        } catch (java.sql.SQLSyntaxErrorException sqlSyntaxErrorException) {
            // sqlSyntaxErrorException.printStackTrace();
            System.err.printf("Có lỗi xảy ra trong quá trình tạo bảng. Error %s.\n", sqlSyntaxErrorException.getMessage());
        }
    }
}
