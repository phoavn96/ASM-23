package service;

import entity.Category;
import repository.GenericRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private GenericRepository<Category> genericRepository;
    public CategoryService() {
        this.genericRepository = new GenericRepository<>(Category.class);
    }
    Date date=new Date(System.currentTimeMillis());
    public boolean create(Category obj) {
        return genericRepository.save(obj);
    }
    public ArrayList<Category> getList() {
        return genericRepository.findAll();
    }
    public Category findById(int id){
        return genericRepository.findById(id);
    }
}
