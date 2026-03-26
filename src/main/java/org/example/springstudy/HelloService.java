package org.example.springstudy;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi() {
        return "안녕";
    }
}
