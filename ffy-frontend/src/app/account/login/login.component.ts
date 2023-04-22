import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { first } from "rxjs/operators";
import { AccountService } from "../../shared/service/account.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});
  loginStatusError = false;
  validationErrors: {} | null = {};

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [
        "",
        [
          Validators.required,
          Validators.email,
          Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
        ],
      ],
      password: ["", [Validators.required, Validators.minLength(4)]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.accountService
        .login(this.loginForm.value)
        .pipe(first())
        .subscribe({
          next: () => {
            this.loginStatusError = false;
            console.log(this.loginForm);
            this.router.navigate(["/"]);
          },
          error: (error) => {
            console.log(error);
            console.log("Invalid credentials");
            this.loginStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }
  }
}
