export class Participant {
private participantId;
private participantFirstName;
private participantLastName;
private participantResidence;
private participantDateOfBirth;

  constructor( participantFirstName: string, participantLastName: string, participantDateOfBirth: string, participantResidence: string)
  {
    this.participantDateOfBirth = participantDateOfBirth;
    this.participantFirstName = participantFirstName;
    this.participantLastName = participantLastName;
    this.participantResidence = participantResidence;

  }

  setParticipantId(id : number){
    this.participantId = id;
  }


}
