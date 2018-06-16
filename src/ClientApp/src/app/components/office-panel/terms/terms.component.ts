import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource, MatSort} from '@angular/material';
import {TermResource} from "../../../resources/term.resource";
import {TermService} from "../../../services/term.service";

@Component({
    selector: 'app-terms',
    templateUrl: './terms.component.html',
    styleUrls: ['./terms.component.css']
})
export class TermsComponent {
    displayedColumns = ['Id', 'Room', 'Date', 'StartTime', 'EndTime', 'Interval', 'edit'];
    terms: TermResource[];
    dataSource = new MatTableDataSource();

    getTerms() {
        this.termService.getTerms().subscribe(
            (res: TermResource[]) => {
                this.terms = res;
                this.terms = this.terms.sort((n1, n2) => n1.Id - n2.Id);
                console.log(this.terms);
                this.dataSource = new MatTableDataSource(this.terms);
            }
        );
    }

    constructor(private termService: TermService) {
        this.getTerms()
    }


    @ViewChild(MatSort) sort: MatSort;

    /**
     * Set the sort after the view init since this component will
     * be able to query its view for the initialized sort.
     */
    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    delete(termId: number) {
        this.termService.deleteTerm(termId).subscribe(
            res => {
                this.getTerms();
            }
        )
    }
}



