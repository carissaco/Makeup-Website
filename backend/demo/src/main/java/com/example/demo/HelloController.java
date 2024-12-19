package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class HelloController {
    
    public static class Hello {
        String greeting;
        String goodbye;
        
    
        // Constructor
        public Hello(String greeting, String goodbye) {
            this.greeting = greeting;
            this.goodbye = goodbye;
        
        }

        // Getter
        public String getGreeting() {
            return greeting;
        }  
        
        public String getGoodbye() {
            return goodbye;
        }  


    // Setters (optional)
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    }
    
    

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    // @GetMapping("/hello")
    // public Hello abc(@RequestParam(input = "input", defaultValue) String input) {// create a function that returns a Hello object . it has a parameter that accepts a string
    //     Hello hello = new Hello();
    //     if (input == "how are you"){
    //         Hello hello = new Hello("I am fine, how about you?", "");
    //         return hello;
    //     }
    //     else {
    //         Hello hello = new Hello("hello world", "bye");
    //         return hello;
    //     }
        
    
    // }

    @GetMapping("/greet")
    public String greetUser(@RequestParam( defaultValue = "Guest") String name,
    @RequestParam(defaultValue = "0") String age) {
        return "Hello, " + name + age + "!";
    }

    @GetMapping("/getHashMap")
    public Map<String, Object> getHashMap() { // Java Spring can return a hashmap
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 123);
        map.put("key3", true);
        return map;
    }

    @PostMapping("/postHashMap")
    public Map<String, Object> postHashMap(@RequestBody Map<String, Object> body) { // Java Spring can return a hashmap
        
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 123);
        map.put("key3", true);
        return map;
    }
    
}
