export type PetDTO = {
  id: number;
  specie: string;
  breed: string;
  name: string;
  birthdate: string;
  gender: 'FEMALE' | 'MALE' | 'NO_GENDER';
  vaccination: boolean;
  image: string;
  releaseTimeStamp: string;
}