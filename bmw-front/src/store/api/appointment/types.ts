export interface AppointmentDto {
  id: number;
  customerId: string;
  appointmentName: string;
  appointmentDate: string;
  flatId: number;
}

export interface AppointmentAddRequest {
  appointmentDto: Omit<AppointmentDto, "id" | "customerId">;
}