package com.infnet.math_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/math")
public class MathController {
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public double add(@RequestParam double a, @RequestParam double b) {
        return a + b;
    }

    @RequestMapping(value = "/subtract", method = {RequestMethod.GET, RequestMethod.POST})
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return a - b;
    }

    @RequestMapping(value = "/multiply", method = {RequestMethod.GET, RequestMethod.POST})
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return a * b;
    }

    @RequestMapping(value = "/divide", method = {RequestMethod.GET, RequestMethod.POST})
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não permitida!");
        }
        return a / b;
    }

    @RequestMapping(value = "/power", method = {RequestMethod.GET, RequestMethod.POST})
    public double power(@RequestParam double base, @RequestParam double exp) {
        return Math.pow(base, exp);
    }
}