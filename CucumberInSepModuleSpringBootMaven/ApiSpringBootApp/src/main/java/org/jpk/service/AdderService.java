package org.jpk.service;

import org.springframework.stereotype.Service;

@Service
public class AdderService implements Adder {

    @Override
    public Integer addTwoNumbers(int a, int b){
        Integer sum = a+b;
        return sum;
    }
}
