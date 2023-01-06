import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { GetFlatResponse, GetFlatsResponse } from './types';

export const flatApi = createApi({
  reducerPath: 'flatApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/flats' }),
  endpoints: (builder) => ({
    getFlats: builder.query<GetFlatsResponse, string>({
      query: (searchValue) => { return {url: "/", params: { searchValue }} }
    }),
    getFlat: builder.query<GetFlatResponse, number>({
      query: (flatId) => `/${flatId}`
    })
  }),
})

export const { useGetFlatsQuery, useGetFlatQuery } = flatApi;