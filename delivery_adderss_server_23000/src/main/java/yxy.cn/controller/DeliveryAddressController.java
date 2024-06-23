package yxy.cn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import yxy.cn.dto.DeliveryAddressDTO;
import yxy.cn.entity.CustomerEntity;
import yxy.cn.entity.DeliveryAddressEntity;
import yxy.cn.feign.CustomerFeign;
import yxy.cn.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/address")
@Tag(name = "DeliveryAddressController")
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;
    @Resource
    private CustomerFeign customerFeign;

    @Modifying
    @Transactional
    @PostMapping("/DeleteAddressByDaId")
    @Operation(summary = "DeleteAddressByDaId")
    public ResponseEntity<DeliveryAddressDTO> deleteByDeliveryAddressId(@RequestParam Integer da_id){
        try{
            DeliveryAddressEntity deliveryAddressEntity = deliveryAddressService.deleteByDeliveryAddressId(da_id);
            CustomerEntity customerEntity = customerFeign.findByCustomerId(deliveryAddressEntity.getCustomerId()).getBody();
            DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                    .daId(deliveryAddressEntity.getDeliveryAddressId())
                    .address(deliveryAddressEntity.getAddress())
                    .contactTel(deliveryAddressEntity.getContactTel())
                    .contactName(deliveryAddressEntity.getContactName())
                    .contactSex(deliveryAddressEntity.getContactSex())
                    .userCode(customerEntity.getCustomerName())
                    .build();
            return new ResponseEntity<>(deliveryAddressDTO, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByCustomerId")
    @Operation(summary = "FindByCustomerId")
    public ResponseEntity<List<DeliveryAddressDTO>> findByCustomerName(@RequestParam Integer customer_id){
        try{
            List<DeliveryAddressEntity> deliveryAddressEntityList = deliveryAddressService.findByCustomerId(customer_id);
            CustomerEntity customerEntity = customerFeign.findByCustomerId(customer_id).getBody();
            List<DeliveryAddressDTO> deliveryAddressDTOList = new ArrayList<>();
            for(DeliveryAddressEntity deliveryAddressEntity : deliveryAddressEntityList){
                DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                        .daId(deliveryAddressEntity.getDeliveryAddressId())
                        .address(deliveryAddressEntity.getAddress())
                        .contactTel(deliveryAddressEntity.getContactTel())
                        .contactName(deliveryAddressEntity.getContactName())
                        .contactSex(deliveryAddressEntity.getContactSex())
                        .userCode(customerEntity.getCustomerName())
                        .build();

                deliveryAddressDTOList.add(deliveryAddressDTO);
            }
            return new ResponseEntity<>(deliveryAddressDTOList, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByCustomerName")
    @Operation(summary = "FindByCustomerName")
    public ResponseEntity<List<DeliveryAddressDTO>> findByCustomerName(@RequestParam String user_code){
        try{
            CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
            List<DeliveryAddressEntity> deliveryAddressEntityList = deliveryAddressService.findByCustomerId(customerEntity.getCustomerId());
            List<DeliveryAddressDTO> deliveryAddressDTOList = new ArrayList<>();
            for(DeliveryAddressEntity deliveryAddressEntity : deliveryAddressEntityList){
                DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                        .daId(deliveryAddressEntity.getDeliveryAddressId())
                        .address(deliveryAddressEntity.getAddress())
                        .contactTel(deliveryAddressEntity.getContactTel())
                        .contactName(deliveryAddressEntity.getContactName())
                        .contactSex(deliveryAddressEntity.getContactSex())
                        .userCode(customerEntity.getCustomerName())
                        .build();

                deliveryAddressDTOList.add(deliveryAddressDTO);
            }
            return new ResponseEntity<>(deliveryAddressDTOList, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveAddress")
    @Operation(summary = "SaveAddress")
    public ResponseEntity<DeliveryAddressDTO> save(@RequestParam Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, String user_code){
        CustomerEntity customerEntity = customerFeign.findByCustomerName(user_code).getBody();
        DeliveryAddressEntity deliveryAddressEntity = deliveryAddressService.save(da_id, contact_name, contact_sex, contact_tel, address, customerEntity.getCustomerId());
        DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                .daId(deliveryAddressEntity.getDeliveryAddressId())
                .address(deliveryAddressEntity.getAddress())
                .contactTel(deliveryAddressEntity.getContactTel())
                .contactName(deliveryAddressEntity.getContactName())
                .contactSex(deliveryAddressEntity.getContactSex())
                .userCode(customerEntity.getCustomerName())
                .build();
        return new ResponseEntity<>(deliveryAddressDTO, HttpStatus.OK);
    }

    @PostMapping("/FindByDeliveryAddressId")
    @Operation(summary = "FindByDeliveryAddressId")
    public ResponseEntity<DeliveryAddressDTO> findByDeliveryAddressId(@RequestParam Integer da_id){
        try{
            DeliveryAddressEntity deliveryAddressEntity = deliveryAddressService.findByDeliveryAddressId(da_id);
            CustomerEntity customerEntity = customerFeign.findByCustomerId(deliveryAddressEntity.getCustomerId()).getBody();
            DeliveryAddressDTO deliveryAddressDTO = DeliveryAddressDTO.builder()
                    .daId(deliveryAddressEntity.getDeliveryAddressId())
                    .address(deliveryAddressEntity.getAddress())
                    .contactTel(deliveryAddressEntity.getContactTel())
                    .contactName(deliveryAddressEntity.getContactName())
                    .contactSex(deliveryAddressEntity.getContactSex())
                    .userCode(customerEntity.getCustomerName())
                    .build();

            return new ResponseEntity<>(deliveryAddressDTO, HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
