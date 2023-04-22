import { Component, OnInit } from '@angular/core';
import {User} from "../../../shared/model/user";
import {ActivatedRoute} from "@angular/router";
import {AccountService} from "../../../shared/service/account.service";

@Component({
  selector: 'app-user-account-page',
  templateUrl: './user-account-page.component.html',
  styleUrls: ['./user-account-page.component.css']
})
export class UserAccountPageComponent implements OnInit {
  user: User;
  userId: string;

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.userId = params.get('id');
      this.getCurrentUser();
    });
    this.accountService.getCurrentUser();
    console.log(this.accountService.getCurrentUser());
  }

  getCurrentUser() {
    this.accountService.getCurrentUser().subscribe((data: User) => {
      this.user = data;
      console.log(data);
    });
  }

  getUser() {
    this.accountService.getById(this.userId).subscribe((data: User) => {
      this.user = data;
    });
  }

}
