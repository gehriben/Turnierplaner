import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreStartTournamentComponent } from './pre-start-tournament.component';

describe('PreStartTournamentComponent', () => {
  let component: PreStartTournamentComponent;
  let fixture: ComponentFixture<PreStartTournamentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreStartTournamentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreStartTournamentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
