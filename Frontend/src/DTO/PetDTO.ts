export type PetDTO = {
  id: number;
  species: string;
  name: string;
  birthDate: string; // Assuming the format is a string
  gender: 'FEMALE' | 'MALE' | 'NO_GENDER'; // Assuming you have an enumeration for Gender
  isSterilized: boolean;
  isVaccinated: boolean;
  isHouseTrained: boolean;
  image: string; // Assuming this is a base64 encoded string or a URL
  releaseTimeStamp: string; // Assuming the format is a string
  attachments: AttachmentDTO[]; // Assuming you have a type for attachments
  breed: string;
  shelterId: number;
  shelterLocationCity: string; // Assuming you store city as an ID or number
  shelterLocationCountry: string; // Assuming you store country as an ID or number
};

// Define AttachmentDTO if it's a complex object
export type AttachmentDTO = {
  title: string;
  attachmentUrl: string;
};
