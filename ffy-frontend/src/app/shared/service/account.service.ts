import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { User } from "../model/user";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, map } from "rxjs/operators";
import { Router } from "@angular/router";
import { environment } from "../../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class AccountService {
  public user: Observable<User>;
  private userSubject: BehaviorSubject<User>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<User>(
      (this.user = JSON.parse(localStorage.getItem("user")))
    );
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(user: User): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}auth/login`, user).pipe(
      map((user) => {
        localStorage.setItem("user", JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      })
    );
  }

  logout(): any {
    localStorage.removeItem("user");
    this.userSubject.next(null);
    this.router.navigate(["/login"]);
  }

  register(user: any) {
    return this.http.post(`${environment.apiUrl}auth/register`, user);
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.apiUrl}users`);
  }

  getById(id: string): any {
    return this.http.get<User>(`${environment.apiUrl}users/${id}`);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}users/current`);
  }

  update(id: string, params: any): any {
    return this.http.put(`${environment.apiUrl}users/${id}`, params).pipe(
      map((x) => {
        // update stored user if the logged in user updated their own record
        if (id == this.userValue.id) {
          // update local storage
          const user = { ...this.userValue, ...params };
          localStorage.setItem("user", JSON.stringify(user));

          // publish updated user to subscribers
          this.userSubject.next(user);
        }
        return x;
      })
    );
  }

  delete(id: string): any {
    return this.http.delete(`${environment.apiUrl}users/${id}`).pipe(
      map((x) => {
        // auto logout if the logged in user deleted their own record
        if (id == this.userValue.id) {
          this.logout();
        }
        return x;
      })
    );
  }

  private sendRequest<T>(
    verb: string,
    url: string,
    body?: User
  ): Observable<T> {
    console.log("\n\n---Request ", verb, url, body);

    const myHeaders = new HttpHeaders({
      Accept: "application/json",
      "Content-Type": "application/json",
    });

    return this.http
      .request<T>(verb, url, {
        body,
        headers: myHeaders,
      })
      .pipe(
        catchError((error: Response) =>
          throwError(`Network Error: ${error.statusText} (${error.status})`)
        )
      );
  }
}
