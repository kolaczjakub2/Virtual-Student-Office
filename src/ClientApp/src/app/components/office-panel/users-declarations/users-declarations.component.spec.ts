import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersDeclarationsComponent } from './users-declarations.component';

describe('UsersDeclarationsComponent', () => {
  let component: UsersDeclarationsComponent;
  let fixture: ComponentFixture<UsersDeclarationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersDeclarationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersDeclarationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
