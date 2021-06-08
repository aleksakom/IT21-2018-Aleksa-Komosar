import { Component, Inject, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { error } from 'selenium-webdriver';
import { Projekat } from 'src/app/models/projekat';
import { Student } from 'src/app/models/student';
import { ProjekatService } from 'src/app/services/projekat.service';
import { StudentService } from 'src/app/services/student.service';
import { StudentComponent } from '../../student/student.component';

@Component({
  selector: 'app-student-dialog',
  templateUrl: './student-dialog.component.html',
  styleUrls: ['./student-dialog.component.css']
})
export class StudentDialogComponent implements OnInit, OnDestroy {

  projekti: Projekat[];
  public flag: number;
  projekatSubscription: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<StudentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Student,
    public projekatService: ProjekatService,
    public studentService: StudentService) { }

    ngOnDestroy(): void {
      this.projekatSubscription.unsubscribe();
   
     }

  ngOnInit(): void {
    this.projekatSubscription = this.projekatService.getAllProjekti().subscribe(
      data => {
        this.projekti = data;
      }
    ),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }

  compareTo(a,b) {
    return a.id == b.id;
  }

  public add(): void {
    this.studentService.addStudent(this.data)
    .subscribe(() => {
      this.snackBar.open('Student uspešno dodat: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom dodavanja studenta: ' + this.data.id, 'Zatvori', {
        duration: 2500
      })
    }
  }
  public update(): void {
    this.studentService.updateStudent(this.data)
    .subscribe(() => {
      this.snackBar.open('Student uspešno izmenjen: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom izmene studenta: ' + this.data.id, 'Zatvori', {
        duration: 2500
      })
    }
  }
  public delete(): void {
    this.studentService.deleteStudent(this.data.id)
    .subscribe(() => {
      this.snackBar.open('Student uspešno obrisan: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom brisanja studenta: ' + this.data.id, 'Zatvori', {
        duration: 2500
      })
    }
  }
  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.' + this.data.id, 'Zatvori', {
      duration: 1000
    })
  }

}
