package yxy.cn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yxy.cn.entity.CommonResult;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
@RefreshScope
@Tag(name="CustomerController")
public class CustomerController {
    @Resource
    CustomerService customerService;

    @Operation(summary = "/FindByCustomerId/{customer_id}")
    @GetMapping("/FindByCustomerId/{customer_id}")
    public ResponseEntity<CustomerEntity> findByCustomerId(@PathVariable(name="customer_id") String customer_id){
        try{
            return new ResponseEntity<>(customerService.findByCustomerId(Integer.parseInt(customer_id)), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByCustomerName")
    public ResponseEntity<CustomerEntity> findByCustomerName(@RequestParam(name="customer_name") String customer_name){
        try{
            System.out.println("调用了customer_server_21001");
            return new ResponseEntity<>(customerService.findByCustomerName(customer_name), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByCustomerNameAndPassword")
    public ResponseEntity<CustomerEntity> findByCustomerNameAndPassword(String customer_name, String password){
        try{
            return new ResponseEntity<>(customerService.findByCustomerNameAndPassword(customer_name,password), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}



