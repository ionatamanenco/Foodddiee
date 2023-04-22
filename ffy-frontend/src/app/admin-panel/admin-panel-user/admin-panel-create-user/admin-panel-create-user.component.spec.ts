import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelCreateUserComponent } from './admin-panel-create-user.component';

describe('AdminPanelCreateUserComponent', () => {
  let component: AdminPanelCreateUserComponent;
  let fixture: ComponentFixture<AdminPanelCreateUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPanelCreateUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPanelCreateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
