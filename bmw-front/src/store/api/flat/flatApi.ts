import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const flatApi = createApi({
  reducerPath: 'flatApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8083/api/v1/flats' }),
  endpoints: (builder) => ({
    getFlats: builder.query<unknown, string>({
      query: (searchPhrase) => { return {url: "/", params: { searchPhrase }} }
    }),
  }),
})

export const { useGetFlatsQuery } = flatApi;