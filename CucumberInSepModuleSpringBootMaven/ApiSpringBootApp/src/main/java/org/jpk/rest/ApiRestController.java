package org.jpk.rest;


import org.jpk.service.Adder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    Adder adder;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus(){
        return ResponseEntity.ok("Service is Up!!");
    }


    @PostMapping("/add")
    public ResponseEntity<String> addTwoNumbers(
            @RequestParam Integer a,
            @RequestParam Integer b){
        int sum = adder.addTwoNumbers(a,b);
        return ResponseEntity.ok(String.valueOf(sum));
    }






}
