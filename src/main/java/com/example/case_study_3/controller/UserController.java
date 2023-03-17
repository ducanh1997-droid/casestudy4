package com.example.case_study_3.controller;

import com.example.case_study_3.model.User;
import com.example.case_study_3.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@PropertySource("classpath:application.properties")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Value("${upload.path}")
    private String link;
    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(iUserService.findALl(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.findOne(id),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user){
        if (!user.getUsername().equals("")&&!user.getEmail().equals("")&&!user.getPassword().equals("")){
            iUserService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User user1=iUserService.findByUserNameAndPassWord(user.getUsername(), user.getPassword());
        if(!user1.equals("")){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody User user){
        User user1= iUserService.findOne(id);
        if (user1!=null){
            iUserService.save(user);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.CONTINUE);
    }
    @Value("${display.path}")
    private String displayLink;
    @PostMapping(value = "/upload")
    public ResponseEntity<Void> createUpload(@RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart("user") User user) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            user.setAvatar(displayLink + fileName);
        } else {
            user.setAvatar(displayLink + "avatar.jpg");
        }
        iUserService.save(user);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload1")
    public ResponseEntity<?> createUpload1(@RequestPart("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}