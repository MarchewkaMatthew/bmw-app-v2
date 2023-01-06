export interface MessageDto {
  id: string;
  firstName: string;
  lastName?: string;
  email: string;
  phoneNumber?: string;
  additionalInformation?: string;
}

export interface MessageAddRequest {
  messageDto: Omit<MessageDto, "id">;
}