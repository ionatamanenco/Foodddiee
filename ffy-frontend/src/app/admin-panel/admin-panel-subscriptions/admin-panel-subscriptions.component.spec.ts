import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelSubscriptionsComponent } from './admin-panel-subscriptions.component';

describe('AdminPanelSubscriptionsComponent', () => {
  let component: AdminPanelSubscriptionsComponent;
  let fixture: ComponentFixture<AdminPanelSubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPanelSubscriptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPanelSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
