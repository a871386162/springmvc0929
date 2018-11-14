package com.newer.controller;

import com.newer.pojo.Car;
import com.newer.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:8080",maxAge = 3600)
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * api/car        get 查询所有
     * api/car/id     get 根据id查询
     * api/car        post 新增
     * api/car        put  修改
     * api/car/id     delete 删除
     */

    /**
     * 查询所有车信息
     * @return
     */
    @RequestMapping(value = "car",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> query(
           @RequestParam(value = "name",required = false) String name,
           @RequestParam(value = "beginDate",required = false)
           @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
           @RequestParam(value = "endDate",required = false)
           @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
       List<Car> carList = carService.queryCarList(name,beginDate,endDate);
       if(carList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(carList,HttpStatus.OK);
    }


    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "car/{id}",method = RequestMethod.DELETE)
    public  ResponseEntity<?> queryById(@PathVariable(value = "id")Integer id){
        int fluRows  = carService.delCar(id);
        return new ResponseEntity<>(fluRows,HttpStatus.OK);
    }


    //produces 返回参数类型
    @RequestMapping(value = "car/{id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<Car> del(@PathVariable(value = "id")Integer id){
        Car car  = carService.queryById(id);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @RequestMapping(value = "car",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Car car){
        int count = carService.addCar(car);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @RequestMapping(value = "car",method = RequestMethod.PUT)
    public ResponseEntity<?> upd(@RequestBody Car car){
        int count = carService.updCar(car);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

}
