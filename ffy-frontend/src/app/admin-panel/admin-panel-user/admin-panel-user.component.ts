import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Role, User} from "../../shared/model/user";
import {SelectionModel} from "@angular/cdk/collections";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AccountService} from "../../shared/service/account.service";
import {AdminPanelCreateUserComponent} from "./admin-panel-create-user/admin-panel-create-user.component";

@Component({
  selector: 'app-admin-panel-user',
  templateUrl: './admin-panel-user.component.html',
  styleUrls: ['./admin-panel-user.component.css']
})
export class AdminPanelUserComponent implements AfterViewInit {

  columns: string[] = [
    "firstname",
    "email",
    "lastname",
    "role",
    "commands"
  ];
  dataSource!: MatTableDataSource<any>;
  selection = new SelectionModel<any>(true, []);


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private _snackBar: MatSnackBar,
    private _dialog: MatDialog,
    private _usersService: AccountService,
  ) {}


  ngAfterViewInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this._usersService.getAll().subscribe({
      next: (res) => {
        console.log(res);
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: () =>
        console.log("An error has occurred while fetching data from database"),
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  createUser() {
    const dialogRef = this._dialog.open(AdminPanelCreateUserComponent, {
      width: "75%",
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      if (result === "save") {
        this.getAllUsers();
      }
    });
  }

  editUser(row: any) {
    this._dialog.open(AdminPanelCreateUserComponent, {
      width: "75%",
      data: row,
    })
      .afterClosed()
      .subscribe((result) => {
        if (result === "update") {
          this.getAllUsers();
          this._snackBar.open("Edited successfully", "OK");
        }
      });
  }
}
