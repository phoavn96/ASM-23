package service;

import entity.Category;
import entity.Food;
import repository.GenericRepository;

import java.sql.Date;
import java.util.ArrayList;

public class FoodService {
    private GenericRepository<Food> genericRepository;
    public FoodService() {
        this.genericRepository = new GenericRepository<>(Food.class);
    }
    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
    public boolean create(Food obj) {
        obj.setUpdateAt(date);
        if(obj.isValid()){
            genericRepository.save(obj);
            return true;
        }
        return false;
    }
    public ArrayList<Food> getList() {
        return genericRepository.findAll();
    }

    public Food findById(int id){
        return genericRepository.findById(id);
    }
    public boolean edit(int id, Food obj){
        obj.setUpdateAt(date);
        if (genericRepository.findById(id)!=null){
            if(obj.isValid()){
                genericRepository.update(id,obj);
                return true;
        }


    }
        return false;
}

    public boolean delete(int id){
        if (genericRepository.findById(id)!=null){
            Food food = genericRepository.findById(id);
            food.setStatus(0);
            genericRepository.update(id,food);
            return true;
        }
        return false;
    }

public int countFood(){
        return genericRepository.getCount();
    }
}
