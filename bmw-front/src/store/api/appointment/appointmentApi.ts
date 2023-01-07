import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { AppointmentAddRequest } from './types';

export const appointmentApi = createApi({
  reducerPath: 'appointmentApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/appointments' }),
  endpoints: (builder) => ({
    addAppointment: builder.mutation<AppointmentAddRequest, AppointmentAddRequest>({
      query: (body) => ({
        url: "",
        method: 'POST',
        body,
      })
    }),
  }),
})

export const { useAddAppointmentMutation } = appointmentApi;