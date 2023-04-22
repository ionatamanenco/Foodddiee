import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelSideMenuComponent } from './admin-panel-side-menu.component';

describe('AdminPanelSideMenuComponent', () => {
  let component: AdminPanelSideMenuComponent;
  let fixture: ComponentFixture<AdminPanelSideMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPanelSideMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPanelSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
