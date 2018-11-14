package com.newer.service;

import com.newer.dao.CarMapper;
import com.newer.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true,rollbackFor = Exception.class)
public class CarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * 查询所有汽车信息
     * @return
     */
    public List<Car> queryList(){
        return carMapper.queryCar();
    }

    /**
     * 条件查询
     * @return
     */
    public List<Car> queryCarList(String name,Date beginDate,Date endDate){
        return carMapper.queryLikeList(name,beginDate,endDate);
    }

    /**
     * 添加车
     * @param car
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int addCar(Car car){
        return carMapper.addCar(car);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public  int updCar(Car car){
        return carMapper.updCar(car);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int delCar(Integer id){
        return carMapper.delCar(id);
    }


    public Car queryById(Integer id){
        return carMapper.queryByID(id);
    }


}
