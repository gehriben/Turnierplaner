export class Tournament {
  private tournamentId;
  private tournamentName;
  private tournamenDate;
  //let Artwks: TournamentParticipant []=[];


  constructor(tournamentName: string, tournamentDate: string) {
    this.tournamentName = tournamentName;
    this.tournamenDate = tournamentDate;
  }

  setTournamentID(id: number) {
    this.tournamentId = id;
  }
}
