import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AddParticipantComponent } from './add-participant/add-participant.component';
import { PreStartTournamentComponent } from './pre-start-tournament/pre-start-tournament.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CreateTournamentComponent } from './create-tournament/create-tournament.component';

const routes: Routes = [
  { path: 'addParticipant', component: AddParticipantComponent },
  { path: 'preStartTournament', component: PreStartTournamentComponent },
  { path: 'createTournament', component: CreateTournamentComponent },
  { path: '', component: MainPageComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
