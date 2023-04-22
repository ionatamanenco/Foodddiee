import {ComponentFixture, TestBed} from '@angular/core/testing';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {of} from 'rxjs';
import {AccountService} from '../../shared/service/account.service';


import {LoginComponent} from './login.component';
import {Role, User} from "../../shared/model/user";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let routerSpy: jasmine.SpyObj<Router>;
  let accountServiceSpy: jasmine.SpyObj<AccountService>;
  let formBuilder: FormBuilder;

  beforeEach(async () => {
    const routerMock = jasmine.createSpyObj('Router', ['navigate']);
    const accountServiceMock = jasmine.createSpyObj('AccountService', ['login']);

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: Router, useValue: routerMock },
        { provide: AccountService, useValue: accountServiceMock },
        { provide: ActivatedRoute, useValue: {} },
        FormBuilder,
      ],
    }).compileComponents();

    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    accountServiceSpy = TestBed.inject(AccountService) as jasmine.SpyObj<AccountService>;
    formBuilder = TestBed.inject(FormBuilder);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to home page after successful login', () => {
    const loginForm = formBuilder.group({
      email: 'test@test.com',
      password: 'password',
    });
    const mockUser: User = {
      email: 'test@test.com',
      password: 'password',
      role: Role.USER,
      userRestaurantId: 'asd'
    };
    accountServiceSpy.login.and.returnValue(of(mockUser));
    component.loginForm = loginForm;

    component.onSubmit();

    expect(routerSpy.navigate).toHaveBeenCalledWith(['/']);
  });

  it('should set loginStatusError to true and set validationErrors on login failure', () => {
    const error = { error: { message: 'Invalid credentials' } };
    // @ts-ignore
    accountServiceSpy.login.and.returnValue(of(error));
    component.loginForm = formBuilder.group({
      email: 'test@test.com',
      password: 'password',
    });

    component.onSubmit();

    expect(component.loginStatusError).toBeTrue();
    expect(component.validationErrors).toEqual(error.error.message);
  });
});

