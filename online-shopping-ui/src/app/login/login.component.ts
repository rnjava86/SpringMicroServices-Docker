import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http:HttpClient) { }

  ngOnInit() {
  }
  username;
  password;
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('password')

  });

  "userObject" = {
          "appOS": "string",
          "appVersion": "string",
          "deviceId": "string",
          "source": "string",
            "userDetails": {
              "email": "string@email.com",

              "password": "string"
              }
};
  //userLogin
  private loginApi='http://localhost:9090/api/user-service/user/login';
  userLogin(){
    console.log(this.loginForm.value);
    console.log(this.loginForm.value.username);
    console.log(this.loginForm.value.password);
      //set value
        this.userObject.userDetails.email=this.loginForm.value.username;
        this.userObject.userDetails.password=this.loginForm.value.password;
    //login user
    //this.http.post(this.loginApi,this.loginForm.value);


    //console.log(this.http.get(this.loginApi).subscribe(data=> console.log(data)));
    console.log(this.http.post(this.loginApi,this.userObject).subscribe(data=> console.log(data)));


  }

  //user userRegistration

  "regObject" = {
          "appOS": "string",
          "appVersion": "string",
          "deviceId": "string",
          "source": "string",
            "userDetails": {
              "contactNumber": "1234567890",
               "email": "rahet@email.com",
               "firstName": "string",
               "lastName": "string",
               "password": "string",
               "roleName": "user"
              }
};
  registerForm = new FormGroup({
    email: new FormControl('ashishkvs11@gmail.com'),
    password: new FormControl('password'),
    lastname: new FormControl('Kumar'),
    firstname: new FormControl('Ashish'),
    contact: new FormControl('')


  });
  private userRegistrationUrl='http://localhost:9090/api/user-service/user/register';

  userRegistration (){
    //console.log(this.loginForm.value);
  //  console.log(this.loginForm.value.username);
    //console.log(this.loginForm.value.password);
      //set value
      this.regObject.userDetails.email=this.registerForm.value.email;
      this.regObject.userDetails.contactNumber=this.registerForm.value.contact;
      this.regObject.userDetails.firstName=this.registerForm.value.firstname;
      this.regObject.userDetails.lastName=this.registerForm.value.lastname;
      this.regObject.userDetails.password=this.registerForm.value.password;
      console.log(this.registerForm.value.lastName)
    //login user
    //this.http.post(this.loginApi,this.loginForm.value);


    //console.log(this.http.get(this.loginApi).subscribe(data=> console.log(data)));
    console.log(this.http.post(this.userRegistrationUrl,this.regObject).subscribe(data=> console.log(data)));


  }
}
