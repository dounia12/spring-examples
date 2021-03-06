package examples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
// @RequestMapping("/api/v1") - add if need additionally add prefix for all routes in current controller
public class RoutesController {

    // return only text - not use template
    // maps all HTTP operations by default
    @RequestMapping("/hello")
    public @ResponseBody String hello() {
        return "Hello World";
    }

    // GET simple - not params
    @GetMapping("/simple-get")
    public String simpleGet() {
        return "simple-get";
    }

    // cookie

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    public String cookie(@CookieValue("name") String name, Model model) {
        model.addAttribute("name", name);
        return "cookie";
    }

    @RequestMapping(value = "/set-cookie", method = RequestMethod.GET)
    public @ResponseBody String cookie(@RequestParam(name="name") String name, @RequestParam(name="value") String value, HttpServletResponse response) {
        Cookie foo = new Cookie(name, value); //bake cookie
        foo.setMaxAge(1000); //set expire time to 1000 sec

        response.addCookie(foo); //put cookie in response
        return "cookie created";
    }

    // GET simple - not params - use @RequestMapping
    @RequestMapping(value = "/only-get", method = RequestMethod.GET) // same like - @GetMapping("/only-get")
    public String onlyGet() {
        return "only-get";
    }

    // POST - not params
    @PostMapping("/post")
    public String post() {
        return "post";
    }

    // GET - display form for post - not params
    @GetMapping("/simple-form-for-display-post")
    public String simpleFormForDisplayPost() {
        return "simple-form-for-display-post";
    }

    // GET with param - "new"
    // example /only-get-param?new
    @RequestMapping(value = "/only-get-param", method = RequestMethod.GET, params="new")
    public String getWithSimpleParam() {
        return "only-get-param";
    }

    // GET url with request get params "name":
    // examples: "/with-required-get-params?name=petya"
    @GetMapping("/with-required-get-params")
    public String withRequiredGetParams(@RequestParam(name="name") String name, Model model) {
        model.addAttribute("name", name);
        return "with-required-get-params";
    }

    // GET url with not request get params "name":
    // examples: "/greeting" or "greeting?name=petya"
    // must be set defaultValue - if not set - be null
    @GetMapping("/with-not-required-get-params")
    public String withNotRequiredGetParams(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "with-not-required-get-params";
    }

    // Simple way - just only set in method params: "String name, String error" - and this vars inserted from GET params
    // GET url with not request get params "name":
    // examples: "/greeting" or "greeting?name=petya"
    // must be set defaultValue - if not set - be null
    @GetMapping("/with-not-required-get-params-simple")
    public String withNotRequiredGetParamsSimple(String name, String error, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("error", error);
        return "with-not-required-get-params-simple";
    }

    // GET url with not request get param "name" and not required - "age":
    // examples: "/with-two-not-required-and-not-required-get-params?name=petya&age=20"
    // must be set defaultValue - if not set - be null
    @GetMapping("/with-two-not-required-and-not-required-get-params")
    public String withTwoGetParams(
            @RequestParam(name="name") String name,
            @RequestParam(name="age", required=false, defaultValue="18") int age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "with-two-get-params";
    }

    // GET url with any get params:
    // and pass all get params - to view
    @GetMapping("/display-all-get-params")
    public String displayAllGetParams(@RequestParam Map<String,String> requestParams,
            Model model) {
        model.addAttribute("requestParams", requestParams);
        return "display-all-get-params";
    }

    // GET url with get params:
    // and get GET params separate
    @GetMapping("/display-get-params")
    public String displayGetParams(@RequestParam Map<String,String> requestParams,
            Model model) {
        String userName=requestParams.get("name");
        int age=Integer.valueOf(requestParams.get("age"));

        model.addAttribute("userName", userName);
        model.addAttribute("age", age);
        return "display-get-params";
    }

    // GET url with value in route
    // URI Template Patterns
    @RequestMapping(value="/owners/{ownerId}", method=RequestMethod.GET)
    public String valueInRoute(@PathVariable String ownerId, Model model) {
        model.addAttribute("ownerId", ownerId);
        return "owner-id";
    }

    // GET url with two value in route
    // URI Template Patterns
    @RequestMapping(value="/owners/{ownerId}/pets/{petId}", method=RequestMethod.GET)
    public String findPet(@PathVariable String ownerId, @PathVariable String petId, Model model) {
        model.addAttribute("ownerId", ownerId);
        model.addAttribute("petId", petId);
        return "owner-id-with-pets";
    }

}