import React from 'react';
import { render } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from '../store/store';
import { App } from './App';

test('renders Welcome screen', () => {
  const { getByText } = render(
    <Provider store={store}>
      <App />
    </Provider>
  );

  // eslint-disable-next-line testing-library/prefer-screen-queries
  expect(getByText(/Będziesz miał wydatki/i)).toBeInTheDocument();
});
