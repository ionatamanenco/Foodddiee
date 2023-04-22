// import { async, ComponentFixture, TestBed } from "@angular/core/testing";
// import { RouterTestingModule } from "@angular/router/testing";
// import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
// import { RegisterComponent } from "./register.component";
// import { AccountService } from "../../shared/service/account.service";
// import { of, throwError } from "rxjs";
// import * as jest from '@jest';
//
//
// describe("RegisterComponent", () => {
//   let component: RegisterComponent;
//   let fixture: ComponentFixture<RegisterComponent>;
//   let accountService: AccountService;
//
//   beforeEach(async(() => {
//     TestBed.configureTestingModule({
//       declarations: [RegisterComponent],
//       imports: [RouterTestingModule, ReactiveFormsModule],
//       providers: [
//         FormBuilder,
//         {
//           provide: AccountService,
//           useValue: {
//             register: jest.fn(),
//           },
//         },
//       ],
//     }).compileComponents();
//   }));
//
//   beforeEach(() => {
//     fixture = TestBed.createComponent(RegisterComponent);
//     component = fixture.componentInstance;
//     accountService = TestBed.inject(AccountService);
//     fixture.detectChanges();
//   });
//
//   it("should create the component", () => {
//     expect(component).toBeTruthy();
//   });
//
//   describe("onSubmit", () => {
//     it("should not call accountService.register if form is invalid", () => {
//       component.registerForm.controls.email.setValue("invalid-email");
//       component.registerForm.controls.password.setValue("");
//       component.onSubmit();
//       expect(accountService.register).not.toHaveBeenCalled();
//     });
//
//     it("should call accountService.register if form is valid", () => {
//       component.registerForm.controls.email.setValue("valid-email@example.com");
//       component.registerForm.controls.password.setValue("password");
//       accountService.register.mockReturnValue(of({}));
//       component.onSubmit();
//       expect(accountService.register).toHaveBeenCalledWith({
//         email: "valid-email@example.com",
//         password: "password",
//       });
//     });
//
//     it("should navigate to '/' on successful registration", () => {
//       component.registerForm.controls.email.setValue("valid-email@example.com");
//       component.registerForm.controls.password.setValue("password");
//       accountService.register.mockReturnValue(of({}));
//       const navigateSpy = jest.spyOn(component.router, "navigate");
//       component.onSubmit();
//       expect(navigateSpy).toHaveBeenCalledWith(["/"]);
//     });
//
//     it("should set registerStatusError to true on registration error", () => {
//       component.registerForm.controls.email.setValue("valid-email@example.com");
//       component.registerForm.controls.password.setValue("password");
//       const errorMessage = "Registration error";
//       accountService.register.mockReturnValue(throwError({ error: { message: errorMessage } }));
//       component.onSubmit();
//       expect(component.registerStatusError).toBe(true);
//       expect(component.validationErrors).toEqual(errorMessage);
//     });
//   });
// });
//
