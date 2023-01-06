import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { MessageAddRequest } from './types';

export const contactApi = createApi({
  reducerPath: 'contactApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/messages' }),
  endpoints: (builder) => ({
    addMessage: builder.mutation<MessageAddRequest, MessageAddRequest>({
      query: (body) => ({
        url: "",
        method: 'POST',
        body,
      })
    }),
  }),
})

export const { useAddMessageMutation } = contactApi;