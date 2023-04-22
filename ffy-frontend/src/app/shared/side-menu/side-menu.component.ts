import { Component, OnInit } from "@angular/core";
import { AccountService } from "../service/account.service";
import { User } from "../model/user";

@Component({
  selector: "app-side-menu",
  templateUrl: "./side-menu.component.html",
  styleUrls: ["./side-menu.component.scss"],
})
export class SideMenuComponent implements OnInit {
  user: User;

  constructor(private accountService: AccountService) {
    this.accountService.user.subscribe((x) => (this.user = x));
  }

  ngOnInit(): void {
    const sidebarBox = document.querySelector("#box"),
      sidebarBtn = document.querySelector("#btn"),
      pageWrapper = document.querySelector("#page-wrapper");
    sidebarBtn.addEventListener("click", (event) => {
      sidebarBtn.classList.toggle("active");
      sidebarBox.classList.toggle("active");
    });
    // pageWrapper.addEventListener("click", (event) => {
    //   if (sidebarBox.classList.contains("active")) {
    //     sidebarBtn.classList.remove("active");
    //     sidebarBox.classList.remove("active");
    //   }
    // });
  }

  logout(): any {
    this.accountService.logout();
  }
}
