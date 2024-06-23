package yxy.cn.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import yxy.cn.entity.BusinessEntity;
import yxy.cn.service.BusinessService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RefreshScope
@RequestMapping("/business")
public class BusinessController {
    @Resource
    BusinessService businessService;

    @PostMapping("/ListAllBusiness")
    public ResponseEntity<List<BusinessEntity>> findByBusinessIdIsNotNull(){
        try{
            return new ResponseEntity<>(businessService.findByBusinessIdIsNotNull(), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/ListBusinessByOrderTypeId")
    public ResponseEntity<List<BusinessEntity>> findByOrderTypeId(@RequestParam Integer order_type_id){
        try{
            return new ResponseEntity<>(businessService.findByOrderTypeId(order_type_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByBusinessId")
    public ResponseEntity<BusinessEntity> findByBusinessId(@RequestParam Integer business_id){
        try{
            return new ResponseEntity<>(businessService.findByBusinessId(business_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
