import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { GetFlatsResponse } from './types';

export const flatApi = createApi({
  reducerPath: 'flatApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/flats' }),
  endpoints: (builder) => ({
    getFlats: builder.query<GetFlatsResponse, string>({
      query: (searchValue) => { return {url: "/", params: { searchValue }} }
    }),
  }),
})

export const { useGetFlatsQuery } = flatApi;