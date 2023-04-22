import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import {AccountService} from "../../shared/service/account.service";
import {first} from "rxjs/operators";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  loading = false;
  submitted = false;
  registerForm: FormGroup = new FormGroup({});
  registerStatusError = false;
  validationErrors: {} | null = {};

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
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
    if (this.registerForm.valid) {
      this.accountService
        .register(this.registerForm.value)
        .pipe(first())
        .subscribe({
          next: () => {
            this.registerStatusError = false;
            console.log(this.registerForm);
            this.router.navigate(["/"]);
          },
          error: (error) => {
            console.log(error);
            console.log("Something went wrong!");
            this.registerStatusError = true;
            this.validationErrors = error?.error?.message;
          },
        });
    }
  }
}
