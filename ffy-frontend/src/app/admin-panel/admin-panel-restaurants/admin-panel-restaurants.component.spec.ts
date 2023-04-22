import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelRestaurantsComponent } from './admin-panel-restaurants.component';

describe('AdminPanelRestaurantsComponent', () => {
  let component: AdminPanelRestaurantsComponent;
  let fixture: ComponentFixture<AdminPanelRestaurantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPanelRestaurantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPanelRestaurantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
