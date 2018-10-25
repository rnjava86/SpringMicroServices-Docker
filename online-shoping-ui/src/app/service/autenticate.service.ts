import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import {User} from '../model/User';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
    constructor(private http: HttpClient) { }
    
     httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      };

    login(username: string, password: string) : Observable<any> {
       /* return this.http.post<any>(`http://localhost:4000/users/authenticate`, { username: username, password: password })
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            })); */
            console.log('service method begin');
            var user : User = {
                id: 111,
                username: "vijay",
                password: "kumar",
                firstName: "nalagonda",
                lastName: 'kumar'
            };

            if(username==="vijay" && password === "kumar"){
                console.log('login credentials verified');
                localStorage.setItem('currentUser', JSON.stringify(user));
              //  return of(user);
             // return this.http.post<User>('api/heroes',user);
             return this.http.get<any>('api/heroes');
            }
    }
    
    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}