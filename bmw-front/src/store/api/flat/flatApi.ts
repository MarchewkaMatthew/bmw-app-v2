import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { FlatAddRequest, FlatUpdateRequest, GetFlatResponse, GetFlatsResponse } from './types';

export const flatApi = createApi({
  reducerPath: 'flatApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/flats' }),
  endpoints: (builder) => ({
    getFlats: builder.query<GetFlatsResponse, string>({
      query: (searchValue) => { return {url: "", params: { searchValue }} }
    }),
    getFlat: builder.query<GetFlatResponse, number>({
      query: (flatId) => `/${flatId}`
    }),
    updateFlat: builder.mutation<FlatUpdateRequest, {body: FlatUpdateRequest, token: string}>({
      query: (req) => ({
        url: "",
        method: 'PUT',
        body: req.body,
        headers: {
          "Authorization": `Bearer ${req.token}`
        }
      })
    }),
    addFlat: builder.mutation<FlatAddRequest, {body: FlatAddRequest, token: string}>({
      query: (req) => ({
        url: "",
        method: 'POST',
        body: req.body,
        headers: {
          "Authorization": `Bearer ${req.token}`
        }
      })
    }),
  }),
})

export const { useGetFlatsQuery, useGetFlatQuery, useUpdateFlatMutation, useAddFlatMutation } = flatApi;