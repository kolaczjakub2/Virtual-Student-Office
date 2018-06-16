import {Component, OnInit} from '@angular/core';
import {TermResource} from "../../../../resources/term.resource";
import {ActivatedRoute, Router} from "@angular/router";
import {TermService} from "../../../../services/term.service";

@Component({
    selector: 'app-terms-form',
    templateUrl: './terms-form.component.html',
    styleUrls: ['./terms-form.component.css']
})
export class TermsFormComponent implements OnInit {

    term: TermResource = new TermResource();
    id: number;

    constructor(private route: ActivatedRoute, private termService: TermService, private router: Router) {
        this.term.Interval = 15;
        this.route.params.subscribe(params => {
            this.id = +params['id'];
            if (this.id)
                this.termService.getTerm(this.id).subscribe((res: TermResource) => {
                    this.term = res;
                });
            console.log(this.id);
        })

    }

    ngOnInit() {
    }

    saveTerm() {
        console.log(this.term)
        this.termService.saveTerm(this.term).subscribe(
            res => {
                console.log(res);
                this.router.navigateByUrl("/office/panel/terms");
            }
        );
    }
}
