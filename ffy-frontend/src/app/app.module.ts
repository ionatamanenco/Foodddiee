import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from "./account/login/login.component";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SideMenuComponent} from "./shared/side-menu/side-menu.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {CommonModule} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {MatDialogModule} from "@angular/material/dialog";
import {MatDividerModule} from "@angular/material/divider";
import {MatMenuModule} from "@angular/material/menu";
import {
  MAT_SNACK_BAR_DEFAULT_OPTIONS,
  MatSnackBarModule,
} from "@angular/material/snack-bar";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTabsModule} from '@angular/material/tabs';
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatCardModule} from "@angular/material/card";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {NgApexchartsModule} from "ng-apexcharts";
import { HomePageComponent } from './home-page/home-page.component';
import { SubscriptionPlanComponent } from './subscription-plan/subscription-plan.component';
import { UserAccountPageComponent } from './account/user-account/user-account-page/user-account-page.component';
import { AdminPanelUserComponent } from './admin-panel/admin-panel-user/admin-panel-user.component';
import {AdminPanelSideMenuComponent} from "./admin-panel/admin-panel-side-menu.component";
import {RegisterComponent} from "./account/register/register.component";
import { AdminPanelRestaurantsComponent } from './admin-panel/admin-panel-restaurants/admin-panel-restaurants.component';
import { AdminPanelSubscriptionsComponent } from './admin-panel/admin-panel-subscriptions/admin-panel-subscriptions.component';
import { AdminPanelCreateUserComponent } from './admin-panel/admin-panel-user/admin-panel-create-user/admin-panel-create-user.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FooterComponent,
    RegisterComponent,
    SideMenuComponent,
    HomePageComponent,
    SubscriptionPlanComponent,
    UserAccountPageComponent,
    AdminPanelUserComponent,
    AdminPanelSideMenuComponent,
    AdminPanelRestaurantsComponent,
    AdminPanelSubscriptionsComponent,
    AdminPanelCreateUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    FormsModule,
    MatSelectModule,
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatDialogModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDividerModule,
    MatMenuModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatTabsModule,
    MatListModule,
    MatExpansionModule,
    MatCardModule,
    ScrollingModule,
    NgApexchartsModule
  ],
  providers: [
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 1500}},
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {
}
