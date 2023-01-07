import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';
import { appointmentApi } from './api/appointment/appointmentApi';
import { contactApi } from './api/contact/contactApi';
import { flatApi } from './api/flat/flatApi';

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    // API
    [contactApi.reducerPath]: contactApi.reducer,
    [flatApi.reducerPath]: flatApi.reducer,
    [appointmentApi.reducerPath]: appointmentApi.reducer
  },
  middleware: (getDefaultMiddleware) => {
    return getDefaultMiddleware()
      .concat(contactApi.middleware)
      .concat(flatApi.middleware)
      .concat(appointmentApi.middleware);
  }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
