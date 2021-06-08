import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { error } from 'selenium-webdriver';
import { Smer } from 'src/app/models/smer';
import { Grupa } from 'src/app/models/grupa';
import { SmerService } from 'src/app/services/smer.service';
import { GrupaService } from 'src/app/services/grupa.service';

@Component({
  selector: 'app-grupa-dialog',
  templateUrl: './grupa-dialog.component.html',
  styleUrls: ['./grupa-dialog.component.css']
})
export class GrupaDialogComponent implements OnInit {

  flag:number;
  smerovi: Smer[];

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<GrupaDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: Grupa,
    public grupaService: GrupaService,
    public smerService: SmerService) { }

  ngOnInit(): void {
    this.smerService.getAllSmerovi().subscribe(
      data => {
        this.smerovi = data;
      }
    );
  }
  compareTo(a,b) {
    return a.id == b.id;
  }
  public add(): void {
    this.grupaService.addGrupa(this.data)
    .subscribe(() => {
      this.snackBar.open('Grupa uspešno dodata: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom dodavanja grupe: ' + this.data.id, 'Zatvori', {
        duration: 2500
      })
    }
  }
  public update(): void {
    this.grupaService.updateGrupa(this.data)
    .subscribe(() => {
      this.snackBar.open('Grupa uspešno izmenjena: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom izmene grupe: ' + this.data.id, 'Zatvori', {
        duration: 2500
      })
    }
  }
  public delete(): void {
    this.grupaService.deleteGrupa(this.data.id)
    .subscribe(() => {
      this.snackBar.open('Grupa uspešno obrisana: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
    (error: Error) => {
      console.log(error.name + ' ' +error.message);
      this.snackBar.open('Došlo je do greške prilikom brisanja grupe: ' + this.data.id, 'Zatvori', {
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
