package com.newer.dao;

import com.newer.pojo.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarMapper {

    List<Car> queryLikeList(@Param(value = "name")String name,
                           @Param(value = "beginDate")Date beginDate,
                           @Param(value = "endDate")Date endDate);

    @Select("Select id,name,price,sale_date saleDate from car")
    List<Car> queryCar();

    @Insert("insert into car (name,price,sale_date) values(#{name},#{price},#{saleDate})")
    int addCar(Car car);


    @Update("update  car  set name=#{name},price=#{price},sale_date=#{saleDate} where id=#{id} ")
    int updCar(Car car);

    @Delete("delete from car where id=#{id}")
    int delCar(Integer id);

    @Select("select id,name,price,sale_date saleDate from car where id=#{id}")
    Car queryByID(Integer id);


}
