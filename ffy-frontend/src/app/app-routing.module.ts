import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./account/login/login.component";
import { AuthGuard } from "./shared/common/auth.guard";
import {RegisterComponent} from "./account/register/register.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {SubscriptionPlanComponent} from "./subscription-plan/subscription-plan.component";
import {UserAccountPageComponent} from "./account/user-account/user-account-page/user-account-page.component";
import {AccountService} from "./shared/service/account.service";
import {AdminPanelUserComponent} from "./admin-panel/admin-panel-user/admin-panel-user.component";
import {AdminPanelSideMenuComponent} from "./admin-panel/admin-panel-side-menu.component";

const routes: Routes = [
  {path: "", component: HomePageComponent},
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  // { path: "**", redirectTo: "login" },
  {path: "subscription", component: SubscriptionPlanComponent},
  { path: 'user', component: UserAccountPageComponent },
  { path: 'admin', component: AdminPanelSideMenuComponent },
  {path: 'admin/user', component: AdminPanelUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
  // constructor(private authService: AccountService) {
  //   const userId = authService.getById();
  //   if (userId) {
  //     routes[0].path = `user/${userId}`;
  //   }
  // }
}
